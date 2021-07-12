/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.wallet.service;

import java.util.Date;

import com.demo.wallet.Exceptions.WalletException;
import com.demo.wallet.Models.Entities.AccountEO;
import com.demo.wallet.Services.AccountService;
import org.junit.Assert;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceImplTest {

    @Autowired
    private AccountService accountService;

    /**
     * quickly setup a test account to do
     * out test with 
     */
    @Before
    public  void setupAccount() throws WalletException {
        accountService.save(new AccountEO(1L, "james doe", "M", new Date()));
    }

    @Test
    public void findPlayerByValidPrimaryKey() throws WalletException {
        AccountEO account = accountService.accountByPK(1L);
        assertNotNull(account);
        Assert.assertEquals("james doe", account.getPlayerName());
    }

    @Test(expected = WalletException.class)
    public void createPlayerWithEmptyPlayerName() throws WalletException {
        accountService.save(new AccountEO(1L, "", "M", new Date()));
    }

    @Test(expected = WalletException.class)
    public void createPlayerWithPlayerNameLessThan5Characters() throws WalletException {
        accountService.save(new AccountEO(1L, "", "M", new Date()));
    }

    @Test(expected = WalletException.class)
    public void createPlayerWithNullPlayerName() throws WalletException {
        accountService.save(new AccountEO(1L, null, "M", new Date()));
    }

    @Test
    public void createPlayerWithValidDetails() throws WalletException {
        accountService.save(new AccountEO(2L, "gumball darwin", "M", new Date()));
    }

}
