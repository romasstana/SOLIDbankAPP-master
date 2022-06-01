package com.example.solidbankapp.Controller;

import com.example.solidbankapp.ACCOUNT.Account;
import com.example.solidbankapp.ACCOUNT.AccountType;
import com.example.solidbankapp.BankCore;
import com.example.solidbankapp.DAO.AccountDAO;
import com.example.solidbankapp.TRANSACTIONS.Transaction;
import com.example.solidbankapp.TRANSACTIONS.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/account")
@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    private AccountDAO accountDAO;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private BankCore bankCore;
    @GetMapping()
    public List<Account> getAllAccounts(@RequestParam String clientId){
        return accountDAO.findAccountsByClientId(clientId);
    }

    @GetMapping("/account_id")
    public Account getAccountById(@RequestParam String clientId, String accountId){
        return accountDAO.getClientAccount(clientId, accountId);
    }
    @PostMapping()
    public String creatNewAccount(@RequestParam String type, String clientId){
        bankCore.createNewAccount(AccountType.valueOf(type), "1");
        return "Account created successfully";
    }
    @GetMapping("/{account_id}/transactions")
    public List<Transaction> getTransactions(@RequestParam String clientId, String accountId){
        return transactionRepository.getAllTransactions(clientId, accountId);
    }

}
