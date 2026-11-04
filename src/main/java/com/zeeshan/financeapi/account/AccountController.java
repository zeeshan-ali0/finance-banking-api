package com.zeeshan.financeapi.account;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountResponse> create(@Valid @RequestBody CreateAccountRequest request) {
        AccountResponse created = accountService.create(request);
        URI location = URI.create("/api/accounts/" + created.id());
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping
    public List<AccountResponse> list() {
        return accountService.list();
    }

    @GetMapping("/{id}")
    public AccountResponse get(@PathVariable Long id) {
        return accountService.get(id);
    }

    @PatchMapping("/{id}")
    public AccountResponse rename(@PathVariable Long id,
                                  @Valid @RequestBody UpdateAccountRequest request) {
        return accountService.rename(id, request);
    }
}