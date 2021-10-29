package com.inspectionAI.dto;

public class ResponseDto {

	String result;
	String targetUrl;
	Boolean success;
	String error;
	Boolean unAuthorizedRequest;
	Boolean __abp;
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getTargetUrl() {
		return targetUrl;
	}
	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public Boolean getUnAuthorizedRequest() {
		return unAuthorizedRequest;
	}
	public void setUnAuthorizedRequest(Boolean unAuthorizedRequest) {
		this.unAuthorizedRequest = unAuthorizedRequest;
	}
	public Boolean get__abp() {
		return __abp;
	}
	public void set__abp(Boolean __abp) {
		this.__abp = __abp;
	}
}
