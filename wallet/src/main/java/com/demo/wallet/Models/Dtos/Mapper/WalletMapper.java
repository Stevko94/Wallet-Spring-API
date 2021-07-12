package com.demo.wallet.Models.Dtos.Mapper;

import com.demo.wallet.Models.Dtos.WalletDTO;
import com.demo.wallet.Models.Entities.WalletEO;

import java.util.List;
import java.util.stream.Collectors;

public class WalletMapper {
    public static WalletEO dtoToDO(WalletDTO w) {
        WalletEO wallet = new WalletEO.WalletBuilder()
                .setAccount(w.getAccountId())
                .setAmount(w.getAmount())
                .setId(w.getId())
                .setPurpose(w.getPurpose())
                .setTransactionDate(w.getTransactionDate())
                .setTransactionReference(w.getTransactionReference()).build();
        return wallet;
    }

    public static WalletDTO doToDTO(WalletEO w) {
        WalletDTO walletDTO = new WalletDTO.WalletDTOBuilder()
                .setAccountId(w.getAccount().getId())
                .setAmount(w.getAmount())
                .setId(w.getId())
                .setPurpose(w.getPurpose())
                .setTransactionDate(w.getTransactionDate())
                .setTransactionReference(w.getTransactionReference()).build();
        return walletDTO;
    }

    public static List<WalletDTO> doToDTOList(List<WalletEO> txns) {
        return txns.stream()
                .map(WalletMapper::doToDTO)
                .collect(Collectors.toList());
    }
}
