package com.example.demo;

public class Movie {
    private String id;
    private String title;


    private String schedule;


    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getId() {return id;}
    public void setId(String id) {this.id = id;}
    public String getSchedule() {return schedule;}
    public void setSchedule(String schedule) {this.schedule = schedule;}


    public Movie(String in) {
        String[] splitted = in.split(", ");
        this.id = splitted[0];
        this.title = "UNKNOWN";
        this.schedule = splitted[1];
    }
}
