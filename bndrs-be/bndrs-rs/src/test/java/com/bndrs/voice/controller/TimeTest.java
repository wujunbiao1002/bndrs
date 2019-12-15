package com.bndrs.voice.controller;

import org.junit.Test;

public class TimeTest {
    @Test
    public void test(){
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {

        }
        long end = System.currentTimeMillis();
        System.out.println((double)(end - start)/1000);
        System.out.println(end - start);
    }
}
