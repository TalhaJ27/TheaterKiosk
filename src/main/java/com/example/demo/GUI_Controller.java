package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GUI_Controller {
    @FXML
    Label lbl_customerEmail;
    @FXML
    TextArea text;
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
    @FXML
     ProgressBar bar;
    @FXML
    TextArea summary;
    @FXML
    ImageView check;
    @FXML
    Button backToDashboard;
    @FXML
    TextArea infoText;
    Button button_newBooking;


    private ArrayList<Booking> bookings;

    @FXML
    protected void onLogoutButtonClick(Event e) throws IOException {
        System.out.println("Admin Logged out");
        // MainApplication.setLoggedInUser(null);
        // MainApplication.logoutUser();
        MainApplication.setLoginScene(MainApplication.getStage());
    }

    @FXML
    protected void onCustomerLogoutButtonClick(Event e) throws IOException {
        System.out.println("Customer Logged out");
//        MainApplication.setLoggedInUser(null);
//        MainApplication.logoutUser();
        MainApplication.setScene("MainApp");
    }

    // btn_viewAllBooking
    @FXML
    protected void onViewAllBooking(Event e) throws IOException {
        System.out.println("==================================");
        System.out.println(MainApplication.Bookings.toString());
        Customer currentlyLoggedIn = MainApplication.getLoggedInUser();

        if (MainApplication.Bookings.containsKey(currentlyLoggedIn.getId()) && currentlyLoggedIn!= null) {
            ArrayList<Booking> currentUsersBooking = MainApplication.Bookings.get(currentlyLoggedIn.getId());
            System.out.println(currentUsersBooking);
            MainApplication.setViewCustomerBookingsScene();
        } else {
            System.out.println(" No booking record! ");
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("No booking record!");
            a.setContentText("No booking record!");
            a.show();
        }

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
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle("Service Unavailable Now");
        a.setContentText("Please Try Later!");
        a.show();
    }

    @FXML
    protected void onAccountInfoButtonClick (Event e) throws IOException {
        MainApplication.setAccountInfoScene(MainApplication.getLoggedInUser());
    }

    @FXML
    protected void onDeleteAccountButtonClick (Event e) throws IOException {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle("Service Unavailable Now");
        a.setContentText("Please Try Later!");
        a.show();
    }

    @FXML
    protected void onGotoCheckout (Event e) throws IOException {
        System.out.println("onGotoCheckout");
        System.out.println(MainApplication.getSummary());
        MainApplication.setSummaryScene ( MainApplication.getSummary() );

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

    @FXML
    public void onBackButtonClickInSummaryPage(Event e) throws IOException {
        MainApplication.setConfirmBookingScene();
    }

    public void onConfirmFinal(Event e) throws IOException, InterruptedException {
        System.out.println("onConfirmFinal");
        MainApplication.setProgressScene(MainApplication.getFinal());
    }

    public void invokeProgress() throws InterruptedException {
        Timeline task = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new KeyValue(bar.progressProperty(), 0)
                ),
                new KeyFrame(
                        Duration.seconds(2),
                        new KeyValue(bar.progressProperty(), 0.75)
                ),
                new KeyFrame(
                        Duration.seconds(6),
                        new KeyValue(bar.progressProperty(), 1)
                )

        );
        task.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Image img = new Image(new File("src/main/resources/com/example/demo/images/CHECK.gif").toURI().toString());
                check.setImage(img);
                backToDashboard.setDisable(false);
            }
        });


        task.play();


    }

    public void onBackToDashboard(ActionEvent actionEvent) throws IOException {
        MainApplication.setCustomerDashboardScene(MainApplication.loggedInUser.getEmail());
    }
}