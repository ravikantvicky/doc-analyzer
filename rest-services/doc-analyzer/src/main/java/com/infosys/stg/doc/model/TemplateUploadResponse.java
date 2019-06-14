package com.infosys.stg.doc.model;

import java.util.List;

public class TemplateUploadResponse {
	private String barcode;
	private List<String> images;

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "TemplateResponse [barcode=" + barcode + ", images=" + images + "]";
	}
}
