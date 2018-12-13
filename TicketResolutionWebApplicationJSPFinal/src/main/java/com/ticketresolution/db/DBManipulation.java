package com.ticketresolution.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ticketresolution.model.Employee;
import com.ticketresolution.model.Issue;
import com.ticketresolution.model.IssueTracker;
import com.ticketresolution.model.Solution;

public class DBManipulation {

	public String getPassWord(String userName) {
		String password = null;
		Statement statement = null;
		String query = "SELECT Password from Employee where UserName=\"" + userName + "\"";
		try {
			statement = DBConnection.connection.createStatement();
			ResultSet resultset = statement.executeQuery(query);
			while (resultset.next()) {
				password = resultset.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return password;
	}

	/*
	 * public boolean addEmployeeDetails(String empId, String userName, String
	 * passWord) { DBConnection.DB_LOGGERR.info("Going to add employee details ");
	 * String addEmployeeQuery =
	 * "INSERT INTO "+DBConnection.employeeTable+" values(\""+empId+"\",\""+userName
	 * +"\",\""+passWord+"\")";
	 * DBConnection.DB_LOGGERR.info("Employee insertion query \n " +
	 * addEmployeeQuery); Statement createStmt = null; boolean created =false; try {
	 * createStmt = DBConnection.con.createStatement(); created =
	 * createStmt.execute(addEmployeeQuery); if(created) {
	 * DBConnection.DB_LOGGERR.info("inserted employee details : " + created); }
	 * else { System.out.println("Not inserted employee details  : " + created); }
	 * DBConnection.DB_LOGGERR.info("Added employee details for emp ID : " + empId);
	 * } catch (SQLException e) { DBConnection.DB_LOGGERR.
	 * info("Error while adding employee details for emp ID : " + empId);
	 * e.printStackTrace(); } return created; }
	 */

	/**
	 * add employee details to employee table
	 * 
	 * @param emp
	 * @return created
	 */
	public int addEmployeeDetails(Employee emp) {
		DBConnection.DB_LOGGERR.info("Going to add employee details ");
		String addEmployeeQuery = "INSERT INTO " + DBConnection.employeeTable + " values(\"" + emp.getEmpID() + "\",\""
				+ emp.getUserName() + "\",\"" + emp.getPassword() + "\")";
		DBConnection.DB_LOGGERR.info("Employee insertion query \n " + addEmployeeQuery);
		Statement createStatement = null;
		int created = 0;
		try {
			createStatement = DBConnection.connection.createStatement();
			created = createStatement.executeUpdate(addEmployeeQuery);
			/*
			 * if(created) { DBConnection.DB_LOGGERR.info("inserted employee details : " +
			 * created); } else { System.out.println("Not inserted employee details  : " +
			 * created); }
			 */
			DBConnection.DB_LOGGERR.info("Added employee details for emp ID : " + emp.getEmpID());
		} catch (SQLException e) {
			DBConnection.DB_LOGGERR.info("Error while adding employee details for emp ID : " + emp.getEmpID());
			e.printStackTrace();
		}
		return created;
	}

	/**
	 * Add issue details to issue table
	 * 
	 * @param is
	 *            (issue)
	 * @return
	 */
	public boolean addIssue(Issue is) {
		DBConnection.DB_LOGGERR.info("To add issue details");
		String addIssueQuery = "INSERT INTO " + DBConnection.issueTable + " values(\"" + is.getIssueID() + "\",\""
				+ is.getIssueType() + "\",\"" + is.getIssueDescription() + "\",\"" + is.getIssueItem() + "\")";
		DBConnection.DB_LOGGERR.info("Issue insertion query \n " + addIssueQuery);
		Statement createStatement = null;
		boolean created = false;
		try {
			createStatement = DBConnection.connection.createStatement();
			created = createStatement.execute(addIssueQuery);
			/*
			 * if(created) { System.out.println("inserted issue details : " + created); }
			 * else { System.out.println("Not inserted issue details  : " + created); }
			 */

			DBConnection.DB_LOGGERR.info("Added to issue table for issue ID : " + is.getIssueID());
		} catch (SQLException e) {
			DBConnection.DB_LOGGERR.info("Error while adding issue to issue table for issue ID : " + is.getIssueID());
			e.printStackTrace();
		}
		return created;
	}

	/**
	 * Add solution details to solution table
	 * 
	 * @param s
	 * @return created flag
	 */
	public int addSolution(Solution s) {
		int no_Of_Inserted_Rows = 0;
		DBConnection.DB_LOGGERR.info("To add solution details");
		String addIssueQuery = "INSERT INTO " + DBConnection.solutionTable + " values(\"" + s.getSolutionID() + "\",\""
				+ s.getIssueID() + "\",\"" + s.getSolutions() + "\")";
		DBConnection.DB_LOGGERR.info("print employee insertion query \n " + addIssueQuery);
		Statement createStatement = null;
		boolean created = false;
		try {
			createStatement = DBConnection.connection.createStatement();
			no_Of_Inserted_Rows = createStatement.executeUpdate(addIssueQuery);
			/*
			 * if(created) { System.out.println("inserted solution details : " + created); }
			 * else { System.out.println("Not inserted solution details  : " + created); }
			 */
			DBConnection.DB_LOGGERR.info("added to solution table for issue ID : " + s.getIssueID());
		} catch (SQLException e) {
			DBConnection.DB_LOGGERR.info("Error while adding to solution table for issue ID : " + s.getIssueID());
			e.printStackTrace();
		}
		return no_Of_Inserted_Rows;
	}

	/**
	 * Add issue along with status details to issuetracker table
	 * 
	 * @param is
	 * @return create flag
	 */
	public boolean addIssueTracker(IssueTracker is) {
		DBConnection.DB_LOGGERR.info("To add issuetracker details");
		String addIssueQuery = "INSERT INTO " + DBConnection.issueTrackerTable + " values(\"" + is.getEmpID() + "\",\""
				+ is.getIssueID() + "\",\"" + is.getDateCreated() + "\",\"" + is.getTargetResolutionDate() + "\",\""
				+ is.getDateResolved() + "\",\"" + is.getAssignedTo() + "\",\"" + is.getTimeTaken() + "\",\""
				+ is.getPriority() + "\",\"" + is.getStatus() + "\")";
		DBConnection.DB_LOGGERR.info("AddIssueTracker query \n " + addIssueQuery);
		Statement createStmt = null;
		boolean created = false;
		try {
			createStmt = DBConnection.connection.createStatement();
			created = createStmt.execute(addIssueQuery);
			/*
			 * if(created) { System.out.println("inserted issueTracker details : " +
			 * created); } else { System.out.println("Not inserted issueTracker details  : "
			 * + created); }
			 */
			DBConnection.DB_LOGGERR.info("added to issuetracker table successfully with issueID : " + is.getIssueID());
		} catch (SQLException e) {
			DBConnection.DB_LOGGERR.error("Error while adding issue details of issue ID : " + is.getIssueID());
			e.printStackTrace();
		}
		return created;
	}

	/**
	 * Get issueIDs from issue table to geneate unique issueID
	 * 
	 * @return issueID
	 */
	public String getlastIssueID() {
		List<String> issueIds = null;
		String issueID = null;
		try {
			Statement stmt = DBConnection.connection.createStatement();
			ResultSet issues = stmt
					.executeQuery("SELECT * from " + DBConnection.issueTable + " ORDER BY SUBSTRING(IssueID,6,1)");
			issueIds = new ArrayList<String>();
			while (issues.next()) {
				issueIds.add(issues.getString(1));
			}
			if (issueIds.size() > 0) {
				issueID = issueIds.get(issueIds.size() - 1);
			} else {
				issueID = "";
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return issueID;
	}

	/**
	 * Get solutionID from solution table to generate new solutionID
	 * 
	 * @return solutionID
	 */
	public String getlastSolutionID() {
		List<String> solutionIds = null;
		String solutionID = null;
		try {
			Statement statement = DBConnection.connection.createStatement();
			ResultSet issues = statement.executeQuery(
					"SELECT * from " + DBConnection.solutionTable + " ORDER BY SUBSTRING(SolutionID,9,1)");
			solutionIds = new ArrayList<String>();
			while (issues.next()) {
				DBConnection.DB_LOGGERR.info("To add issuetracker details" + issues.getString(1));
				solutionIds.add(issues.getString(1));
			}
			if (solutionIds.size() > 0) {
				solutionID = solutionIds.get(solutionIds.size() - 1);
			} else {
				solutionID = "";
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return solutionID;
	}

	/**
	 * Check whether same solution exist for same issue
	 * 
	 * @param issueID
	 * @param solution
	 * @return solution id if exists
	 */
	public String isNewSolution(String issueID, String solution) {
		DBConnection.DB_LOGGERR.info("Checking whether the solution is new");
		String SolutionID = "";
		ResultSet solutionCount = null;
		String solutionCheckQuery = "SELECT SolutionID FROM " + DBConnection.solutionTable + " WHERE IssueID=\""
				+ issueID + "\" and Solutions=\"" + solution + "\"";
		try {
			Statement statement = DBConnection.connection.createStatement();
			solutionCount = statement.executeQuery(solutionCheckQuery);
			if (solutionCount.next()) {
				SolutionID = solutionCount.getString(1);
			}
			solutionCount.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return SolutionID;
	}

	public boolean isValidIssue(String issueID) {
		DBConnection.DB_LOGGERR.info(this.getClass().getName()+ " : Checking whether the issue is valid");
		boolean valid = false;
		String issueValidationQuery = "SELECT IssueID FROM " + DBConnection.issueTable + " WHERE IssueID=\"" + issueID
				+ "\"";
		try {
			Statement statement = DBConnection.connection.createStatement();
			ResultSet validCount = statement.executeQuery(issueValidationQuery);
			if (validCount.next()) {
				valid = true;
			}
			validCount.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return valid;
	}

	/**
	 * 
	 * Checking for duplicate issue in issuetracker table
	 * @param empID
	 * @param issueID
	 * @param dateCreated
	 * @return
	 */
	public boolean isDuplicateIssueForUser(String empID, String issueID, String dateCreated) {
		DBConnection.DB_LOGGERR.info(this.getClass().getName() + "Checking whether the issue already exist for the user");
		String isDuplicateQuery = "SELECT * FROM " + DBConnection.issueTrackerTable + " WHERE EmpID=\"" + empID
				+ "\" and IssueID=\"" + issueID + "\" and DateCreated=\"" + dateCreated + "\"";
		boolean result = false;
		try {
			Statement statement = DBConnection.connection.createStatement();
			ResultSet issueCount = statement.executeQuery(isDuplicateQuery);
			if (issueCount.next()) {
				result = true;
			}
			issueCount.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * check whether the given issue description is already in tables
	 * 
	 * @param issueType
	 * @param issueDescription
	 * @param issueItem
	 * @return existing issue id if exists
	 */
	public String isNewIssue(String issueType, String issueDescription, String issueItem) {
		DBConnection.DB_LOGGERR.info("Checking whether the issue is new");
		String IssueID = "";
		String issueCheckQuery = "SELECT IssueID FROM " + DBConnection.issueTable + " WHERE IssueType=\"" + issueType
				+ "\" and IssueDescription=\"" + issueDescription + "\" and IssueItem=\"" + issueItem + "\"";
		try {
			Statement statement = DBConnection.connection.createStatement();
			ResultSet issueCount = statement.executeQuery(issueCheckQuery);
			if (issueCount.next()) {
				IssueID = issueCount.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBConnection.DB_LOGGERR.info("existing issue with id :" + IssueID+":");
		return IssueID;
	}

	/**
	 * @param empID
	 * @return whether it is new user
	 */
	public boolean isNewUser(String empID) {
		boolean newUser = true;
		String userCheckQuery = "SELECT * FROM " + DBConnection.employeeTable + " WHERE EmpID=\"" + empID + "\"";
		try {
			Statement statement = DBConnection.connection.createStatement();
			ResultSet usersCount = statement.executeQuery(userCheckQuery);
			if (usersCount.next()) {
				newUser = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newUser;
	}

	/**
	 * Update issue status
	 * 
	 * @param is
	 * @return
	 */
	public int updateIsuueStatus(IssueTracker is) {
		DBConnection.DB_LOGGERR.info("Updating issue status");
		String updateStatusQuery = "UPDATE " + DBConnection.issueTrackerTable + " SET status=\"" + is.getStatus()
				+ "\", DateResolved=\"" + is.getDateResolved() + "\",TimeTaken=\"" + is.getTimeTaken()
				+ "\" WHERE EmpID=\"" + is.getEmpID() + "\" AND  IssueID=\"" + is.getIssueID() + "\" AND DateCreated=\""
				+ is.getDateCreated() + "\"";
		DBConnection.DB_LOGGERR.info("update status query \n " + updateStatusQuery);
		DBConnection.DB_LOGGERR.info("date created before db manipulation \n " + is.getDateCreated());
		Statement createStmt = null;
		int created = 0;
		try {
			createStmt = DBConnection.connection.createStatement();
			created = createStmt.executeUpdate(updateStatusQuery);
			if (created == 0) {
				return created;
			}
			DBConnection.DB_LOGGERR.info("status updated successfully : " + created);
		} catch (SQLException e) {
			DBConnection.DB_LOGGERR.error("Error in updating status to issuetracker table with empID " + is.getEmpID());
			e.printStackTrace();
		}
		return created;
	}

	/**
	 * Delete issue from ticket resolution db based on following given parameters.
	 * 
	 * @param issueID
	 * @param empID
	 * @param dateCreated
	 * @return
	 */
	public int deleteIssue(String issueID, String empID, String dateCreated) {
		boolean deleted = false;
		DBConnection.DB_LOGGERR.info("Going to delete issue with ID : " + issueID + " datet created : " + dateCreated);
		String deleteQuery = "DELETE FROM " + DBConnection.issueTrackerTable + " WHERE IssueID=\"" + issueID
				+ "\" and EmpID=\"" + empID + "\" and DateCreated=\"" + dateCreated + "\"";
		DBConnection.DB_LOGGERR.info("delete issue query \n " + deleteQuery);

		Statement createStmt = null;
		int deletedRows = 0;
		try {
			createStmt = DBConnection.connection.createStatement();
			deletedRows = createStmt.executeUpdate(deleteQuery);
			if (deletedRows == 0) {
				return deletedRows;
			} else {
				deleted = true;
			}
			String issueIDQuery = "SELECT * FROM " + DBConnection.issueTrackerTable + " WHERE IssueID = \"" + issueID
					+ "\"";
			ResultSet issueIDResultset = createStmt.executeQuery(issueIDQuery);
			if (issueIDResultset.next()) {
				DBConnection.DB_LOGGERR.info("issue is still present in table");
			} else {
				// String deleteIssueQuery = "DELETE * FROM "+DBConnection.issueTable+"
				// issuetable JOIN "+DBConnection.solutionTable + " solution ON
				// issuetable.IssueID = "+issueID + " and solution.IssueID ="+issueID;
				String deleteIssueQuery = "DELETE FROM " + DBConnection.issueTable + " WHERE IssueID = \"" + issueID
						+ "\"";
				int issueDelCount = createStmt.executeUpdate(deleteIssueQuery);
				DBConnection.DB_LOGGERR.info("Total issue deleted : " + issueDelCount);
				String deleteSolQuery = "DELETE FROM " + DBConnection.solutionTable + " WHERE IssueID = \"" + issueID
						+ "\"";
				int issueSolCount = createStmt.executeUpdate(deleteSolQuery);
				DBConnection.DB_LOGGERR.info("Total solution deleted : " + issueSolCount);
				String empIDQuery = "SELECT * FROM " + DBConnection.issueTrackerTable + " WHERE EmpID = \"" + empID
						+ "\"";
				ResultSet empSet = createStmt.executeQuery(empIDQuery);
				if (empSet.next()) {
					DBConnection.DB_LOGGERR.info("employee is still present in table");
				} else {
					String deleteEmpQuery = "DELETE FROM " + DBConnection.employeeTable + " WHERE EmpID = \"" + empID
							+ "\"";
					int issueEmpCount = createStmt.executeUpdate(deleteEmpQuery);
					DBConnection.DB_LOGGERR.info("Total employee deleted : " + issueEmpCount);
				}
			}
			DBConnection.DB_LOGGERR.info("deleted issue successfully : " + deletedRows);
		} catch (SQLException e) {
			DBConnection.DB_LOGGERR
					.error("Error while delting  issue wiht id :   " + issueID + " for empID : " + empID);
			e.printStackTrace();
		}

		return deletedRows;
	}
	
}
