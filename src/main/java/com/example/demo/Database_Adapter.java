package com.example.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Database_Adapter {
    File customerFile = new File("src/main/resources/com/example/demo/MockDatabase/customers.txt");
    File bookingFile = new File("src/main/resources/com/example/demo/MockDatabase/bookings.txt");
    public Map<String, String> passwordMap = new HashMap<>();


    public Database_Adapter() throws FileNotFoundException {
    }


    public Map<String, Customer>  initializeDatabase() throws FileNotFoundException {
        Map<String, Customer> map = new HashMap<>();
        Scanner in = new Scanner(customerFile);
        while (in.hasNextLine()) {
            String line = in.nextLine();
            Customer p =  createCustomer(line);
            map.put(p.getId(), p);
            passwordMap.put(p.getEmail(), p.getPassword() );
        }
        return map;
    }

    public Map<String, ArrayList<Booking>>  initializeBookingsFromDatabase() throws FileNotFoundException {
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



    public Customer createCustomer(String line){
        String[] list = line.split(", ");
        Customer newCustomer = new Customer(list[0], list[1], list[2], list[3]);
        return newCustomer;
    }
    public Booking createBooking(String line){
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