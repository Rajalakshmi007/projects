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
import com.ticketresolution.model.Report;

/**
 * Servlet implementation class AddSolutionServlet
 */
@WebServlet("/AddSolutionServlet")
public class AddSolutionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String issueID = request.getParameter("issueID");
		String solution = request.getParameter("solution");
		int no_of_rows_inserted = 0;
		String message = null;
		List<String> solutions = new ArrayList<String>();
		solutions.add(solution);
		IssueTrackerController updateSolutionController = new IssueTrackerController();
		try {
			no_of_rows_inserted = updateSolutionController.createSolutions(issueID, solutions);
		} catch (InvalidInputException e) {
			message = e.getMessage();
		}finally {
			try {
				updateSolutionController.closeDBConnection();
			} catch (SQLException e) {
				message = "Error while closing the db connection : " + e.getMessage();
				e.printStackTrace();
			}
		}
		if(no_of_rows_inserted == 1)
		{
			message = "Added solution Successfully";
			request.setAttribute("message", message);
	        //RequestDispatcher requestDisapatcher=request.getRequestDispatcher("/ResponseServlet");  
	        //requestDisapatcher.include(request, response);
			request.getRequestDispatcher("Response.jsp").forward(request, response);
		}
		else {
	    	RequestDispatcher requestDisapatcher=request.getRequestDispatcher("AddSolution.html");  
	    	response.getWriter().println("<p style=\"color:red;margin-top:10px;text-align:center;\">* Could not add solution. "+message+" *</p>");
	    	requestDisapatcher.include(request, response); 
	    	//response.getWriter().println(message);
		}
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
