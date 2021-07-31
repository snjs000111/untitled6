package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    int t = 1;
    int seriesNum = 1;

    public static void main(String[] args) {

        System.out.println("hell");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart lineChart = new LineChart(xAxis, yAxis);
//        LineChart lineChart = new LineChart();
        //创建列表存储曲线
        ObservableList<XYChart.Series<String, Double>> answer = FXCollections.observableArrayList();

        EventHandler<ActionEvent> eventHandler = e -> {

            if (seriesNum>0){
                //如果曲线的条数少于id的条数，则需要创建一条曲线到列表中
                if (answer.size()<seriesNum){
                    XYChart.Series<String,Double> series = new XYChart.Series<String,Double>();
                    series.setName("Temperature");
                    answer.addAll(series);
                    lineChart.getData().addAll(series);

                }
                // 遍历列表，给曲线赋值
                for (int i = 0; i < answer.size(); i++) {
                    int temp = (int) (Math.random()*50);
                    answer.get(i).getData().add(new XYChart.Data(Integer.toString(t), temp));
                    answer.get(i).setName("Temperature-"+i);
                }
                t++;
            }

        };

        Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), eventHandler));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();

        StackPane root = new StackPane();
        root.getChildren().add(lineChart);
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

    }
}
