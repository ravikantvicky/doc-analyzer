package com.infosys.stg.doc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocController {
	@GetMapping("/")
	public String root() {
		return "Services are up and running !";
	}
}
