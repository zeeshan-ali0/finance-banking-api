package com.zeeshan.financeapi.transaction;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/accounts/{accountId}/transactions")
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponse create(@PathVariable Long accountId,
                                      @Valid @RequestBody CreateTransactionRequest request) {
        return transactionService.create(accountId, request);
    }

    @GetMapping("/accounts/{accountId}/transactions")
    public List<TransactionResponse> list(@PathVariable Long accountId) {
        return transactionService.list(accountId);
    }

    @PutMapping("/transactions/{id}")
    public TransactionResponse update(@PathVariable Long id,
                                      @Valid @RequestBody UpdateTransactionRequest request) {
        return transactionService.update(id, request);
    }

    @DeleteMapping("/transactions/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        transactionService.delete(id);
    }
}