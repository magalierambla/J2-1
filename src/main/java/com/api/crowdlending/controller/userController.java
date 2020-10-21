package com.api.crowdlending.controller;


import java.awt.PageAttributes.MediaType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.crowdlending.model.user;

import com.api.crowdlending.model.adminstrateur;

import com.api.crowdlending.repository.userRepository;

import com.api.crowdlending.model.contactModel;
import com.api.crowdlending.model.project;
import com.api.crowdlending.repository.contactVisitorRepository;

import com.api.crowdlending.functionsUtils.*;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class userController {
	
    @Autowired
    userRepository userRepository;
    
    @Autowired
    contactVisitorRepository  _contactVisitorRepository;
    
    
 
    
    @PostMapping(value = "/visitor/createMessageContact") 
    @ResponseBody
    public ResponseEntity<contactModel> createMessageContact(@RequestBody  contactModel  newMessage) {
 	   
    	System.out.println("newMessage="+ newMessage.toString());
 	   
 	    String  newToken = methodesUtils.generateAlphanumericStringToken();   	
 	   
  	
 	   newMessage.setToken(newToken);
 	    
 	   
        return ResponseEntity.ok(_contactVisitorRepository.save(newMessage));
        
       // return ResponseEntity.ok().build();
     }
    
    @PostMapping("/admin/listMessagesContact")   
    public ResponseEntity<List<contactModel>> getAllMessagesContact(@RequestBody  adminstrateur  infosAdmin) { 
    	
    	List<contactModel> messages = _contactVisitorRepository.findAll();
    	
    	return ResponseEntity.ok(messages);  
        
    }



   @PostMapping(value = "/users/create") 
   @ResponseBody
   public ResponseEntity<user> createUser(@RequestBody  user newUser) throws NoSuchAlgorithmException, IOException, JSONException {  
	   
	    
	    user userCheck = userRepository.checkExistMailUser(newUser.getLogin());
	    
	    if(userCheck == null) {
	    	
	    	System.out.println("user-non-BDD");  
	    	
	    	 /******************************************************************************************/
	    	
	    	 String  newToken = methodesUtils.generateAlphanumericStringToken();    
	    		
	  	   
	 	    user userBdd = new user();
	 	   
	 	    userBdd.setDateNaissance(newUser.getDateNaissance());        
	 	    
	 	    userBdd.setNom(newUser.getNom());
	 	    
	 	    userBdd.setPrenom(newUser.getPrenom());
	 	    
	 	    userBdd.setSex(newUser.getSex());
	 	    
	 	    userBdd.setPhotoUser(newUser.getPhotoUser());
	 	    
	 	    userBdd.setLogin(newUser.getLogin());
	 	    
	 	    userBdd.setPassword(methodesUtils.getMD5Hex(newUser.getPassword()));
	 	    
	 	    userBdd.setToken(newToken);  
	 	    
	 	    userBdd.setTypeCompte(newUser.getTypeCompte());
	 	    
	 	    userBdd.setPseudo_name(newUser.getPseudo_name());
	 	    
	 	    System.out.println("newUser.getNom()="+ newUser.getNom());  
	 	    
	 	   
	    	
		    /**************************confirmation-inscription **************************************/
		    
		         String  token_confir_register = methodesUtils.generateAlphanumericStringToken();   
		          
		          Instant instant = Instant.now();
		  		
		  	   	  long timestamp_expire_token_confirm_register = instant.toEpochMilli() + 86400000;
		  	   	  
		  	   	  
		  	      userBdd.setTimestamp_expire_token_confirm_register(timestamp_expire_token_confirm_register);
		  	      
		          
		          userBdd.setToken_confir_register(token_confir_register);          
		          
		    
		          String USER__AGENT = "Mozilla/5.0";
			
		  	 	  String url = "https://mail-server-api.herokuapp.com/api/sendMailConfirmationInscription?token="+token_confir_register + "&email=" + newUser.getLogin();

		          URL obj = new URL(url);
		          
		          HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		       
		          con.setRequestMethod("GET");
		         
		          con.setRequestProperty("User-Agent", USER__AGENT);

		          int responseCode = con.getResponseCode();
		          
		          System.out.println("\nSending 'GET' request to URL : " + url);
		          
		          System.out.println("Response Code : " + responseCode);

		          BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		          
		          String inputLine;
		          
		          StringBuffer response = new StringBuffer();

		          while ((inputLine = in.readLine()) != null) {
		          	
		              response.append(inputLine);
		          }
		         
		  		
		  		JSONObject resp = new JSONObject(response.toString());
		  		
		  		if((boolean) resp.get("resMail")) {
		  			
		  			System.out.println(resp.get("resMail"));
		  		}       
		  		
		  		
		  		 in.close();  
		    
		    
		        /****************************************************************/
		    
	    	return new ResponseEntity<>(userRepository.save(userBdd), HttpStatus.OK);
	    	
	    	
	    	
	    }
	    
	    
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  		 
	  	
    }
    
    @PostMapping("/users/checkUser")
    @ResponseBody
    public ResponseEntity<user> checkUser(@Valid @RequestBody  user infosUser) throws NoSuchAlgorithmException{   	 
    	
    	
    	
 	    
 	    System.out.println("infosUser.login="+ infosUser.getLogin());     
 	    
 	    System.out.println("infosUser.password="+ infosUser.getPassword());   
 	    
 	    String passwordMd5 = methodesUtils.getMD5Hex(infosUser.getPassword());   
 	   
        return ResponseEntity.ok(userRepository.getUserByEmailAndPassword(infosUser.getLogin(),passwordMd5));
        
 	   
     }
    
    @PostMapping("/users/checkExistMailUser")
    @ResponseBody
    public ResponseEntity<user> checkExistMailUser(@Valid @RequestBody  user infosUser) {   	   
 	  
 	    
 	    System.out.println("infosUser.login="+ infosUser.getLogin());  
 	    
 	 
 	    
 	   
        return ResponseEntity.ok(userRepository.checkExistMailUser(infosUser.getLogin()));
        
 	   
     }
    
    @PutMapping(value = "/users/update") 
    @ResponseBody
    public ResponseEntity<user>  updateUser(@RequestBody  user updateUser) throws NoSuchAlgorithmException {	   
 	   
    	 System.out.println("infosUser.token="+ updateUser.getToken()); 
    	 
    	 Optional<user>  userBdd = userRepository.checkExistUserByToken(updateUser.getToken());   	 
    	 
    	 if(userBdd.isPresent()) { 
    		 
    		 user _user = userBdd.get();
    		 
    		 _user.setDate_naissance(updateUser.getDateNaissance());
    		 
    		 _user.setDate_update(updateUser.getDate_update());
    		 
    		 _user.setNom(updateUser.getNom());
    		 
    		 _user.setPrenom(updateUser.getPrenom());
    		 
    		 _user.setPhotoUser(updateUser.getPhotoUser());
    		 
    		 _user.setSex(updateUser.getSex());
    		 
    		 _user.setPassword(methodesUtils.getMD5Hex(updateUser.getPassword()));
    		 
    		 _user.setLogin(updateUser.getLogin());
    		 
    		 _user.setPseudo_name(updateUser.getPseudo_name());
    		 
    		 System.out.println("userbdd exist"+_user.getNom()); 
    		 
    		 return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
    		 
    	 }else {
    		 
    		 System.out.println("non-userbdd exist"); 
    		 
    		 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	 }  
 	 
    	
    	
     }
    
    @PostMapping(value = "/users/checkConfirmationInscription/{token}") 
    @ResponseBody
    public ResponseEntity<user>  checkConfirmationInscription(@PathVariable String token) throws NoSuchAlgorithmException {	   
 	   
    	 System.out.println("token-scription ="+ token); 
    	 
    	 Instant instant = Instant.now();
	  		
 	   	  long timestamp = instant.toEpochMilli();
    	 
    	 Optional<user>  userBdd = userRepository.checkExistUserByTokenIscription(token);   	 
    	 
    	 if(userBdd.isPresent()) {    		 
    		 
    		 
    		 user _user = userBdd.get();
    		 
    		 long timestamp_expire_token_confirm_register = _user.getTimestamp_expire_token_confirm_register();
    		 
    		 if(timestamp-timestamp_expire_token_confirm_register<=86400000) {
    			 
    			 
    			 _user.setIs_active(1);
        		 
        		 System.out.println("userbdd exist"+_user.getNom()); 
        		 
        		 return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);    			 
    			 
    			 
    		 }else {
    			 
    			 
    			 System.out.println("session-token-expire"); 
        		 
        		 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    			 
    		 }
    		 
    		 
    		 
    	 }else {
    		 
    		 System.out.println("non-userbdd exist"); 
    		 
    		 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	 }  
 	 
    	
    	
     }


    
  

}
