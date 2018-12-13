package com.ticketresolution.controller;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.ticketresolution.db.DBConnection;
import com.ticketresolution.db.DBManipulation;
//import com.ticketresolution.db.DBReports;
import com.ticketresolution.model.Employee;
import com.ticketresolution.model.Issue;
import com.ticketresolution.model.IssueTracker;
import com.ticketresolution.model.Solution;

public class IssueTrackerController {
	static Logger CONTORLLER_LOGGER = Logger.getLogger(DBConnection.class);
	static final SimpleDateFormat CURRENT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
	static final SimpleDateFormat TO_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static final String DATE_FORMAT = "0000-00-00 00:00:00";
	static final String UNFIXED_ISSUE="NOT_FIXED_YET";
	DBConnection dbConnection;
	DBManipulation dbManipulation;

	/**
	 * Prepare the db environment and create object to manipulate tables.
	 */
	public IssueTrackerController() {
		dbConnection = new DBConnection();
		dbManipulation = new DBManipulation();
		// boolean inserted = dmt.addEmployeeDetails("raji", "devi", "raji007spr");
	}

	/**
	 * Add issue into issue table
	 * 
	 * @param issueID
	 * @param issueType
	 * @param issueDescription
	 * @param issueItem
	 */
	public void createIssue(String issueID, String issueType, String issueDescription, String issueItem) {
		CONTORLLER_LOGGER.info("Going to add issue to issue table");
		Issue issue = new Issue(issueID, issueType, issueDescription, issueItem);
		this.dbManipulation.addIssue(issue);
		CONTORLLER_LOGGER.info("created issue successfully");
	}

	/**
	 * 
	 * Add issue into issuetracker table
	 */
	public void createIssueTracker(String empID, String issueID, String dateCreated, String targetResolutionDate,
			String dateResolved, String assignedTo, String timeTaken, String priority, String status) {
		CONTORLLER_LOGGER.info("Going to add issue to issue tracking table");
		if (dateCreated.equals("")) {
			dateCreated = TO_DATE_FORMAT.format(new Timestamp(System.currentTimeMillis()));
			dateCreated = dateCreated.substring(0, dateCreated.length() - 2) + "00";
		}
		if (dateResolved.equals("") && status.equals("FIXED")) {
			dateResolved = TO_DATE_FORMAT.format(new Timestamp(System.currentTimeMillis()));
			dateResolved = dateResolved.substring(0, dateResolved.length() - 2) + "00";
		} else if (dateResolved.equals("") && !(status.equals("FIXED"))) {
			dateResolved = DATE_FORMAT;
		}
		IssueTracker issueTracker = new IssueTracker(empID, issueID, dateCreated, targetResolutionDate, dateResolved,
				assignedTo, timeTaken, priority, status);
		this.dbManipulation.addIssueTracker(issueTracker);
		CONTORLLER_LOGGER.info("Added to issue tracker table successfully");
	}

	/**
	 * Add entry into solutions table
	 * 
	 * @param issueID
	 * @param solutions
	 * @throws InvalidInputException
	 */
	public int createSolutions(String issueID, List<String> solutions) throws InvalidInputException {
		CONTORLLER_LOGGER.info("Going to add solutions table");
		int insertedNoOFRows = 0;
		boolean isValidIssue = false;
		String message = "";
		String solutionID = null;
		issueID = issueID.toUpperCase();
		if ((isValidIssue = this.dbManipulation.isValidIssue(issueID))) {
			for (int it = 0; it < solutions.size(); it++) {
				// String solutionID = this.generateSolutionID(issueID,solutions.get(it));
				String solution = solutions.get(it);
				// Solution sol = new Solution(solutionID, issueID, solution);
				if (this.dbManipulation.isNewSolution(issueID, solution).equals("")) {
					solutionID = this.generateSolutionID(issueID, solutions.get(it));
					Solution sol = new Solution(solutionID, issueID, solution);
					insertedNoOFRows = this.dbManipulation.addSolution(sol);
					CONTORLLER_LOGGER.info("Solution added successfully ");
				} else {
					CONTORLLER_LOGGER.info("Solution already exists. ");
					message += "Solution already exists. ";
					throw new InvalidInputException(message);
				}
			}
		} else {
			message += "Issue ID : " + issueID + " ";
			throw new InvalidInputException(message + " not valid. ");
		}
		return insertedNoOFRows;
	}

	/**
	 * Add entry into employee table
	 * 
	 * @param empId
	 * @param userName
	 * @param password
	 */
	public int createEmployee(String empId, String userName, String password) {
		CONTORLLER_LOGGER.info("Going to add employee credentials");
		int created = 0;
		Employee emp = new Employee(empId, userName, password);
		if (!(empId.isEmpty()) && empId != null && !(userName.isEmpty()) && userName != null && !(password.isEmpty())
				&& (password != null)) {
			created = this.dbManipulation.addEmployeeDetails(emp);
		}
		if (created == 1) {
			CONTORLLER_LOGGER.info("Employee details added successfully");
		} else {
			CONTORLLER_LOGGER.info("Could not add employee details");
		}
		return created;
	}

	/**
	 * Generate unique issueID for issue addition.
	 * 
	 * @return issueID
	 */
	public String generateIssueID(String issueType, String issueDescription, String issueItem) {
		CONTORLLER_LOGGER.info("ENTER  generateIssueID");
		String oldIssueID = this.dbManipulation.isNewIssue(issueType, issueDescription, issueItem);
		if (!(oldIssueID.equals(""))) {
			return oldIssueID;
		}
		String lastIssueID = this.dbManipulation.getlastIssueID();
		String newIssueID = null;
		String prefix = "ISSUE_";
		CONTORLLER_LOGGER.info("last issue id retrieved from table : " + lastIssueID);
		if (lastIssueID == null || lastIssueID.equals("")) {
			CONTORLLER_LOGGER.info("Going to add first issue");
			lastIssueID = prefix + "1";
			return lastIssueID;
		}
		CONTORLLER_LOGGER
				.info("logging lenght detaisl : " + prefix.length() + "last issue id lenght" + lastIssueID.length());
		String issueSuffix = lastIssueID.substring(prefix.length(), lastIssueID.length());
		CONTORLLER_LOGGER.info("issuesuffix retrieved : " + issueSuffix);
		int id = Integer.valueOf(issueSuffix);
		id = id + 1;
		newIssueID = prefix + id;
		CONTORLLER_LOGGER.info("EXIT  generateIssueID");
		return newIssueID;
	}

	/**
	 * Generate unique solution id for solution addition.
	 * 
	 * @return unique solutionID.
	 */
	public String generateSolutionID(String issueID, String solution) {
		String oldSolutiomID = this.dbManipulation.isNewSolution(issueID, solution);
		if (!(oldSolutiomID.equals(""))) {
			return oldSolutiomID;
		}
		String lastSolutionID = this.dbManipulation.getlastSolutionID();
		String newSolutionID = null;
		String prefix = "SOLUTION_";
		CONTORLLER_LOGGER.info("last solution id retrieved from table : " + lastSolutionID);
		if (lastSolutionID == null || lastSolutionID.equals("")) {
			lastSolutionID = prefix + "1";
			return lastSolutionID;
		}
		String solutionSuffix = lastSolutionID.substring(prefix.length(), lastSolutionID.length());
		int id = Integer.valueOf(solutionSuffix);
		CONTORLLER_LOGGER.info("solutionsuffix retrieved : " + id);
		id = id + 1;
		newSolutionID = prefix + id;
		CONTORLLER_LOGGER.info("last solution id retrieved from table : " + newSolutionID);
		return newSolutionID;
	}

	/**
	 * validate issue details before inserting into tables
	 * 
	 * @param targetDate
	 * @param issueType
	 * @param priority
	 * @param status
	 * @throws ParseException
	 * @throws InvalidInputException
	 */
	public void validateIssueTrackerDetails(String createdDate, String resolvedDate, String targetDate,
			String issueType, String priority, String status, List<String> solution)
			throws ParseException, InvalidInputException {
		CONTORLLER_LOGGER.info("Validating issue tracker details");
		Date parsedCreatedDate = null;
		Date parsedResolvedDate = null;
		Date parsedTargetResolutionDate = null;
		if (!(targetDate.isEmpty()) && !(targetDate.equals(null))) {
			parsedTargetResolutionDate = TO_DATE_FORMAT.parse(targetDate);
		}
		if (!(createdDate.isEmpty()) && !(createdDate.equals(null))) {
			parsedCreatedDate = TO_DATE_FORMAT.parse(createdDate);
		}
		if (!(resolvedDate.isEmpty()) && !(resolvedDate.equals(null))) {
			parsedResolvedDate = TO_DATE_FORMAT.parse(resolvedDate);
		}
		if (!(resolvedDate.isEmpty()) && !(resolvedDate.equals(null)) && !(createdDate.isEmpty())
				&& !(createdDate.equals(null)) && !(resolvedDate.equals(DATE_FORMAT))
				&& !(parsedCreatedDate.before(parsedResolvedDate))) {
			throw new InvalidInputException("Invalid date given. Created date should come before resolved date");
		}
		if (!(targetDate.isEmpty()) && !(targetDate.equals(null)) && !(createdDate.isEmpty())
				&& !(createdDate.equals(null)) && !(parsedCreatedDate.before(parsedTargetResolutionDate))) {
			throw new InvalidInputException("Invalid date given. Created date should come before target date");
		}
		if (!issueType.equalsIgnoreCase("N/W") && !issueType.equalsIgnoreCase("S/W")
				&& !issueType.equalsIgnoreCase("H/W")) {
			throw new InvalidInputException("Invalid issuetype. Only h/w, s/w, n/w are allowed");
		}
		if (!priority.equalsIgnoreCase("MINOR") && !priority.equalsIgnoreCase("MAJOR")
				&& !priority.equalsIgnoreCase("critical")) {
			throw new InvalidInputException("invalid priority");
		}
		if (!status.equalsIgnoreCase("new") && !status.equalsIgnoreCase("in_progress")
				&& !status.equalsIgnoreCase("fixed")) {
			throw new InvalidInputException("invalid status");
		}
		if (status.equalsIgnoreCase("FIXED") && !(solution.size() > 0)) {
			throw new InvalidInputException("Solution not provided for fixed issue");
		}
	}
	
	

	/**
	 * 
	 * Convert the given date format(yyyy-MM-dd'T'HH:mm) to common format(yyyy-MM-dd HH:mm:ss)
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public String getFormattedDate(String date) throws ParseException {
		CONTORLLER_LOGGER.debug(this.getClass().getName() +" : Formatting the date to standard format ");
		String result = null;
		Date parsedDate = CURRENT_DATE_FORMAT.parse(date);
		result = TO_DATE_FORMAT.format(parsedDate);
		return result;
	}

	/**
	 * Add issue to ticket resolution system
	 * 
	 * @param empId
	 * @param userName
	 * @param password
	 * @param issueType
	 * @param issueDescription
	 * @param issueItem
	 * @param dateCreated
	 * @param targetResolutionDate
	 * @param dateResolved
	 * @param assignedTo
	 * @param priority
	 * @param status
	 * @param solutions
	 */
	public String addIssue(Issue issue, IssueTracker issuetracker, List<String> solutions)
			throws InvalidInputException {
		CONTORLLER_LOGGER.info("Going to add new issue ");
		String message = null;
		String timeTaken = null;
		String issueID = null;
		String dateCreated = issuetracker.getDateCreated();
		String dateResolved = issuetracker.getDateResolved();
		String targetResolutionDate = issuetracker.getTargetResolutionDate();
		if ((issuetracker.getEmpID() != null) && !(this.dbManipulation.isNewUser(issuetracker.getEmpID()))) {

			if (!(issue.getIssueType().isEmpty()) && !(issue.getIssueType().equals(null))
					&& !(issue.getIssueDescription().isEmpty()) && !(issue.getIssueDescription().equals(null))
					&& !(issue.getIssueItem().isEmpty()) && !(issue.getIssueItem().equals(null))
					&& !(issuetracker.getEmpID().isEmpty()) && !(issuetracker.getEmpID().equals(null))
					&& !(targetResolutionDate.isEmpty()) && !(targetResolutionDate.equals(null))
					&& !(issuetracker.getAssignedTo().isEmpty()) && !(issuetracker.getAssignedTo().equals(null))
					&& !(issuetracker.getPriority().isEmpty()) && !(issuetracker.getPriority().equals(null))) {
				try {
					if (dateCreated.isEmpty() || dateCreated.equals(null)) {
						dateCreated = TO_DATE_FORMAT.format(new Timestamp(System.currentTimeMillis()));
						dateCreated = dateCreated.substring(0, dateCreated.length() - 2) + "00";
					} else {
						dateCreated = getFormattedDate(dateCreated);
					}
					if (issuetracker.getStatus().equalsIgnoreCase("FIXED")) {
						if (dateResolved.isEmpty() || dateResolved.equals(null)) {
							dateResolved = TO_DATE_FORMAT.format(new Timestamp(System.currentTimeMillis()));
							dateResolved = dateResolved.substring(0, dateResolved.length() - 2) + "00";
						} else {
							dateResolved = getFormattedDate(dateResolved);
						}
						timeTaken = this.getTimeTaken(dateCreated, dateResolved);
					} else {
						if (!(dateResolved.equals("")) && !(dateResolved.equals(null))) {
							throw new InvalidInputException("Resolved issue status should be set fixed : "+ dateResolved);
						} else {
							dateResolved = DATE_FORMAT;
							timeTaken = UNFIXED_ISSUE;
						}
					}
					targetResolutionDate = getFormattedDate(targetResolutionDate);
					CONTORLLER_LOGGER.info(
							"date created : " + dateCreated + "  : " + dateResolved + " : " + targetResolutionDate);
					this.validateIssueTrackerDetails(dateCreated, dateResolved, targetResolutionDate,
							issue.getIssueType(), issuetracker.getPriority(), issuetracker.getStatus(), solutions);
					String existingIssueID = this.dbManipulation
							.isNewIssue(issue.getIssueType(), issue.getIssueDescription(), issue.getIssueItem());
					if (existingIssueID == null || existingIssueID.equals("")) {
						issueID = this.generateIssueID(issue.getIssueType(), issue.getIssueDescription(),
								issue.getIssueItem());
						CONTORLLER_LOGGER.info("Generated issue id : " +issueID );
						this.createIssue(issueID, issue.getIssueType(), issue.getIssueDescription(),
								issue.getIssueItem());
					} else {
						CONTORLLER_LOGGER.info("issue already exists with issue ID : " + existingIssueID);
					}
					if (!this.dbManipulation.isDuplicateIssueForUser(issuetracker.getEmpID(), issueID, dateCreated)) {
						this.createIssueTracker(issuetracker.getEmpID(), issueID, dateCreated, targetResolutionDate,
								dateResolved, issuetracker.getAssignedTo(), timeTaken, issuetracker.getPriority(),
								issuetracker.getStatus());
					} else {
						CONTORLLER_LOGGER.info("duplicate issue. Could not add to issue tracker");
					}
					if (solutions.size() > 1) {
						System.out.println("solutions.size() " + solutions);
						CONTORLLER_LOGGER.info("solution size : " + solutions.size());
						this.createSolutions(issueID, solutions);
					} else {
						CONTORLLER_LOGGER.info("empty solution");
					}
					message = "success";
					CONTORLLER_LOGGER.info("Added issue successfully");
				} catch (ParseException e) {
					message = "Invalid date format";
					CONTORLLER_LOGGER.info("Could not add issue. Invalid date format ");
					e.printStackTrace();
				}
			} else {
				message = "One of the given value is either null or empty";
				CONTORLLER_LOGGER.info("One of the given value is either null or empty");
				throw new InvalidInputException(message);
			}
		} else {
			message = "EmployeeID " + issuetracker.getEmpID() + " not valid.";
			CONTORLLER_LOGGER.error("EmployeeID : " + issuetracker.getEmpID() + " not valid.");
			throw new InvalidInputException(message);
		}
		return message;
	}
	
	/**
	 * calls method in db layer to close the database connection
	 * @throws SQLException
	 */
	public void closeDBConnection() throws SQLException {
		this.dbConnection.closeDBConnection();
		CONTORLLER_LOGGER.info("Database connection closed successfully ");
	}

	/**
	 * Generate timetaken from date created and date resolved.
	 * 
	 * @param dateCreated
	 * @param dateResolved
	 * @return
	 * @throws InvalidInputException
	 */
	public String getTimeTaken(String dateCreated, String dateResolved) throws InvalidInputException {
		CONTORLLER_LOGGER.info("calculating time taken between dates");
		String timeTaken = "";
		Date createdDate = null;
		Date resolvedDate = null;
		try {
			System.out.println(dateCreated + " : " + dateResolved);
			createdDate = TO_DATE_FORMAT.parse(dateCreated);
			resolvedDate = TO_DATE_FORMAT.parse(dateResolved);
			System.out.println(dateCreated + " : " + dateResolved);
			if (!(createdDate.before(resolvedDate))) {
				throw new InvalidInputException("Invalid date. Issue created date should come before resolved date.");
			}
			long milliSeconds = resolvedDate.getTime() - createdDate.getTime();
			long seconds = milliSeconds / 1000 % 60;
			long minutes = milliSeconds / (60 * 1000) % 60;
			long hours = milliSeconds / (60 * 60 * 1000) % 24;
			long days = milliSeconds / (24 * 60 * 60 * 1000);
			timeTaken = days + " days " + hours + ":" + minutes + ":" + seconds;
			System.out.println("time taken : " + timeTaken);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return timeTaken;
	}

	/**
	 * 
	 * Update issue status
	 * 
	 * @param empID
	 * @param issueID
	 * @param dateCreated
	 * @param status
	 * @throws InvalidInputException
	 */
	public int updateStatus(String empID, String issueID, String dateCreated, String status)
			throws InvalidInputException, ParseException {
		CONTORLLER_LOGGER.info("Going to update status for the issue : " + issueID);
		int updated = 0;
		boolean isNewUser = false;
		boolean isValidIssue = false;
		String message = "";
		IssueTracker issue = null;
		issueID = issueID.toUpperCase();
		if (!(isNewUser = this.dbManipulation.isNewUser(empID))
				&& (isValidIssue = this.dbManipulation.isValidIssue(issueID))) {
			issue = new IssueTracker();
			issue.setDateCreated(getFormattedDate(dateCreated));
			issue.setEmpID(empID);
			issue.setIssueID(issueID.toUpperCase());
			issue.setStatus(status);
			if (issue.getStatus().equalsIgnoreCase("FIXED")) {
				String dateResolved = TO_DATE_FORMAT.format(new Timestamp(System.currentTimeMillis()));
				dateResolved = dateResolved.substring(0, dateResolved.length() - 2) + "00";
				issue.setDateResolved(dateResolved);
				String timeTaken = null;

				timeTaken = this.getTimeTaken(issue.getDateCreated(), dateResolved);

				issue.setTimeTaken(timeTaken);
			} else {
				issue.setTimeTaken("NOT_FIXED_YET");
				issue.setDateResolved("NOT_RESOLVED");
			}
			CONTORLLER_LOGGER.info("date created before updation : " + issue.getDateCreated());
			updated = this.dbManipulation.updateIsuueStatus(issue);
			if (updated == 1) {
				CONTORLLER_LOGGER.info("Updated issue successfully");
			} else {
				CONTORLLER_LOGGER.info("Status could not be updated. Invalid issue details given");
				throw new InvalidInputException("Status could not be updated. Invalid date detail given");
			}
		} else {
			if (isValidIssue == false) {
				message += "Issue ID : " + issueID + ". ";
			}
			if (isNewUser == true) {
				message += "Employee ID : " + empID;
			}
			throw new InvalidInputException(message + " not valid. ");
		}
		return updated;
	}

	/**
	 * 
	 * Delete issue from the tables for a user id
	 * 
	 * @param empID
	 * @param issueID
	 * @param dateCreated
	 * @throws InvalidInputException
	 * @throws ParseException
	 */
	public int deleteIssue(String empID, String issueID, String dateCreated)
			throws InvalidInputException, ParseException {
		CONTORLLER_LOGGER.info(this.getClass().getName()+ " Deleting issue");
		boolean isNewUser = false;
		boolean isValidIssue = false;
		String message = "";
		int deletedRows = 0;
		issueID = issueID.toUpperCase();
		if (!(empID.isEmpty()) || !(empID.equals(null)) || !(issueID.isEmpty()) || !(issueID.equals(null))
				|| !(dateCreated.isEmpty()) || !(dateCreated.equals(null))) {
			if (!(isNewUser = this.dbManipulation.isNewUser(empID))) {
				if ((isValidIssue = this.dbManipulation.isValidIssue(issueID))) {
					dateCreated = getFormattedDate(dateCreated);
					deletedRows = this.dbManipulation.deleteIssue(issueID, empID, dateCreated);
					if (deletedRows == 0) {
						CONTORLLER_LOGGER.info("Could not delete issue. Requested details does not exist");
						throw new InvalidInputException("Invalid input details");
					} else {
						CONTORLLER_LOGGER.info("Deleted successfully");
					}
				} else {
					message += "Issue ID : " + issueID + ". ";
					throw new InvalidInputException(message + " not valid. ");
				}
			} else {
				message += "Employee ID : " + empID;
				throw new InvalidInputException(message + " not valid. ");
			}

		} else {
			throw new InvalidInputException(
					"Provided details should not be null or empty. Invalid input provided for deleting issue with id : "
							+ issueID);
		}
		return deletedRows;
	}

}
