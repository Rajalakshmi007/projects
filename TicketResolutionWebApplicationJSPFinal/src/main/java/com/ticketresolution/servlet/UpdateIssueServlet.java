package com.ticketresolution.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ticketresolution.controller.InvalidInputException;
import com.ticketresolution.controller.IssueTrackerController;

/**
 * Servlet implementation class UpdateIssueServlet
 */
@WebServlet("/UpdateIssueServlet")
public class UpdateIssueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String empId = request.getParameter("empId");
		String issueID = request.getParameter("issueID");;
		String status = request.getParameter("status");;
		String dateCreated = request.getParameter("dateCreated");
		String message = null;
		IssueTrackerController updateController = new IssueTrackerController();
		int updated = 0;
		try {
			updated = updateController.updateStatus(empId, issueID, dateCreated, status);
		} catch (InvalidInputException e) {
			message = e.getMessage();
			e.printStackTrace();
		} catch (ParseException e) {
			message = e.getMessage();
			e.printStackTrace();
		}finally {
			try {
				updateController.closeDBConnection();
			} catch (SQLException e) {
				message = "Error while closing the db connection : " + e.getMessage();
				e.printStackTrace();
			}
		}
		if(updated == 1)
		{
			message = "Updated Successfully";
			request.setAttribute("message", message);
	        // RequestDispatcher requestDisapatcher=request.getRequestDispatcher("/ResponseServlet");  
	        // requestDisapatcher.include(request, response); 
			request.getRequestDispatcher("Response.jsp").forward(request, response);
		}
		else {
			RequestDispatcher requestDisapatcher=request.getRequestDispatcher("UpdateIssue.html");  
	    	response.getWriter().println("<p style=\"color:red;margin-top:10px;text-align:center;\">*Could not Update issue. "+message+"*</p>");
	    	requestDisapatcher.include(request, response); 
		}
	}

}
