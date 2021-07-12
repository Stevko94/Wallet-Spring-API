package com.demo.wallet.Services;

import com.demo.wallet.Exceptions.WalletException;
import com.demo.wallet.Models.Entities.AccountEO;

public interface AccountService extends GeneralService<AccountEO> {
    AccountEO accountByPK(Long accountId) throws WalletException;
}
