package com.example.service;
import java.util.List;
import com.example.entity.Account;
import com.example.repository.AccountRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class AccountService {

    AccountRepository accountRepository;
    
     @Autowired
     public AccountService(AccountRepository accountRepository){
         this.accountRepository = accountRepository;
     }

     public ResponseEntity<Account> saveAccount(Account account){
        List<Account> allAccounts = accountRepository.findAll();
        Boolean within = false;
        System.out.println(account.getUsername());
        for(int i = 0; i < allAccounts.size(); i++){


            if(allAccounts.get(i).getUsername().equals(account.getUsername())){
                within = true;
            }
        }
        
        if(within){
            return ResponseEntity.status(409).build();
        }
        accountRepository.saveAndFlush(account);    
        return ResponseEntity.status(200).build();
    }

    public ResponseEntity<Account> loginAccount(Account account){
        String user = account.getUsername();
        String pass = account.getPassword();
        String realUser = "";
        String realPass = "";
        List<Account> allAccounts = accountRepository.findAll();
        Boolean within = false;
        Account returnAcc = new Account(null, user, pass);
        //  System.out.println(account.getUsername());
        //  System.out.println(account.getPassword());
        for(int i = 0; i < allAccounts.size();i++){
            realPass = allAccounts.get(i).getPassword();
            realUser = allAccounts.get(i).getUsername();
   
            if(realPass.equals(pass) && realUser.equals(user)){
                returnAcc.setAccount_id(allAccounts.get(i).getAccount_id());
                within = true;
            }
        }
        
        if(within){
            
            return new ResponseEntity<Account>(returnAcc,HttpStatus.OK);
        }
        return ResponseEntity.status(401).build();
    }




}

