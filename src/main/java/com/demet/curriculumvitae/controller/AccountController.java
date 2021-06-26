package com.demet.curriculumvitae.controller;

import com.demet.curriculumvitae.configuration.jwt.JwtTokenProvider;
import com.demet.curriculumvitae.exception.AlreadyExists;
import com.demet.curriculumvitae.exception.NotFound;
import com.demet.curriculumvitae.exception.UnableToProcess;
import com.demet.curriculumvitae.exception.Unauthorised;
import com.demet.curriculumvitae.helper.IsAdmin;
import com.demet.curriculumvitae.helper.IsAuthenticated;
import com.demet.curriculumvitae.helper.IsOwned;
import com.demet.curriculumvitae.model.account.Account;
import com.demet.curriculumvitae.payload.SignInRequest;
import com.demet.curriculumvitae.payload.SignUpRequest;
import com.demet.curriculumvitae.payload.UpdateAccountPasswordRequest;
import com.demet.curriculumvitae.payload.UpdateUserAccountRequest;
import com.demet.curriculumvitae.service.AccountService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final AccountService accountService;

    public AccountController(AuthenticationManager authenticationManager,
                             JwtTokenProvider jwtTokenProvider,
                             AccountService accountService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.accountService = accountService;
    }

    @GetMapping
    @IsAdmin
    public Page<Account> get(@RequestParam Map<String, Object> queryParams) {
        return accountService.getByPage(queryParams);
    }

    @GetMapping("/{username}")
    @IsAuthenticated
    @IsOwned
    public Account get(@PathVariable String username) throws NotFound {
        return accountService.getByUsername(username);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@Valid @RequestBody SignInRequest request)
    throws Unauthorised {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (DisabledException e) {
            throw new Unauthorised("AccountController.signIn.DisabledException");
        } catch (BadCredentialsException e) {
            throw new Unauthorised("AccountController.signIn.BadCredentialsException");
        }

        String jwt = jwtTokenProvider.createToken(authentication.getName(), authentication.getAuthorities());
        Map<String, Object> responseBody = Map.of(
                "idToken", jwt,
                "tokenType", "Bearer",
                "roles", authentication.getAuthorities()
        );

        return ResponseEntity.ok()
                             .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt)
                             .body(responseBody);
    }

    @PostMapping("/sign-up")
    public Account create(@Valid @RequestBody SignUpRequest request)
    throws AlreadyExists {
        Account account = accountService.create(request);
        account.setPassword("?");
        return account;
    }

    @PatchMapping("/{username}")
    @IsAuthenticated
    @IsOwned
    public Account update(@PathVariable String username,
                          @Valid @RequestBody UpdateUserAccountRequest request)
    throws UnableToProcess, NotFound {
        return accountService.update(username, request);
    }

    @PatchMapping("/{username}/password")
    @IsAuthenticated
    @IsOwned
    public Account updatePassword(@PathVariable String username,
                                  @Valid @RequestBody UpdateAccountPasswordRequest request)
    throws UnableToProcess, NotFound {
        return accountService.update(username, request);
    }
}
