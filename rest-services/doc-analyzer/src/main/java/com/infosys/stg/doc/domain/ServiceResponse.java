package com.infosys.stg.doc.domain;

public class ServiceResponse {
	private int status; // 1: success, 0: error
	private String message;
	private Object data;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ServiceResponse [status=" + status + ", message=" + message + ", data=" + data + "]";
	}
}
