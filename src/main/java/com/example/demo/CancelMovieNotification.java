package com.example.demo;

import java.util.List;

public class CancelMovieNotification implements Notification{
    List<Customer> lis;
    Movie movie;
    String notification;

    public CancelMovieNotification(Movie movie, List<Customer> lis){
        this.lis = lis;
        this.movie = movie;
        notification = movie.getTitle() + " is scheduled at " + movie.getSchedule();
    }

    @Override
    public void notifyCustomer() {
        for(Customer c: lis)
            c.addNotification(this);
    }
}
