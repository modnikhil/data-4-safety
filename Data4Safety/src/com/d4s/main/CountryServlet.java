package com.d4s.main;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.*;


//@SuppressWarnings("serial")
public class CountryServlet extends HttpServlet {
	
	
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
		    String pCountryId =  request.getParameter("CountryId");
		    String pCountryWHOCd =  request.getParameter("CountryWHOCd");
		    String pCountryISOCd =  request.getParameter("CountryISOCd");
		    String pCountryNm = request.getParameter("CountryNm");
		   
	
		   
		    
		    
		    maintainCountry(pRecActionCd,pCountryId, pCountryWHOCd, pCountryISOCd ,pCountryNm);
	        

	       // request.getRequestDispatcher("/WEB-INF/some-result.jsp").forward(request, response);
	    }
	 
	 private void maintainCountry(String pRecActionCd, String pCountryId, String pCountryWHOCd , String pCountryISOCd ,String pCountryNm) {
			
			//if statements
		 CountryDataSvc myClass = new CountryDataSvc();
	        myClass.createCountry(pCountryId,pCountryWHOCd, pCountryISOCd,pCountryNm);
				
		}
	 
}
