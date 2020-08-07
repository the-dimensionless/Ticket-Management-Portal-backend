package com.nagarro.dev.portal.controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nagarro.dev.portal.models.AdminComments;
import com.nagarro.dev.portal.models.LoginForm;
import com.nagarro.dev.portal.models.Ticket;
import com.nagarro.dev.portal.services.AdminCommentsService;
import com.nagarro.dev.portal.services.AdminService;
import com.nagarro.dev.portal.services.TicketService;
import com.nagarro.dev.portal.utils.JwtUtil;

@RestController
@RequestMapping("/admin")
public class AdminsController {
	
	@Autowired
	TicketService ticketService;
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	AdminCommentsService commentService;
	
	@Autowired
	JwtUtil jwtTokenUtil;
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)	// login a User
	public ResponseEntity<?> adminLogin(@RequestBody LoginForm data) {
		return adminService.checkLogin(data);
	}
	
	@PostMapping("/getTickets")
	@ResponseStatus(HttpStatus.OK)
	public List<Ticket> getTickets(@RequestBody LoginForm data) {	// Get all tickets (Works)
		if (adminService.isValid(data)) {
			
			return (ticketService.getAllTickets()).stream().map(x->{
				x.getUser().setListOfTickets(null);
				return x;
			}).collect(Collectors.toList());
		} else {
			return null;
		}
	}
	
	@PostMapping("/getTickets/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Ticket getTicketById(@PathVariable("id") long id, @RequestBody LoginForm data) {	// Get one ticket (Works)
		if (adminService.isValid(data)) {
			return ticketService.getTicketById(id);
		} else {
			return null;
		}
	}
	
	@PostMapping("/updateTicket/{status}/{id}/{admin}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<?> updateTicket(@PathVariable("id") long id, @PathVariable("status") String status, 
			@PathVariable("admin") String admin, @RequestBody LoginForm data) {	// Update a ticket (Works)
		System.out.println("status received is "+status);
		if (adminService.isValid(data)) {
			ticketService.updateTicket(id, status, admin);
			return ResponseEntity.ok().body("Updated");
		} else {
			return ResponseEntity.ok().body("No such User");
		}
	}
	
	@RequestMapping(value="updateTicket/{id}/", method=RequestMethod.POST,
	consumes=MediaType.ALL_VALUE)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<?> addComment(
			@RequestParam("file") MultipartFile file,
			@RequestParam("comments") String comments, 
			@RequestParam("ticketId") Long tid,
			@RequestParam("adminId") Long aid
			) {
		
		System.out.println("We are here");
		
		AdminComments ob = new AdminComments();
		
		ob.setComments(comments);
		ob.setTicketId(tid);
		ob.setFileName(file.getOriginalFilename());
		ob.setAdminId(aid);
		
		try {
			ob.setFile(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		commentService.saveData(ob);
		
		if (file !=null) {
			System.out.println("file received is "+file);
		}
		
		return ResponseEntity.ok().body("Updated");
	}
	
	
}
