package com.ticket.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "report") 
public class Report implements Serializable{

	private Issue issue;
	private List<Solution> solutions;
	private IssueTracker issuetracker;
	private Employee employee;

	public Report() {
		issue = new Issue();
		employee = new Employee();
		solutions = new ArrayList<Solution>();
		issuetracker = new IssueTracker();
	}

	@XmlElement
	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	@XmlElement
	public List<Solution> getSolutions() {
		return solutions;
	}

	public void setSolutions(List<Solution> solutions) {
		this.solutions = solutions;
	}

	@XmlElement
	public IssueTracker getIssuetracker() {
		return issuetracker;
	}

	public void setIssuetracker(IssueTracker issuetracker) {
		this.issuetracker = issuetracker;
	}

	@XmlElement
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getSolution() {
		String solutionMsg = "[ ";
		for (Solution solution : solutions) {
			solutionMsg += " , " + solution.getSolutions();
		}
		solutionMsg += " ]";
		return solutionMsg;
	}

	@Override
	public String toString() {
		String reportMsg = employee.getEmpID() + "\t" + employee.getUserName() + "\t" + issuetracker.getIssueID() + "\t"
				+ issuetracker.getDateCreated() + "\t" + issuetracker.getTargetResolutionDate() + "\t"
				+ issuetracker.getDateResolved() + "\t" + issuetracker.getAssignedTo() + "\t"
				+ issuetracker.getTimeTaken() + "\t" + issuetracker.getPriority() + "\t" + issuetracker.getStatus()
				+ "\t" + issue.getIssueType() + "\t" + issue.getIssueItem() + "\t" + issue.getIssueDescription() + "\t"
				+ getSolution();
		return reportMsg;
	}

}
