package com.nagarro.dev.portal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.dev.portal.models.AdminComments;
import com.nagarro.dev.portal.repositories.AdminCommentsRepository;

@Service
public class AdminCommentsServiceImpl implements AdminCommentsService {
	
	@Autowired
	private AdminCommentsRepository repo;
	
	public AdminComments saveData(AdminComments data) {
		return repo.save(data);
	}
	
	public List<AdminComments> getComments(Long id) {
		return repo.findByTicketId(id);
	}

}
