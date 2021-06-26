package com.demet.curriculumvitae.model.account.impl;

public class CommunicationDetails {
    private String email;
    private String phoneNumber;

    public CommunicationDetails() {
    }

    public CommunicationDetails(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
