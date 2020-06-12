package com.inovision.commander.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inovision.commander.exception.NotfoundException;
import com.inovision.commander.model.User;
import com.inovision.commander.service.UserService;

@Controller
@RequestMapping(path="/user", produces="application/json")
public class UserController {

	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<User> getUsers() {
		//return userService.getUsers();
		return userService.getUsers2();
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/{id}")
	public @ResponseBody User getUser(@PathVariable("id") int id) throws NotfoundException {
		return userService.getUser(id);
	}

	@RequestMapping(method=RequestMethod.PUT, path="/{id}")
	public @ResponseBody User updateUser(@PathVariable("id") int id, @RequestBody User user) throws NotfoundException {
		getUser(id);
		user.setId(id);
		return userService.saveUser(user);
	}

	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody  User saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
	@RequestMapping(method=RequestMethod.POST, path="/signup")
	public @ResponseBody User signUp(@RequestBody User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return saveUser(user);
	}
	
}
