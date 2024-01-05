package com.example.controller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import com.example.service.AccountService;
import com.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */


@RestController
public class SocialMediaController {

    AccountService accountService;
    MessageService messageService;

    AccountRepository accountRepository;
    MessageRepository messageRepository;

    @Autowired
    public SocialMediaController(AccountService accountService){
        this.accountService = accountService;
    } 




    @PostMapping("register")
    public ResponseEntity<Account> createAccount(@RequestBody Account account){
        if(!account.getUsername().isBlank() && account.getPassword().length() >= 4 ){
            return accountService.saveAccount(account);
        }
        else{
            return ResponseEntity.status(400).build();
        }
        

        
    }

}
