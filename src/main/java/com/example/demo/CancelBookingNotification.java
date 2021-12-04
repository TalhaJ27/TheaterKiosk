package com.example.demo;

import java.util.List;

public class CancelBookingNotification implements Notification{
    List<Customer> lis;
    Booking booking;
    String notification;

    public CancelBookingNotification(Booking booking, List<Customer> lis){
        this.lis = lis;
        this.booking = booking;
        notification = "Movie booked scheduled at "+booking.getScheduleString()+" has been canceled";
    }

    @Override
    public void notifyCustomer() {
        for(Customer c: lis)
            if(c.getId().equals(booking.customerId))
                c.addNotification(this);
    }
}
