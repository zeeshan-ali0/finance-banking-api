package com.zeeshan.financeapi.transaction;

import com.zeeshan.financeapi.account.Account;
import com.zeeshan.financeapi.account.AccountNotFoundException;
import com.zeeshan.financeapi.account.AccountRepository;
import com.zeeshan.financeapi.category.Category;
import com.zeeshan.financeapi.category.CategoryNotFoundException;
import com.zeeshan.financeapi.category.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class TransactionService {

    // TEMPORARY until Day 6: every request acts as the test user (id 1).
    private static final Long CURRENT_USER_ID = 1L;

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final CategoryRepository categoryRepository;

    public TransactionService(TransactionRepository transactionRepository,
                              AccountRepository accountRepository,
                              CategoryRepository categoryRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public TransactionResponse create(Long accountId, CreateTransactionRequest request) {
        Account account = findOwnedAccount(accountId);
        Category category = findCategoryFor(request.categoryId(), request.type());

        Transaction transaction = new Transaction(account, category, request.type(),
                request.amount(), request.description(), request.occurredOn());

        if (transaction.getType() == TransactionType.INCOME) {
            account.deposit(transaction.getAmount());
        } else {
            account.withdraw(transaction.getAmount());
        }
        return TransactionResponse.from(transactionRepository.save(transaction));
    }

    @Transactional(readOnly = true)
    public List<TransactionResponse> list(Long accountId) {
        findOwnedAccount(accountId);
        return transactionRepository.findByAccountIdOrderByOccurredOnDesc(accountId).stream()
                .map(TransactionResponse::from)
                .toList();
    }

    @Transactional
    public TransactionResponse update(Long id, UpdateTransactionRequest request) {
        Transaction transaction = findOwnedTransaction(id);
        Category category = findCategoryFor(request.categoryId(), transaction.getType());
        transaction.update(category, request.description(), request.occurredOn());
        return TransactionResponse.from(transaction);
    }

    @Transactional
    public void delete(Long id) {
        Transaction transaction = findOwnedTransaction(id);
        Account account = transaction.getAccount();

        if (transaction.getType() == TransactionType.INCOME) {
            account.withdraw(transaction.getAmount());
        } else {
            account.deposit(transaction.getAmount());
        }
        transactionRepository.delete(transaction);
    }

    private Account findOwnedAccount(Long accountId) {
        return accountRepository.findByIdAndUserId(accountId, CURRENT_USER_ID)
                .orElseThrow(() -> new AccountNotFoundException(accountId));
    }

    private Transaction findOwnedTransaction(Long id) {
        return transactionRepository.findByIdAndAccountUserId(id, CURRENT_USER_ID)
                .orElseThrow(() -> new TransactionNotFoundException(id));
    }

    private Category findCategoryFor(Long categoryId, TransactionType type) {
        Category category = categoryRepository.findByIdAndUserId(categoryId, CURRENT_USER_ID)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        if (category.getKind() != type) {
            throw new CategoryTypeMismatchException();
        }
        return category;
    }
}