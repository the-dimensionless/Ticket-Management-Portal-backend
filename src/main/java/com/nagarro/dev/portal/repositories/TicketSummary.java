package com.nagarro.dev.portal.repositories;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TicketSummary {
		private long ticketId;
		private Date dateCreated;
		private String status;
		private String requestType;
		
		public TicketSummary(long id, Date dateCreated, String status, String requestTye) {
			ticketId = id;
			this.dateCreated = dateCreated;
			this.requestType = requestTye;
			this.status = status;
		}
		public long getId() {
			return ticketId;
		}
		public void setId(long id) {
			ticketId = id;
		}
		public Date getDateCreated() {
			return dateCreated;
		}
		public void setDateCreated(Date dateCreated) {
			this.dateCreated = dateCreated;
		}
		public String getRequestTye() {
			return requestType;
		}
		public void setRequestTye(String requestTye) {
			this.requestType = requestTye;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		
	}
