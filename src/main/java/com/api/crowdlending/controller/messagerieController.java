package com.api.crowdlending.controller;

import java.awt.PageAttributes.MediaType;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.crowdlending.model.user;
import com.api.crowdlending.model.vueProjectModel;
import com.api.crowdlending.model.adminstrateur;

import com.api.crowdlending.repository.userRepository;

import com.api.crowdlending.model.contactModel;
import com.api.crowdlending.model.likeDislikeProjectModel;
import com.api.crowdlending.model.messageModel;
import com.api.crowdlending.model.project;
import com.api.crowdlending.repository.contactVisitorRepository;
import com.api.crowdlending.repository.messageRepository;
import com.api.crowdlending.functionsUtils.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class messagerieController {
	
    @Autowired
    messageRepository  _messageRepository;
    
    @PostMapping(value = "users/{token_user}/messages/create") 
    @ResponseBody
    public ResponseEntity<messageModel>  createMessageByUser(@PathVariable String token_user , @RequestBody   messageModel  _message) {	
 	   
 	   
 	  
        
       return ResponseEntity.ok().build();
 	   
 	   
     }    
   
    
    @PostMapping(value = "users/{token_user}/messages_recus/all")   
    @ResponseBody
    public ResponseEntity<List<messageModel>> getAllMessagesRecus(@PathVariable String token_user , @RequestBody user _user) { 
    	
    	 System.out.println("token-user="+ _user.getToken());
    	
    	 String tokenUser = (String)  _user.getToken();
    	
    	List<messageModel> messages=  _messageRepository.findAllMessagesRecusByTokenUser(tokenUser);    	
    	
    	
    	 if(messages.size()>0) { 
    		 
    		 for(int index=0;index<messages.size();index++) {
 	    		
 	    		  		
 	    		
 	    		
 	    	}   
    		
    		return ResponseEntity.ok(messages);  
    		
    	}
    	
    	 return new ResponseEntity<>(HttpStatus.NOT_FOUND);   	 
    	
    	
        
    }
    
    @PostMapping(value = "users/{token_user}/messages_envoyes/all")   
    @ResponseBody
    public ResponseEntity<List<messageModel>> getAllMessagesEnvoyes(@PathVariable String token_user , @RequestBody user _user) { 
    	
    	 System.out.println("token-user="+ _user.getToken());
    	
    	 String tokenUser = (String)  _user.getToken();
    	
    	List<messageModel> messages=  _messageRepository.findAllMessagesEnvoyesByTokenUser(tokenUser);    	
    	
    	
    	 if(messages.size()>0) { 
    		 
    		 for(int index=0;index<messages.size();index++) {
 	    		
 	    		  		
 	    		
 	    		
 	    	}   
    		
    		return ResponseEntity.ok(messages);  
    		
    	}
    	
    	 return new ResponseEntity<>(HttpStatus.NOT_FOUND);   	 
    	
    	
        
    }
    
    
    @PostMapping(value = "users/{token_user}/messages/{token_message}")   
    @ResponseBody
    public ResponseEntity<messageModel> getDataMessage(@PathVariable String token_user ,@PathVariable String token_message,  @RequestBody user _user) {     	
       
    	
    	 Optional<messageModel>  message = _messageRepository.findMessageByToken(token_message);       	
    	
    	
    	 if(message.isPresent()) { 
    		 
    		 messageModel messageBdd = message.get();  	 	 
    		 
    		 
    		 return ResponseEntity.ok(messageBdd);  
    	 } 	
    		
    	
    	
    	 return new ResponseEntity<>(HttpStatus.NOT_FOUND);   	 
    	
    	
        
    }  
    
    
    @PostMapping(value = "users/{token_user}/messages/{token_message}/update")   
    @ResponseBody
    public ResponseEntity<messageModel> updateDataMessage(@PathVariable String token_user ,@PathVariable String token_message,  @RequestBody messageModel _message) {     	
       
    	
    	 Optional<messageModel>  _messageOptional =   _messageRepository.findMessageByToken(token_message);   	
    	
    	
    	 if(_messageOptional.isPresent()) { 
    		 
    		 messageModel messageBdd = _messageOptional.get();  
    		 
    		 
    		 messageBdd.setDateConsultation(_message.getDateConsultation());
    		 
    		 messageBdd.setTimestampConsultation(_message.getTimestampConsultation());
    		 
    		 messageModel messageBddUpdate =_messageRepository.save(messageBdd);
    		 
    		 
    		 return ResponseEntity.ok(messageBddUpdate);  
    	 } 	
    		
    	
    	
    	 return new ResponseEntity<>(HttpStatus.NOT_FOUND);   	 
    	
    	
        
    }  
    
    
    @PostMapping(value = "users/{token_user}/list_messages_non_lus")   
    @ResponseBody
    public ResponseEntity<List<messageModel>> getListMessagesNonLus(@PathVariable String token_user ,  @RequestBody user _user) {     	
       
    	
    	 String tokenUser = (String)  _user.getToken();	
    	
    	
    	 List<messageModel> messages=  _messageRepository.findAllMessagesNonLusByTokenUser(tokenUser);    
    	 
          if(messages.size()>0) {    		 
    		
    		
    		return ResponseEntity.ok(messages);  
    		
    	}
    		
    	
    	
    	 return new ResponseEntity<>(HttpStatus.NOT_FOUND);   	 
    	
    	
        
    }  
    
    
    @PostMapping(value = "users/{token_user}/count_messages_non_lus")   
    @ResponseBody
    public ResponseEntity<?> countMessagesNonLus(@PathVariable String token_user, @RequestBody user _user) {     	
       
    	
    	
    	String tokenUser = (String)  _user.getToken();	
    	
    	int  nbrMessagesNonLus =   _messageRepository.countMessageNonLusByTokenUser(tokenUser);   	
    	
    	
    	return ResponseEntity.ok(nbrMessagesNonLus);  
    		
    	
    	
    	 //return new ResponseEntity<>(HttpStatus.NOT_FOUND);   	 
    	
    	
        
    }  
    
    


    
  

}
