package com.quanglinhit.version2;

public class Persion {
    private String firstName;
    private String lastName;

    public Persion() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Persion(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
