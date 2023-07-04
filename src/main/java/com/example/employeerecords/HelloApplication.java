package com.example.employeerecords;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
//    the begging of my application
    public static Boolean isLoaded = false;
    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Employee Recorder");
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {launch();}
}