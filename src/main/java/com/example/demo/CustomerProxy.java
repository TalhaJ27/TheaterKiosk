package com.example.demo;

public class CustomerProxy {
    Customer customer;
    public CustomerProxy(Customer customer) {
        this.customer = customer;
    }

    public String getProxyEmail() {
        String in = customer.getEmail();
        StringBuilder out = new StringBuilder();
        String[] list = in.split("@");

        int size = list[0].length();
        char[] domain = list[0].toCharArray();

        for (int i=0; i< size ; i++){
            if (i<3 || i> size-3){
                out.append(domain[i]);
            } else {
                out.append('*');
            }
        }
        out.append("@");
        out.append(list[1]);
        return out.toString();
    }


    public String getProxyPhone() {
        String in = customer.getPhone();
        if (in.length()<10){
            System.out.println("invalid customer phone");
            return "***-***-****";
        }
        StringBuilder out= new StringBuilder();
        out.append("***-***-");
        out.append(in.substring(8));
        return  out.toString(); //
    }
}
