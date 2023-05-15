package com.class8.model;

import java.util.Scanner;

public class Person {
    private int id;

    private String name;
    private String email;
    private String address;
    private String phone;

    public void inputData(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter name: ");
        this.name = input.nextLine();
        System.out.print("Enter email: ");
        this.email = input.nextLine();
        System.out.print("Enter address: ");
        this.address = input.nextLine();
        System.out.print("Enter phone: ");
        this.phone = input.nextLine();
    }

    public Person() {

    }

    public Person(int id, String name, String email, String address,String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone=phone;
    }

    @Override
    public String toString() {
        return this.id + ", " +
                this.name + ", " +
                this.email + ", " +
                this.address + ", " +
                this.phone ;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

