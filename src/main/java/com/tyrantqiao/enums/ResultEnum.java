package com.tyrantqiao.enums;

public enum ResultEnum {
	UNKNOWN_ERROR(-1, "UNOKWN_ERROR"),
	SUCCESS(200, "SUCCESS"),
	NOTFOUND(404,"Not found, mabye password or name error.");

	private int status;
	private String msg;

	ResultEnum(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	public int getStatus() {
		return status;
	}

	public String getMsg() {
		return msg;
	}
}
