package com.ticketresolution.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "issue") 
public class Issue implements Serializable{

	private String issueID;
	private String issueType;
	private String issueDescription;
	private String issueItem;

	public Issue() {

	}

	public Issue(String issueID, String issueType, String issueDescription, String issueItem) {
		this.issueID = issueID;
		this.issueType = issueType;
		this.issueDescription = issueDescription;
		this.issueItem = issueItem;
	}
	@XmlElement
	public String getIssueID() {
		return issueID;
	}

	public void setIssueID(String issueID) {
		this.issueID = issueID;
	}
	@XmlElement
	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}
	@XmlElement
	public String getIssueDescription() {
		return issueDescription;
	}

	public void setIssueDescription(String issueDescription) {
		this.issueDescription = issueDescription;
	}
	@XmlElement
	public String getIssueItem() {
		return issueItem;
	}

	public void setIssueItem(String issueItem) {
		this.issueItem = issueItem;
	}

	@Override
	public String toString() {
		return "Issue [issueID=" + this.issueID + ", issueType=" + this.issueType + ", issueDescription="
				+ this.issueDescription + ", issueItem=" + this.issueItem + "]";
	}

}
