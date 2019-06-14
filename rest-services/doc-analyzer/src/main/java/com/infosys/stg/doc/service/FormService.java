package com.infosys.stg.doc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.infosys.stg.doc.exception.DocAnalyzeException;
import com.infosys.stg.doc.model.TemplateUploadResponse;

@Service
public class FormService {
	@Autowired
	private DocService docService;

	public TemplateUploadResponse uploadFile(MultipartFile file) throws DocAnalyzeException {
		TemplateUploadResponse response = new TemplateUploadResponse();
		try {
			List<String> images = docService.pdf2Image(file).getImages();
			System.out.println(images);
		} catch (Exception e) {
			throw new DocAnalyzeException("Error in upload file.");
		}
		return response;
	}

}
