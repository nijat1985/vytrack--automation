package com.vytrack.magic;

import org.testng.annotations.Test;

public class TestNGMagicChild1 extends TestNGMagic {
    @Test
    public void test1(){
        System.out.println("myString = " + myString);
    }

}
