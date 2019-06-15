package com.infosys.stg.doc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.infosys.stg.doc.exception.DocAnalyzeException;
import com.infosys.stg.doc.model.ServiceResponse;
import com.infosys.stg.doc.service.FormService;

@RestController
@CrossOrigin
public class FormController {

	@Autowired
	FormService formService;

	@PostMapping(value = "/uploadForm")
	public ServiceResponse uploadFile(@RequestParam("file") MultipartFile file,
			@RequestParam(value = "barcode", required = false) String barcode) {
		ServiceResponse response = new ServiceResponse();
		try {
			return formService.uploadFile(file, barcode);
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

}
