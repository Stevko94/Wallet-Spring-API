package com.demo.wallet.Models.Dtos.Mapper;

import com.demo.wallet.Models.Dtos.AccountDTO;
import com.demo.wallet.Models.Entities.AccountEO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AccountMapper {
    public static AccountEO dtoToDO(AccountDTO a) {
        AccountEO account = new AccountEO.AccountBuilder()
                .setDateCreated(new Date())
                .setId(a.getId())
                .setPlayerName(a.getPlayerName())
                .setSex(a.getSex())
                .build();
        return account;
    }

    public static AccountDTO doToDTO(AccountEO a) {
        double balance = a.getTransactions().stream().mapToDouble(o -> o.getAmount().doubleValue()).sum();
        AccountDTO dto = new AccountDTO.AccountDTOBuilder().setId(a.getId())
                .setDateCreated(a.getDateCreated())
                .setPlayerName(a.getPlayerName())
                .setId(a.getId())
                .setSex(a.getSex())
                .setBalance(new BigDecimal(balance))
                .build();
        return dto;
    }

    public static List<AccountDTO> doToDTOList(List<AccountEO> account) {
        return account.stream()
                .map(AccountMapper::doToDTO)
                .collect(Collectors.toList());
    }
}
