package com.ticketresolution.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "solution") 
public class Solution implements Serializable{

	private String solutionID;
	private String issueID;
	private String solutions;

	public Solution() {

	}

	public Solution(String solutionID, String issueID, String solutions) {
		this.solutionID = solutionID;
		this.issueID = issueID;
		this.solutions = solutions;
	}
	@XmlElement
	public String getSolutionID() {
		return solutionID;
	}

	public void setSolutionID(String solutionID) {
		this.solutionID = solutionID;
	}
	@XmlElement
	public String getIssueID() {
		return issueID;
	}

	public void setIssueID(String issueID) {
		this.issueID = issueID;
	}
	@XmlElement
	public String getSolutions() {
		return solutions;
	}

	public void setSolutions(String solutions) {
		this.solutions = solutions;
	}

	@Override
	public String toString() {
		return "Solution [solutionID=" + solutionID + ", issueID=" + issueID + ", solutions=" + solutions + "]";
	}

}
