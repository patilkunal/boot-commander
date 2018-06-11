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
import com.inovision.commander.model.Host;
import com.inovision.commander.repository.HostRepository;

@Controller
@RequestMapping(value="/hosts", produces="application/json")
public class HostController {

	private HostRepository hostReposistory;
	
	@Autowired
	public void setHostReposistory(HostRepository hostReposistory) {
		this.hostReposistory = hostReposistory;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody Iterable<Host> getAllHosts() {
		return this.hostReposistory.findAll();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public @ResponseBody Host getCategory(@PathVariable("id") Integer id) throws NotfoundException {
		try {
			return hostReposistory.findById(id).get();
		} catch(NoSuchElementException nse) {
			throw new NotfoundException("Category not found with id: " + id);
		}
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public @ResponseBody Host save(@RequestBody Host host) {
		return hostReposistory.save(host);
	}

	@RequestMapping(value="/{id}",method=RequestMethod.PUT, consumes="application/json")
	public @ResponseBody Host update(@PathVariable("id") Integer id, @RequestBody Host host) throws NotfoundException {
		if(hostReposistory.existsById(id)) {
			return hostReposistory.save(host);
		} else {
			throw new NotfoundException("Category not found with id: " + id);
		}
	}
	
}
