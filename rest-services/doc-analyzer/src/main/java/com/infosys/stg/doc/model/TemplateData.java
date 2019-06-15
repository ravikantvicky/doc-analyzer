package com.infosys.stg.doc.model;

public class TemplateData {
	private String label;
	private int pageNo;
	private int startX;
	private int startY;
	private int endX;
	private int endY;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getStartX() {
		return startX;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public int getStartY() {
		return startY;
	}

	public void setStartY(int startY) {
		this.startY = startY;
	}

	public int getEndX() {
		return endX;
	}

	public void setEndX(int endX) {
		this.endX = endX;
	}

	public int getEndY() {
		return endY;
	}

	public void setEndY(int endY) {
		this.endY = endY;
	}

	@Override
	public String toString() {
		return "TemplateData [label=" + label + ", pageNo=" + pageNo + ", startX=" + startX + ", startY=" + startY
				+ ", endX=" + endX + ", endY=" + endY + "]";
	}
}
