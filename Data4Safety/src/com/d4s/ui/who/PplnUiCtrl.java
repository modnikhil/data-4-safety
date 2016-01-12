/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.d4s.ui.who;


import javax.json.JsonObject;
import javax.servlet.*;
import javax.servlet.http.*;

import com.d4s.bil.who.*;

import java.io.*;

/**
 * 
 * @author Compaq_Owner
 */
public class PplnUiCtrl extends HttpServlet {

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
 
 		url = "/ui/who/Ppln.html";
 		response.setContentType("text/plain; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		Ppln oPpln = new Ppln();
 		
		switch (pReqTpCd) {
		case "List":
			oPpln.setPplnId(Integer.valueOf(request.getParameter("PplnId")));
			oPpln.setCntryId(Integer.valueOf(request.getParameter("CntryId")));
			oPpln.setClndrId(Integer.valueOf(request.getParameter("ClndrId")));
			break;
		case "Add":
			oPpln.setPplnId(Integer.valueOf(request.getParameter("PplnId")));
			oPpln.setCntryId(Integer.valueOf(request.getParameter("CntryId")));
			oPpln.setClndrId(Integer.valueOf(request.getParameter("ClndrId")));
			oPpln.setPplnAllNum(Double.valueOf(request.getParameter("PplnAllNum")));
 			break;
		case "Update":
			oPpln.setPplnId(Integer.valueOf(request.getParameter("PplnId")));
			oPpln.setCntryId(Integer.valueOf(request.getParameter("CntryId")));
			oPpln.setClndrId(Integer.valueOf(request.getParameter("ClndrId")));
			oPpln.setPplnAllNum(Double.valueOf(request.getParameter("PplnAllNum")));
			break;
		case "AdminPage":
	 		url = "/ui/admin/who/Ppln_426.html";
			break;
		default:
 			break;
		}
		
		if (pReqTpCd != null) {
			if (pReqTpCd.equalsIgnoreCase("List")) {
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				out.println(getPplnList(pReqSubTpCd, oPpln.getCntryId(), oPpln.getClndrId()));  
			} else if (pReqTpCd.equalsIgnoreCase("Add")) {
 				addPplnRec(oPpln);
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher(url);
				dispatcher.forward(request, response);  
			} else if (pReqTpCd.equalsIgnoreCase("Update")) {
 				updatePplnRec(oPpln);
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher(url);
				dispatcher.forward(request, response);  
			} else if (pReqTpCd.equalsIgnoreCase("Delete")) {
 				deletePplnRec(oPpln);
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher(url);
				dispatcher.forward(request, response);  
			} else if (pReqTpCd.equalsIgnoreCase("View")) {
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
 				out.println(getPplnRec(oPpln));  
			} else if (pReqTpCd.equalsIgnoreCase("LoadBulk")) {
				updatePplnBulkData();
		 		url = "/ui/admin/who/Ppln_426.html";
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
	
	private JsonObject getPplnList(String pReqSubTpCd, Integer pCntryId, Integer pClndrId) {
		PplnSvc oPplnSvc = new PplnSvc();
		JsonObject jsonPplnList = oPplnSvc.getPplnListJson(pReqSubTpCd, pCntryId, pClndrId);
		return jsonPplnList;
	}
	
	private void addPplnRec(Ppln oPpln) {
		PplnSvc oDeathCauseSvc = new PplnSvc();
		oDeathCauseSvc.addPpln(oPpln);		 
	}	
	
	private void updatePplnRec(Ppln oPpln) {
		PplnSvc oPplnSvc = new PplnSvc();
		oPplnSvc.updatePpln(oPpln);		 
	}	
	
	private void deletePplnRec(Ppln oPpln) {
		PplnSvc oPplnSvc = new PplnSvc();
		oPplnSvc.deletePplnRec(oPpln);		 
	}	

	private JsonObject getPplnRec(Ppln oPpln) {
		PplnSvc oPplnSvc = new PplnSvc();
		JsonObject jsonPpln  = oPplnSvc.getPplnJson(oPpln);
		return jsonPpln;		 
	}	
	
	private void updatePplnBulkData() {
		PplnBulkDataSvc oPplnBulkDataSvc = new PplnBulkDataSvc();
		 oPplnBulkDataSvc.updatePplnBulkData();
		 		 
	}	
	/**
	 * Returns a short description of the servlet.
	 * 
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Ppln";

	}// </editor-fold>
}
