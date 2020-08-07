package com.nagarro.dev.portal.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "admin")
public class Admin {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long adminId;
	
	@Column (name = "admin_name")
	private String fname;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	/*@OneToOne
	private Users user;*/

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}
