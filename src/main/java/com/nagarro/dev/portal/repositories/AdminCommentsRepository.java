package com.nagarro.dev.portal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.dev.portal.models.AdminComments;

@Repository
public interface AdminCommentsRepository extends JpaRepository<AdminComments, Long> {
	
	List<AdminComments> findByTicketId(Long ticketId);

}