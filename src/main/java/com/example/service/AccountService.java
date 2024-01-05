package com.example.service;
import java.util.List;
import com.example.entity.Account;
import com.example.repository.AccountRepository;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
        // <Optional>Account check_acc = accountRepository.findById(account.getAccount_id());
        // Account acc = check_acc.get();
    //    Boolean duplicate = false; 
    //    List<Account> list = accountRepository.findAll();
    //     for(int i = 0; i< list.size(); i++){
    //         if(list.get(i).getUsername() == account.getUsername()){
    //             System.out.println("this should be printed");
    //             duplicate = true;
    //         }
         Optional<Account> test = accountRepository.findById(account.getAccount_id());
         Account acc = test.get();
         String test_name = acc.getUsername();
        if(test_name == account.getUsername()){
            return ResponseEntity.status(409).build();
        }
        accountRepository.save(account);
        return ResponseEntity.status(200).build();
     
    }
}
