package com.ticketresolution.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ticketresolution.controller.IssueTrackerController;

/**
 * Servlet implementation class CreateEmployeeServlet
 */
@WebServlet("/CreateEmployeeServlet")
public class CreateEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
/*
    *//**
     * Default constructor. 
     *//*
    public CreateEmployeeServlet() {
        // TODO Auto-generated constructor stub
    }*/

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String empID = request.getParameter("empid");
		String message = null;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		IssueTrackerController controller = new IssueTrackerController();
		int created = controller.createEmployee(empID, username, password);
		if(created == 1)
		{
			message = "Employee "+empID+" added successfully";
			request.setAttribute("message", message);
	        request.getRequestDispatcher("Response.jsp").forward(request, response);
		}else {
			message = "Invalid details entered";
			RequestDispatcher requestDisapatcher=request.getRequestDispatcher("AddEmployee.html");  
	    	response.getWriter().println("<p style=\"color:red;margin-top:10px;text-align:center;\">*Could not add Employee. "+message+"*</p>");
	    	requestDisapatcher.include(request, response);  
		}
		//response.getWriter().append(message);
	}

}
