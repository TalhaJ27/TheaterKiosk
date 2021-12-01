package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Inactivity_Listener {
    private Timeline timer;

    public Inactivity_Listener(VBox mainPanel) {

        timer = new Timeline(new KeyFrame(Duration.seconds(3600), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent evt) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Inactivity");
                alert.setHeaderText("Connection closed due to inactivity");
                alert.show();
            }
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();

        mainPanel.addEventFilter(MouseEvent.ANY, new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                timer.playFromStart();
            }
        });
    }
}