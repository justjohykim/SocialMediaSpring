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
    public SocialMediaController(AccountService accountService,MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
    } 

    
    // public SocialMediaController(MessageService messageService){
    //     this.messageService = messageService;
    // }


    @PostMapping("register")
    public ResponseEntity<Account> createAccount(@RequestBody Account account){

        
        if(!account.getUsername().isBlank() && account.getPassword().length() >= 4 ){
            return accountService.saveAccount(account);
        }
        else{
            return ResponseEntity.status(400).build();
        }
        
    }

    @PostMapping("login")
    public ResponseEntity<Account> login(@RequestBody Account account){
        return accountService.loginAccount(account);
    }

    @PostMapping("messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message){
        // List<Account> allAccounts = accountRepository.findAll();
        // Boolean within = false;
        // int posted = message.getPosted_by();
        // int account_id = 0;
        // System.out.println(posted);
        // for(int i = 0; i< allAccounts.size(); i++){
        //     account_id = allAccounts.get(i).getAccount_id();
        //     System.out.println(account_id);
        //     if(posted == account_id){
        //         within = true;
        //     }
        // }
        if(message.getMessage_text().length() > 255 || message.getMessage_text().isBlank()){
            return ResponseEntity.status(400).build();
            
        }
        return messageService.saveMessage(message);
        
    }
    @GetMapping("messages")
    public List<Message> getAllMessages(){
        return messageService.getAllMessages();
    }

    @GetMapping("/messages/{message_id}")
    public ResponseEntity<Message> getMessageByMessageId(@PathVariable int message_id){
        Message message = messageService.getMessageByMessageId(message_id);
        if(message != null){
            return new ResponseEntity<Message>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @DeleteMapping("/messages/{message_id}")
    public ResponseEntity<Integer> deleteMessageByMessageId(@PathVariable int message_id){
        int returned = messageService.deleteMessageByMessageId(message_id);
        if(returned > 0 ){
            return new ResponseEntity<>(returned, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    @PatchMapping("/messages/{message_id}")
    public ResponseEntity<Integer> updateMessagebyMessageId(@PathVariable int message_id, @RequestBody Message message_text){
        int returned = messageService.updateMessagebyMessageId(message_id,message_text);
        if(returned > 0 ){
            return new ResponseEntity<>(returned, HttpStatus.OK);
        }
        else{
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("/accounts/{account_id}/messages")
    public List<Message> getAllMessagesbyAccount(@PathVariable int account_id){
        List<Message> allMessages = messageService.getAllMessagesbyAccount(account_id);
        return allMessages;
    }

}
