/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.23
 * Generated at: 2018-11-11 11:47:25 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.ticketresolution.model.Report;
import java.util.Iterator;
import java.util.ArrayList;

public final class reports_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("java.util.Iterator");
    _jspx_imports_classes.add("com.ticketresolution.model.Report");
    _jspx_imports_classes.add("java.util.ArrayList");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("<style>\r\n");
      out.write("body {\r\n");
      out.write("\tbackground-image: url(img/rainbow.jpg);\r\n");
      out.write("\tbackground-size: cover;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("table {\r\n");
      out.write("\twidth: 100%;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("table, th, td {\r\n");
      out.write("\tborder: 1px solid black;\r\n");
      out.write("\tborder-collapse: collapse;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("th, td {\r\n");
      out.write("\tpadding: 5px;\r\n");
      out.write("\ttext-align: left;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("th {\r\n");
      out.write("\tbackground-color: black;\r\n");
      out.write("\tcolor: white;\r\n");
      out.write("\tborder: 1px solid white;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\r\n");
      out.write("<script type=\"text/javascript\" src=\"includeHtml.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<h1 id=\"system\" style=\"color: blue; text-align: center;\">TICKET\r\n");
      out.write("\t\tRESOLUTION SYSTEM</h1>\r\n");
      out.write("\t\t<div id=\"include_div\" style=\"width:100%\" w3-include-html=\"headerNavigation.html\"></div>\r\n");
      out.write("\t\t");
      com.ticketresolution.model.Report reportBean = null;
      reportBean = (com.ticketresolution.model.Report) _jspx_page_context.getAttribute("reportBean", javax.servlet.jsp.PageContext.PAGE_SCOPE);
      if (reportBean == null){
        reportBean = new com.ticketresolution.model.Report();
        _jspx_page_context.setAttribute("reportBean", reportBean, javax.servlet.jsp.PageContext.PAGE_SCOPE);
        out.write('\r');
        out.write('\n');
        out.write('	');
      }
      out.write("\r\n");
      out.write("\t<table>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<th>EmployeeID</th>\r\n");
      out.write("\t\t\t<th>IssueID</th>\r\n");
      out.write("\t\t\t<th>DateCreated</th>\r\n");
      out.write("\t\t\t<th>TargetResolutionDate</th>\r\n");
      out.write("\t\t\t<th>DateResolved</th>\r\n");
      out.write("\t\t\t<th>AssignedTo</th>\r\n");
      out.write("\t\t\t<th>TimeTaken</th>\r\n");
      out.write("\t\t\t<th>Priority</th>\r\n");
      out.write("\t\t\t<th>Status</th>\r\n");
      out.write("\t\t\t<th>IssueType</th>\r\n");
      out.write("\t\t\t<th>IssueItem</th>\r\n");
      out.write("\t\t\t<th>IssueDescription</th>\r\n");
      out.write("\t\t\t<th>Solution</th>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\r\n");
      out.write("\t\t");

			Object reportObj = request.getAttribute("reportList");
			if (reportObj instanceof ArrayList<?>) {
				ArrayList<Report> reports = (ArrayList<Report>) reportObj;
				if (reports.size() > 0) {
					for (Report report : reports) {
						if (report instanceof Report) {
		
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td>");
      out.print(report.getIssuetracker().getEmpID());
      out.write("</td>\r\n");
      out.write("\t\t\t<td>");
      out.print(report.getIssuetracker().getIssueID());
      out.write("</td>\r\n");
      out.write("\t\t\t<td>");
      out.print(report.getIssuetracker().getDateCreated());
      out.write("</td>\r\n");
      out.write("\t\t\t<td>");
      out.print(report.getIssuetracker().getTargetResolutionDate());
      out.write("</td>\r\n");
      out.write("\t\t\t<td>");
      out.print(report.getIssuetracker().getDateResolved());
      out.write("</td>\r\n");
      out.write("\t\t\t<td>");
      out.print(report.getIssuetracker().getAssignedTo());
      out.write("</td>\r\n");
      out.write("\t\t\t<td>");
      out.print(report.getIssuetracker().getTimeTaken());
      out.write("</td>\r\n");
      out.write("\t\t\t<td>");
      out.print(report.getIssuetracker().getPriority());
      out.write("</td>\r\n");
      out.write("\t\t\t<td>");
      out.print(report.getIssuetracker().getStatus());
      out.write("</td>\r\n");
      out.write("\t\t\t<td>");
      out.print(report.getIssue().getIssueType());
      out.write("</td>\r\n");
      out.write("\t\t\t<td>");
      out.print(report.getIssue().getIssueItem());
      out.write("</td>\r\n");
      out.write("\t\t\t<td>");
      out.print(report.getIssue().getIssueDescription());
      out.write("</td>\r\n");
      out.write("\t\t\t<td>");
      out.print(report.getSolutions().toString());
      out.write("</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t");

			}
					}
					
      out.write("\r\n");
      out.write("\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t");
	}else{
					
      out.write("\r\n");
      out.write("\t<h2 style=\"color:red;\" align=\"center\">* ");
      out.print(request.getAttribute("message"));
      out.write(" *</h2>\r\n");
      out.write("\t");

				}
			}
		
      out.write("\r\n");
      out.write("\t\t<script>\r\n");
      out.write("includeHTML();\r\n");
      out.write("</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
      out.write("\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
