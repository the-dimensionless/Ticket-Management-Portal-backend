package com.nagarro.dev.portal.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginForm {
		private String username;
	    private String password;
	    
	    public LoginForm() {
	    	
	    }
		public LoginForm(String username, String password) {
			this.username = username;
			this.password = password;
		}
		
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPass() {
			return password;
		}
		public void setPass(String pass) {
			this.password = pass;
		}
}
