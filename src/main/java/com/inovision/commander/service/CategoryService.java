package com.inovision.commander.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.inovision.commander.exception.NotfoundException;
import com.inovision.commander.exception.OperationNotAllowed;
import com.inovision.commander.model.Category;
import com.inovision.commander.repository.CategoryRepository;

@Component
@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
public class CategoryService {

	private CategoryRepository categoryRespository;
	
	@Autowired
	public void setCategoryRespository(CategoryRepository categoryRespository) {
		this.categoryRespository = categoryRespository;
	}

	public Iterable<Category> getAllCategories() {
		return categoryRespository.findAll();
	}
	
	public Category getCategory(int id) throws NotfoundException {
		try {
			return categoryRespository.findById(id).get();
		} catch(NoSuchElementException nse) {
			throw new NotfoundException("Category not found with id: " + id);
		}
	}

	@Transactional(readOnly=false)
	public void deleteCategory(@PathVariable("id") Integer id) throws NotfoundException, OperationNotAllowed {
		try {
			if(categoryRespository.existsById(id)) {
				Category cat = categoryRespository.findById(id).get();
				if(cat.getHost() != null && cat.getHost().size() > 0) {
					throw new OperationNotAllowed("Cannot delete category used to define existing hosts");
				}
				if(cat.getTestCases() != null && cat.getTestCases().size() > 0) {
					throw new OperationNotAllowed("Cannot delete category used to define Test Definitions");
				}
				categoryRespository.deleteById(id);
			} else { 
				throw new NotfoundException("Category not found with id: " + id);
			}
		} catch(NoSuchElementException nse) {
			throw new NotfoundException("Category not found with id: " + id);
		}
	}
	
	@Transactional(readOnly=false)
	public Category saveCategory(@RequestBody Category cat) {
		return categoryRespository.save(cat);	
	}

	@Transactional(readOnly=false)
	public Category updateCategory(Category cat) throws NotfoundException {
		if(categoryRespository.existsById(cat.getId())) {
			return categoryRespository.save(cat);
		} else {
			throw new NotfoundException("Category not found with id: " + cat.getId());
		}
	}
	
}
