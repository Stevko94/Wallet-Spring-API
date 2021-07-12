package com.demo.wallet.Models.Entities;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@Table(name = "Wallet")
public class WalletEO {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private AccountEO account;
    private BigDecimal amount;
    private String purpose;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date transactionDate;
    @Column(unique = true)
    private Long transactionReference;
    @Version //for concurrency
    private int version;

    public WalletEO() {
    }

    public WalletEO(AccountEO account, BigDecimal amount, String purpose, Date transactionDate, Long transactionReference) {
        this.account = account;
        this.amount = amount;
        this.purpose = purpose;
        this.transactionDate = transactionDate;
        this.transactionReference = transactionReference;
    }



    public WalletEO(WalletBuilder builder) {
        id = builder.id;
        account = new AccountEO(builder.accountId);
        amount = builder.amount;
        purpose = builder.purpose;
        transactionDate = builder.transactionDate;
        transactionReference = builder.transactionReference;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountEO getAccount() {
        return account;
    }

    public void setAccount(AccountEO account) {
        this.account = account;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Long getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(Long transactionReference) {
        this.transactionReference = transactionReference;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }



    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WalletEO other = (WalletEO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public static class WalletBuilder {

        private Long id;
        private Long accountId;
        private BigDecimal amount;
        private String purpose;
        private Date transactionDate;
        private Long transactionReference;

        public WalletBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public WalletBuilder setAccount(Long accountId) {
            this.accountId = accountId;
            return this;
        }

        public WalletBuilder setAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public WalletBuilder setPurpose(String purpose) {
            this.purpose = purpose;
            return this;
        }

        public WalletBuilder setTransactionDate(Date transactionDate) {
            this.transactionDate = transactionDate;
            return this;
        }

        public WalletBuilder setTransactionReference(Long transactionReference) {
            this.transactionReference = transactionReference;
            return this;
        }

        public WalletEO build() {
            return new WalletEO(this);
        }

    }
}
