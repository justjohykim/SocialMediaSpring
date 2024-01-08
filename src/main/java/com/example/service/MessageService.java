package com.example.service;
import java.util.*; 
import com.example.entity.Message;
import com.example.repository.MessageRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    MessageRepository messageRepository;
   

    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }


    public ResponseEntity<Message> saveMessage(Message message){
        List<Message> allMessages = messageRepository.findAll();
        Boolean within = false;
        int posted = message.getPosted_by();
        int account_id = 0;
        System.out.println(posted);
        for(int i = 0; i< allMessages.size(); i++){
            account_id = allMessages.get(i).getPosted_by();
            System.out.println(account_id);
            if(posted == account_id){
                
                within = true;
            }
        }
        if(within){
            Message res = messageRepository.saveAndFlush(message);
            //get message_id
            
            
            return new ResponseEntity<Message>(res,HttpStatus.OK);
           // return ResponseEntity.status(200).build(); 
        }
        return ResponseEntity.status(400).build();        

    }



    public List<Message> getAllMessages(){
        List<Message> allMessages = messageRepository.findAll();
        return allMessages;
    }
    public Message getMessageByMessageId(int message_id){
        Optional<Message> mes = messageRepository.findById(message_id);
        return mes.orElse(null);
    }

    public int deleteMessageByMessageId(int message_id){
        Optional<Message> mes = messageRepository.findById(message_id);
        Message real_mess = mes.orElse(null);

        if(real_mess != null){
            messageRepository.delete(real_mess);
            return 1;
        }
        else{
            return 0;
        }
    }
    public int updateMessagebyMessageId(int message_id,Message message){
        String message_body = message.getMessage_text();
        Optional<Message> mes = messageRepository.findById(message_id);
        Message real_mess = mes.orElse(null);
        if(real_mess != null && message_body.length() < 255 && !message_body.isBlank()){
            real_mess.setMessage_text(message_body);
            messageRepository.saveAndFlush(real_mess);
            return 1;
        }
        else{
            return 0;
        }
    }

    public List<Message> getAllMessagesbyAccount(int posted_by){
        List<Message> allMessages = messageRepository.findAll();
        int account_id = 0;
        List<Message> toReturn = new ArrayList<Message>();
        for(int i =0; i < allMessages.size();i++){
            account_id = allMessages.get(i).getPosted_by();
            if(account_id == posted_by){
                toReturn.add(allMessages.get(i));
            }

        }
        if(toReturn.isEmpty()){
            return List.of();
        }
        else{
            return toReturn;
        }
        
    }


}
