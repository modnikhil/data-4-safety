package com.d4s.main;
import java.io.IOException;

import javax.servlet.http.*;



//@SuppressWarnings("serial")
public class Data4SafetyServlet extends HttpServlet {
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	resp.setContentType("text/plain");
	resp.getWriter().println("Hello, World");
		
		
	}
//	 public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	        CNTRYServlet myClass = new CNTRYServlet();
//	        
//	        
//
//	        myClass.createEntity();
//
//	       // request.getRequestDispatcher("/WEB-INF/some-result.jsp").forward(request, response);
//	    }
}
