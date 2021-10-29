package com.inspectionAI.RESTcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inspectionAI.dto.DetectionDto;
import com.inspectionAI.service.ProcessorService;

@RestController
@RequestMapping("/api/inspectionAI/")
public class InspectionAIRestController {
	
	@Autowired
	private ProcessorService processorSerrvice;
	
	@PostMapping("/detection")
	public void detection(@RequestBody DetectionDto request) {
		processorSerrvice.process(request);
    }
}
