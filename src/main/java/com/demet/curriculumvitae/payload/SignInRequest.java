package com.demet.curriculumvitae.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SignInRequest {

    @NotNull
    @NotBlank
    @Size(min = 5, max = 35)
    private String username;

    @NotNull
    @NotBlank
    @Size(min = 6, max = 25)
    private String password;

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
}
