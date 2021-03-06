package com.demo.wallet.Services;

import com.demo.wallet.Exceptions.WalletException;
import com.demo.wallet.Models.Entities.AccountEO;
import com.demo.wallet.Models.Repositories.AccountRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AccountEO accountByPK(Long accountId) throws WalletException {
        return accountRepository.findById(accountId).
                orElseThrow(() -> new WalletException(String.format("account with '%d' not found ", accountId)));
    }

    /**
     * this operations registers a player and creates and account for him/her
     * with minimal details
     */
    @Override
    @Transactional
    public AccountEO save(AccountEO account) throws WalletException {
        if (account.getPlayerName() != null) {
            if (account.getPlayerName().length() < 5) {
                throw new WalletException("player name is should be 5 characters of more");
            }
            return accountRepository.save(account);
        }
        throw new WalletException("player name is mandatory");
    }

    /**
     * this operation updates a players account information
     * and checks for concurrent player modification
     */
    @Override
    @Transactional
    public AccountEO update(AccountEO account, Long accountId) throws WalletException {
        if (account.getPlayerName() != null) {
            account.setId(accountId);
            try {
                return accountRepository.save(account);
            } catch (ObjectOptimisticLockingFailureException e) {
                throw new WalletException("refresh your page to get updated players");
            }
        }
        throw new WalletException("player name is mandatory");

    }

    /**
     * this operation gets all account lists and their respective
     * wallet transactions
     */
    @Override
    public List<AccountEO> getList() {
        return Lists.newArrayList(accountRepository.findAll());
    }

}
