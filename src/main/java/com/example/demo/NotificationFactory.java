package com.example.demo;

import java.util.List;

public class NotificationFactory {

    List<Customer> lis;

    public NotificationFactory(List<Customer> lis) {
        this.lis = lis;
    }

    public Notification getNotification(String notType, Movie movie, Booking booking){
        if(notType == null)
            return null;
        if(notType.equalsIgnoreCase("Add Movie"))
            return new AddMovieNotification(movie, lis);
        else if(notType.equalsIgnoreCase("Cancel Movie"))
            return new AddMovieNotification(movie, lis);
        else if(notType.equalsIgnoreCase("Add Booking"))
            return new MovieBookingNotification(booking, lis);
        else if(notType.equalsIgnoreCase("Cancel Booking"))
            return new CancelBookingNotification(booking, lis);
        else
            return null;
    }

    public void setLis(List<Customer> lis) {
        this.lis = lis;
    }
}
