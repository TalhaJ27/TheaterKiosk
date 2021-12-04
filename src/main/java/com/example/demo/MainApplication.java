package com.example.demo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainApplication extends Application {
    public static Map<String,Movie> currentMovies;
    public static Customer loggedInUser = null;
    public static Map<String, Customer> Customers;
    public static Map<String, ArrayList<Booking>> Bookings ;
    public static Map<String,Scene> scenes = new HashMap<>();
    public static Stage globalStage;
    public static BookingService bookingService = BookingService.getInstance();
    public static AccountService accountService = AccountService.getInstance();
    // public static AccountService accountService = new AccountService();
    public static Database_Adapter database;


    public static void main(String[] args) throws FileNotFoundException {

        /* Elvis Moyolema: Test Cases */
        // Stating transaction type
        bookingService.setTransactionType("c");
        // Giving number of tickets and standard tickets (s)
        bookingService.getTicketPrice(3, "s");
        // Since we are done with the transaction we can end it
        System.out.println(bookingService.endTransaction());



        /* Md Rahman: Test Cases */
        Customers = Database_Adapter.initializeCustomers();
        Bookings = Database_Adapter.initializeBookings();
        currentMovies = Database_Adapter.initializeMovies();




        launch();
    }




    @Override
    public void start(Stage stage) throws IOException {


        //GUI setup:
        globalStage = stage;
        scenes.put("MainApp",setLoginScene(stage));
        globalStage.setScene(scenes.get("MainApp"));
        globalStage.show();
        Platform.runLater(() -> new LandingPage().start(new Stage()));
    }


    public static void setCustomerDashboard(String loginEmail) {
        System.out.println("loginEmail: < "+ loginEmail +" > is Approved");

        //RebeccaHWehner@nomail.com
    }

    public static Scene setLoginScene(Stage stage) throws IOException {
        // Login GUI initialization:
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login-page-admin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        stage.setTitle("Login Page");
        Login_GUI_Controller controller =  fxmlLoader.getController();
        controller.getText_admin_indicator().setVisible(false);
        controller.getChoiceBox().getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                    System.out.println(new_val.intValue());
                    if (new_val.intValue() == 0) {
                        controller.getLabel_email().setText("Id");
                        controller.getText_admin_indicator().setVisible(true);
                    } else {
                        controller.getLabel_email().setText("Email");
                        controller.getText_admin_indicator().setVisible(false);
                    }
                });
        return  scene;
    }

    public static void setLoginScene() throws IOException {
        globalStage.setScene(scenes.get("login"));
        globalStage.show();
    }

//    public static void setSignupScene() throws IOException {
//        globalStage.setScene(scenes.get("login"));
//        globalStage.show();
//    }

    public static Scene setAdminDashboardScene() throws IOException {
        FXMLLoader fxmlLoader2 = new FXMLLoader(MainApplication.class.getResource("dashboard-admin.fxml"));
        Scene scene = new Scene(fxmlLoader2.load() ,640, 480);
        AdminDashboard_GUI_Controller controller = fxmlLoader2.getController();
        fxmlLoader2.setController(controller);
        getScenes().put("adminDashboard",scene);
        getStage().setScene(scenes.get("adminDashboard"));
        getStage().show();
        return  scene;
    }

    public static Scene setCustomerDashboardScene(String customerEmail) throws IOException {
        System.out.println("Customer Approved to Login : "+customerEmail);
        FXMLLoader fxmlLoader4 = new FXMLLoader(MainApplication.class.getResource("dashboard-customer.fxml"));
        Scene scene = new Scene(fxmlLoader4.load() ,640, 480);
        AdminDashboard_GUI_Controller controller = fxmlLoader4.getController();
        fxmlLoader4.setController(controller);
        controller.lbl_customerEmail.setText("Welcome Back! "+ customerEmail);
        getScenes().put("customerDashboard",scene);
        getStage().setScene(scenes.get("customerDashboard"));
        getStage().show();
        return  scene;
    }

    public static Scene setSignupScene() throws IOException {
        FXMLLoader fxmlLoader3 = new FXMLLoader(MainApplication.class.getResource("signup-page-customer.fxml"));
        Scene scene = new Scene(fxmlLoader3.load() ,640, 480);
        Login_GUI_Controller controller = new Login_GUI_Controller();
        fxmlLoader3.setController(controller);
        getScenes().put("signup",scene);
        getStage().setScene(scenes.get("signup"));
        getStage().show();
        return  scene;
    }

    public static Scene setViewCustomerBookingsScene() throws IOException {
        FXMLLoader fxmlLoader5 = new FXMLLoader(MainApplication.class.getResource("view-allBookings-customer.fxml"));
        Scene scene = new Scene(fxmlLoader5.load() ,640, 480);
        AdminDashboard_GUI_Controller controller = new AdminDashboard_GUI_Controller();
        controller = fxmlLoader5.getController();
        controller.text.setText("Something");
        fxmlLoader5.setController(controller);
        controller.setBookingArray(Bookings.get(loggedInUser.getId()));
        controller.prepareBookingArray();
        getScenes().put("allBookings-customer",scene);
        getStage().setScene(scenes.get("allBookings-customer"));
        getStage().show();
        return  scene;
    }


    public static Scene setCreateBookingScene () throws IOException {
        FXMLLoader fxmlLoader6 = new FXMLLoader(MainApplication.class.getResource("newBooking-customer.fxml"));
        Scene scene = new Scene(fxmlLoader6.load() ,640, 480);
        AdminDashboard_GUI_Controller controller = new AdminDashboard_GUI_Controller();
        controller = fxmlLoader6.getController();
        fxmlLoader6.setController(controller);
        getScenes().put("newBooking-customer",scene);
        getStage().setScene(scenes.get("newBooking-customer"));
        getStage().show();
        return  scene;
    }

    public static void setConfirmBookingScene(Map<String, Integer> cart) throws IOException {
        FXMLLoader fxmlLoader7 = new FXMLLoader(MainApplication.class.getResource("confirmBooking-customer.fxml"));
        Scene scene = new Scene(fxmlLoader7.load() ,640, 480);
        AdminDashboard_GUI_Controller controller = new AdminDashboard_GUI_Controller();
        controller = fxmlLoader7.getController();
        fxmlLoader7.setController(controller);

        StringBuilder   movieColumnText = new StringBuilder(),
                        scheduleColumnText = new StringBuilder(),
                        qtyColumnText = new StringBuilder(),
                        priceColumnText = new StringBuilder();

        for (Map.Entry<String,Integer> entry : cart.entrySet()) {
            Movie currentMovie = currentMovies.get(entry.getKey());
            movieColumnText.append(currentMovie.getTitle());
            movieColumnText.append(System.getProperty("line.separator"));
            scheduleColumnText.append(currentMovie.getSchedule());
            scheduleColumnText.append(System.getProperty("line.separator"));
            qtyColumnText.append(entry.getValue().toString());
            qtyColumnText.append(System.getProperty("line.separator"));
            priceColumnText.append("$ 39.99");
            priceColumnText.append(System.getProperty("line.separator"));
        }
        controller.movieColumn.setText(movieColumnText.toString());
        controller.scheduleColumn.setText(scheduleColumnText.toString());
        controller.qtyColumn.setText(qtyColumnText.toString());
        controller.priceColumn.setText(priceColumnText.toString());

        // To-do:
            // => calculate total, netTotal
            // => add promo function to show discount


        getScenes().put("confirmBooking-customer",scene);
        getStage().setScene(scenes.get("confirmBooking-customer"));
        getStage().show();
    }

    public static Map<String, Scene> getScenes(){
        return scenes;
    }

    public static Stage getStage(){
        if (globalStage==null) return new Stage();
        return globalStage;
    }

    public static Database_Adapter getDatabase(){
        return database;
    }

    public static Map<String, Customer> getCustomerMap(){
        return Customers;
    }

    public static void addToCustomerMap(Customer c) throws FileNotFoundException {

        Customers.put(c.getId(), c);
        updateCustomers(Customers);  // <<< ===== Problem Here: NullPointer Exception
    }


    public static void  updateCustomers(Map<String, Customer> customerMap) throws FileNotFoundException {
        File fOld = new File("src/main/resources/com/example/demo/MockDatabase/customers.txt");
        fOld.delete();
        File fNew = new File("src/main/resources/com/example/demo/MockDatabase/customers.txt");
        try {
            FileWriter f2 = new FileWriter(fNew, false);
            for (Map.Entry<String,Customer> entry : customerMap.entrySet()){
                f2.write(entry.getKey());
                f2.write(", ");
                f2.write(entry.getValue().getName());
                f2.write(", ");
                f2.write(entry.getValue().getEmail());
                f2.write(", ");
                f2.write(entry.getValue().getPhone());
                f2.write("\n");
            }
            f2.close();
        } catch (IOException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
        Database_Adapter.initializeCustomers();
    }

    public static void  updateBookings( Map<String, ArrayList<Booking>> Bookings) throws FileNotFoundException {
        File fOld = new File("src/main/resources/com/example/demo/MockDatabase/bookings.txt");
        fOld.delete();
        File fNew = new File("src/main/resources/com/example/demo/MockDatabase/bookings.txt");
        try {
            FileWriter f2 = new FileWriter(fNew, false);
            for (Map.Entry<String,ArrayList<Booking>> entry : Bookings.entrySet()){
                for (Booking b : entry.getValue()) {
                    f2.write(entry.getKey());
                    f2.write(", ");
                    f2.write(b.getScheduleString());
                    f2.write(", ");
                    f2.write(b.getShowId());
                    f2.write("\n");
                }
            }
            f2.close();
        } catch (IOException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
        Database_Adapter.initializeBookings();
    }

    public static Customer getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(Customer c) {
        loggedInUser = c;
    }

    public static void logoutUser(){
        loggedInUser = null;
    }

}