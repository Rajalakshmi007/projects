package com.ticketresolution.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ticketresolution.controller.ReportGenerator;
import com.ticketresolution.model.Report;

/**
 * Servlet implementation class GenerateStatusWiseReport
 */
@WebServlet("/GenerateStatusWiseReportServlet")
public class GenerateStatusWiseReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String referrer = request.getParameter("htmlname");
		List<Report> reports = null;
		String message = null;
		ReportGenerator reportGenerator = new ReportGenerator();
		switch (referrer) {
		case "StatusReport":
			String status = request.getParameter("status");
			reports = reportGenerator.generateStatuswiseReport(status);
			break;
		case "PriorityWiseReport":
			String priority = request.getParameter("priority");
			reports = reportGenerator.generatePrioritywiseReport(priority);
			break;
			
		case "IssueTypeWise":
			String issuetype = request.getParameter("issueType");
			reports = reportGenerator.generateIssueTypewiseReport(issuetype);
			break;
			
		case "DateWiseReport":
			String datecreated = request.getParameter("datecreated");
			reports = reportGenerator.generateDateWiseReport(datecreated);
			break;
			
		case "AssigneeWiseReport":
			String assignee = request.getParameter("assignee");
			reports = reportGenerator.generateIssueAssigneewiseReport(assignee);
			break;
			
		case "UserWiseReport":
			String username = request.getParameter("username");
			reports = reportGenerator.generateUserwiseReport(username);
			break;

		}
		if(reports.size() == 0) {
			message = "No reports exist for the search";
		}
		try {
			reportGenerator.closeDBConnection();
		} catch (SQLException e) {
			message = e.getMessage();
			e.printStackTrace();
		}
		request.setAttribute("reportList", reports);
		request.setAttribute("message", message);
		request.getRequestDispatcher("reports.jsp").forward(request, response);
		//generateReport(request, response, reports);
	}

	public void generateReport(HttpServletRequest request, HttpServletResponse response, List<Report> reports)
			throws IOException {

		String htmlResponse = "<html>";
		htmlResponse += "<head>";
		htmlResponse += "<style>";
		htmlResponse += "body{background-image: url(img/rainbow.jpg);background-size: cover;}";
		htmlResponse += "table {width:100%;}";
		htmlResponse += "table, th, td {border: 1px solid black; border-collapse: collapse;}";
		htmlResponse += "th, td {padding: 5px;text-align: left;}";
		htmlResponse += "th {background-color: black;color: white; border:1px solid white;}";
		htmlResponse += "</style>";
		htmlResponse += "</head>";
		htmlResponse += "<body>";
		htmlResponse += "<h1 id=\"system\" style=\"color:blue;text-align: center;\">TICKET RESOLUTION SYSTEM</h1>";
		htmlResponse += "<table>";
		htmlResponse += "<tr>";
		htmlResponse += "<th>EmployeeID</th>";
		htmlResponse += "<th>IssueID</th>";
		htmlResponse += "<th>DateCreated</th>";
		htmlResponse += "<th>TargetResolutionDate</th>";
		htmlResponse += "<th>DateResolved</th>";
		htmlResponse += "<th>AssignedTo</th>";
		htmlResponse += "<th>TimeTaken</th>";
		htmlResponse += "<th>Priority</th>";
		htmlResponse += "<th>Status</th>";
		htmlResponse += "<th>IssueType</th>";
		htmlResponse += "<th>IssueItem</th>";
		htmlResponse += "<th>IssueDescription</th>";
		htmlResponse += "<th>Solution</th>";
		htmlResponse += "</tr>";
		for (Report report : reports) {
			htmlResponse += "<tr>";
			htmlResponse += "<td>" + report.getIssuetracker().getEmpID() + "</td>";
			htmlResponse += "<td>" + report.getIssuetracker().getIssueID() + "</td> ";
			htmlResponse += "<td>" + report.getIssuetracker().getDateCreated() + "</td>";
			htmlResponse += "<td>" + report.getIssuetracker().getTargetResolutionDate() + "</td>";
			htmlResponse += "<td>" + report.getIssuetracker().getDateResolved() + "</td>";
			htmlResponse += "<td>" + report.getIssuetracker().getAssignedTo() + "</td>";
			htmlResponse += "<td>" + report.getIssuetracker().getTimeTaken() + "</td>";
			htmlResponse += "<td>" + report.getIssuetracker().getPriority() + "</td>";
			htmlResponse += "<td>" + report.getIssuetracker().getStatus() + "</td>";
			htmlResponse += "<td>" + report.getIssue().getIssueType() + "</td>";
			htmlResponse += "<td>" + report.getIssue().getIssueItem() + "</td>";
			htmlResponse += "<td>" + report.getIssue().getIssueDescription() + "</td>";
			htmlResponse += "<td>" + report.getSolutions().toString() + "</td>";
			htmlResponse += "</tr>";
		}
		htmlResponse += "</table>";
		htmlResponse += "</body>";
		htmlResponse += "</html>";
		response.getWriter().println(htmlResponse);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
