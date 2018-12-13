package com.ticket.serviceclient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ticket.model.Report;

public class ReportGeneratorClient {
	
	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/TicketResolutionWebApplicationJSP/rest").path("GenerateReportService").path("statusReport").queryParam("status", "FIXED");
		Response responseReports = target.request(MediaType.APPLICATION_JSON).get();
		System.out.println("response status : " + responseReports.getStatus());
		System.out.println("response object : " + responseReports.getEntity());
		System.out.println("response readentity : " + responseReports.readEntity(String.class));
	}

}
