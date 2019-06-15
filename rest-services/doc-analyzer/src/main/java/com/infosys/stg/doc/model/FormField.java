package com.infosys.stg.doc.model;

public class FormField {
	private int pageNo;
	private String dataLabel;
	private String dataValue;
	private String dataType;
	private FormCoordinates coordinates;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getDataLabel() {
		return dataLabel;
	}

	public void setDataLabel(String dataLabel) {
		this.dataLabel = dataLabel;
	}

	public String getDataValue() {
		return dataValue;
	}

	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public FormCoordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(FormCoordinates coordinates) {
		this.coordinates = coordinates;
	}

	@Override
	public String toString() {
		return "FormField [pageNo=" + pageNo + ", dataLabel=" + dataLabel + ", dataValue=" + dataValue + ", dataType="
				+ dataType + ", coordinates=" + coordinates + "]";
	}
}
