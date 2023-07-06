package com.example.springRedis.service;

import org.springframework.stereotype.Service;

@Service
public class ExternalApiService {

    public String getUserName(String userId) {
        // dhlan tjqltmsk DB 호출
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }

        System.out.println("Getting user name from other service...");

        if (userId.equals("A")) {
            return "Adam";
        }
        if (userId.equals("B")) {
            return "Bob";
        }
        return "";
    }

    public int getUserAge(String userId) {
        // dhlan tjqltmsk DB 호출
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }

        System.out.println("Getting user age from other service...");

        if (userId.equals("A")) {
            return 28;
        }
        if (userId.equals("B")) {
            return 32;
        }
        return 0;
    }
}
