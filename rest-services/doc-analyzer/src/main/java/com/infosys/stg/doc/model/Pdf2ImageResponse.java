package com.infosys.stg.doc.model;

import java.util.List;

public class Pdf2ImageResponse {
	private List<String> images;

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "Pdf2ImageResponse [images=" + images + "]";
	}
}
