package com.ticketresolution.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ticketresolution.controller.InvalidInputException;
import com.ticketresolution.controller.IssueTrackerController;
import com.ticketresolution.model.Issue;
import com.ticketresolution.model.IssueTracker;

/**
 * Servlet implementation class AddIssue
 */
@WebServlet("/AddIssueServlet")
public class AddIssueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Issue issue = new Issue();
		IssueTracker issuetracker = new IssueTracker();
		String message = null;
		List<String> solutions = new ArrayList<String>();
		issue.setIssueDescription(request.getParameter("issueDescription"));
		issue.setIssueItem(request.getParameter("issueItem"));
		issue.setIssueType(request.getParameter("issueType"));
		issuetracker.setEmpID(request.getParameter("empid"));
		issuetracker.setAssignedTo(request.getParameter("assignedTo"));
		issuetracker.setPriority(request.getParameter("priority"));
		issuetracker.setStatus(request.getParameter("status"));
		issuetracker.setDateCreated(request.getParameter("dateCreated"));
		issuetracker.setDateResolved(request.getParameter("dateResolved"));
		System.out.println("servlet date resolved : " + issuetracker.getDateResolved());
		issuetracker.setTargetResolutionDate(request.getParameter("targetResolutionDate"));
		String solution = request.getParameter("solutions");
		solutions.add(solution);
		System.out.println("solutions : " + solutions);
		System.out.println("solutions size : " + solutions);
		IssueTrackerController controller = new IssueTrackerController();
		try {
			message = controller.addIssue(issue,issuetracker,solutions);
		} catch (InvalidInputException e) {
			message = e.getMessage();
		}finally {
			try {
				controller.closeDBConnection();
			} catch (SQLException e) {
				message = "Error while closing the db connection : " + e.getMessage();
				e.printStackTrace();
			}
		}
		if(message.equalsIgnoreCase("success")){    
			message = "Issue added successfully";
			request.setAttribute("message", message);
	        request.getRequestDispatcher("Response.jsp").forward(request, response);
		}  
	    else{  
	    	RequestDispatcher requestDisapatcher=request.getRequestDispatcher("AddIssue.html");  
	    	response.getWriter().println("<p style=\"color:red;margin-top:10px;text-align:center;\">*Could not add issue. "+message+"*</p>");
	    	requestDisapatcher.include(request, response);  
	        }  	
	}

}
