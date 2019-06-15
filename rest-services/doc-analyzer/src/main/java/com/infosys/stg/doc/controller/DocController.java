package com.infosys.stg.doc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.infosys.stg.doc.exception.DocAnalyzeException;
import com.infosys.stg.doc.model.SaveTemplateRequest;
import com.infosys.stg.doc.model.ServiceResponse;
import com.infosys.stg.doc.service.TemplateService;

@RestController
@CrossOrigin
public class DocController {
	@Autowired
	TemplateService tempService;

	@PostMapping(value = "/uploadTemplate")
	public ServiceResponse uploadFile(@RequestParam("file") MultipartFile file,
			@RequestParam("templetName") String templateName) {
		ServiceResponse response = new ServiceResponse();
		try {
			response.setData(tempService.uploadFile(file, templateName));
			response.setStatus(1);
		} catch (DocAnalyzeException e) {
			e.printStackTrace();
			response.setStatus(0);
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(0);
			response.setMessage("Error in template upload.");
		}
		return response;
	}

	@PostMapping("/saveTemplateData")
	public ServiceResponse saveTemplateData(@RequestBody SaveTemplateRequest request) {
		ServiceResponse response = new ServiceResponse();
		try {
			response.setMessage("Template " + tempService.saveTemplateData(request) + " saved successfully !!");
			response.setStatus(1);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(0);
			response.setMessage("Error in saving template data !");
		}
		return response;
	}

	@GetMapping("/")
	public String root() {
		return "Services are up and running !";
	}
}
