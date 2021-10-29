package com.inspectionAI.dto;

import java.time.LocalDateTime;
import java.util.List;

public class TransferDetectionsDto {
                        
    private Integer assemblyLineId;
    private Integer productId;
    private Integer stageId;
    private Integer defectsCount;
    private String detectionTime;
    private String imageUrl;
    private List<TransferDetectsDto> assemblyDefects;
    
	public Integer getAssemblyLineId() {
		return assemblyLineId;
	}
	public void setAssemblyLineId(Integer assemblyLineId) {
		this.assemblyLineId = assemblyLineId;
	}
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
	public Integer getDefectsCount() {
		return defectsCount;
	}
	public void setDefectsCount(Integer defectsCount) {
		this.defectsCount = defectsCount;
	}
	public String getDetectionTime() {
		return detectionTime;
	}
	public void setDetectionTime(String detectionTime) {
		this.detectionTime = detectionTime;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public List<TransferDetectsDto> getAssemblyDefects() {
		return assemblyDefects;
	}
	public void setAssemblyDefects(List<TransferDetectsDto> assemblyDefects) {
		this.assemblyDefects = assemblyDefects;
	}
}
