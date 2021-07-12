/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.wallet.datatransferobject;

import com.demo.wallet.Models.Dtos.AccountDTO;
import com.demo.wallet.Models.Dtos.Mapper.AccountMapper;
import com.demo.wallet.Models.Entities.AccountEO;
import java.util.Date;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class AccountMapperTest {

    private static AccountEO account;
    private static AccountDTO accountDTO;

    @Before
    public void setupAccountObject() {
        account = new AccountEO(1L, "sponge bob", "F", new Date());
    }

    @Test
    public void testDataObjectToDataTransferObject() {
        accountDTO = AccountMapper.doToDTO(account);
        Assert.assertEquals(accountDTO.getId(), account.getId());
        Assert.assertEquals(accountDTO.getPlayerName(), account.getPlayerName());
        Assert.assertEquals(accountDTO.getSex(), account.getSex());

    }

    @Test
    public void testDataTransferObjectToDataObject() {
        account = AccountMapper.dtoToDO(accountDTO);
        Assert.assertEquals(account.getId(), accountDTO.getId());
        Assert.assertEquals(account.getPlayerName(), accountDTO.getPlayerName());
        Assert.assertEquals(account.getSex(), accountDTO.getSex());

    }
}
