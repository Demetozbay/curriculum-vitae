package com.demet.curriculumvitae.payload;

import com.demet.curriculumvitae.model.account.impl.CommunicationDetails;
import com.demet.curriculumvitae.model.account.impl.PersonalDetails;
import com.demet.curriculumvitae.model.account.impl.UserAccount;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.function.Supplier;

public class SignUpRequest implements Supplier<UserAccount> {

    @NotNull
    @NotBlank
    @Size(min = 5, max = 35)
    private String username;

    @NotNull
    @NotBlank
    @Size(min = 6, max = 25)
    private String password;

    @NotNull
    @NotBlank
    @Email
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 55)
    private String fullName;

    @Size(min = 11, max = 11)
    private String phoneNumber;

    @Size(min = 1, max = 10)
    private String gender;

    @Size(min = 3, max = 35)
    private String company;

    @Override
    public UserAccount get() {
        UserAccount account = new UserAccount();
        account.setUsername(username);
        account.setPassword(password);
        account.setRole("ROLE_USER");
        account.setDisabled(false);

        account.setCommunicationDetails(new CommunicationDetails(
                email, phoneNumber));
        account.setPersonalDetails(new PersonalDetails(
                fullName, gender, company));
        return account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
