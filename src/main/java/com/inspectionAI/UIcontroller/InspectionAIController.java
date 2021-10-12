package com.inspectionAI.UIcontroller;

import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import org.springframework.stereotype.Component;

import com.inspectionAI.dto.UpdateUIDto;

@Component
public class InspectionAIController {

    @FXML
    public Label label1;
    @FXML
    public Label label2;
    @FXML
    public Label label3;
    @FXML
    public Label label4;
    
    @FXML
    public ImageView img1;
    @FXML
    public ImageView img2;
    @FXML
    public ImageView img3;
    @FXML
    public ImageView img4;
    
    @FXML
    public Label total;
    @FXML
    public Label good;
    @FXML
    public Label defective;

    @FXML
    public PieChart pie;
    private ObservableList<javafx.scene.chart.PieChart.Data> pieData = FXCollections.observableArrayList();
    
    @FXML
    public StackedBarChart<String, Integer> bar;
    private ObservableList<Series<String, Integer>> seriesData = FXCollections.observableArrayList();
    
    
    
    public InspectionAIController() {
    }
    
    public void AddNewDetection(UpdateUIDto dto) {
    	if (!dto.isGood()) {
	    	Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					for (int j = 0; j < dto.getDefects().size(); j++) {
						boolean found = false;
						for (int i = 0; i < pieData.size(); i++) {
							if (dto.getDefects().get(j).getName().equalsIgnoreCase(pieData.get(i).getName())) {
								pieData.get(i).setPieValue(pieData.get(i).getPieValue()+dto.getDefects().get(j).getCount());
								//pieData.set(i, pieData.get(i).setPieValue(pieData.get(i).getPieValue()+dto.getDefects().get(j).getCount()));
								//pieData.set(i, new javafx.scene.chart.PieChart.Data(dto.getDefects().get(j).getName(), pieData.get(i).getPieValue() + dto.getDefects().get(j).getCount()));
								found = true;
								break;
							}
						}
						if (found == false) {
					    	pieData.add(new javafx.scene.chart.PieChart.Data(dto.getDefects().get(j).getName(), dto.getDefects().get(j).getCount()));
						}
					}
					
				}
			});
    	}
    	
    }

    @FXML
    public void initialize () {
    	pie.setData(pieData);
    	bar.setData(seriesData);
    	
    }
}
