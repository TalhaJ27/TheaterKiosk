package com.example.demo;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Login_GUI_Controller {
    @FXML
    private Pane pane_main;
    @FXML
    private TextField tf_name;
    @FXML
    private TextField tf_pass;
    @FXML
    private ImageView image_logo;


    @FXML
    private ChoiceBox choiceBox;


    @FXML
    protected void onHelloButtonClick() {
        System.out.println("test1");
    }

    @FXML
    protected void onOkButtonClick(Event e) {
        System.out.println("onOkButtonClick");
        System.out.println(tf_name.getText());
        System.out.println(tf_pass.getText());
    }




}