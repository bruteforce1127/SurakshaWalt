package com.kucp1127.SurakshaWalt.CompanyAskingConsent.Controller;


public class Sample_Controller {
    public void generateRandomCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomCode = new StringBuilder();
        int length = 8; // Length of the random code

        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * characters.length());
            randomCode.append(characters.charAt(index));
        }

        System.out.println("Generated Random Code: " + randomCode.toString());
    }
}