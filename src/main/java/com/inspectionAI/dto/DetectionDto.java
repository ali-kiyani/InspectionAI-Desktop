package com.inspectionAI.dto;

import java.time.LocalDateTime;
import java.util.List;

public class DetectionDto {
	
	private Integer productId;
	private Integer stageId;
	private Integer assemblyLineId;
	private String imageUrl;
	private boolean isGood;
	private LocalDateTime dateTime;
	private List<DefectCountDto> defects;
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getStageId() {
		return stageId;
	}
	public void setStageId(Integer stageId) {
		this.stageId = stageId;
	}
	public Integer getAssemblyLineId() {
		return assemblyLineId;
	}
	public void setAssemblyLineId(Integer assemblyLineId) {
		this.assemblyLineId = assemblyLineId;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public void setGood(boolean isGood) {
		this.isGood = isGood;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public boolean getIsGood() {
		return isGood;
	}
	public void setIsGood(boolean isGood) {
		this.isGood = isGood;
	}
	public List<DefectCountDto> getDefects() {
		return defects;
	}
	public void setDefects(List<DefectCountDto> defects) {
		this.defects = defects;
	}
}
