package com.qlnh.rest.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qlnh.entity.Authority;
import com.qlnh.service.AuthorityService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/authorities")
public class AuthorityRestController {
	@Autowired
	AuthorityService authorityService;

	@GetMapping
	public Map<String, Object> getAuthorities(){
		System.out.println("gfgfgf: "+authorityService.getAuthorities());
		return authorityService.getAuthorities();
	}
	
//	public List<Authority> findAll(@RequestParam("admin") Optional<Boolean> admin) {
//		if (admin.orElse(false)) {
//			return authorityService.findAuthoritiesOfAdministrators();
//		}
//		return authorityService.findAll();
//	}

	@PostMapping
	public Authority post(@RequestBody Authority auth) {
		return authorityService.create(auth);
	} 
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id")Integer id) {
		authorityService.delete(id);
	}
}
