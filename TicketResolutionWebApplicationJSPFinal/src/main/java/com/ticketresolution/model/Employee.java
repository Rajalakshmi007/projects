package com.ticketresolution.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "employee") 
public class Employee implements Serializable{

	private String empID;
	private String userName;
	private String password;

	public Employee() {

	}

	public Employee(String empID, String userName, String password) {
		super();
		this.empID = empID;
		this.userName = userName;
		this.password = password;
	}
	@XmlElement
	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}
	@XmlElement
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	@XmlElement
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Employee [empID=" + empID + ", userName=" + userName + ", password=" + password + "]";
	}

}
