CREATE TABLE users (
                       id            BIGSERIAL PRIMARY KEY,
                       email         VARCHAR(255) NOT NULL UNIQUE,
                       password_hash VARCHAR(100) NOT NULL,
                       full_name     VARCHAR(100) NOT NULL,
                       created_at    TIMESTAMPTZ  NOT NULL DEFAULT now()
);

CREATE TABLE accounts (
                          id         BIGSERIAL PRIMARY KEY,
                          user_id    BIGINT        NOT NULL REFERENCES users(id),
                          name       VARCHAR(100)  NOT NULL,
                          type       VARCHAR(20)   NOT NULL CHECK (type IN ('CURRENT','SAVINGS','CASH')),
                          currency   CHAR(3)       NOT NULL DEFAULT 'EUR',
                          balance    NUMERIC(19,2) NOT NULL DEFAULT 0,
                          version    BIGINT        NOT NULL DEFAULT 0,   -- optimistic locking, used on Day 5
                          created_at TIMESTAMPTZ   NOT NULL DEFAULT now()
);
CREATE INDEX idx_accounts_user ON accounts(user_id);

CREATE TABLE categories (
                            id      BIGSERIAL PRIMARY KEY,
                            user_id BIGINT      NOT NULL REFERENCES users(id),
                            name    VARCHAR(50) NOT NULL,
                            kind    VARCHAR(10) NOT NULL CHECK (kind IN ('INCOME','EXPENSE')),
                            UNIQUE (user_id, name)
);

CREATE TABLE transactions (
                              id          BIGSERIAL PRIMARY KEY,
                              account_id  BIGINT        NOT NULL REFERENCES accounts(id),
                              category_id BIGINT        REFERENCES categories(id),
                              type        VARCHAR(10)   NOT NULL CHECK (type IN ('INCOME','EXPENSE')),
                              amount      NUMERIC(19,2) NOT NULL CHECK (amount > 0),
                              description VARCHAR(255),
                              occurred_on DATE          NOT NULL,
                              created_at  TIMESTAMPTZ   NOT NULL DEFAULT now()
);
CREATE INDEX idx_tx_account_date ON transactions(account_id, occurred_on);

CREATE TABLE budgets (
                         id           BIGSERIAL PRIMARY KEY,
                         user_id      BIGINT        NOT NULL REFERENCES users(id),
                         category_id  BIGINT        NOT NULL REFERENCES categories(id),
                         budget_month DATE          NOT NULL,   -- always the 1st of the month
                         amount       NUMERIC(19,2) NOT NULL CHECK (amount > 0),
                         UNIQUE (user_id, category_id, budget_month)
);