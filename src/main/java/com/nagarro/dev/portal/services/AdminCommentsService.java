package com.nagarro.dev.portal.services;

import java.util.List;

import com.nagarro.dev.portal.models.AdminComments;

public interface AdminCommentsService {
	
	public AdminComments saveData(AdminComments data);
	
	public List<AdminComments> getComments(Long id);

}
