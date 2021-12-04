package com.example.demo;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminDashboard_GUI_Controller {
    @FXML
    Label lbl_customerEmail;
    @FXML
    TextArea text;
    @FXML
    ListView<Booking> listView;
    @FXML
     ChoiceBox c001;
    @FXML
     ChoiceBox c002;
    @FXML
     ChoiceBox c003;
    @FXML
     ChoiceBox c004;
    @FXML
     ChoiceBox c005;
    @FXML
     ChoiceBox c006;
    @FXML
     ChoiceBox c007;
    @FXML
     ChoiceBox c008;


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

    @FXML
    protected void onBackButtonClickInBookingsList(Event e) throws IOException {
        MainApplication.setCustomerDashboardScene(MainApplication.getLoggedInUser().getEmail());
    }

    @FXML
    protected void onNewBookingButtonClick (Event e) throws IOException {
        MainApplication.setCreateBookingScene();
    }

    @FXML
    protected void onCancelBookingButtonClick (Event e) throws IOException {

    }

    @FXML
    protected void onAccountInfoButtonClick (Event e) throws IOException {

    }

    @FXML
    protected void onDeleteAccountButtonClick (Event e) throws IOException {

    }




    @FXML
    protected void onMovieChosenConfirm (Event e) throws IOException {
        System.out.println("Movie selection complete");
        ArrayList<ChoiceBox> choiceBoxes = new ArrayList<>();
        Map<String, Integer> cart= new HashMap<>();
        choiceBoxes.add(c001);
        choiceBoxes.add(c002);
        choiceBoxes.add(c003);
        choiceBoxes.add(c004);
        choiceBoxes.add(c005);
        choiceBoxes.add(c006);
        choiceBoxes.add(c007);
        choiceBoxes.add(c008);

        for ( ChoiceBox c : choiceBoxes){
            if (c.getSelectionModel().getSelectedIndex() > 0) {
                cart.put(c.getId().substring(1), c.getSelectionModel().getSelectedIndex());
            }
        }

        System.out.println("Customer Wants to Buy: \n");
        for (Map.Entry<String, Integer> item : cart.entrySet()) {
            System.out.println(item.getKey() + ":" + item.getValue().toString());
        }

        MainApplication.setConfirmBookingScene( cart);
    }


    void setBookingArray (ArrayList<Booking> bookings) {
        this.bookings = bookings;
    }

    public void prepareBookingArray(){
        StringBuilder sb = new StringBuilder();
        for ( Booking b : bookings) {
            sb.append( b.showId ) ;
            sb.append(" = Date & Time => ");
            sb.append( b.schedule );
            sb.append("\n");
        }
        text.setText(sb.toString());
    }

    /* Cart Page */
    @FXML
    TextArea movieColumn;
    @FXML
    TextArea scheduleColumn;
    @FXML
    TextArea qtyColumn;
    @FXML
    TextArea priceColumn;


    @FXML
    TextField promo;
    @FXML
    TextField total;
    @FXML
    TextField discount;
    @FXML
    TextField netTotal;


}