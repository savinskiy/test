/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ncedu.java.tasks.businesscard;

import java.util.*;
import java.text.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DimaZ
 */
public class MyBusinessCard implements BusinessCard {

    private String name;
    private String lastName;
    private String department;
    private String birthDate;
    private Character gender;
    private int salary;
    private long phoneNumber;

    @Override
    public BusinessCard getBusinessCard(Scanner scanner) {
        String[] arr = scanner.nextLine().split(";");
        name = arr[0];
        lastName = arr[1];
        department = arr[2];
        birthDate = arr[3];
        gender = arr[4].charAt(0);
        salary = Integer.parseInt(arr[5]);
        phoneNumber = Long.parseLong(arr[6]);
        return this;
    }

    @Override
    public String getEmployee() {
        return name + " " + lastName;
    }

    @Override
    public String getDepartment() {
        return department;
    }

    @Override
    public int getSalary() {
        return salary;
    }

    @Override
    public int getAge() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date birth = null;
        try {
            birth = dateFormat.parse(birthDate);
        } catch (ParseException ex) {
            Logger.getLogger(MyBusinessCard.class.getName()).log(Level.SEVERE, null, ex);
        }
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(birth);
        cal2.setTime(new Date());
        long age = (cal2.getTimeInMillis() - cal1.getTimeInMillis()) / 1000 / 3600 / 24 / 365;
        return (int)age;
    }

    @Override
    public String getGender() {
        final String male = "male";
        final String female = "female";
        final String error = "error";
        if (gender == 'V') {
            return female;
        } else {
            if (gender == 'M') {
                return male;
            } else {
                return error;
            }
        }
    }

    @Override
    public String getPhoneNumber() {
        String phone = String.valueOf(phoneNumber);
        String result = "+7 " + phone.substring(0, 3) + "-"
                + phone.substring(3, 6) + "-" + phone.substring(6, 8) + "-"
                + phone.substring(8, 10);
        return result;
    }
}
