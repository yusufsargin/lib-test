package com.wkrzywiec.spring.library.service;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(ServiceTestSuits.class);

        for (Failure failure : result.getFailures()) {
            System.out.println("ERROR => " + failure.toString());
        }

        System.out.println("Is Passed All test => " + result.wasSuccessful());
    }
}
