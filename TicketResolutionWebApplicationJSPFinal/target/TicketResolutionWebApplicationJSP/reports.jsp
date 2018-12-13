<%@ page import="com.ticketresolution.model.Report"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
body {
	background-image: url(img/rainbow.jpg);
	background-size: cover;
}

table {
	width: 100%;
}

table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

th, td {
	padding: 5px;
	text-align: left;
}

th {
	background-color: black;
	color: white;
	border: 1px solid white;
}
</style>
</head>
<body>
	<h1 id="system" style="color: blue; text-align: center;">TICKET
		RESOLUTION SYSTEM</h1>
	<jsp:useBean id="reportBean" class="com.ticketresolution.model.Report">
	</jsp:useBean>
	<table>
		<tr>
			<th>EmployeeID</th>
			<th>IssueID</th>
			<th>DateCreated</th>
			<th>TargetResolutionDate</th>
			<th>DateResolved</th>
			<th>AssignedTo</th>
			<th>TimeTaken</th>
			<th>Priority</th>
			<th>Status</th>
			<th>IssueType</th>
			<th>IssueItem</th>
			<th>IssueDescription</th>
			<th>Solution</th>
		</tr>

		<%
			Object reportObj = request.getAttribute("reportList");
			if (reportObj instanceof ArrayList<?>) {
				ArrayList<Report> reports = (ArrayList<Report>) reportObj;
				if (reports.size() > 0) {
					for (Report report : reports) {
						if (report instanceof Report) {
		%>

		<tr>
			<td><%=report.getIssuetracker().getEmpID()%></td>
			<td><%=report.getIssuetracker().getIssueID()%></td>
			<td><%=report.getIssuetracker().getDateCreated()%></td>
			<td><%=report.getIssuetracker().getTargetResolutionDate()%></td>
			<td><%=report.getIssuetracker().getDateResolved()%></td>
			<td><%=report.getIssuetracker().getAssignedTo()%></td>
			<td><%=report.getIssuetracker().getTimeTaken()%></td>
			<td><%=report.getIssuetracker().getPriority()%></td>
			<td><%=report.getIssuetracker().getStatus()%></td>
			<td><%=report.getIssue().getIssueType()%></td>
			<td><%=report.getIssue().getIssueItem()%></td>
			<td><%=report.getIssue().getIssueDescription()%></td>
			<td><%=report.getSolutions().toString()%></td>
		</tr>
	</table>
	<%
			}
					}
				}else{
					%>
	<h2 style="color:red;" align="center">* <%=request.getAttribute("message")%> *</h2>
	<%
				}
			}
		%>
</body>
</html>

