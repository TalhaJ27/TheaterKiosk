package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Person{
    final String id;
    String  name;
    String email;
    String phone;
    String password = "123";
    List<Notification> notificationList = new ArrayList<>();
    CustomerProxy proxy;

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Customer(String id) {
        this.id = id;
    }
    public Customer(String id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.proxy = new CustomerProxy(this);
    }
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    String getProxyEmail(){
        return proxy.getProxyEmail();
    }

    String getProxyPhone(){
        return proxy.getProxyPhone();
    }
    public void addNotification(Notification n) {notificationList.add(n);}

}
