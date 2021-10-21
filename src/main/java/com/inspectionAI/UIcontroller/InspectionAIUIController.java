package com.inspectionAI.UIcontroller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.inspectionAI.dto.UpdateUIDto;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

@Component
public class InspectionAIUIController {

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
    public StackedBarChart<String, Number> bar;
    Series<String, Number> goodSeries = new Series<String, Number>();
    Series<String, Number> defectiveSeries = new Series<String, Number>();    
    String defaultLabel;
    
    int totalCount = 0;
    int goodCount = 0;
    int defectCount = 0;
    public InspectionAIUIController(@Value("${spring.application.ui.default.label}") String defaultLabel) {
    	this.defaultLabel = defaultLabel;
    }
    
    public void AddNewDetection(UpdateUIDto dto) {
    	Platform.runLater(new Runnable() {
			@Override
			public void run() {
				if (!dto.isGood()) {
					for (int j = 0; j < dto.getDefects().size(); j++) {
						boolean found = false;
						for (int i = 0; i < pieData.size(); i++) {
							if (dto.getDefects().get(j).getName().equalsIgnoreCase(pieData.get(i).getName())) {
								pieData.get(i)
										.setPieValue(pieData.get(i).getPieValue() + dto.getDefects().get(j).getCount());
								found = true;
								break;
							}
						}
						if (found == false) {
							pieData.add(new javafx.scene.chart.PieChart.Data(dto.getDefects().get(j).getName(),
									dto.getDefects().get(j).getCount()));
						}
					}
				}
			}
		});
    	Platform.runLater(new Runnable() {

			@Override
			public void run() {
		    	if (!dto.isGood()) {
					updateImages(dto);
		    	}
			}
    		
    	});
    	Platform.runLater(new Runnable() {

			@Override
			public void run() {
		    	if (!dto.isGood()) {
		    		defectiveSeries.getData().add(new XYChart.Data<>(String.valueOf(dto.getDateTime().getHour()), 1));
		    	} else {
		    		goodSeries.getData().add(new XYChart.Data<>(String.valueOf(dto.getDateTime().getHour()), 1));
		    	}
			}
    	
    	});
	    	Platform.runLater(new Runnable() {
				@Override
				public void run() {
			    	if (!dto.isGood()) {
			    		defectCount++;
						defective.setText(String.valueOf(defectCount));
			    	}
			    	else {
						good.setText(String.valueOf(Integer.valueOf(good.getText())+1));
			    	}
		    		totalCount++;
					total.setText(String.valueOf(totalCount));
				}
			});    	
    }
    
    private void updateImages(UpdateUIDto dto) {
    	if (dto.getImageUrl() != null && !dto.getImageUrl().isEmpty()) {
    		try {
    	      Image image = new Image("file:///" + dto.getImageUrl());
    	      img4.setImage(img3.getImage());
    	      label4.setText(label3.getText());
    	      img3.setImage(img2.getImage());
    	      label3.setText(label2.getText());
    	      img2.setImage(img1.getImage());
    	      label2.setText(label1.getText());
    	      img1.setImage(image);
    	      if (dto.getDefects() != null) {
    	    	  if (dto.getDefects().size() == 1) {
    	    		  label1.setText(dto.getDefects().get(0).getName());
    	    	  }
    	    	  else if (dto.getDefects().size() > 1) {
    	    		  label1.setText(defaultLabel);
    	    	  }
    	      }
    		}
    		catch (Exception e) {
    			System.out.println("error: " + e.getMessage());
    		}
    	}
    }

    @FXML
    public void initialize () {
    	pie.setData(pieData);
    	goodSeries.setName("GOOD");
    	defectiveSeries.setName("DEFECTIVE");

        ObservableList<XYChart.Series<String, Number>> data = FXCollections.observableArrayList();
    	data.addAll(defectiveSeries, goodSeries);
    	bar.setData(data);
    }
}
