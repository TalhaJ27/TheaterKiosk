package com.example.demo;

import java.util.UUID;

public class BookingService {
    private double PROMO_CODE = 0.1f; //10% discount can be change by ADMIN
    private double total_cost = 0f;  //  Final price of the ticket
    private double sticket = 13.59f; //  Standard ticket price
    private double pticket = 10.39f;  //  Premium ticket cost
    private double tax = 0.08875f;    // NYC tax
    private static BookingService instance = new BookingService(); // eagerly loads the singleton
    private UUID bookingID = UUID.randomUUID(); // Specifc transaction ID
    private int nump = 0, nums = 0;
    private String transanctionType = "";
    private BookingService() {
        // private to prevent anyone else from instantiating
    }

    public static BookingService getInstance() {
        return instance;
    }

    // This should only be an option of the ADMIN if they want to change the discount
    public void setDiscount(String code, double d) {
        if(code == "Admin123")
            PROMO_CODE = d;
        else
            System.out.println("You can't perform this action");
    }

    // Sets what type of transactions it is, credit card, cash
    public void setTransactionType(String t) {
        if(t=="c")
            transanctionType = "Cash";
        else if(t=="cc")
            transanctionType = "Credit Card";
        else
            System.out.println("Choose a valid transanction:\nCredit card (cc)\nCash (c)");

    }

    // It returns the transaction type that we'll be performing
    private String getTransactionType(){
        return transanctionType;
    }

    // It returns Total prices of ticket(s) + tax
    private double getTotalPrice() {
        return total_cost;
    }

    private int getNumStickets(){
        return nums;
    }

    private int getNumPtickets(){
        return nump;
    }
    //Gets the unique booking id related to the Booking that's being executed
    private UUID getBookingID() {
        return bookingID;
    }

    public double getTicketPrice(int x, String s) {
        // Depending on the input "s" for standard and "p" for premium (ticket) we'll perform different calculations
        if(x <= 0 || x > 9){
            System.out.println("Please enter valid number of tickets (1 - 9)");
            return 0;
        }

        if(s=="s"){
            for(int i=0;i < x; i++){
                total_cost += sticket + (sticket * tax); nums++;}
        }
        else if(s=="p"){
            for(int i=0;i < x; i++){
                total_cost += pticket + (pticket * tax); nump++;}
        } else {
            System.out.println("Please select one of the available tickets\nStandard Ticket (s)\nPremium Ticket (p)");
        }
        return this.total_cost;
    }
    // Adds discount base on a promoName and Code, promotion is fixed unless changed by Admin
    public void addDiscount(String promo, int code) {
        if(promo == "movies4all" && code == 123)
            total_cost -= (total_cost * PROMO_CODE);
        else
            System.out.println("Sorry, not valid promo");
    }

    public String endTransaction(){
        String s = "";
        s += "Transaction ID: " + getBookingID() + "\n" +
                "Transanction Type: " + getTransactionType() + "\n" +
                "Standard Tickets: " + getNumStickets() + "\n" +
                "Premium tickets: " + getNumPtickets() + "\n" +
                "Total price: "  + String.format("%.2f", getTotalPrice());
        return s;

    }
}
