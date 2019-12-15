package com.bndrs.voice.common;

public class ApiResponseEntity {
	private String code;

	private String message;

	private Object data;

	public ApiResponseEntity() {
	}

	public String getCode() {
		return code;
	}

	public ApiResponseEntity setCode(String code) {
		this.code = code;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public ApiResponseEntity setMessage(String message) {
		this.message = message;
		return this;
	}

	public Object getData() {
		return data;
	}

	public ApiResponseEntity setData(Object data) {
		this.data = data;
		return this;
	}

	public ApiResponseEntity success() {
		return this.setCode("0000");
	}

	public ApiResponseEntity success(String message) {
		return this.setCode("0000").setMessage(message);
	}

	public boolean isSuccess(){
		return "0000".equals(this.getCode());
	}
}
