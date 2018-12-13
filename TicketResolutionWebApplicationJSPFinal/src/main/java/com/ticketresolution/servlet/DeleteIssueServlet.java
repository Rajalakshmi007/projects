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
 * Servlet implementation class DeleteIssueServlet
 */
@WebServlet("/DeleteIssueServlet")
public class DeleteIssueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String issueID = request.getParameter("issueID");
		String empID = request.getParameter("empId");
		String dateCreated = request.getParameter("dateCreated");
		int deletedRows = 0;
		String message = null;
		IssueTrackerController deleteController = new IssueTrackerController();
		try {
			deletedRows = deleteController.deleteIssue(empID, issueID, dateCreated);
		} catch (InvalidInputException e) {
			message = e.getMessage();
			e.printStackTrace();
		} catch (ParseException e) {
			message = e.getMessage();
			e.printStackTrace();
		}finally {
			try {
				deleteController.closeDBConnection();
			} catch (SQLException e) {
				message = "Error while closing the db connection : " + e.getMessage();
				e.printStackTrace();
			}
		}
		if(deletedRows == 1)
		{
			message = "Deleted Successfully";
			request.setAttribute("message", message);
	       // RequestDispatcher requestDisapatcher=request.getRequestDispatcher("/ResponseServlet");  
	       // requestDisapatcher.include(request, response); 
	        request.getRequestDispatcher("Response.jsp").forward(request, response);
		}
		else {
	    	RequestDispatcher requestDisapatcher=request.getRequestDispatcher("DeleteIssue.html");  
	    	response.getWriter().println("<p style=\"color:red;margin-top:10px;text-align:center;\">*Not a valid issue. "+message+"*</p>");
	    	requestDisapatcher.include(request, response); 
		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
