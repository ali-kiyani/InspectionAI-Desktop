package com.inspectionAI.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class UpdateUIDto {

	private String imageUrl;
	private boolean isGood = false;
	private Date dateTime;
	private List<DefectCountDto> defects;
	
	public List<DefectCountDto> getDefects() {
		return defects;
	}
	public void setDefects(List<DefectCountDto> defects) {
		this.defects = defects;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public boolean isGood() {
		return isGood;
	}
	public void setGood(boolean isGood) {
		this.isGood = isGood;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
}
