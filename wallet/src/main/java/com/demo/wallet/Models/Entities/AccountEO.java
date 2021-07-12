package com.demo.wallet.Models.Entities;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "Account")
public class AccountEO {
    @Id
    @GeneratedValue
    private Long id;
    private String playerName;
    private String sex;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateCreated;
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private Set<WalletEO> transactions = new HashSet<>();
    @Version
    private int version;

    public AccountEO() {
    }

    public AccountEO(Long id) {
        this.id = id;
    }

    public AccountEO(String playerName, String sex, Date dateCreated) {
        this.playerName = playerName;
        this.sex = sex;
        this.dateCreated = dateCreated;
    }



    public AccountEO(Long id,String playerName, String sex, Date dateCreated) {
        this.id = id;
        this.playerName = playerName;
        this.sex = sex;
        this.dateCreated = dateCreated;
    }


    public AccountEO(AccountBuilder builder) {
        id = builder.id;
        playerName = builder.playerName;
        sex = builder.sex;
        dateCreated = builder.dateCreated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Set<WalletEO> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<WalletEO> transactions) {
        this.transactions = transactions;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
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
        final AccountEO other = (AccountEO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public static class AccountBuilder {

        private Long id;
        private String playerName;
        private String sex;
        private Date dateCreated;

        public Long getId() {
            return id;
        }

        public AccountBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public AccountBuilder setPlayerName(String playerName) {
            this.playerName = playerName;
            return this;
        }

        public AccountBuilder setSex(String sex) {
            this.sex = sex;
            return this;
        }

        public AccountBuilder setDateCreated(Date dateCreated) {
            this.dateCreated = dateCreated;
            return this;
        }

        public AccountEO build() {
            return new AccountEO(this);
        }

    }

}
