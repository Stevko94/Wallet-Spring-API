/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.wallet.service;

import java.math.BigDecimal;
import java.util.Date;
import static org.junit.Assert.*;

import com.demo.wallet.Exceptions.InsufficientBalanceException;
import com.demo.wallet.Exceptions.WalletException;
import com.demo.wallet.Models.Entities.AccountEO;
import com.demo.wallet.Models.Entities.WalletEO;
import com.demo.wallet.Services.AccountService;
import com.demo.wallet.Services.WalletService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class WalletServiceImplTest {

    @Autowired
    private AccountService accountService;
    @Autowired
    private WalletService walletService;

    private AccountEO account;

    @Before
    public void setupPlayerAccount() throws WalletException {
        account = accountService.save(new AccountEO("eric cartman", "M", new Date()));
    }

    @Test(expected = InsufficientBalanceException.class)
    public void registerDebitTransactionof5000ForEricCartman() throws WalletException, InsufficientBalanceException {
        WalletEO wallet = new WalletEO(account, new BigDecimal(-5000), "debit", new Date(), 150L);
        walletService.createTransaction(wallet);
    }

    @Test
    public void registerCreditTransactionof10000ForEricCartman() throws WalletException, InsufficientBalanceException {
        WalletEO saved1 = new WalletEO(account, new BigDecimal(10000), "credit", new Date(), 150L);
        WalletEO savedTransaction1 = walletService.createTransaction(saved1);
        assertNotNull(savedTransaction1);
        BigDecimal balance = walletService.balanceByAccountID(account.getId());
        assertTrue(balance.doubleValue() == 10000);
    }

}
