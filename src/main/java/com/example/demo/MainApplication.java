package com.example.demo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MainApplication extends Application {
    public static List<Scene> scenes = new ArrayList<>();
    public static Stage globalStage;
    public static BookingService bookingService = BookingService.getInstance();
    Map<String, Customer> Customers;

    public static void main(String[] args) throws FileNotFoundException {

        /* Elvis Moyolema: Test Cases */
        // Stating transaction type
        bookingService.setTransactionType("c");
        // Giving number of tickets and standard tickets (s)
        bookingService.getTicketPrice(3, "s");
        // Since we are done with the transaction we can end it
        System.out.println(bookingService.endTransaction());



        /* Md Rahman: Test Cases */
        Database_Adapter database = new Database_Adapter();
        Map<String, Customer> Customers = database.initializeDatabase();
        Map<String, ArrayList<Booking>> Bookings = database.initializeBookingsFromDatabase();

        // System.out.printf(Bookings.toString());

        launch();
    }












    @Override
    public void start(Stage stage) throws IOException {


        //GUI setup:
        globalStage = stage;
        scenes.add(setLoginScene(stage));
        globalStage.setScene(scenes.get(0));
        globalStage.show();
        Platform.runLater(() -> new LandingPage().start(new Stage()));
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
        globalStage.setScene(scenes.get(0));
        globalStage.show();
    }

    public static Scene setAdminDashboardScene() throws IOException {
        FXMLLoader fxmlLoader2 = new FXMLLoader(MainApplication.class.getResource("dashboard-admin.fxml"));
        Scene scene = new Scene(fxmlLoader2.load() ,640, 480);
        AdminDashboard_GUI_Controller controller = fxmlLoader2.getController();
        fxmlLoader2.setController(controller);
        getScenes().add(scene);
        getStage().setScene(scenes.get(1));
        getStage().show();
        return  scene;
    }

    public static List<Scene> getScenes(){

        return scenes;
    }

    public static Stage getStage(){
        if (globalStage==null) return new Stage();
        return globalStage;
    }


}