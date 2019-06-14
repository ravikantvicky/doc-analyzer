package com.infosys.stg.doc.model;

public class TemplatePages {
	private String imageUrl;
	private int pageNo;
	private double width;
	private double height;

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "TemplatePages [imageUrl=" + imageUrl + ", pageNo=" + pageNo + ", width=" + width + ", height=" + height
				+ "]";
	}
}
