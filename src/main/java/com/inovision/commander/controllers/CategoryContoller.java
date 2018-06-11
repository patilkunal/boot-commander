package com.inovision.commander.controllers;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inovision.commander.exception.NotfoundException;
import com.inovision.commander.model.Category;
import com.inovision.commander.repository.CategoryRepository;

@Controller
@RequestMapping(value="/categories", produces="application/json")
public class CategoryContoller {
	
	private CategoryRepository categoryRespository;
	
	@Autowired
	public void setCategoryRespository(CategoryRepository categoryRespository) {
		this.categoryRespository = categoryRespository;
	}

	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody Iterable<Category> getAllCategories() {
		return categoryRespository.findAll();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public @ResponseBody Category getCategory(@PathVariable("id") Integer id) throws NotfoundException {
		try {
			return categoryRespository.findById(id).get();
		} catch(NoSuchElementException nse) {
			throw new NotfoundException("Category not found with id: " + id);
		}
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public @ResponseBody Category save(@RequestBody Category cat) {
		return categoryRespository.save(cat);	
	}

	@RequestMapping(value="/{id}",method=RequestMethod.PUT, consumes="application/json")
	public @ResponseBody Category update(@PathVariable("id") Integer id, @RequestBody Category cat) throws NotfoundException {
		if(categoryRespository.existsById(id)) {
			return categoryRespository.save(cat);
		} else {
			throw new NotfoundException("Category not found with id: " + id);
		}
	}
}
