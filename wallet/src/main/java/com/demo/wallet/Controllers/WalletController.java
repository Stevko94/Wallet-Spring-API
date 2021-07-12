package com.demo.wallet.Controllers;

import com.demo.wallet.Exceptions.InsufficientBalanceException;
import com.demo.wallet.Exceptions.WalletException;
import com.demo.wallet.Models.Dtos.AccountDTO;
import com.demo.wallet.Models.Dtos.Mapper.AccountMapper;
import com.demo.wallet.Models.Dtos.Mapper.WalletMapper;
import com.demo.wallet.Models.Dtos.WalletDTO;
import com.demo.wallet.Models.Entities.AccountEO;
import com.demo.wallet.Models.Entities.WalletEO;
import com.demo.wallet.Services.AccountService;
import com.demo.wallet.Services.WalletService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/V1")
public class WalletController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private WalletService walletService;

    @ApiOperation(" Returns a list of players created ")
    @GetMapping
    public ResponseEntity getPlayers() {
        List<AccountEO> accounts = accountService.getList();
        return new ResponseEntity<List<AccountDTO>>(AccountMapper.doToDTOList(accounts), HttpStatus.OK);
    }
    @ApiOperation(" Returns Player by id ")
    @GetMapping("/{id}")
    public ResponseEntity getPlayer(@PathVariable("id") Long id) {
        AccountEO account;
        try {
            account = accountService.accountByPK(id);
        } catch (WalletException ex) {
            Logger.getLogger(WalletController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<AccountDTO>(AccountMapper.doToDTO(account), HttpStatus.OK);

    }

    @ApiOperation(" Returns Transactions by player id ")
    @GetMapping("/{id}/transactions")
    public ResponseEntity getTransactionsforPlayer(@PathVariable("id") Long id) {
        List<WalletEO> account;
        try {
            account = walletService.transactionsByAccountID(id);
        } catch (WalletException ex) {
            Logger.getLogger(WalletController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<WalletDTO>>(WalletMapper.doToDTOList(account), HttpStatus.OK);

    }

    @ApiOperation(" Creates new Player ")
    @PostMapping
    public ResponseEntity createPlayer(@RequestBody AccountDTO accountDTO) {
        AccountEO saved;
        try {
            saved = accountService.save(AccountMapper.dtoToDO(accountDTO));
        } catch (WalletException ex) {
            Logger.getLogger(WalletController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<AccountDTO>(AccountMapper.doToDTO(saved), HttpStatus.CREATED);
    }

    @ApiOperation(" Updates player by id ")
    @PutMapping("/{id}")
    public ResponseEntity updatePlayer(@PathVariable("id") Long accountId, @RequestBody AccountDTO accountDTO) {
        AccountEO saved;
        try {
            saved = accountService.update(AccountMapper.dtoToDO(accountDTO), accountId);
        } catch (WalletException ex) {
            Logger.getLogger(WalletController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<AccountDTO>(AccountMapper.doToDTO(saved), HttpStatus.OK);
    }

    @ApiOperation(" Makes player transaction by player id ")
    @PostMapping("/{id}/transactions")
    public ResponseEntity createTransaction(@PathVariable("id")Long accountId,@RequestBody WalletDTO walletDTO) {
        WalletEO saved;
        try {
            walletDTO.setAccountId(accountId);
            saved = walletService.createTransaction(WalletMapper.dtoToDO(walletDTO));
        } catch (WalletException | InsufficientBalanceException ex) {
            Logger.getLogger(WalletController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<WalletDTO>(WalletMapper.doToDTO(saved), HttpStatus.CREATED);
    }

}
