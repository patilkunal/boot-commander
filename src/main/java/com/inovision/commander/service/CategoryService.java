package com.inovision.commander.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.inovision.commander.exception.NotfoundException;
import com.inovision.commander.exception.OperationNotAllowed;
import com.inovision.commander.model.Category;
import com.inovision.commander.repository.CategoryRepository;

@Component
@Transactional(isolation=Isolation.DEFAULT, readOnly=true, propagation=Propagation.SUPPORTS)
public class CategoryService {

	private static final String CATEGORY_NOT_FOUND_WITH_ID = "Category not found with id: ";
	private static final String CATEGORY_NOT_FOUND_WITH_NAME = "Category not found with name: ";
	private CategoryRepository categoryRespository;
	
	@Autowired
	public void setCategoryRespository(CategoryRepository categoryRespository) {
		this.categoryRespository = categoryRespository;
	}

	public Iterable<Category> getAllCategories() {
		return categoryRespository.findAll();
	}
	
	public Category getCategory(int id) throws NotfoundException {
		Optional<Category> optional = categoryRespository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new NotfoundException(CATEGORY_NOT_FOUND_WITH_ID + id);
		}
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void deleteCategory(@PathVariable("id") Integer id) throws NotfoundException, OperationNotAllowed {
		Optional<Category> optional = categoryRespository.findById(id);
		if(optional.isPresent()) {
			Category cat = optional.get();
			deleteCategory(cat);
		} else { 
			throw new NotfoundException(CATEGORY_NOT_FOUND_WITH_ID + id);
		}
	}
	
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public Category saveCategory(@RequestBody Category cat) {
		return categoryRespository.save(cat);	
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public Category updateCategory(Category cat) throws NotfoundException {
		if(categoryRespository.existsById(cat.getId())) {
			return categoryRespository.save(cat);
		} else {
			throw new NotfoundException(CATEGORY_NOT_FOUND_WITH_ID + cat.getId());
		}
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void deleteCategoryByName(String name) throws NotfoundException, OperationNotAllowed {
		Optional<Category> optional = categoryRespository.findByName(name);
		if(optional.isPresent()) {
			Category cat = optional.get();
			deleteCategory(cat);
		} else { 
			throw new NotfoundException(CATEGORY_NOT_FOUND_WITH_NAME + name);
		}		
	}

	private void deleteCategory(Category cat) throws OperationNotAllowed {
		if(cat.getHost() != null && !cat.getHost().isEmpty()) {
			throw new OperationNotAllowed("Cannot delete category used to define existing hosts");
		}
		if(cat.getTestCases() != null && !cat.getTestCases().isEmpty()) {
			throw new OperationNotAllowed("Cannot delete category used to define Test Definitions");
		}
		categoryRespository.deleteById(cat.getId());
		
	}
}
