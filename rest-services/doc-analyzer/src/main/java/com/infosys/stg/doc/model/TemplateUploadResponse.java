package com.infosys.stg.doc.model;

import java.util.List;

public class TemplateUploadResponse {
	private long templateId;
	private String barcode;
	private List<TemplatePages> images;

	public long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(long templateId) {
		this.templateId = templateId;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public List<TemplatePages> getImages() {
		return images;
	}

	public void setImages(List<TemplatePages> images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "TemplateUploadResponse [templateId=" + templateId + ", barcode=" + barcode + ", images=" + images + "]";
	}
}
