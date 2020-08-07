package com.nagarro.dev.portal.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.dev.portal.models.Ticket;
import com.nagarro.dev.portal.repositories.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	TicketRepository ticketRepository;
	
	public Ticket getTicketById(Long id) {
		return ticketRepository.findById(id).orElse(null);
	}
	
	public void updateTicket(Long id, String status, String admin) {
		Ticket t = ticketRepository.getOne(id);
		t.setStatus(status);
		t.setProcessedBy(admin);
		ticketRepository.save(t);
	}
	
	public List<Ticket> getAllTickets() {
		return (List<Ticket>)ticketRepository.findAll();
	}
	
	
}
