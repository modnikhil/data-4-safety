/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.d4s.ui.who;


import javax.json.JsonObject;
import javax.servlet.*;
import javax.servlet.http.*;

import com.d4s.bil.DeathCause.DeathCause;
import com.d4s.bil.who.*;

import java.io.*;

/**
 * 
 * @author Compaq_Owner
 */
public class DeathUiCtrl extends HttpServlet {

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
		String url = "/ui/who/Death.html";
		String pReqTpCd = request.getParameter("Req");
		String pReqSubTpCd = request.getParameter("ReqSubTp");
  		
 		response.setContentType("text/plain; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		Death oDeath = new Death();
				
		switch (pReqTpCd) {
		case "List":
			oDeath.setCntryId(Integer.valueOf(request.getParameter("CntryId")));
			oDeath.setClndrId(Integer.valueOf(request.getParameter("ClndrId")));
			oDeath.setDeathCauseId(Integer.valueOf(request.getParameter("DeathCauseId")));
 			break;
		case "Add":
			oDeath.setDeathId(Integer.valueOf(request.getParameter("DeathId")));
			oDeath.setCntryId(Integer.valueOf(request.getParameter("CntryId")));
			oDeath.setClndrId(Integer.valueOf(request.getParameter("ClndrId")));
			oDeath.setDeathCauseId(Integer.valueOf(request.getParameter("DeathCauseId")));
			oDeath.setDeathAllNum(Double.valueOf(request.getParameter("DeathAllNum")));
 			break;
		case "Update":
			oDeath.setDeathId(Integer.valueOf(request.getParameter("DeathId")));
			oDeath.setCntryId(Integer.valueOf(request.getParameter("CntryId")));
			oDeath.setClndrId(Integer.valueOf(request.getParameter("ClndrId")));
			oDeath.setDeathCauseId(Integer.valueOf(request.getParameter("DeathCauseId")));
			oDeath.setDeathAllNum(Double.valueOf(request.getParameter("DeathAllNum")));
			break;
		case "AdminPage":
	 		url = "/ui/admin/who/Death_359.html";
			break;
		default:
 			break;
		}
		
		if (pReqTpCd != null) {
			if (pReqTpCd.equalsIgnoreCase("List")) {
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				out.println(getDeathList(pReqSubTpCd, oDeath.getCntryId(), oDeath.getClndrId(),
						oDeath.getDeathCauseId()));  
			} else if (pReqTpCd.equalsIgnoreCase("Add")) {
				addDeathRec(oDeath);
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher(url);
				dispatcher.forward(request, response);  
			} else if (pReqTpCd.equalsIgnoreCase("Update")) {
				updateDeathRec(oDeath);
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher(url);
				dispatcher.forward(request, response);  
			} else if (pReqTpCd.equalsIgnoreCase("Delete")) {
				deleteDeathRec(oDeath);
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher(url);
				dispatcher.forward(request, response);  
			} else if (pReqTpCd.equalsIgnoreCase("View")) {
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				out.println(getDeathRec(oDeath));  
			} else if (pReqTpCd.equalsIgnoreCase("LoadBulk")) {
				updateDeathBulkData();
		 		url = "/ui/admin/who/Death_359.html";
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
	
	private JsonObject getDeathList(String pReqSubTpCd, Integer pCntryId, Integer pClndrId, Integer pDeathCauseId) {
		DeathSvc oDeathSvc = new DeathSvc();
		JsonObject jsonDeathList = oDeathSvc.getDeathListJson(pReqSubTpCd, pCntryId, pClndrId, pDeathCauseId);
		return jsonDeathList;
	}
	
	private void addDeathRec(Death oDeath) {
		DeathSvc oDeathCauseSvc = new DeathSvc();
		oDeathCauseSvc.addDeath(oDeath);		 
	}	
	
	private void updateDeathRec(Death oDeath) {
		DeathSvc oDeathSvc = new DeathSvc();
		oDeathSvc.updateDeath(oDeath);		 
	}	
	
	private void deleteDeathRec(Death oDeath) {
		DeathSvc oDeathSvc = new DeathSvc();
		oDeathSvc.deleteDeath(oDeath);		 
	}	

	private JsonObject getDeathRec(Death oDeath) {
		DeathSvc oDeathSvc = new DeathSvc();
		JsonObject jsonDeath  = oDeathSvc.getDeathJson(oDeath);
		return jsonDeath;		 
	}	
	
	private void updateDeathBulkData() {
		DeathBulkDataSvc oDeathBulkDataSvc = new DeathBulkDataSvc();
		 oDeathBulkDataSvc.updateDeathBulkData();
		 		 
	}	
	
	/**
	 * Returns a short description of the servlet.
	 * 
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Death";

	}// </editor-fold>
}
