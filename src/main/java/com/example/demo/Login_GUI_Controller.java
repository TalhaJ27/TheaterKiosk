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

import java.io.IOException;


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
    private Label admin_indicator;

    @FXML
    private Label label_email;


    @FXML
    private ChoiceBox choiceBox;


    @FXML
    protected void onHelloButtonClick() {
        System.out.println("test1");
    }

    @FXML
    protected void onOkButtonClick(Event e) throws IOException {
        System.out.println("onOkButtonClick");
        System.out.println(tf_name.getText());
        System.out.println(tf_pass.getText());

        if (    tf_name.getText().compareTo("admin")==0 &&
                tf_pass.getText().compareTo("admin")==0 ){
            MainApplication.setAdminDashboardScene();
        }
    }

    public Label getText_admin_indicator() {
       return admin_indicator;
    }

    public ChoiceBox getChoiceBox() {
        return choiceBox;
    }

    public Label getLabel_email() {
        return label_email;
    }



}