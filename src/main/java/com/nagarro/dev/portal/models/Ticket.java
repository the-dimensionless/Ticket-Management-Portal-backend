package com.nagarro.dev.portal.models;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table (name = "ticket")
public class Ticket {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column (name = "ticket_id")
	private Long ticketId;
	
	@Column (name = "request_type")
	private String requestType;
	@Column (name = "priority")
	private String priority;
	
	@Column (name = "from_location")
	private String fromLocation;
	@Column (name = "to_location")
	private String toLocation;
	
	/*@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class) */ 
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column (name = "start_date")
	public LocalDate startDate;
	@Column (name = "end_date")
	private LocalDate endDate;
	
	@Column (name = "passport_number")
	private String passportNumber;
	@Column (name = "expenses_borne_by")
	private String expenseBorneBy;
	
	@Column (name = "approver_name")
	private String travelApproverName;
	@Column (name = "duration")
	private String durationOfTravel;
	@Column (name = "upper_bound_amount")
	private String upperBoundOnAmount;
	@Column (name = "additional_details")
	private String additionalDetails;
	
	@Column(name="project")
	private String project;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	/*@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class)  */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column (name = "date_created")
	public Date dateCreated;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	/*@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class) */ 
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column (name = "date_updated")
	public Date dateUpdated;
	@Column (name = "status")
	private String status;
	@Column (name = "processed_by")
	private String processedBy;
	
	/*@JsonBackReference*/
	@ManyToOne 
	private Users user;
	
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getDateUpdated() {
		return dateUpdated;
	}
	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProcessedBy() {
		return processedBy;
	}
	public void setProcessedBy(String processedBy) {
		this.processedBy = processedBy;
	}
	public Long getTicketId() {
		return ticketId;
	}
	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getFromLocation() {
		return fromLocation;
	}
	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}
	public String getToLocation() {
		return toLocation;
	}
	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public String getPassportNumber() {
		return passportNumber;
	}
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
	public String getExpenseBorneBy() {
		return expenseBorneBy;
	}
	public void setExpenseBorneBy(String expenseBorneBy) {
		this.expenseBorneBy = expenseBorneBy;
	}
	public String getTravelApproverName() {
		return travelApproverName;
	}
	public void setTravelApproverName(String travelApproverName) {
		this.travelApproverName = travelApproverName;
	}
	public String getDurationOfTravel() {
		return durationOfTravel;
	}
	public void setDurationOfTravel(String durationOfTravel) {
		this.durationOfTravel = durationOfTravel;
	}
	public String getUpperBoundOnAmount() {
		return upperBoundOnAmount;
	}
	public void setUpperBoundOnAmount(String upperBoundOnAmount) {
		this.upperBoundOnAmount = upperBoundOnAmount;
	}
	public String getAdditionalDetails() {
		return additionalDetails;
	}
	public void setAdditionalDetails(String additionalDetails) {
		this.additionalDetails = additionalDetails;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", requestType=" + requestType + ", priority=" + priority
				+ ", fromLocation=" + fromLocation + ", toLocation=" + toLocation + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", passportNumber=" + passportNumber + ", expenseBorneBy=" + expenseBorneBy
				+ ", travelApproverName=" + travelApproverName + ", durationOfTravel=" + durationOfTravel
				+ ", upperBoundOnAmount=" + upperBoundOnAmount + ", additionalDetails=" + additionalDetails
				+ ", project=" + project + ", dateCreated=" + dateCreated + ", dateUpdated=" + dateUpdated + ", status="
				+ status + ", processedBy=" + processedBy + ", user=" + user + "]";
	}
	
	
}
