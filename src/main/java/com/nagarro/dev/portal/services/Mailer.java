package com.nagarro.dev.portal.services;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;


public class Mailer {
	
	@Autowired
    private JavaMailSender sender;
 
    public String forgotPasscode(String email, String username, String fname, String password) {
    	  String msg = "Hi "+fname+ " as per your request for details to access Nagarro Travel Portal"
          		+ "\n\nHere is your Username:"+username+"\nHere is your Password:"+password
          		+ "\n\nPlease contact the Travel team if you have any questions.\n\n"
          		+ "Thank You, \nNagarro Travel Team.";
    	  
    	  String subject = "Nagarro Travel Portal Forgot Password";
    	
    	try {
    		this.sendEmail(email, username, fname, password, msg, subject);
    		return "Sent Successfully";
    	} catch (Exception ex) {
    		return "Error in sending mail:" + ex;
    	}
    }
    
    public String initUsername(String email, String username, String fname, String password) {
    	
    	System.out.println("in mailer");
    	 String msg = "Hi "+fname+ ", you have successfully registered with us."
           		+ "\n\nHere is your Username:"+username+"\nHere is your initial Password:"+password
           		+ "\n\nPlease contact the Travel team if you have any questions.\n\n"
           		+ "Thank You, \nNagarro Travel Team.";
     	  
     	  String subject = "Nagarro Travel Portal New User Registration";
     	
     	try {
     		this.sendEmail(email, username, fname, password, msg, subject);
     		return "Sent Successfully";
     	} catch (Exception ex) {
     		return "Error in sending mail:" + ex.getStackTrace();
     	}
    }
    
    private void sendEmail(String email, String username, String fname, String password, String msg, String subject) throws Exception{
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
         
        helper.setTo(email);
        helper.setText(msg);
        helper.setSubject(subject);
         
        sender.send(message);
    }
    

}
