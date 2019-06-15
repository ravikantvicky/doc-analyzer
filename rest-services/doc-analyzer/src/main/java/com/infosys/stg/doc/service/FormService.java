package com.infosys.stg.doc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.infosys.stg.doc.exception.DocAnalyzeException;
import com.infosys.stg.doc.model.FormResponse;
import com.infosys.stg.doc.model.ServiceResponse;
import com.infosys.stg.doc.repository.FormRepository;

@Service
public class FormService {
	@Autowired
	private DocService docService;
	@Autowired
	private FormRepository formRepo;

	public ServiceResponse uploadFile(MultipartFile file, String barcode) throws DocAnalyzeException {
		ServiceResponse response = new ServiceResponse();
		try {
			List<String> images = docService.pdf2Image(file).getImages();
			if (images == null || images.isEmpty())
				throw new DocAnalyzeException("Error in form upload..");
			if (StringUtils.isEmpty(barcode)) {
				barcode = docService.scanBarCode(images.get(0));
				if (StringUtils.isEmpty(barcode)) {
					response.setStatus(2);
					response.setMessage("Unable to read barcode, please enter manually.");
					return response;
				}
			}
			formRepo.fetchImages(barcode).forEach(imgData -> {
				
			});
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(0);
			response.setMessage("Error in parsing form data.");
		}
		return response;
	}

}
