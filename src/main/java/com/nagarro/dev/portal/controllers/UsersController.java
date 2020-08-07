package com.nagarro.dev.portal.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.dev.portal.models.AdminComments;
import com.nagarro.dev.portal.models.LoginForm;
import com.nagarro.dev.portal.models.Ticket;
import com.nagarro.dev.portal.models.Users;
import com.nagarro.dev.portal.repositories.TicketSummary;
import com.nagarro.dev.portal.services.AdminCommentsService;
import com.nagarro.dev.portal.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UsersController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AdminCommentsService commentService;
	
	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)	// Login a User
	public ResponseEntity<?> loginUser(@RequestBody LoginForm data) throws Exception {
		return userService.loginUser(data);
	}
	
	@GetMapping
	public List<Users> list() {		// Get all Users (Works)
		return userService.list();
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)		
	@ResponseStatus(HttpStatus.OK)	
	public ResponseEntity<?> createUser(@RequestBody Users user)  {	// Add a User (Works)
		return userService.createUser(user);
	}
	
	@GetMapping("/{id}")
	public Set<TicketSummary> getUserTickets(@PathVariable("id") long id) {	// Get all user tickets(Works)
		return userService.getUserTickets(id);
	}
	
	@PostMapping("/{uid}")		
	@ResponseStatus(HttpStatus.OK)	
	public void createUserTicket(@PathVariable("uid") long uid, @RequestBody Ticket ticket) {	// Add a user ticket(works)
		userService.createUserTicket(uid, ticket);
	}
	
	@GetMapping("/{uid}/{tid}")
	public Ticket viewUserTicketSolo(@PathVariable("uid") long uid, @PathVariable("tid") long tid) {	// view One user ticket(Works)
		return userService.viewUserTicketSolo(uid, tid);
	}
	
	@PutMapping("/{uid}/{tid}")		
	@ResponseStatus(HttpStatus.OK)	
	public void updateUserTicket(@PathVariable("tid") long tid, @RequestBody Ticket ticket) {	// Update user ticket
		userService.updateUserTicket(tid, ticket);
	}
	
	@PostMapping("/forgotpassword")
	public ResponseEntity<?> sendMail(@RequestBody LoginForm data) {
		return userService.sendMail(data.getUsername());
	}
	
	@GetMapping("/getAdminResponses/{tid}")
	public List<AdminComments> getAdminResponses(@PathVariable("tid") long tid) {
		return commentService.getComments(tid);
	}

}