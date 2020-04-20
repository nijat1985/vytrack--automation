package com.vytrack.magic;

import org.testng.annotations.*;

public class TestNGMagic {

    String myString;

    @BeforeSuite
    public void setMyString(){
        myString = "Vasya";
    }





}
