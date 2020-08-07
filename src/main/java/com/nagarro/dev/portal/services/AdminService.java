package com.nagarro.dev.portal.services;

import org.springframework.http.ResponseEntity;

import com.nagarro.dev.portal.models.LoginForm;

public interface AdminService {
	
	public boolean isValid(LoginForm data);
	
	public ResponseEntity<?> checkLogin(LoginForm data);

}
