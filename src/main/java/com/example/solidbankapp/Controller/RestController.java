package com.example.solidbankapp.Controller;

import com.example.solidbankapp.ACCOUNT.Account;
import com.example.solidbankapp.ACCOUNT.AccountType;
import com.example.solidbankapp.BankCore;
import com.example.solidbankapp.DAO.AccountDAO;
import com.example.solidbankapp.DEPOSIT.AccountDepositService;
import com.example.solidbankapp.LISTING.AccountListingService;
import com.example.solidbankapp.TRANSACTIONS.Transaction;
import com.example.solidbankapp.TRANSACTIONS.TransactionRepository;
import com.example.solidbankapp.TransactionDeposit;
import com.example.solidbankapp.TransactionWithdraw;
import com.example.solidbankapp.WITHDRAW.AccountWithdrawService;
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
    @Autowired
    private AccountDepositService accountDepositService;
    @Autowired
    private AccountListingService accountListingService;
    @Autowired
    private AccountWithdrawService accountWithdrawService;
    @Autowired
    private TransactionWithdraw transactionWithdraw;
    @Autowired
    private TransactionDeposit transactionDeposit;
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
    public List<Transaction> getTransactions(@PathVariable  String account_id){
        return transactionRepository.getAllTransactions("1", account_id);
    }


    @DeleteMapping("/account_id")
    public String deleteAccount(@RequestParam String accountId){
        accountDAO.deleteAccount(accountId);
        return "Account" + accountId + " Deleted";
    }

    @PostMapping("/{account_id}/withdraw")
    public void postWithdraw(@RequestBody RequestTransaction requestTransaction, @PathVariable("account_id") String accountId){
        transactionWithdraw.execute(accountListingService.getClientWithdrawAccount("1", accountId), requestTransaction.amount);
        System.out.println(requestTransaction.amount + "$ was transferred from " + accountListingService.getClientWithdrawAccount("1", accountId));
    }
    @PostMapping("/{account_id}/deposit")
    public void postDeposit(@RequestBody RequestTransaction requestTransaction, @PathVariable("account_id") String accountId){
        transactionDeposit.execute(accountListingService.getClientAccount("1", accountId), requestTransaction.amount);
        System.out.println(requestTransaction.amount + "$ was transferred to " + accountListingService.getClientAccount("1", accountId));
    }

}
