package com.infosys.stg.doc.model;

public class FormCoordinates {
	private boolean isPresent;
	private double xStart;
	private double yStart;
	private double xEnd;
	private double yEnd;

	public boolean isPresent() {
		return isPresent;
	}

	public void setPresent(boolean isPresent) {
		this.isPresent = isPresent;
	}

	public double getxStart() {
		return xStart;
	}

	public void setxStart(double xStart) {
		this.xStart = xStart;
	}

	public double getyStart() {
		return yStart;
	}

	public void setyStart(double yStart) {
		this.yStart = yStart;
	}

	public double getxEnd() {
		return xEnd;
	}

	public void setxEnd(double xEnd) {
		this.xEnd = xEnd;
	}

	public double getyEnd() {
		return yEnd;
	}

	public void setyEnd(double yEnd) {
		this.yEnd = yEnd;
	}

	@Override
	public String toString() {
		return "FormCoordinates [isPresent=" + isPresent + ", xStart=" + xStart + ", yStart=" + yStart + ", xEnd="
				+ xEnd + ", yEnd=" + yEnd + "]";
	}
}
