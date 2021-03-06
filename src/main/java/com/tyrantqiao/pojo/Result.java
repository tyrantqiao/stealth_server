package com.tyrantqiao.pojo;

import com.tyrantqiao.enums.ResultEnum;

public class Result<T> {
	private String message;
	private int status;
	private T data;

	public Result() {
	}

	public Result(String message, int status, T data) {
		this.message = message;
		this.status = status;
		this.data = data;
	}

	public Result(ResultEnum resultEnum){
		this.message=resultEnum.getMsg();
		this.status=resultEnum.getStatus();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
