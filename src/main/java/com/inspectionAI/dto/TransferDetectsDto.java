package com.inspectionAI.dto;

import java.time.LocalDateTime;

public class TransferDetectsDto {
	
	private Integer assemblyDetectionId;
    private Integer stageId;
    private Integer defectId;
    private Double confidence;
    private String detectionTime;
    
	public Integer getAssemblyDetectionId() {
		return assemblyDetectionId;
	}
	public void setAssemblyDetectionId(Integer assemblyDetectionId) {
		this.assemblyDetectionId = assemblyDetectionId;
	}
	public Integer getStageId() {
		return stageId;
	}
	public void setStageId(Integer stageId) {
		this.stageId = stageId;
	}
	public Integer getDefectId() {
		return defectId;
	}
	public void setDefectId(Integer defectId) {
		this.defectId = defectId;
	}
	public Double getConfidence() {
		return confidence;
	}
	public void setConfidence(Double confidence) {
		this.confidence = confidence;
	}
	public String getDetectionTime() {
		return detectionTime;
	}
	public void setDetectionTime(String detectionTime) {
		this.detectionTime = detectionTime;
	}
}
