package com.nagarro.dev.portal.services;

import java.util.List;

import com.nagarro.dev.portal.models.Ticket;

public interface TicketService {
	
	public Ticket getTicketById(Long id);
	
	public void updateTicket(Long id, String status, String admin);
	
	public List<Ticket> getAllTickets();

}
