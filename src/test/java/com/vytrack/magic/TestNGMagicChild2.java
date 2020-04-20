package com.vytrack.magic;

import org.testng.annotations.Test;

public class TestNGMagicChild2 extends TestNGMagic {
    @Test
    public void test2() {
        System.out.println("myString = " + myString);
    }
}
