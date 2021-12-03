package com.example.demo;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;


public class AdminDashboard_GUI_Controller {
    @FXML
    Label lbl_customerEmail;


    @FXML
    protected void onLogoutButtonClick(Event e) throws IOException {
        System.out.println("Admin Logged out");
        MainApplication.setLoginScene();
    }

    @FXML
    protected void onCustomerLogoutButtonClick(Event e) throws IOException {
        System.out.println("Customer Logged out");
        MainApplication.setLoginScene();
    }


}