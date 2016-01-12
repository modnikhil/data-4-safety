/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.d4s.ui.DeathCause;


import javax.json.JsonObject;
import javax.servlet.*;
import javax.servlet.http.*;

import com.d4s.bil.Cntry.CntryBulkDataSvc;
import com.d4s.bil.DeathCause.*;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

import java.io.*;

/**
 * 
 * @author Compaq_Owner
 */
public class DeathCauseUiCtrl extends HttpServlet {

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
		String  url = "/ui/who/DeathCause.html";
 		response.setContentType("text/plain; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
 
		String pReqTpCd = request.getParameter("Req");
		String pReqSubTpCd = request.getParameter("ReqSubTp");
 		DeathCause oDeathCause = new DeathCause();
		
		switch (pReqTpCd) {
		case "Add":
			oDeathCause.setDeathCauseId(Integer.valueOf(request.getParameter("DeathCauseId")));
			oDeathCause.setDeathCauseGBDCd(request.getParameter("DeathCauseGBDCd"));
			oDeathCause.setDeathCauseNm(request.getParameter("DeathCauseNm"));
			oDeathCause.setDeathCauseGrpCd(request.getParameter("DeathCauseGrpCd"));
			oDeathCause.setDeathCauseGrp0Id(Integer.valueOf(request.getParameter("DeathCauseGrp0Id")));
			oDeathCause.setDeathCauseGrp0GBDCd(request.getParameter("DeathCauseGrp0GBDCd"));
			oDeathCause.setDeathCauseGrp0Nm(request.getParameter("DeathCauseGrp0Nm"));
			oDeathCause.setDeathCauseGrp1Id(Integer.valueOf(request.getParameter("DeathCauseGrp1Id")));
			oDeathCause.setDeathCauseGrp1GBDCd(request.getParameter("DeathCauseGrp1GBDCd"));
			oDeathCause.setDeathCauseGrp1Nm(request.getParameter("DeathCauseGrp1Nm"));
			oDeathCause.setDeathCauseGrp2Id(Integer.valueOf(request.getParameter("DeathCauseGrp2Id")));
			oDeathCause.setDeathCauseGrp2GBDCd(request.getParameter("DeathCauseGrp2GBDCd"));
			oDeathCause.setDeathCauseGrp2Nm(request.getParameter("DeathCauseGrp2Nm"));
			oDeathCause.setDeathCauseGrp3Id(Integer.valueOf(request.getParameter("DeathCauseGrp3Id")));
			oDeathCause.setDeathCauseGrp3GBDCd(request.getParameter("DeathCauseGrp3GBDCd"));
			oDeathCause.setDeathCauseGrp3Nm(request.getParameter("DeathCauseGrp3Nm"));
			break;
		case "Update":
			oDeathCause.setDeathCauseId(Integer.valueOf(request.getParameter("DeathCauseId")));
			oDeathCause.setDeathCauseGBDCd(request.getParameter("DeathCauseGBDCd"));
			oDeathCause.setDeathCauseNm(request.getParameter("DeathCauseNm"));
			oDeathCause.setDeathCauseGrpCd(request.getParameter("DeathCauseGrpCd"));
			oDeathCause.setDeathCauseGrp0Id(Integer.valueOf(request.getParameter("DeathCauseGrp0Id")));
			oDeathCause.setDeathCauseGrp0GBDCd(request.getParameter("DeathCauseGrp0GBDCd"));
			oDeathCause.setDeathCauseGrp0Nm(request.getParameter("DeathCauseGrp0Nm"));
			oDeathCause.setDeathCauseGrp1Id(Integer.valueOf(request.getParameter("DeathCauseGrp1Id")));
			oDeathCause.setDeathCauseGrp1GBDCd(request.getParameter("DeathCauseGrp1GBDCd"));
			oDeathCause.setDeathCauseGrp1Nm(request.getParameter("DeathCauseGrp1Nm"));
			oDeathCause.setDeathCauseGrp2Id(Integer.valueOf(request.getParameter("DeathCauseGrp2Id")));
			oDeathCause.setDeathCauseGrp2GBDCd(request.getParameter("DeathCauseGrp2GBDCd"));
			oDeathCause.setDeathCauseGrp2Nm(request.getParameter("DeathCauseGrp2Nm"));
			oDeathCause.setDeathCauseGrp3Id(Integer.valueOf(request.getParameter("DeathCauseGrp3Id")));
			oDeathCause.setDeathCauseGrp3GBDCd(request.getParameter("DeathCauseGrp3GBDCd"));
			oDeathCause.setDeathCauseGrp3Nm(request.getParameter("DeathCauseGrp3Nm"));
			break;
		case "AdminPage":
	 		url = "/ui/admin/who/DeathCause_128.html";
			break;
		default:
 			break;
		}
		
		if (pReqTpCd != null) {
			if (pReqTpCd.equalsIgnoreCase("List")) {
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				out.println(getDeathCauseList(pReqSubTpCd)) ;  
			} else if (pReqTpCd.equalsIgnoreCase("Add")) {
 				addDeathCause(oDeathCause);
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher(url);
				dispatcher.forward(request, response);  
			} else if (pReqTpCd.equalsIgnoreCase("Update")) {
				oDeathCause.setDeathCauseId(Integer.valueOf(request.getParameter("DeathCauseId")));
				oDeathCause.setDeathCauseGBDCd(request.getParameter("DeathCauseGBDCd"));
 				oDeathCause.setDeathCauseNm(request.getParameter("DeathCauseNm"));
				updateDeathCause(oDeathCause);
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher(url);
				dispatcher.forward(request, response);  
			} else if (pReqTpCd.equalsIgnoreCase("Delete")) {
				oDeathCause.setDeathCauseId(Integer.valueOf(request.getParameter("DeathCauseId")));
				oDeathCause.setDeathCauseGBDCd(request.getParameter("DeathCauseGBDCd"));
 				oDeathCause.setDeathCauseNm(request.getParameter("DeathCauseNm"));
				deleteDeathCauseRec(oDeathCause);
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher(url);
				dispatcher.forward(request, response);  
			} else if (pReqTpCd.equalsIgnoreCase("View")) {
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				oDeathCause.setDeathCauseId(Integer.valueOf(request.getParameter("DeathCauseId")));
				out.println(getDeathCauseJson(DeathCauseSvc.DeathCause_ByDeathCauseId, oDeathCause));  
			} else if (pReqTpCd.equalsIgnoreCase("LoadBulk")) {
				updateDeathCauseBulkData();
		 		url = "/ui/admin/who/DeathCause_128.html";
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
	
	private JsonObject getDeathCauseList(String pReqSubTpCd   ) {
		DeathCauseSvc oDeathCauseSvc = new DeathCauseSvc();
		JsonObject jsonDeathCauseList = oDeathCauseSvc.getDeathCauseListJson(pReqSubTpCd);
		return jsonDeathCauseList;
	}
	
	private void addDeathCause(DeathCause oDeathCause) {
		DeathCauseSvc oDeathCauseSvc = new DeathCauseSvc();
		oDeathCauseSvc.addDeathCause(oDeathCause);		 
	}	
	
	private void updateDeathCause(DeathCause oDeathCause) {
		DeathCauseSvc oDeathCauseSvc = new DeathCauseSvc();
		oDeathCauseSvc.updateDeathCause(oDeathCause);		 
	}	
	
	private void deleteDeathCauseRec(DeathCause oDeathCause) {
		DeathCauseSvc oDeathCauseSvc = new DeathCauseSvc();
		oDeathCauseSvc.deleteDeathCause(oDeathCause);		 
	}	

	private JsonObject getDeathCauseJson(String ListType, DeathCause oDeathCause) {
		DeathCauseSvc oDeathCauseSvc = new DeathCauseSvc();
		JsonObject jsonDeathCause  = oDeathCauseSvc.getDeathCauseJson(ListType, oDeathCause);
		return jsonDeathCause;		 
	}	
	
	private void updateDeathCauseBulkData() {
		DeathCauseBulkDataSvc oDeathCauseBulkDataSvc = new DeathCauseBulkDataSvc();
		 oDeathCauseBulkDataSvc.updateDeathCauseBulkData();
		 		 
	}	
	/**
	 * Returns a short description of the servlet.
	 * 
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Death Cause";

	}// </editor-fold>
}
