package com.demet.curriculumvitae.model.account.impl;

public class PersonalDetails {
    private String fullName;
    private String gender;
    private String company;

    public PersonalDetails() {
    }

    public PersonalDetails(String fullName, String gender, String company) {
        this.fullName = fullName;
        this.gender = gender;
        this.company = company;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
