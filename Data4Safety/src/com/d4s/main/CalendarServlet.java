package com.d4s.main;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.*;


//@SuppressWarnings("serial")
public class CalendarServlet extends HttpServlet {
	
	
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
	    String pCalendarId =  request.getParameter("CalendarId");
	    String pCalendarYr = request.getParameter("CalendarYr");
	    
	    
	    maintainCalendar(pRecActionCd, pCalendarId,pCalendarYr);
	 }
	
	
	private void maintainCalendar(String pRecActionCd, String pCalendarId, String pCalendarYr) {
		
		//if statements
		CalendarDataSvc myClass = new CalendarDataSvc();	     	        
	        myClass.createCalendar(pCalendarId, pCalendarYr);
			
	}
}
