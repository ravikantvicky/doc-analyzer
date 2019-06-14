package com.infosys.stg.doc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.infosys.stg.doc.exception.DocAnalyzeException;
import com.infosys.stg.doc.model.TemplatePages;
import com.infosys.stg.doc.model.TemplateUploadResponse;
import com.infosys.stg.doc.repository.TemplateRepository;
import com.infosys.stg.doc.thread.SaveImageService;

@Service
public class TemplateService {
	@Autowired
	private Environment env;
	@Autowired
	private DocService docService;
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	@Autowired
	private TemplateRepository tempRepository;

	public TemplateUploadResponse uploadFile(MultipartFile file, String templateName) throws DocAnalyzeException {
		TemplateUploadResponse response = new TemplateUploadResponse();
		try {
			List<String> images = docService.pdf2Image(file).getImages();
			long templateId = tempRepository.saveTemplate(templateName, UUID.randomUUID().toString(), " ");
			List<TemplatePages> templatePages = new ArrayList<>();
			List<Future<TemplatePages>> allImages = new ArrayList<>();
			AtomicInteger pageNo = new AtomicInteger(1);
			images.forEach(image -> {
				SaveImageService saveImageService = new SaveImageService(image, templateId, pageNo.getAndAdd(1),
						env.getProperty("url.imageBaseUrl"), tempRepository);
				allImages.add(taskExecutor.submit(saveImageService));
			});
			allImages.forEach(img -> {
				if (img != null) {
					try {
						templatePages.add(img.get());
					} catch (InterruptedException | ExecutionException e) {
						e.printStackTrace();
					}
				}
			});
			response.setImages(templatePages);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DocAnalyzeException("Error in upload file.");
		}
		return response;
	}

}
