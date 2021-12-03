package com.example.demo;

import java.io.FileNotFoundException;
import java.util.Map;


public class AccountService {
    private static AccountService instance = new AccountService();
    AccountService() {}
    public static AccountService getInstance() {return instance;}

    public static Customer createAccount(String id, String name, String email, String phone)  {
        Customer newCustomer = new Customer( id,  name,  email,  phone);
        System.out.println("Created a customer : " + newCustomer.toString());
        return newCustomer;
    }




}
