package com.ticketresolution.ui;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.ticketresolution.controller.InvalidInputException;
import com.ticketresolution.controller.IssueTrackerController;
import com.ticketresolution.controller.ReportGenerator;
import com.ticketresolution.db.DBConnection;

public class IssueTrackerUI {
	//static final Logger UI_LOGGER = Logger.getLogger(IssueTrackerUI.class.getName());
	static final Logger UI_LOGGER = Logger.getLogger(DBConnection.class.getName());
	
	/**
	 * Get emloyee details from user
	 */
	public void addEmployeeDetails() {
		Scanner scanner = new Scanner(System.in);
		String empId = null;
		String userName = null;
		String password = null;
		System.out.println("Enter empId : ");
		empId = scanner.nextLine();
		System.out.println("Enter username : ");
		userName = scanner.nextLine();
		System.out.println("Enter password : ");
		password = scanner.nextLine();
		IssueTrackerController controller = new IssueTrackerController();
		controller.createEmployee(empId, userName, password);
		scanner.close();
	}

	/**
	 * Get input from user to add add new issue.
	 */
	public void addIssueDetails() {
		String empId;
		String issueType;
		String issueDescription;
		String issueItem;
		List<String> solutions;
		String targetResolutionDate;
		String assignedTo;
		String priority;
		String status;
		String dateCreated;
		String dateResolved;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the following details to  add the issue ");
		System.out.println("Enter empId : ");
		empId = scanner.nextLine();
		System.out.println("Enter issueType(h/w,s/w,n/w) : ");
		issueType = scanner.nextLine();
		System.out.println("Enter issueDescription : ");
		issueDescription = scanner.nextLine();
		System.out.println("Enter issueItem : ");
		issueItem = scanner.nextLine();
		/*
		 * System.out.println("Enter username : "); userName = sin.nextLine();
		 * System.out.println("Enter password : "); password = sin.nextLine();
		 */
		System.out.println("Enter date created(yyyy-mm-dd) : ");
		dateCreated = scanner.nextLine();
		System.out.println("Enter who the issue is assgned to : ");
		assignedTo = scanner.nextLine();
		System.out.println("Enter targetResolutionDate(yyyy-mm-dd) :");
		targetResolutionDate = scanner.nextLine();
		System.out.println("Enter date resolved(yyyy-mm-dd) : ");
		dateResolved = scanner.nextLine();
		System.out.println("Enter the priority(MINOR, MAJOR, CRITICAL) of the issue: ");
		priority = scanner.nextLine();
		System.out.println("Enter the status(NEW,IN_PROGRESS,FIXED) of the issue : ");
		status = scanner.nextLine();
		// String solution;
		boolean addsolution = true;
		String choice = null;
		solutions = new ArrayList<String>();
		while (addsolution) {
			System.out.println("Do you want to add solution for the issue.\n If yes press Y ");
			choice = scanner.nextLine();
			if (choice.equalsIgnoreCase("Y")) {
				System.out.println("Enter the solutions : ");
				String solution = scanner.nextLine();
				solutions.add(solution);
				addsolution = true;
			} else {
				addsolution = false;
			}
		}
		//IssueTrackerController controller = new IssueTrackerController();
		/*controller.addIssue(empId, issueType, issueDescription, issueItem, dateCreated, targetResolutionDate,
				dateResolved, assignedTo, priority, status, solutions);*/
		scanner.close();
	}

	/**
	 * Get empID, issueID, dateCreated to update status
	 * 
	 */
	public void getUpdateStatusDetails() {
		String empId;
		String issueID;
		String status;
		String dateCreated;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the employee ID : ");
		empId = scanner.nextLine();
		System.out.println("Enter the issue ID you want to update : ");
		issueID = scanner.nextLine();
		System.out.println("Enter the created date of the issue : ");
		dateCreated = scanner.nextLine();
		System.out.println("Enter the status to be updated");
		status = scanner.nextLine();
		IssueTrackerController updateController = new IssueTrackerController();
		try {
			updateController.updateStatus(empId, issueID, dateCreated, status);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scanner.close();
	}

	/**
	 * Get date from user
	 * 
	 * @return date
	 */
	public void getDateWiseReport() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the date in the following format yyyy-mm-dd");
		String date = scanner.nextLine();
		ReportGenerator dateRepGen = new ReportGenerator();
		dateRepGen.generateDateWiseReport(date);
		scanner.close();
	}

	/**
	 * Get username from user
	 * 
	 * @return userName
	 */
	public void getUserwiseReport() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the username ");
		String userName = scanner.nextLine();
		ReportGenerator userRepGen = new ReportGenerator();
		userRepGen.generateUserwiseReport(userName);
		scanner.close();
	}

	/**
	 * Get status from user for report generation
	 */
	public void getStatuswiseReport() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the status for report generation ");
		String status = scanner.nextLine();
		ReportGenerator statusRepGen = new ReportGenerator();
		statusRepGen.generateStatuswiseReport(status);
		scanner.close();
	}

	/**
	 * Get assignee from user for report generation
	 */
	public void getAssigneewiseReport() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the assignee for report generation ");
		String issueAssignee = scanner.nextLine();
		ReportGenerator issueAssigneeRepGen = new ReportGenerator();
		issueAssigneeRepGen.generateIssueAssigneewiseReport(issueAssignee);
		scanner.close();
	}

	/**
	 * Get priority from user for report generation
	 */
	public void getPrioritywiseReport() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the priority for report generation ");
		String priority = scanner.nextLine();
		ReportGenerator priorityRepGen = new ReportGenerator();
		priorityRepGen.generatePrioritywiseReport(priority);
		scanner.close();
	}

	/**
	 * Get IssueType from user.
	 * 
	 * @return IssueType
	 */
	public void getIssueTypewiseReport() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the issue type for report generation ");
		String issueType = scanner.nextLine();
		ReportGenerator issueTypeRepGen = new ReportGenerator();
		issueTypeRepGen.generateIssueTypewiseReport(issueType);
		scanner.close();
	}

	/**
	 * Get details to add solution
	 */
	public void addSolutionDetails() {
		Scanner scanner = new Scanner(System.in);
		IssueTrackerController updateSolutionController = new IssueTrackerController();
		System.out.println("Enter the issue ID for which the solution is to be added");
		String isueID = scanner.nextLine();
		List<String> solution = new ArrayList<String>();
		boolean ch = false;
		while (!ch) {
			System.out.println("Enter the solution");
			String sol = scanner.nextLine();
			solution.add(sol);
			System.out.println("Do you want to add more solution. If yes press Y");
			String input = scanner.nextLine();
			if (!input.equalsIgnoreCase("y")) {
				ch = true;
			}
		}
		try {
			updateSolutionController.createSolutions(isueID, solution);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scanner.close();
	}

	/**
	 * Get details to delete issue
	 */
	public void deleteIssue() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter issue id to be deleted ");
		String issueID = scanner.nextLine();
		System.out.println("Enter empID whose issue is to be deleted ");
		String empID = scanner.nextLine();
		System.out.println("Enter datecreated of the issue to be deleted ");
		String dateCreated = scanner.nextLine();
		IssueTrackerController deleteController = new IssueTrackerController();
		try {
			deleteController.deleteIssue(empID, issueID, dateCreated);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		scanner.close();
	}

	/**
	 * Get the option from the user(Adding issue, Updating Issue Status, Generating
	 * report datewise,userwise and issuetype wise , Deleting the issue) and perform
	 * specific action.
	 * 
	 * @param args
	 *            Not used
	 */
	public static void main(String[] args) {
		IssueTrackerUI issueTrackerObj = new IssueTrackerUI();
		UI_LOGGER.info(
				"Enter the action \n 1) Add Employee \n 2) Add Issue \n 3) Update Issue Status \n 4) Generate DateWise Report \n 5) Generate userwise report \n 6) Generate IssueTypeWise Report \n 7) Generate prioritywise Report \n 8) Generate Assigneewise Report \n 9) Generate statuswise Report \n 10) Add solution \n 11) Delete Issue");
		Scanner scanner = new Scanner(System.in);
		int choice = scanner.nextInt();
		switch (choice) {
		case 1:
			issueTrackerObj.addEmployeeDetails();
			break;
		case 2:
			issueTrackerObj.addIssueDetails();
			break;
		case 3:
			issueTrackerObj.getUpdateStatusDetails();
			break;
		case 4:
			issueTrackerObj.getDateWiseReport();
			break;
		case 5:
			issueTrackerObj.getUserwiseReport();
			break;
		case 6:
			issueTrackerObj.getIssueTypewiseReport();
			break;
		case 7:
			issueTrackerObj.getPrioritywiseReport();
			break;
		case 8:
			issueTrackerObj.getAssigneewiseReport();
			break;
		case 9:
			issueTrackerObj.getStatuswiseReport();
			break;
		case 10:
			issueTrackerObj.addSolutionDetails();
			break;
		case 11:
			issueTrackerObj.deleteIssue();
			break;
		default:
			UI_LOGGER.info("Not a valid action...");
		}
		scanner.close();

	}

}
