package com.inspectionAI.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.inspectionAI.UIcontroller.InspectionAIUIController;
import com.inspectionAI.dto.DetectionDto;
import com.inspectionAI.dto.UpdateUIDto;

@Service
public class ProcessorService {

	private InspectionAIUIController controller;
	
	public ProcessorService(InspectionAIUIController controller) {
		this.controller = controller;
	}
	
	public String process(DetectionDto request) {
		UpdateUIDto dto = new UpdateUIDto();
		if (request.getDateTime() != null)
			dto.setDateTime(request.getDateTime());
		else
			dto.setDateTime(LocalDateTime.now());
		dto.setGood(request.getIsGood());
		dto.setImageUrl(request.getImageUrl());
		dto.setDefects(request.getDefects());
		controller.AddNewDetection(dto);
		return "hi";
	}
}
