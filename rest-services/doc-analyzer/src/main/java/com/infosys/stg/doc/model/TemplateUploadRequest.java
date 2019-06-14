package com.infosys.stg.doc.model;

public class TemplateUploadRequest {
	private String fileData;

	public String getFileData() {
		return fileData;
	}

	public void setFileData(String fileData) {
		this.fileData = fileData;
	}

	@Override
	public String toString() {
		return "TemplateUploadRequest [fileData=" + fileData + "]";
	}
}
