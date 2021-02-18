package com.GymManagementSystem;

import com.sun.javafx.iio.gif.GIFImageLoaderFactory;

public class Over60Member extends DefaultMember{
    private int age;
    private String medications;

    public Over60Member(String membershipNumber, String memberName, String membershipStartDate, String contactNo, double height, double weight, int age, String medications) {
        super(membershipNumber, memberName, membershipStartDate, contactNo, height, weight);
        setAge(age);
        setMedications(medications);
    }

    //Implementation of getters and setters

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 60){
            this.age = age;
        }else{
            throw new IllegalArgumentException("Age must be over 60.");
        }

    }

    public String getMedications() {
        return medications;
    }

    public void setMedications(String medications) {
        this.medications = medications;
    }
}
