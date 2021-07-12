package com.demo.wallet.Services;

import com.demo.wallet.Exceptions.InsufficientBalanceException;
import com.demo.wallet.Exceptions.WalletException;
import com.demo.wallet.Models.Entities.WalletEO;

import java.math.BigDecimal;
import java.util.List;

public interface WalletService extends GeneralService<WalletEO>{
    List<WalletEO> transactionsByAccountID(Long accountId)  throws WalletException;
    WalletEO transactionByRef(Long txnRef)  throws WalletException;
    BigDecimal balanceByAccountID(Long accountId)  throws WalletException;
    List<WalletEO> transactions();
    WalletEO createTransaction(WalletEO wallet) throws WalletException, InsufficientBalanceException;
}
