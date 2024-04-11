package com.datn.watch.common.model.json;

import lombok.Getter;

@Getter
@SuppressWarnings("unused")
public enum Status {

	SUCCESS("success", "00", "api.success"),
	FAIL("fail", "01", "api.fail"),
	EXCEPTION_FAIL("fail", "99", "api.fail"),
	;
	private final String status;
	private final String code;
	private final String message;

	Status(String status, String code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}

}