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
    static File bookingFile = new File("src/main/resources/com/example/demo/MockDatabase/bookings.txt");
    public static Map<String, String> passwordMap = new HashMap<>();
    private static Set<String> email = new HashSet<>();
    public static Map<String, Customer> map_id_customer = new HashMap<>();
    public static Map<String, Customer> map_email_customer = new HashMap<>();





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

            if (map.containsKey(list[0])) {
                map.get(list[0]).add(currentBooking);
            }
            else {
                map.put(list[0], new ArrayList<Booking>());
                map.get(list[0]).add(currentBooking);
            }

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


/*


class Main{
  public static void main(String args[]) throws FileNotFoundException {

		myBST tree = new myBST();
		LoadCustomers (tree);



	}


public static void LoadCustomers (myBST tree) throws FileNotFoundException {  // so this function is reading line-by-line
                   File inputFile = new File("TestFile.txt");
                   Scanner in = new Scanner(inputFile);


                   while(in.hasNextLine()){
                   String line = in.nextLine();


public static Customer createCustomerObject(String line){ // this method now returns a customer object
        String[] list = line.split(" ");
        double balance = 0f;
        if (list.length == 4 ) {
        balance = Integer.valueOf(list[3]);
        }
        Customer newCustomer = new Customer(list[0], list[1], list[2], balance);
        return newCustomer;
        }
*/