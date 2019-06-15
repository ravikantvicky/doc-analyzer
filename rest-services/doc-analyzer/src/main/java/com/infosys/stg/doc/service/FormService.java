package com.infosys.stg.doc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.infosys.stg.doc.exception.DocAnalyzeException;
import com.infosys.stg.doc.model.FormResponse;
import com.infosys.stg.doc.model.ServiceResponse;
import com.infosys.stg.doc.model.TemplatePages;
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
			AtomicInteger counter = new AtomicInteger(0);
			List<TemplatePages> allImages = new ArrayList<>();
			formRepo.fetchImages(barcode).forEach(imgData -> {
				String data = docService.alignImage(images.get(counter.getAndIncrement()), imgData.getImageData());
				imgData.setImageData(data);
				allImages.add(imgData);
			});
			FormResponse resp = new FormResponse();
			resp.setImages(allImages);
			resp.setFields(formRepo.fetchCoordinates(barcode));
			response.setData(resp);
			response.setStatus(1);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(0);
			response.setMessage("Error in parsing form data.");
		}
		return response;
	}

}
