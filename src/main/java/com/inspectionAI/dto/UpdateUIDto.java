package com.inspectionAI.dto;

import java.time.LocalDateTime;
import java.util.List;

public class UpdateUIDto {

	private String imageUrl;
	private boolean isGood = false;
	private LocalDateTime dateTime;
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
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
}
