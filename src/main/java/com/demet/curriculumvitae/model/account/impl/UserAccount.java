package com.demet.curriculumvitae.model.account.impl;

import com.demet.curriculumvitae.model.account.Account;

public class UserAccount extends Account {
    private CommunicationDetails communicationDetails;
    private PersonalDetails personalDetails;

    public CommunicationDetails getCommunicationDetails() {
        return communicationDetails;
    }

    public void setCommunicationDetails(CommunicationDetails communicationDetails) {
        this.communicationDetails = communicationDetails;
    }

    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }

    public void setPersonalDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
    }
}
