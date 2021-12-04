package com.example.demo;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

abstract class Database_Adapter {
    static final File customerFile = new File("src/main/resources/com/example/demo/MockDatabase/customers.txt");
    static final File bookingFile = new File("src/main/resources/com/example/demo/MockDatabase/bookings.txt");
    static final File movieFile = new File("src/main/resources/com/example/demo/MockDatabase/movies.txt");
    public static Map<String, String> passwordMap = new HashMap<>(); // email to password map
    private static Set<String> email = new HashSet<>();  // this will help avoid duplicate account creation
    public static Map<String, Customer> map_id_customer = new HashMap<>();  //helps find a Customer object with id
    public static Map<String, Customer> map_email_customer = new HashMap<>();//helps find a Customer object with email
    public static Map<String, Movie> map_movieid_schedule = new HashMap<>();  //helps find a schedule with movie id






    public Database_Adapter() throws FileNotFoundException {
    }

    public static Map<String, Customer> getMap_email_customer() {
        return map_email_customer;
    }
    public static Map<String, String> getPasswordMap() {
        return passwordMap;
    }

    public static Map<String, Customer> getMap() {
        return map_id_customer;
    }

    public static Map<String, Customer>  initializeCustomers() throws FileNotFoundException {
        Scanner in = new Scanner(customerFile);
        while (in.hasNextLine()) {
            String line = in.nextLine();
            Customer p =  createCustomer(line);
            map_id_customer.put(p.getId(), p);
            map_email_customer.put(p.getEmail(), p);
            passwordMap.put(p.getEmail(), p.getPassword() );
            email.add(p.getEmail());
        }
        return map_id_customer;
    }

    public static Map<String, Movie> initializeMovies() throws FileNotFoundException {
        Scanner in = new Scanner(movieFile);
        while (in.hasNextLine()) {
            String line = in.nextLine();
            Movie m =  new Movie(line);
            map_movieid_schedule.put(m.getId(), m);
        }
        return map_movieid_schedule;
    }


    public void  updateCustomers(Map<String, Customer> customerMap) throws FileNotFoundException {
        File fOld = new File("src/main/resources/com/example/demo/MockDatabase/customers.txt");
        fOld.delete();
        File fNew = new File("src/main/resources/com/example/demo/MockDatabase/customers.txt");
        try {
            FileWriter f2 = new FileWriter(fNew, false);
            for (Map.Entry<String,Customer> entry : customerMap.entrySet()){
                f2.write(entry.getKey());
                f2.write(", ");
                f2.write(entry.getValue().getName());
                f2.write(", ");
                f2.write(entry.getValue().getEmail());
                f2.write(", ");
                f2.write(entry.getValue().getPhone());
                f2.write("\n");
            }
            f2.close();
        } catch (IOException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }



    public static Map<String, ArrayList<Booking>>  initializeBookings() throws FileNotFoundException {
        Map<String, ArrayList<Booking>> map = new HashMap<>();
        // <CustomerId, array of Bookings>
        Scanner in = new Scanner(bookingFile);
        System.out.println("============= Fetching Bookings =============");
        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] list = line.split(", ");
            Booking currentBooking =  createBooking(line);

            if (!map.containsKey(list[0])) {
                map.put(list[0], new ArrayList<Booking>());
            }
            map.get(list[0]).add(currentBooking);

        }
        System.out.println("============= Done! Fetching Bookings =======");
        return map;
    }



    public static Customer createCustomer(String line){
        String[] list = line.split(", ");
        Customer newCustomer = new Customer(list[0], list[1], list[2], list[3]);
        return newCustomer;
    }
    public static Booking createBooking(String line){
        String[] list = line.split(", ");
        Booking newBooking = new Booking(list[0], list[1], list[2]);
        return newBooking;
    }

}

