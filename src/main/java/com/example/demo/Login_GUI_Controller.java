package com.example.demo;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.jar.Manifest;

import static com.example.demo.AccountService.createAccount;


public class Login_GUI_Controller {
    @FXML
    private TextField tf_name;
    @FXML
    private TextField tf_pass;
    @FXML
    private Label admin_indicator;
    @FXML
    private Label label_email;
    @FXML
    private ChoiceBox choiceBox;
    @FXML
    private TextField tf_signup_name;
    @FXML
    private TextField tf_signup_password;
    @FXML
    private TextField tf_signup_email;
    @FXML
    private TextField tf_signup_phone;


//    @FXML
//    protected void onHelloButtonClick() {
//        System.out.println("test1");
//    }

    @FXML
    protected void onOkButtonClick(Event e) throws IOException {
        System.out.println(tf_name.getText());
        System.out.println(tf_pass.getText());

        if (    choiceBox.getSelectionModel().getSelectedIndex()== 0 &&
                tf_name.getText().compareTo("admin")==0 &&
                tf_pass.getText().compareTo("admin")==0 ){
            MainApplication.setAdminDashboardScene();
            return;
        }

        String loginEmail = tf_name.getText();
        String loginPass = tf_pass.getText();

        if (Database_Adapter.getPasswordMap().containsKey(loginEmail)){
            String customerName = Database_Adapter.getMap_email_customer().get(loginEmail).getName();
            Customer loginCustomer = Database_Adapter.getMap_email_customer().get(loginEmail);
            MainApplication.setLoggedInUser(loginCustomer);
            MainApplication.setCustomerDashboardScene(customerName);

        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Account Not Found");
            a.setContentText("No account found! Please Try again, or SignUp ");
            a.show();
        }
    }

    @FXML
    protected void onSignupClick(Event e) throws IOException{
        MainApplication.setSignupScene();
    }

    @FXML
    protected void onLoginLinkClick(Event e) throws IOException{
        MainApplication.setLoginScene();
    }

    @FXML
    protected void onSignupConfirmClick(Event e) throws IOException{
        UUID tempUUID = UUID.randomUUID();
        String uuid = tempUUID.toString();

        try {
            System.out.println("Wants account with details: "+ uuid  + tf_signup_name.getText() + " : " +tf_signup_email.getText()+ " : " +tf_signup_phone.getText());
            Customer c = createAccount(uuid,  tf_signup_name.getText(), tf_signup_email.getText(), tf_signup_phone.getText());
            MainApplication.addToCustomerMap(c);
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("Account Created !");
            a.show();
        }
        catch(Exception err) {
            System.err.println(err.toString());
        }

        MainApplication.setLoginScene();
    }


    @FXML
    protected void onDebugClick(Event e) throws IOException{
        System.out.println("Debugging Log: " + MainApplication.currentMovies.size());
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