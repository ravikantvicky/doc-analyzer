package com.infosys.stg.doc.thread;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.concurrent.Callable;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import com.infosys.stg.doc.model.TemplatePages;
import com.infosys.stg.doc.repository.TemplateRepository;

public class SaveImageService implements Callable<TemplatePages> {
	private String imageData;
	private long templateId;
	private int pageNo;
	private String imageBaseUrl;
	private TemplateRepository tempRepository;

	public SaveImageService(String imageData, long templateId, int pageNo, String imageBaseUrl,
			TemplateRepository tempRepository) {
		this.imageData = imageData;
		this.templateId = templateId;
		this.pageNo = pageNo;
		this.imageBaseUrl = imageBaseUrl;
		this.tempRepository = tempRepository;
	}

	@Override
	public TemplatePages call() throws Exception {
		BufferedImage image = ImageIO.read(new ByteArrayInputStream(DatatypeConverter.parseBase64Binary(imageData)));
		double width = image.getWidth();
		double height = image.getHeight();
		long imageId = tempRepository.saveTemplatePages(templateId, pageNo, imageData, width, height);
		TemplatePages tempPage = new TemplatePages();
		tempPage.setPageNo(pageNo);
		tempPage.setWidth(width);
		tempPage.setHeight(height);
		tempPage.setImageUrl(imageBaseUrl + imageId);
		return tempPage;
	}
}
