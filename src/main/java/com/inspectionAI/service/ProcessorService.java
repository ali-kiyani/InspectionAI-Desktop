package com.inspectionAI.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inspectionAI.UIcontroller.InspectionAIUIController;
import com.inspectionAI.dto.DefectCountDto;
import com.inspectionAI.dto.DetectionDto;
import com.inspectionAI.dto.ResponseDto;
import com.inspectionAI.dto.TransferDetectionsDto;
import com.inspectionAI.dto.TransferDetectsDto;
import com.inspectionAI.dto.UpdateUIDto;

@Service
public class ProcessorService {

	private InspectionAIUIController controller;
	private List<TransferDetectionsDto> detectionsBuffer;
	private String baseUrl;
	private String bulkUrl;
	private Integer transferLimit;
	
	public ProcessorService(@Value("${main.server.url.base}") String serverBaseUrl, 
			@Value("${main.server.url.bulk.detections}") String serverBulkUrl,
			@Value("${main.server.transfer.limit}") Integer transferLimit,
			InspectionAIUIController controller) {
		this.controller = controller;
		this.detectionsBuffer = Collections.synchronizedList(new ArrayList<TransferDetectionsDto>());
		this.baseUrl = serverBaseUrl;
		this.bulkUrl = serverBulkUrl;
		this.transferLimit = transferLimit;
	}
	
	public void process(DetectionDto request) {
		UpdateUIDto dto = new UpdateUIDto();
		if (request.getDateTime() != null)
			dto.setDateTime(request.getDateTime());
		else
			dto.setDateTime(LocalDateTime.now());
		dto.setGood(request.getIsGood());
		dto.setImageUrl(request.getImageUrl());
		dto.setDefects(request.getDefects());
		controller.AddNewDetection(dto);
		bufferDetection(request);
	}
	
	private void bufferDetection(DetectionDto dto) {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		TransferDetectionsDto detection = new TransferDetectionsDto();
		detection.setProductId(dto.getProductId());
		detection.setStageId(dto.getStageId());
		detection.setAssemblyLineId(dto.getAssemblyLineId());
		detection.setDetectionTime(dto.getDateTime().format(f));
		detection.setImageUrl(dto.getImageUrl());
		detection.setAssemblyDefects(new ArrayList<TransferDetectsDto>());
		int defectsCount = 0;
		for (DefectCountDto defectDto : dto.getDefects()) {
			for (int i = 0; i < defectDto.getCount(); i++) {
				TransferDetectsDto tDefectDto = new TransferDetectsDto();
				tDefectDto.setConfidence(0.0);
				tDefectDto.setDefectId(defectDto.getDefectId());
				tDefectDto.setDetectionTime(dto.getDateTime().format(f));
				tDefectDto.setStageId(dto.getStageId());
				tDefectDto.setAssemblyDetectionId(0);
				detection.getAssemblyDefects().add(tDefectDto);
				defectsCount++;
			}
		}
		detection.setDefectsCount(defectsCount);
		detectionsBuffer.add(detection);
		if (detectionsBuffer.size() >= this.transferLimit)
			sendBufferedData();
	}
	
	private void sendBufferedData() {
		Thread transferThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				RestTemplate restTemplate = new RestTemplate();
				ObjectMapper mapper = new ObjectMapper();
				String strData = null;
				try {
					int size = detectionsBuffer.size();
					strData = mapper.writeValueAsString(detectionsBuffer);
					HttpHeaders requestHeaders = new HttpHeaders();
			        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
			        HttpEntity<String> requestEntity = new HttpEntity<String>(strData, requestHeaders);
					restTemplate.exchange(baseUrl + bulkUrl, HttpMethod.POST, requestEntity, ResponseDto.class);
					detectionsBuffer.clear();
				} catch (Exception e) {
					System.out.println("ERROR: " + e.getMessage());
				}
			}
		});
		transferThread.start();
	}
}
