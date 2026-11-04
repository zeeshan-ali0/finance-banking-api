package com.zeeshan.financeapi.account;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class AccountService {

    // TEMPORARY until Day 6: every request acts as the test user (id 1).
    // Replaced by the logged-in user from the JWT.
    private static final Long CURRENT_USER_ID = 1L;

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public AccountResponse create(CreateAccountRequest request) {
        Account account = new Account(
                CURRENT_USER_ID, request.name(), request.type(), request.currency());
        return AccountResponse.from(accountRepository.save(account));
    }

    @Transactional(readOnly = true)
    public List<AccountResponse> list() {
        return accountRepository.findByUserId(CURRENT_USER_ID).stream()
                .map(AccountResponse::from)
                .toList();
    }
}