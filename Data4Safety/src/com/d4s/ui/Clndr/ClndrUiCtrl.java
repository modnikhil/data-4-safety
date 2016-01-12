/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.d4s.ui.Clndr;


import javax.json.JsonObject;
import javax.servlet.*;
import javax.servlet.http.*;

import com.d4s.bil.Clndr.*; 

import java.io.*;

/**
 * 
 * @author Compaq_Owner
 */
public class ClndrUiCtrl extends HttpServlet {

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
		String url;
		String pReqTpCd = request.getParameter("Req");
		String pReqSubTpCd = request.getParameter("ReqSubTp");
 
 		url = "/ui/who/Clndr.html";
 		response.setContentType("text/plain; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		Clndr oClndr = new Clndr();
		
		switch (pReqTpCd) {
		case "Add":
			oClndr.setClndrId(Integer.valueOf(request.getParameter("ClndrId")));
			oClndr.setYearCd(Integer.valueOf(request.getParameter("YearCd")));
			oClndr.setYearEndCd(request.getParameter("YearEndCd"));
 			break;
		case "Update":
			oClndr.setClndrId(Integer.valueOf(request.getParameter("ClndrId")));
			oClndr.setYearCd(Integer.valueOf(request.getParameter("YearCd")));
			oClndr.setYearEndCd(request.getParameter("YearEndCd"));
			break;
		case "AdminPage":
	 		url = "/ui/admin/who/Clndr_127.html";
			break;
		default:
 			break;
		}
		
		if (pReqTpCd != null) {
			if (pReqTpCd.equalsIgnoreCase("List")) {
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				out.println(getClndrList(pReqSubTpCd, oClndr.getClndrId()));  
			} else if (pReqTpCd.equalsIgnoreCase("Add")) {
 				addClndrRec(oClndr);
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher(url);
				dispatcher.forward(request, response);  
			} else if (pReqTpCd.equalsIgnoreCase("Update")) {
 				updateClndrRec(oClndr);
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher(url);
				dispatcher.forward(request, response);  
			} else if (pReqTpCd.equalsIgnoreCase("Delete")) {
 				deleteClndrRec(oClndr);
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher(url);
				dispatcher.forward(request, response);  
			} else if (pReqTpCd.equalsIgnoreCase("View")) {
				response.setContentType("application/json");
 				PrintWriter out = response.getWriter();
				out.println(oClndr);  
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
	
	private JsonObject getClndrList(String pReqSubTpCd, Integer pClndrId) {
		ClndrSvc oClndrSvc = new ClndrSvc();
		JsonObject jsonClndrList = oClndrSvc.getClndrListJson(pReqSubTpCd, pClndrId);
		return jsonClndrList;
	}
	
	private void addClndrRec(Clndr oClndr) {
		ClndrSvc oClndrSvc = new ClndrSvc();
		oClndrSvc.addClndrRec(oClndr);		 
	}	
	
	private void updateClndrRec(Clndr oClndr) {
		ClndrSvc oClndrSvc = new ClndrSvc();
		oClndrSvc.updateClndrRec(oClndr);		 
	}	
	
	private void deleteClndrRec(Clndr oClndr) {
		ClndrSvc oClndrSvc = new ClndrSvc();
		oClndrSvc.deleteClndrRec(oClndr);		 
	}	

	private JsonObject getClndrRec(Clndr oClndr) {
		ClndrSvc oClndrSvc = new ClndrSvc();
		JsonObject jsonClndr  = oClndrSvc.getClndrJson(oClndr);
		return jsonClndr;		 
	}	
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
