package com.nagarro.dev.portal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nagarro.dev.portal.models.Admin;
import com.nagarro.dev.portal.models.ErrorResponse;
import com.nagarro.dev.portal.models.LoginForm;
import com.nagarro.dev.portal.repositories.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	AdminRepository adminRepo;
	
	@Autowired
	PasswordEncoder enc;
	
	public boolean isValid(LoginForm data) {
		Admin admin;
		try {
			admin = adminRepo.findByEmail(data.getUsername());
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		
		if (admin != null && admin.getPassword().equals(data.getPass())) {
			return true;
		} else {
			return false;
		}
	}
	
	public ResponseEntity<?> checkLogin(LoginForm data) {
		if (this.isValid(data)) {
			//instead of data
			return ResponseEntity.ok().body(adminRepo.findByEmail(data.getUsername()));
		} else {
			return ResponseEntity.ok(new ErrorResponse("No Such Admin"));
		}
	}

}
