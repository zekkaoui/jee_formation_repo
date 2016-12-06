package com.mef.formationjee.core.exception;

public class FormationGenericException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String errCode;

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public FormationGenericException(String errCode) {
		this.errCode = errCode;
	}

}