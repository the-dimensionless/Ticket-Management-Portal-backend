package com.nagarro.dev.portal.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.Data;

@Data
@Entity
@Table (name = "users")

public class Users {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column (name = "user_id")
	private long userId;
	
	@Column (name = "username", length = 50, unique = true)
	private String username;
	@Column (name = "password", length = 50)
	private String password;
	
	@Column (name = "email", length = 50)
	private String email;
	
	@Column (name = "first_name", length = 50)
	private String firstName;
	@Column (name = "last_name", length = 50)
	private String lastName;
	
	@Column (name = "business_unit", length = 50)
	private String businessUnit;
	@Column (name = "title", length = 30)
	private String title;
	@Column (name = "telephone", length = 15)
	private String telephone;
	
	@Column (name = "address1", length = 400)
	private String address1;
	@Column (name = "address2", length = 400)
	private String address2;
	@Column (name = "zip_code", length = 10)
	private Long zip;
	
	@Column (name = "city", length = 50)
	private String city;
	@Column (name = "state", length = 50)
	private String state;
	@Column (name = "country", length = 50)
	private String country;
	
	/*@JsonManagedReference*/
	@OneToMany (cascade = CascadeType.ALL,  orphanRemoval = true, fetch = FetchType.EAGER)
	Set<Ticket> listOfTickets;
	
	public long getId() {
		return userId;
	}
	public void setId(long id) {
		this.userId = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getBusinessUnit() {
		return businessUnit;
	}
	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public Long getZip() {
		return zip;
	}
	public void setZip(Long zip) {
		this.zip = zip;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public Set<Ticket> getListOfTickets() {
		return listOfTickets;
	}
	public void setListOfTickets(Set<Ticket> listOfTickets) {
		this.listOfTickets = listOfTickets;
	}
	public void addTicket(Ticket ticket) {
		
		this.listOfTickets.add(ticket);
	}
	@Override
	public String toString() {
		return "Users [userId=" + userId + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", businessUnit=" + businessUnit + ", title="
				+ title + ", telephone=" + telephone + ", address1=" + address1 + ", address2=" + address2 + ", zip="
				+ zip + ", city=" + city + ", state=" + state + ", country=" + country + ", listOfTickets="
				+ listOfTickets + "]";
	}
	
	
}
	