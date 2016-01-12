package com.d4s.main;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.*;


//@SuppressWarnings("serial")
public class CauseServlet extends HttpServlet {
	
	
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
	    String pCauseNm = request.getParameter("CauseNm");
	    String pCauseGBDCd = request.getParameter("CauseGBDCd");
	    String pCauseId =  request.getParameter("CauseId");
	    
	    maintainCause(pRecActionCd, pCauseId, pCauseGBDCd, pCauseNm);
	 }
	
	
	private void maintainCause(String pRecActionCd, String pCauseId,String pCauseGBDCd, String pCauseNm) {
		
		//if statements
			CauseDataSvc myClass = new CauseDataSvc();	     	        
	        myClass.createCause(pCauseId,pCauseGBDCd, pCauseNm);
			
	}
}
