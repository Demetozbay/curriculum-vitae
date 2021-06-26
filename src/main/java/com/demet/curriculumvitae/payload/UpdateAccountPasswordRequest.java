package com.demet.curriculumvitae.payload;

import com.demet.curriculumvitae.Context;
import com.demet.curriculumvitae.model.account.Account;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.function.Function;

public class UpdateAccountPasswordRequest implements Function<Account, Account> {

    @NotNull
    @NotBlank
    @Size(min = 6, max = 25)
    private String password;

    @Override
    public Account apply(Account account) {
        PasswordEncoder passwordEncoder = Context.getBean(PasswordEncoder.class);
        account.setPassword(
                passwordEncoder.encode(password));
        return account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
