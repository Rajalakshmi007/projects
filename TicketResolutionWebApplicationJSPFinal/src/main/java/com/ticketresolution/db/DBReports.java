package com.ticketresolution.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBReports {

	/**
	 * get issue details based on priority
	 * 
	 * @param priority
	 * @return
	 */
	public ResultSet getPrioritywiseReport(String priority) {
		DBConnection.DB_LOGGERR.info("preparing to  generate priority wise report ... ");
		String priorityReportQuery = "SELECT * FROM " + DBConnection.issueTrackerTable + " issuetracker join "
				+ DBConnection.employeeTable
				+ " employee ON issuetracker.EmpID = employee.EmpID and issuetracker.Priority = \"" + priority + "\""
				+ " JOIN " + DBConnection.issueTable + " issue ON issuetracker.IssueID = issue.IssueID ";
		DBConnection.DB_LOGGERR.info("userwise report query \n " + priorityReportQuery);
		ResultSet priorityReport = getReport(priorityReportQuery);
		return priorityReport;
	}

	/**
	 * Get datewise report
	 * 
	 * @param date
	 * @return resultset
	 */
	public ResultSet getDatewiseReport(String date) {
		DBConnection.DB_LOGGERR.info("preparing to  generate datetwise report ... ");
		String datewiseReportQuery = "SELECT * FROM " + DBConnection.issueTrackerTable + " issuetracker join "
				+ DBConnection.employeeTable
				+ " employee ON issuetracker.EmpID = employee.EmpID and issuetracker.DateCreated LIKE \"" + date + "%\""
				+ " JOIN " + DBConnection.issueTable + " issue ON issuetracker.IssueID = issue.IssueID";
		DBConnection.DB_LOGGERR.info("datewise report query \n " + datewiseReportQuery);
		DBConnection.DB_LOGGERR.debug("debugging datewise report query \n " + datewiseReportQuery);
		ResultSet userReport = getReport(datewiseReportQuery);
		return userReport;
	}

	/**
	 * Get solutions for the given issue id
	 * 
	 * @param issueID
	 * @return
	 */
	public ResultSet getSolutionForIssueID(String issueID) {
		String solutionQuery = "SELECT * FROM " + DBConnection.solutionTable + " WHERE IssueID=\"" + issueID + "\"";
		DBConnection.DB_LOGGERR.info("Solution query \n " + solutionQuery);
		ResultSet solutionReport = getReport(solutionQuery);
		return solutionReport;
	}

	/**
	 * Get userwise report
	 * 
	 * @param userName
	 * @return resultSet of userWise Report
	 */
	public ResultSet getUserwiseReport(String userName) {
		DBConnection.DB_LOGGERR.info("preparing to  generate userwise report ... ");
		String userReportQuery = "SELECT * FROM " + DBConnection.issueTrackerTable + " issuetracker join "
				+ DBConnection.employeeTable
				+ " employee ON issuetracker.EmpID = employee.EmpID and issuetracker.EmpID LIKE \"" + userName + "%\""
				+ " JOIN " + DBConnection.issueTable + " issue ON issuetracker.IssueID = issue.IssueID ";
		DBConnection.DB_LOGGERR.info("userwise report query \n " + userReportQuery);
		ResultSet userReport = getReport(userReportQuery);
		return userReport;
	}

	/**
	 * retreive data from database based on issue assignee
	 * 
	 * @param issueAssignee
	 * @return
	 */
	public ResultSet getIssueAssigneewiseReport(String issueAssignee) {
		DBConnection.DB_LOGGERR.info("preparing to  generate issueassigneewise report ... ");
		String issueAssigneeReportQuery = "SELECT * FROM " + DBConnection.issueTrackerTable + " issuetracker join "
				+ DBConnection.issueTable
				+ " issue ON issuetracker.IssueID = issue.IssueID and issuetracker.AssignedTo=\"" + issueAssignee
				+ "\" JOIN " + DBConnection.employeeTable + " employee ON issuetracker.EmpID = employee.EmpID";
		DBConnection.DB_LOGGERR.info("Assignee wise report query \n " + issueAssigneeReportQuery);
		ResultSet assigneeReport = getReport(issueAssigneeReportQuery);
		return assigneeReport;
	}

	/**
	 * retreive data from database based on issue status
	 * 
	 * @param status
	 * @return
	 */
	public ResultSet getStatuswiseReport(String status) {
		DBConnection.DB_LOGGERR.info("preparing to  generate statuswise report ... ");
		String statusReportQuery = "SELECT * FROM " + DBConnection.issueTrackerTable + " issuetracker join "
				+ DBConnection.issueTable + " issue ON issuetracker.IssueID = issue.IssueID and issuetracker.Status=\""
				+ status + "\" JOIN " + DBConnection.employeeTable + " employee ON issuetracker.EmpID = employee.EmpID";
		DBConnection.DB_LOGGERR.info("status wise report query \n " + statusReportQuery);
		ResultSet statusReport = getReport(statusReportQuery);
		return statusReport;
	}

	/**
	 * Get issueTypewise report
	 * 
	 * @param issueType
	 * @return resultSet of issueTypeWiseReport
	 */
	public ResultSet getIssueTypewiseReport(String issueType) {
		DBConnection.DB_LOGGERR.info("preparing to  generate userwise report ... ");
		String issueTypeReportQuery = "SELECT * FROM " + DBConnection.issueTable + " issue join "
				+ DBConnection.issueTrackerTable
				+ " issuetracker ON issue.IssueID = issuetracker.IssueID and issue.IssueType=\"" + issueType
				+ "\" JOIN " + DBConnection.employeeTable + " employee ON issuetracker.EmpID = employee.EmpID";
		DBConnection.DB_LOGGERR.info("userwise report query \n " + issueTypeReportQuery);
		ResultSet userReport = getReport(issueTypeReportQuery);
		return userReport;
	}

	/**
	 * Get report after executing the query
	 * 
	 * @param reportQuery
	 * @return resultset
	 */
	public static ResultSet getReport(String reportQuery) {

		ResultSet reportResultSet = null;
		Statement createStmt = null;
		try {
			createStmt = DBConnection.connection.createStatement();
			reportResultSet = createStmt.executeQuery(reportQuery);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return reportResultSet;
	}

}
