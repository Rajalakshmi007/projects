package com.ticketresolution.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ticketresolution.db.DBConnection;
//import com.ticketresolution.db.DBManipulation;
import com.ticketresolution.db.DBReports;
import com.ticketresolution.model.Report;
import com.ticketresolution.model.Solution;

public class ReportGenerator {
	DBReports dbReport = null;
	DBConnection dbConnection = null;
	static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
	static final SimpleDateFormat toFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//DBManipulation db_manipulation = null;

	public ReportGenerator() {
		dbConnection = new DBConnection();
		//db_manipulation = new DBManipulation();
		dbReport = new DBReports();
	}

	/**
	 * show the report based on datewise, userwise and issueTypewise
	 * 
	 * @param report
	 */
	public void showReport(List<Report> report) {
		IssueTrackerController.CONTORLLER_LOGGER.info("-----------------------------------------------------------");
		IssueTrackerController.CONTORLLER_LOGGER.info("GENERATED REPORT");
		IssueTrackerController.CONTORLLER_LOGGER.info("-----------------------------------------------------------");
		IssueTrackerController.CONTORLLER_LOGGER.info("REPORT SIZE : " + report.size());
		for (int count = 0; count < report.size(); count++) {
			Report individual_report = report.get(count);
			IssueTrackerController.CONTORLLER_LOGGER.info("msg : \n" + individual_report.toString());
		}
		IssueTrackerController.CONTORLLER_LOGGER.info("-----------------------------------------------------------");
	}

	/**
	 * Create issue,issuetracker,solution and employee object from the resultset
	 * 
	 * @param resultset
	 *            resultset
	 * @return report in list
	 */
	public List<Report> generateReport(ResultSet resultset) {
		ResultSet solutionSet = null;
		Report individualReport = null;
		List<Report> reports = new ArrayList<Report>();
		boolean reportExist = false;
		try {
			while (resultset.next()) {
				reportExist = true;
				individualReport = new Report();
				// System.out.println("fkjdlks : " + rs.getString("IssueID"));
				individualReport.getIssue().setIssueID(resultset.getString("IssueID"));
				individualReport.getIssue().setIssueType(resultset.getString("IssueType"));
				individualReport.getIssue().setIssueDescription(resultset.getString("IssueDescription"));
				individualReport.getIssue().setIssueItem(resultset.getString("IssueItem"));
				individualReport.getEmployee().setEmpID(resultset.getString("EmpID"));
				individualReport.getEmployee().setUserName(resultset.getString("UserName"));
				individualReport.getEmployee().setPassword(resultset.getString("Password"));
				solutionSet = this.dbReport.getSolutionForIssueID(resultset.getString("IssueID"));
				while (solutionSet.next()) {
					Solution solution = new Solution();
					solution.setIssueID(solutionSet.getString("IssueID"));
					solution.setSolutionID(solutionSet.getString("SolutionID"));
					solution.setSolutions(solutionSet.getString("Solutions"));
					individualReport.getSolutions().add(solution);
				}
				individualReport.getIssuetracker().setEmpID(resultset.getString("EmpID"));
				individualReport.getIssuetracker().setIssueID(resultset.getString("IssueID"));
				individualReport.getIssuetracker().setDateCreated(resultset.getString("DateCreated"));
				individualReport.getIssuetracker().setTargetResolutionDate(resultset.getString("TagetResolutionDate"));
				individualReport.getIssuetracker().setDateResolved(resultset.getString("DateResolved"));
				individualReport.getIssuetracker().setAssignedTo(resultset.getString("AssignedTo"));
				individualReport.getIssuetracker().setTimeTaken(resultset.getString("TimeTaken"));
				individualReport.getIssuetracker().setPriority(resultset.getString("Priority"));
				individualReport.getIssuetracker().setStatus(resultset.getString("Status"));
				reports.add(individualReport);
			}
			IssueTrackerController.CONTORLLER_LOGGER.info("checking REPORT SIZE : " + reports.size());
			if (reportExist) {
				showReport(reports);
			} else {
				IssueTrackerController.CONTORLLER_LOGGER.info("NO REPORTS TO SHOW");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return reports;
	}

	/**
	 * generate userwisereport based on given uesrname
	 * 
	 * @param userName
	 * @return userwise report
	 */
	public List<Report> generateUserwiseReport(String userName) {
		ResultSet resultset = this.dbReport.getUserwiseReport(userName);
		List<Report> reports = null;
		reports = generateReport(resultset);
		return reports;
	}

	/**
	 * generate issuetypewise report based on given issuetype
	 * 
	 * @param issueType
	 * @return issuewise report
	 */
	public List<Report> generateIssueTypewiseReport(String issueType) {
		ResultSet resultset = this.dbReport.getIssueTypewiseReport(issueType);
		List<Report> reports = null;
		reports = generateReport(resultset);
		return reports;
	}

	/**
	 * generate assignee report based on assignee
	 * 
	 * @param issueAssignee
	 * @return
	 */
	public List<Report> generateIssueAssigneewiseReport(String issueAssignee) {
		ResultSet resultset = this.dbReport.getIssueAssigneewiseReport(issueAssignee);
		List<Report> reports = null;
		reports = generateReport(resultset);
		return reports;
	}

	/**
	 * get status report
	 * 
	 * @param status
	 * @return
	 */
	public List<Report> generateStatuswiseReport(String status) {
		ResultSet resultset = this.dbReport.getStatuswiseReport(status.toUpperCase());
		List<Report> reports = null;
		reports = generateReport(resultset);
		return reports;
	}

	/**
	 * generate priority wise report based on priority
	 * 
	 * @param priority
	 * @return
	 */
	public List<Report> generatePrioritywiseReport(String priority) {
		ResultSet resultset = this.dbReport.getPrioritywiseReport(priority.toUpperCase());
		List<Report> reports = null;
		reports = generateReport(resultset);
		return reports;
	}

	/**
	 * generate datewise report based on given date
	 * 
	 * @param date
	 * @return datewise report
	 */
	public List<Report> generateDateWiseReport(String date) {
		List<Report> reports = null;
		try {
			Date parsedDate = format.parse(date);
			String formattedDate = toFormat.format(parsedDate);
			ResultSet resultset = this.dbReport.getDatewiseReport(formattedDate);
			reports = generateReport(resultset);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return reports;
	}

	/**
	 * calls method in db layer to close the database connection
	 * @throws SQLException
	 */
	public void closeDBConnection() throws SQLException {
		IssueTrackerController.CONTORLLER_LOGGER.info("Closing database connection ");
		this.dbConnection.closeDBConnection();
	}
	
	
}
