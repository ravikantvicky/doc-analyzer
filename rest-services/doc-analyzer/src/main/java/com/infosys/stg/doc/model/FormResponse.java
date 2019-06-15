package com.infosys.stg.doc.model;

import java.util.List;

public class FormResponse {
	private List<TemplatePages> images;
	private List<FormField> fields;

	public List<TemplatePages> getImages() {
		return images;
	}

	public void setImages(List<TemplatePages> images) {
		this.images = images;
	}

	public List<FormField> getFields() {
		return fields;
	}

	public void setFields(List<FormField> fields) {
		this.fields = fields;
	}

	@Override
	public String toString() {
		return "FormResponse [images=" + images + ", fields=" + fields + "]";
	}
}
