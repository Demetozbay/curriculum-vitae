package com.demet.curriculumvitae.service;

import com.demet.curriculumvitae.CurriculumVitaeApplication;
import com.demet.curriculumvitae.exception.AlreadyExists;
import com.demet.curriculumvitae.exception.NotFound;
import com.demet.curriculumvitae.exception.UnableToProcess;
import com.demet.curriculumvitae.model.account.Account;
import com.demet.curriculumvitae.repository.AccountRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountService(AccountRepository accountRepository,
                          PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean existByUsername(String username) {
        return accountRepository.existsByUsername(username);
    }

    public Page<Account> getByPage(Map<String, Object> queryParams) {
        Pageable pageable = PageRequest.of(Integer.parseInt("" + queryParams.getOrDefault("page", "0")),
                                           CurriculumVitaeApplication.DEFAULT_CONTENT_SIZE,
                                           CurriculumVitaeApplication.DEFAULT_SORT);
        return accountRepository.findAll(pageable);
    }

    public Account getByUsername(String username)
    throws NotFound {
        return accountRepository.findByUsername(username)
                                .orElseThrow(() -> new NotFound("Account not found"));
    }

    public Account create(Supplier<? extends Account> supplier)
    throws AlreadyExists {
        Account account = supplier.get();
        if (existByUsername(account.getUsername()))
            throw new AlreadyExists("Username already exist");

        account.setPassword(
                passwordEncoder.encode(account.getPassword()));
        account.setCreationDate(System.currentTimeMillis());
        return accountRepository.insert(account);
    }

    public Account update(String username,
                          Function<Account, ? extends Account> updater)
    throws NotFound, UnableToProcess {
        Account account = getByUsername(username);
        account = updater.apply(account);

        if (account == null)
            throw new UnableToProcess("Account type not suitable for this operation");

        account.setUpdatedDate(System.currentTimeMillis());
        return accountRepository.save(account);
    }
}
