package com.example.demo;

public class AinfoProxy extends AinfoImpl {

    @Override
    public void getAccountInfo()
    {
        //Perform additional logic and security
        //Even we can block the operation execution
        System.out.println("Delegating work on real object");
        super.getAccountInfo();
    }
}