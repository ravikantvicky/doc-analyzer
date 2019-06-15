package com.infosys.stg.doc.model;

import java.util.List;

public class SaveTemplateRequest {
	private long templateId;
	private String barcode;
	private List<TemplateData> data;

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

	public List<TemplateData> getData() {
		return data;
	}

	public void setData(List<TemplateData> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "SaveTemplateRequest [templateId=" + templateId + ", barcode=" + barcode + ", data=" + data + "]";
	}
}
