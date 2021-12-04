package com.example.demo;

import java.util.List;

public class AddMovieNotification implements Notification{

    List<Customer> lis;
    Movie movie;
    String notification;

    public AddMovieNotification(Movie movie, List<Customer> lis){
        this.lis = lis;
        this.movie = movie;
        notification = movie.getTitle() + " is scheduled at " + movie.getSchedule();
        System.out.println(notification);
    }

    @Override
    public void notifyCustomer() {
        for(Customer c: lis)
            c.addNotification(this);
    }

    public static void main(String[] args) {
        AddMovieNotification m =new AddMovieNotification(new Movie("Avatar"),null);
    }
}
