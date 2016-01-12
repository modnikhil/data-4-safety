package com.d4s.main;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.*;


//@SuppressWarnings("serial")
public class PopulationFactServlet extends HttpServlet {
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	resp.setContentType("text/plain");
	resp.getWriter().println("Hello, World");
		
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 	
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	   
	    
	    //Actual stuff
	    Enumeration paramNames = request.getParameterNames();
	    while(paramNames.hasMoreElements()) {
	    	
	      String paramName = (String)paramNames.nextElement();
	      out.println(paramName );
	      out.print(request.getParameter(paramName));
	       
	    }
	   
	    String pRecActionCd = "A";
	    String pCountryId = request.getParameter("CountryId");	  
	    String pCalendarId = request.getParameter("CalendarId");
	    String pPopulationNum = request.getParameter("PopulationNum");
	    
	    maintainPopulationFact(pRecActionCd, pCountryId, pCalendarId,pPopulationNum);
	 }
	
	
	private void maintainPopulationFact(String pRecActionCd, String pCountryId, String pCalendarId, String pPopulationNum) {
		
		//if statements
		PopulationFactDataSvc myClass = new PopulationFactDataSvc();	     	        
	        myClass.createPopulationFact(pCountryId, pCalendarId, pPopulationNum);
			
	}
}