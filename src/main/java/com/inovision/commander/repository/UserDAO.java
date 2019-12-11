package com.inovision.commander.repository;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inovision.commander.model.User;

@Repository
public class UserDAO {

	@Autowired
	protected EntityManager entityManager;
	
	private static final String GET_ALL_USERS = "select id, username, name, email from users";
	
	public List<User> getUsers() {
		Session session = entityManager.unwrap(Session.class);
		//if we don't set addEntity, we get Object Array and in this case List<Object[]>
		//we will have to map them manually
		NativeQuery<User> query = session.createNativeQuery(GET_ALL_USERS).addEntity(User.class);		
		return query.getResultList();//.stream().collect(Collectors.toList());
		
	}

	public List<User> getUsersUsingMap() {
		Session session = entityManager.unwrap(Session.class);
		NativeQuery<?> query = session.createNativeQuery(GET_ALL_USERS);		
		//return query.getResultList().stream().map(userMapper()).collect(Collectors.toList());
		return query.getResultList().stream().map(new ResultTransformerAdapter<User>(new UserTransformer())).collect(Collectors.toList());
		
	}
	
	
	private Function<Object, User> userMapper() {
		Function<Object, User> mapper  = new Function<Object, User>() {
			@Override
			public User apply(Object obj) {
				Object[] arr = (Object[]) obj;
				UserTransformer tr = new UserTransformer();
				return tr.transformTuple(arr, null);
			}
		};
		
		return mapper;
	}
	
	private class ResultTransformerAdapter<T> implements Function<Object, T> {
		
		private ResultTransformer tr;
		
		public ResultTransformerAdapter(ResultTransformer tr) {
			this.tr = tr;
		}
		
		@Override
		public T apply(Object t) {
			return (T) tr.transformTuple((Object[]) t, null);
		}
	}

	private class UserTransformer implements ResultTransformer {
		
		@Override
		public User transformTuple(Object[] arr, String[] aliases) {
			User user = new User();
			user.setId((int)arr[0]);
			user.setUserName((String)arr[1]);
			user.setName((String)arr[2]);
			user.setEmail((String)arr[3]);
			return user;
		}
		
		@Override
		public List<User> transformList(List collection) {
			return collection;
		}
	}
	
}
