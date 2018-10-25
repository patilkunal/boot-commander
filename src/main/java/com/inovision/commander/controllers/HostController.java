package com.inovision.commander.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inovision.commander.exception.NotfoundException;
import com.inovision.commander.model.Host;
import com.inovision.commander.service.HostService;

@Controller
@RequestMapping(value="/hosts", produces="application/json")
public class HostController {

	private HostService hostService;
	
	@Autowired
	public void setHostService(HostService hostReposistory) {
		this.hostService = hostReposistory;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody Iterable<Host> getAllHosts() {
		return this.hostService.getAllHosts();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public @ResponseBody Host getHost(@PathVariable("id") Integer id) throws NotfoundException {
		return hostService.getHost(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public @ResponseBody Host save(@RequestBody Host host) throws NotfoundException {
		return hostService.saveOrUpdate(host);
	}

	@RequestMapping(value="/{id}",method=RequestMethod.PUT, consumes="application/json")
	public @ResponseBody Host update(@PathVariable("id") Integer id, @RequestBody Host host) throws NotfoundException {
		host.setId(id);
		return hostService.saveOrUpdate(host);
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public @ResponseBody Host deleteHost(@PathVariable("id") Integer id) throws NotfoundException {
		return hostService.deleteHost(id);
	}
	
}
