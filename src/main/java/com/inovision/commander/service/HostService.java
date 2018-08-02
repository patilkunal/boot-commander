package com.inovision.commander.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inovision.commander.exception.NotfoundException;
import com.inovision.commander.model.Host;
import com.inovision.commander.repository.HostRepository;

@Component
@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
public class HostService {

	private HostRepository hostReposistory;
	
	@Autowired
	public void setHostReposistory(HostRepository hostReposistory) {
		this.hostReposistory = hostReposistory;
	}

	public Host getHost(Integer id) throws NotfoundException {
		try {
			return hostReposistory.findById(id).get();
		} catch(NoSuchElementException nse) {
			throw new NotfoundException("Host not found with id: " + id);
		}
	}
	
	public Iterable<Host> getAllHosts() {
		return this.hostReposistory.findAll();
	}
	
	@Transactional(readOnly=false)	
	public Host saveOrUpdate(@RequestBody Host host) throws NotfoundException {
		Host saved = null;
		if(host.getId() < 0) {
			saved = hostReposistory.save(host);
		} else {
			int id = host.getId();
			if(hostReposistory.existsById(id)) {
				saved = hostReposistory.save(host);
			} else {
				throw new NotfoundException("Host not found with id: " + id);
			}			
		}
		
		return saved;
	}

	@Transactional(readOnly=false)	
	public Host deleteHost(Integer id) throws NotfoundException {
		Host host = hostReposistory.findById(id).get();
		if(host == null) {
			throw new NotfoundException("Host not found with id: " + id);
		}
		hostReposistory.deleteById(id);
		return host;
	}
	
}
