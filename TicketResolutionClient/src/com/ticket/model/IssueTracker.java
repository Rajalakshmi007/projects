package com.ticket.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "issuetracker") 
public class IssueTracker implements Serializable{
	private String empID;
	private String issueID;
	private String dateCreated;
	private String targetResolutionDate;
	private String dateResolved;
	private String assignedTo;
	private String timeTaken;
	private String priority;
	private String status = "NEW";

	public IssueTracker() {

	}

	public IssueTracker(String empID, String issueID, String dateCreated, String targetResolutionDate,
			String dateResolved, String assignedTo, String timeTaken, String priority, String status) {
		this.empID = empID;
		this.issueID = issueID;
		this.dateCreated = dateCreated;
		this.targetResolutionDate = targetResolutionDate;
		this.dateResolved = dateResolved;
		this.assignedTo = assignedTo;
		this.timeTaken = timeTaken;
		this.priority = priority;
		this.status = status;
	}
	@XmlElement
	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}
	@XmlElement
	public String getIssueID() {
		return issueID;
	}

	public void setIssueID(String issueID) {
		this.issueID = issueID;
	}
	@XmlElement
	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	@XmlElement
	public String getTargetResolutionDate() {
		return targetResolutionDate;
	}

	public void setTargetResolutionDate(String targetResolutionDate) {
		this.targetResolutionDate = targetResolutionDate;
	}
	@XmlElement
	public String getDateResolved() {
		return dateResolved;
	}

	public void setDateResolved(String dateResolved) {
		this.dateResolved = dateResolved;
	}
	@XmlElement
	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	@XmlElement
	public String getTimeTaken() {
		return timeTaken;
	}

	public void setTimeTaken(String timeTaken) {
		this.timeTaken = timeTaken;
	}
	@XmlElement
	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}
	@XmlElement
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "IssueTracker [empID=" + empID + ", issueID=" + issueID + ", dateCreated=" + dateCreated
				+ ", targetResolutionDate=" + targetResolutionDate + ", dateResolved=" + dateResolved + ", assignedTo="
				+ assignedTo + ", timeTaken=" + timeTaken + ", priority=" + priority + ", status=" + status + "]";
	}
}
