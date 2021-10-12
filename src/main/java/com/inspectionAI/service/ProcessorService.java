package com.inspectionAI.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.inspectionAI.UIcontroller.InspectionAIController;
import com.inspectionAI.dto.DetectionDto;
import com.inspectionAI.dto.UpdateUIDto;

@Service
public class ProcessorService {

	private InspectionAIController controller;
	
	public ProcessorService(InspectionAIController controller) {
		this.controller = controller;
	}
	
	public String process(DetectionDto request) {
		UpdateUIDto dto = new UpdateUIDto();
		dto.setDateTime(new Date());
		dto.setGood(request.isGood());
		dto.setImageUrl(request.getImageUrl());
		dto.setDefects(request.getDefects());
		controller.AddNewDetection(dto);
		return "hi";
	}
}
