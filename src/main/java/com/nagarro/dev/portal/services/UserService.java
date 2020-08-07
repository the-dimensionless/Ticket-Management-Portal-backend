package com.nagarro.dev.portal.services;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.nagarro.dev.portal.models.LoginForm;
import com.nagarro.dev.portal.models.Ticket;
import com.nagarro.dev.portal.models.Users;
import com.nagarro.dev.portal.repositories.TicketSummary;

public interface UserService {
	int length = 8;
	
	public String passcode();
	
	public ResponseEntity<?> loginUser(LoginForm data) throws Exception;
	
	public List<Users> list();
	
	public ResponseEntity<?> createUser(Users user);
	
	
	
	public Set<TicketSummary> getUserTickets(long id);
	
	public void createUserTicket(long uid, Ticket ticket);
	
	public Ticket viewUserTicketSolo(long uid, long tid);
	
	public void updateUserTicket(long id, Ticket ticket);
	
	
	
	public ResponseEntity<?> sendMail(String username);

}
