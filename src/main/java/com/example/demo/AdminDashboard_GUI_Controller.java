package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;


public class AdminDashboard_GUI_Controller {
    @FXML
    Label lbl_customerEmail;
    @FXML
    TextArea text;
    @FXML
    ListView<Booking> listView;


    private ArrayList<Booking> bookings;

    @FXML
    protected void onLogoutButtonClick(Event e) throws IOException {
        System.out.println("Admin Logged out");
        MainApplication.setLoginScene();
    }

    @FXML
    protected void onCustomerLogoutButtonClick(Event e) throws IOException {
        System.out.println("Customer Logged out");
        MainApplication.setLoggedInUser(null);
        MainApplication.setLoginScene();
    }

    // btn_viewAllBooking
    @FXML
    protected void onViewAllBooking(Event e) throws IOException {
        System.out.println("Customer wants to view all their shit : ");
        System.out.println("==================================");
        System.out.println(MainApplication.Bookings.toString());
        Customer currentlyLoggedIn = MainApplication.getLoggedInUser();


        if (MainApplication.Bookings.containsKey(currentlyLoggedIn.getId())) {
            ArrayList<Booking> currentUsersBooking = MainApplication.Bookings.get(currentlyLoggedIn.getId());
            System.out.println(currentUsersBooking);
        } else {
            System.out.println(" No booking record! ");
        }


        MainApplication.setViewCustomerBookingsScene();

    }

    void setBookingArray (ArrayList<Booking> bookings) {
        this.bookings = bookings;
    }

    public void prepareBookingArray(){
        StringBuilder sb = new StringBuilder();
        for ( Booking b : bookings) {
            sb.append( b.showId ) ;
            sb.append(" = Date & Time => ");
            sb.append( b.schedule ) ;
            sb.append("\n");
        }
        text.setText(sb.toString());
    }

}