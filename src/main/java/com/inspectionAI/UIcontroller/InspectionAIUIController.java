package com.inspectionAI.UIcontroller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.inspectionAI.dto.UpdateUIDto;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

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
	double duration = 0.7;

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
								pieData.get(i).setPieValue(pieData.get(i).getPieValue() + dto.getDefects().get(j).getCount());
								found = true;
								Tooltip tip = new Tooltip(String.valueOf(pieData.get(i).getPieValue()));
				    			tip.setShowDelay(Duration.seconds(duration));
				    			Tooltip.install(pieData.get(i).getNode(), tip);
								break;
							}
						}
						if (found == false) {
							pieData.add(new javafx.scene.chart.PieChart.Data(dto.getDefects().get(j).getName(),
									dto.getDefects().get(j).getCount()));
							int index = j;
							List<javafx.scene.chart.PieChart.Data> dataList = pieData.stream().filter(x -> x.getName().equals(dto.getDefects().get(index).getName())).collect(Collectors.toList());
							Tooltip tip = new Tooltip(String.valueOf(dataList.get(0).getPieValue()));
			    			tip.setShowDelay(Duration.seconds(duration));
			    			Tooltip.install(dataList.get(0).getNode(), tip);
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
		    		List<Data<String, Number>> filterList = defectiveSeries.getData().stream().filter(x -> x.getXValue().equals(String.valueOf(dto.getDateTime().getHour()))).collect(Collectors.toList());
		    		if (filterList.size() > 0) {
		    			int val = filterList.get(0).getYValue().intValue()+1;
		    			filterList.get(0).setYValue(val);
		    			Tooltip tip = new Tooltip(filterList.get(0).getYValue().toString());
		    			tip.setShowDelay(Duration.seconds(duration));
		    			Tooltip.install(filterList.get(0).getNode(), tip);
		    		} else {
		    			defectiveSeries.getData().add(new XYChart.Data<>(String.valueOf(dto.getDateTime().getHour()), 1));
			    		List<Data<String, Number>> getCurrentNode = defectiveSeries.getData().stream().filter(x -> x.getXValue().equals(String.valueOf(dto.getDateTime().getHour()))).collect(Collectors.toList());
			    		Tooltip tip = new Tooltip(getCurrentNode.get(0).getYValue().toString());
		    			tip.setShowDelay(Duration.seconds(duration));
		    			Tooltip.install(getCurrentNode.get(0).getNode(), tip);
		    		}
		    		
		    	} else {
		    		List<Data<String, Number>> filterList = goodSeries.getData().stream().filter(x -> x.getXValue().equals(String.valueOf(dto.getDateTime().getHour()))).collect(Collectors.toList());
		    		if (filterList.size() > 0) {
		    			int val = filterList.get(0).getYValue().intValue()+1;
		    			filterList.get(0).setYValue(val);
		    			Tooltip tip = new Tooltip(filterList.get(0).getYValue().toString());
		    			tip.setShowDelay(Duration.seconds(duration));
		    			Tooltip.install(filterList.get(0).getNode(), tip);
		    		} else {
		    			goodSeries.getData().add(new XYChart.Data<>(String.valueOf(dto.getDateTime().getHour()), 1));
			    		List<Data<String, Number>> getCurrentNode = goodSeries.getData().stream().filter(x -> x.getXValue().equals(String.valueOf(dto.getDateTime().getHour()))).collect(Collectors.toList());
			    		Tooltip tip = new Tooltip(getCurrentNode.get(0).getYValue().toString());
		    			tip.setShowDelay(Duration.seconds(duration));
		    			Tooltip.install(getCurrentNode.get(0).getNode(), tip);
		    		}
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
