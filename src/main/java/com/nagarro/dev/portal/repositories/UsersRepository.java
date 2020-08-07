package com.nagarro.dev.portal.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nagarro.dev.portal.models.Ticket;
import com.nagarro.dev.portal.models.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
	
	@Query("SELECT distinct new com.nagarro.dev.portal.repositories.TicketSummary(t.ticketId, t.dateCreated, t.status, t.requestType) FROM "
			+ "Users as u join Ticket as t on\r\n" + 
			" t.user.userId = ?1")
	Set<TicketSummary> getTicketsSummary(long id);
	
	@Query("FROM "
			+ "Ticket as t where " + 
			" t.ticketId = ?1 and t.user.userId = ?2")
	Ticket getTicketOneView(long tid, long uid);

	Boolean existsByUsername(String username);
	
    Users findByUsername(String username);
    
}
