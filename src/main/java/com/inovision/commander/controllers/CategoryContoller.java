package com.inovision.commander.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inovision.commander.exception.NotfoundException;
import com.inovision.commander.exception.OperationNotAllowed;
import com.inovision.commander.model.Category;
import com.inovision.commander.service.CategoryService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping(value="/categories", produces="application/json")
public class CategoryContoller {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryContoller.class);
	private CategoryService categoryService;
	
	@Autowired
	public void setCategoryRespository(CategoryService categoryRespository) {
		this.categoryService = categoryRespository;
	}

	@ApiOperation(value = "List all available categories of tests", response = Iterable.class)
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody Iterable<Category> getAllCategories() {
		return categoryService.getAllCategories();
	}
	
	@ApiOperation(value = "Get categories by ID", response = Category.class)
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public @ResponseBody Category getCategory(@ApiParam(value = "ID value of category", example = "1") @PathVariable("id") Integer id) throws NotfoundException {
		return categoryService.getCategory(id);
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public @ResponseBody String deleteCategory(@PathVariable("id") Integer id) throws NotfoundException, OperationNotAllowed {
		categoryService.deleteCategory(id);
		return "success";
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/name/{name}")
	public @ResponseBody String deleteCategoryByName(@PathVariable("name") String name) throws NotfoundException, OperationNotAllowed {
		categoryService.deleteCategoryByName(name);
		return "success";
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public @ResponseBody Category save(@RequestBody Category cat) {
		return categoryService.saveCategory(cat);	
	}

	@RequestMapping(value="/{id}",method=RequestMethod.PUT, consumes="application/json")
	public @ResponseBody Category update(@PathVariable("id") Integer id, @RequestBody Category cat) throws NotfoundException {
		LOGGER.debug("Category Update got following category: {}", cat);
		cat.setId(id);
		return categoryService.updateCategory(cat);
	}
}
