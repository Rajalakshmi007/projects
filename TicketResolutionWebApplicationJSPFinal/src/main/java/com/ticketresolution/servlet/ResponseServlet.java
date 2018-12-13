package com.ticketresolution.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ResponseServlet
 */
@WebServlet("/ResponseServlet")
public class ResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResponseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String htmlResponse = "<!DOCTYPE html>";
		htmlResponse += "<html>";
		htmlResponse += "<head>";
		htmlResponse += "<meta charset=\"ISO-8859-1\">";
		htmlResponse += "<title>Success</title>";
		htmlResponse += "<style>";
		htmlResponse += "body{";
		htmlResponse += "background-image: url(img/issues_i.jpg);";
		htmlResponse += "background-size: cover;";
		htmlResponse += "}";
		htmlResponse += "div {";
		htmlResponse += "text-align: center;";
		htmlResponse += "width: 400px;";
		htmlResponse += "height: 100px;";
		htmlResponse += "background-color: #D7BDE2;";
		htmlResponse += "color: #17202A;";
		htmlResponse += "margin: auto;";
		htmlResponse += "margin-top: 20%;";
		htmlResponse += "border-color: coral;";
		htmlResponse += "}";
		htmlResponse += "#submit_id{";
		htmlResponse += "margin-bottom: 10px;";
		htmlResponse += "width: 60px;";
		htmlResponse += "height: 25px;";
		htmlResponse += "}";
		htmlResponse += "</style>";
		htmlResponse += "</head>";
		htmlResponse += "<body>";
		htmlResponse += "<div>";
		htmlResponse += "<h2>" + request.getAttribute("message") + "</h2>";
		htmlResponse += "<form id=\"button_id\" action=\"index.html\">";
		htmlResponse += "<input id=\"submit_id\" type=\"submit\" value=\"Submit\">";
		htmlResponse += "</form>";
		htmlResponse += "</div>";
		htmlResponse += "</body>";
		htmlResponse += "</html>";
		response.getWriter().println(htmlResponse);
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
