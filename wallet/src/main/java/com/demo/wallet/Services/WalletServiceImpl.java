package com.demo.wallet.Services;

import com.demo.wallet.Exceptions.InsufficientBalanceException;
import com.demo.wallet.Exceptions.WalletException;
import com.demo.wallet.Models.Entities.WalletEO;
import com.demo.wallet.Models.Repositories.WalletRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
@Service
public class WalletServiceImpl implements WalletService{

    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private AccountService accountService;

    /**
     * retrieve transactions by their transaction reference this operations is
     * used to validate if a transaction ref has been used previously
     */
    @Override
    public WalletEO transactionByRef(Long txnRef) throws WalletException {
        return walletRepository.getTransactionByRef(txnRef).
                orElseThrow(() -> new WalletException(String.format("transaction with ref '%d' doesnt exist", txnRef)));
    }

    /**
     * this operations registers a transaction on behalf of player
     * debit/credits, it also validates if a player has insufficient funds if a
     * debit is to be made
     */
    @Override
    @Transactional
    public WalletEO createTransaction(WalletEO wallet) throws WalletException, InsufficientBalanceException {
        // checks for transaction ref to ensure its uniqueness
        if (walletRepository.getTransactionByRef(wallet.getTransactionReference()).isPresent()) {
            throw new WalletException("transaction ref has been used ");
        }
        BigDecimal balance = walletRepository.getBalance(wallet.getAccount().getId());

        if (balance.add(wallet.getAmount()).compareTo(BigDecimal.ZERO) == 1
                | balance.add(wallet.getAmount()).compareTo(BigDecimal.ZERO) == 0) {
            return walletRepository.save(wallet);
        }

        throw new InsufficientBalanceException(String.format("player's balance is %.2f and cannot perform a transaction of %.2f ",
                balance.doubleValue(), wallet.getAmount().doubleValue()));

    }

    @Override
    public WalletEO update(WalletEO wallet, Long id) throws WalletException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<WalletEO> getList() {
        return Lists.newArrayList(walletRepository.findAll());
    }

    @Override
    public List<WalletEO> transactionsByAccountID(Long accountId) {
        return walletRepository.getTransactionsForPlayer(accountId);
    }

    @Override
    public BigDecimal balanceByAccountID(Long accountId) {
        return walletRepository.getBalance(accountId);
    }

    @Override
    public List<WalletEO> transactions() {
        return Lists.newArrayList(walletRepository.findAll());
    }

    @Override
    public WalletEO save(WalletEO wallet) throws WalletException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
