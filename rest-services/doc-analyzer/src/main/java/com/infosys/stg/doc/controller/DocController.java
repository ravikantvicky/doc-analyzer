package com.infosys.stg.doc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.infosys.stg.doc.model.ServiceResponse;
import com.infosys.stg.doc.model.TemplateUploadRequest;
import com.infosys.stg.doc.model.TemplateUploadResponse;
import com.infosys.stg.doc.service.StorageService;

@RestController
public class DocController {
	@Autowired
	StorageService s;

	@PostMapping(value = "/uploadFile")
	public ServiceResponse uploadFile(@RequestParam("file") MultipartFile file) {
		ServiceResponse response = new ServiceResponse();
		TemplateUploadResponse resp = new TemplateUploadResponse();
		String m = s.uploadFile(file);
		resp.setMessage(m);
		response.setData(resp);
		response.setStatus(1);
		return response;
	}

	@GetMapping("/")
	public String root() {
		return "Services are up and running !";
	}
}
