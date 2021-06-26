package com.demet.curriculumvitae.payload;

import com.demet.curriculumvitae.model.account.Account;
import com.demet.curriculumvitae.model.account.impl.UserAccount;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.function.Function;

public class UpdateUserAccountRequest implements Function<Account, UserAccount> {

    @Email
    private String email;

    @Size(min = 11, max = 11)
    private String phoneNumber;

    @Size(min = 3, max = 55)
    private String fullName;

    @Size(min = 1, max = 10)
    private String gender;

    @Size(min = 3, max = 35)
    private String company;

    @Override
    public UserAccount apply(Account account) {
        if (account instanceof UserAccount userAccount) {
            if (email != null)
                userAccount.getCommunicationDetails()
                           .setEmail(email);
            if (phoneNumber != null)
                userAccount.getCommunicationDetails()
                           .setPhoneNumber(phoneNumber);
            if (gender != null)
                userAccount.getPersonalDetails()
                           .setGender(gender);
            if (company != null)
                userAccount.getPersonalDetails()
                           .setCompany(company);
            return userAccount;
        }
        return null;
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
