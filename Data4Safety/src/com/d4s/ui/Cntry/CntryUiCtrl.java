/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.d4s.ui.Cntry;


import javax.json.JsonObject;
import javax.servlet.*;
import javax.servlet.http.*;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.d4s.bil.Cntry.*;
import com.d4s.bil.DeathCause.DeathCause;
import com.d4s.bil.who.PplnBulkDataSvc;

import java.io.*;

/**
 * 
 * @author Compaq_Owner
 */
public class CntryUiCtrl extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			
		} finally {
			out.close();
		}
	}

	// <editor-fold defaultstate="collapsed"
	// desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// processRequest(request, response);
//        UserService userService = UserServiceFactory.getUserService();
//
//        String thisURL = request.getRequestURI();
//
//        response.setContentType("text/html");
//        if (request.getUserPrincipal() != null) {
//        	response.getWriter().println("<p>Hello, " +
//        			request.getUserPrincipal().getName() +
//                                     "!  You can <a href=\"" +
//                                     userService.createLogoutURL(thisURL) +
//                                     "\">sign out</a>.</p>");
//        } else {
//        	response.getWriter().println("<p>Please <a href=\"" +
//                                     userService.createLoginURL(thisURL) +
//                                     "\">sign in</a>.</p>");
//        }
		doPost(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String url = "/ui/who/Cntry.html";
 		response.setContentType("text/plain; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		 		
		String pReqTpCd = request.getParameter("Req");
		String pReqSubTpCd = request.getParameter("ReqSubTp");
 		Cntry oCntry = new Cntry();

		switch (pReqTpCd) {
		case "Add":
			oCntry.setCntryId(Integer.valueOf(request.getParameter("CntryId")));
			oCntry.setCntryISOCd(request.getParameter("CntryISOCd"));
			oCntry.setCntryWHOCd(request.getParameter("CntryWHOCd"));
			oCntry.setCntryNm(request.getParameter("CntryNm"));
 			break;
		case "Update":
			oCntry.setCntryId(Integer.valueOf(request.getParameter("CntryId")));
			oCntry.setCntryISOCd(request.getParameter("CntryISOCd"));
			oCntry.setCntryWHOCd(request.getParameter("CntryWHOCd"));
			oCntry.setCntryNm(request.getParameter("CntryNm"));
			break;
		case "AdminPage":
	 		url = "/ui/admin/who/Cntry_568.html";
			break;
		case "AdminList":
	 		url = "/ui/admin/who/Cntry_568.html";
			break;
		default:
 			break;
		}
 
		if (pReqTpCd != null) {
			if (pReqTpCd.equalsIgnoreCase("List")) {
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				out.println(getCntryList(pReqSubTpCd, oCntry.getCntryId()));  
			}
//			else if (pReqTpCd.equalsIgnoreCase("Convert")) {
//				convertCntry();
//				response.setContentType("application/json");
//				PrintWriter out = response.getWriter();
//				out.println(getCntryList(pReqSubTpCd, Integer.valueOf(pCntryId)));  
//			} 
			else if (pReqTpCd.equalsIgnoreCase("Add")) {
 				addCountryRec(oCntry);
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher(url);
				dispatcher.forward(request, response);  
			} else if (pReqTpCd.equalsIgnoreCase("Update")) {
 				updateCountryRec(oCntry);
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher(url);
				dispatcher.forward(request, response);  
			} else if (pReqTpCd.equalsIgnoreCase("Delete")) {
 				deleteCountryRec(oCntry);
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher(url);
				dispatcher.forward(request, response);  
			} else if (pReqTpCd.equalsIgnoreCase("View")) {
				response.setContentType("application/json");
 				PrintWriter out = response.getWriter();
				out.println(getCountryRec(oCntry));  
			} else if (pReqTpCd.equalsIgnoreCase("LoadBulk")) {
				updateCntryBulkData();
		 		url = "/ui/admin/who/Cntry_568.html";
		 		RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher(url);
				dispatcher.forward(request, response); 
			} else {				 
 				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher(url);
				dispatcher.forward(request, response); 
			}			        	 
		} else {
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}

	}
	
 
	private JsonObject getCntryList(String pReqSubTpCd, Integer pCntryId) {
		CntrySvc oCntrySvc = new CntrySvc();
		JsonObject jsonCntryList = oCntrySvc.getCntryListJson(pReqSubTpCd, pCntryId);
		return jsonCntryList;
	}
	
	private void addCountryRec(Cntry oCntry) {
		CntrySvc oCntrySvc = new CntrySvc();
		oCntrySvc.addCntry(oCntry);		 
	}	
	
	private void updateCountryRec(Cntry oCntry) {
		CntrySvc oCntrySvc = new CntrySvc();
		oCntrySvc.updateCntry(oCntry);		 
	}	
	
	private void deleteCountryRec(Cntry oCntry) {
		CntrySvc oCntrySvc = new CntrySvc();
		oCntrySvc.deleteCntry(oCntry);		 
	}	

	private JsonObject getCountryRec(Cntry oCntry) {
		CntrySvc oCntrySvc = new CntrySvc();
		JsonObject jsonCntry  = oCntrySvc.getCntryJson(oCntry);
		return jsonCntry;		 
	}	
	
	private void updateCntryBulkData() {
		CntryBulkDataSvc oCntryBulkDataSvc = new CntryBulkDataSvc();
		 oCntryBulkDataSvc.updateCntryBulkData();
		 		 
	}	
	
//	private void convertCntry() {
//		CntrySvc oCntrySvc = new CntrySvc();
//		  oCntrySvc.convertCntry(); 
//		 	 
//	}	
	/**
	 * Returns a short description of the servlet.
	 * 
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Country";

	}// </editor-fold>
}
