package com.infosys.stg.doc.model;

import java.util.List;

public class TemplateUploadResponse {
	private String barcode;
	private List<TemplatePages> images;

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
		return "TemplateUploadResponse [barcode=" + barcode + ", images=" + images + "]";
	}
}
