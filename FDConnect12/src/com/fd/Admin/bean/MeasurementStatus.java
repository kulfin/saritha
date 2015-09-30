package com.fd.Admin.bean;

public class MeasurementStatus {

	private int measurementStatusId;
	private String measurementStatusName;

	// Setter
	public void setMeasurementStatusId(int measurementStatusId) {
		this.measurementStatusId = measurementStatusId;
	}

	public void setMeasurementStatusName(String measurementStatusName) {
		this.measurementStatusName = measurementStatusName;
	}

	// Getter
	public int getMeasurementStatusId() {
		return measurementStatusId;
	}

	public String getMeasurementStatusName() {
		return measurementStatusName;
	}

}
