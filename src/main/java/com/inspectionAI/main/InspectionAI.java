package com.inspectionAI.main;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.inspectionAI.main","com.inspectionAI.UIcontroller",
		"com.inspectionAI.RESTcontroller","com.inspectionAI.service"} )
public class InspectionAI {

	public static void main(String[] args) {
		Application.launch(JavafxApplication.class, args);
	}

}