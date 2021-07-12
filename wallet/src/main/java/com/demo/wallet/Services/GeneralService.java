package com.demo.wallet.Services;

import com.demo.wallet.Exceptions.WalletException;

import java.util.List;

public interface GeneralService<T> {
    T save(T t) throws WalletException;
    T update(T t,Long id) throws WalletException ;
    List<T> getList();
}
