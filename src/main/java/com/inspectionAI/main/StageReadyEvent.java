package com.inspectionAI.main;

import org.springframework.context.ApplicationEvent;

import javafx.stage.Stage;

public class StageReadyEvent extends ApplicationEvent{

	public Stage getStage() {
	    return Stage.class.cast(getSource());
	}
	
	public StageReadyEvent(Object source) {
	    super(source);
	}
}
