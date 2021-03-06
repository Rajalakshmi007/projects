package com.ticketresolution.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.ticketresolution.controller.ReportGenerator;
import com.ticketresolution.model.Report;

@Path("/GenerateReportService")
public class ReportGenerationService {

	ReportGenerator reportGenerator = new ReportGenerator();
	
	@GET
	@Path("/statusReport")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Report> getStatusWiseReport(@QueryParam("status") String status) {
		List<Report> reports = reportGenerator.generateStatuswiseReport(status);
		return reports;
	}
	
	@GET
	@Path("/userReport")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Report> getUserWiseReport(@QueryParam("user") String user) {
		List<Report> reports = reportGenerator.generateUserwiseReport(user);
		return reports;
	}
	
	@GET
	@Path("/priorityReport")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Report> getPriorityWiseReport(@QueryParam("priority") String priority) {
		List<Report> reports = reportGenerator.generatePrioritywiseReport(priority);
		return reports;
	}
	
	@GET
	@Path("/assigneeReport")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Report> getAssigneeWiseReport(@QueryParam("assignee") String assignee) {
		List<Report> reports = reportGenerator.generateIssueAssigneewiseReport(assignee);
		return reports;
	}
	
	@GET
	@Path("/issueTypeReport")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Report> getIssueTypeWiseReport(@QueryParam("issueType") String issueType) {
		List<Report> reports = reportGenerator.generateIssueTypewiseReport(issueType);
		return reports;
	}
}
