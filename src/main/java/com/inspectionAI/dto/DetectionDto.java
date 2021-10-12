package com.inspectionAI.dto;

import java.util.List;

public class DetectionDto {

	private String ImageUrl;
	private boolean isGood;
	private List<DefectCountDto> defects;
	
	public String getImageUrl() {
		return ImageUrl;
	}
	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}
	public boolean isGood() {
		return isGood;
	}
	public void setGood(boolean isGood) {
		this.isGood = isGood;
	}
	public List<DefectCountDto> getDefects() {
		return defects;
	}
	public void setDefects(List<DefectCountDto> defects) {
		this.defects = defects;
	}
}
