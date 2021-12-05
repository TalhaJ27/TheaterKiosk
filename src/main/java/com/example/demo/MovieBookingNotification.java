package com.example.demo;

import java.util.List;

public class MovieBookingNotification implements Notification {
    List<Customer> lis;
    Booking booking;
    String notification;

    public MovieBookingNotification(Booking booking, List<Customer> lis) {
        this.lis = lis;
        this.booking = booking;
        notification = "Movie booked scheduled at " + booking.getScheduleString();
    }

    @Override
    public void notifyCustomer() {
        for (Customer c : lis) {
            if (c.getId().equals(booking.customerId))
                c.addNotification(this);
        }
    }
}
