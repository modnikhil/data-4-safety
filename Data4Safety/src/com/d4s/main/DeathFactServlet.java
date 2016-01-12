package com.d4s.main;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.*;


//@SuppressWarnings("serial")
public class DeathFactServlet extends HttpServlet {
	
	
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
	    String pCauseId =  request.getParameter("CauseId");
	    String pCalendarId = request.getParameter("CalendarId");
	    String pDeathNum =  request.getParameter("DeathNum");
	    String pDALYNum = request.getParameter("DALYNum");
	    
	    maintainDeathFact(pRecActionCd, pCountryId,pCauseId, pCalendarId,pDeathNum,pDALYNum);
	 }
	
	
	private void maintainDeathFact(String pRecActionCd, String pCountryId, String pCauseId, String pCalendarId, String pDeathNum, String pDALYNum) {
		
		//if statements
		DeathFactDataSvc myClass = new DeathFactDataSvc();	     	        
	        myClass.createDeathFact(pCountryId, pCauseId, pCalendarId, pDeathNum, pDALYNum);
			
	}
}
