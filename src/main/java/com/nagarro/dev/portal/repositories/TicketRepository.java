package com.nagarro.dev.portal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.dev.portal.models.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
