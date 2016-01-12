package com.d4s.bil.who;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import com.d4s.bil.Clndr.Clndr;
import com.d4s.bil.Clndr.ClndrSvc;
import com.d4s.bil.Cntry.Cntry;
import com.d4s.bil.Cntry.CntrySvc;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.CompositeFilter;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Transaction;
 
 

public class PplnSvc {

	public static final String PplnList_ByCntry = "PplnList_ByCntry";
	public static final String PplnList_ByClndr = "PplnList_ByClndr";
	//
	public static final String Ppln_ByPplnId = "Ppln_ByPplnId";
	public static final String Ppln_ByPplnBk = "Ppln_ByPplnBk";
	 
	public JsonObject getPplnListJson(String ListType, Integer pCntryId, Integer pClndrId) {

		JsonObjectBuilder jsonBldrPplnList = Json.createObjectBuilder();
		JsonArrayBuilder jsonArBldrPpln = Json.createArrayBuilder();
		Cntry oCntry ;	 		  
		CntrySvc oCntrySvc   = new CntrySvc();
		Clndr oClndr ;	 		  
		ClndrSvc oClndrSvc   = new ClndrSvc();

		DatastoreService dsD4S = DatastoreServiceFactory.getDatastoreService();
		
		Query query = new Query("Ppln");
		
		Filter CntryFilter = new FilterPredicate("CntryId", FilterOperator.EQUAL, pCntryId);
		Filter ClndrFilter =   new FilterPredicate("ClndrId", FilterOperator.EQUAL, pClndrId);
		Filter PplFilter;
		
		switch (ListType) {  
		case PplnList_ByCntry:
			PplFilter =   CntryFilter;
            break;
		case PplnList_ByClndr:
			PplFilter =   ClndrFilter;
            break;
         default: 
        	 PplFilter =   CntryFilter;
            break;
		}
		
		query.setFilter(PplFilter);

		// Use PreparedQuery interface to retrieve results
		PreparedQuery pq = dsD4S.prepare(query);
		  
		for (Entity result : pq.asIterable()) {
			// Get country details
			oCntry = new Cntry();
			oCntry.setCntryId((int) (long) result.getProperty("CntryId"));
			JsonObject jsonCntry = oCntrySvc.getCntryJson(oCntry);
			// Get calendar details
			oClndr = new Clndr();
			oClndr.setClndrId((int) (long) result.getProperty("ClndrId"));
			JsonObject jsonClndr = oClndrSvc.getClndrJson(oClndr);
						 
			JsonObjectBuilder jsonBldrPpl = Json.createObjectBuilder();
			jsonBldrPpl
					.add("PplnId", (int) (long) result.getProperty("PplnId"));
			jsonBldrPpl.add("ClndrId",
					(int) (long) result.getProperty("ClndrId"));
			jsonBldrPpl.add("YearCd",    jsonClndr.getInt("YearCd"));
			jsonBldrPpl.add("CntryId",
					(int) (long) result.getProperty("CntryId"));
			jsonBldrPpl.add("CntryWHOCd",    jsonCntry.getString("CntryWHOCd"));
			jsonBldrPpl.add("CntryISOCd", jsonCntry.getString("CntryISOCd"));
			jsonBldrPpl.add("CntryNm", jsonCntry.getString("CntryNm"));
			//
			jsonBldrPpl.add("PplnAllNum", (double) result.getProperty("PplnAllNum"));
			jsonBldrPpl.add("PplnMaleNum", (double) result.getProperty("PplnMaleNum"));
			jsonBldrPpl.add("PplnFemaleNum", (double) result.getProperty("PplnFemaleNum"));
			//
			jsonBldrPpl.add("PplnAll0To14Num", (double) result.getProperty("PplnAll0To14Num"));
			jsonBldrPpl.add("PplnMale0To14Num", (double) result.getProperty("PplnMale0To14Num"));
			jsonBldrPpl.add("PplnFemale0To14Num", (double) result.getProperty("PplnFemale0To14Num"));
			//
			jsonBldrPpl.add("PplnAll15To59Num", (double) result.getProperty("PplnAll15To59Num"));
			jsonBldrPpl.add("PplnMale15To59Num", (double) result.getProperty("PplnMale15To59Num"));
			jsonBldrPpl.add("PplnFemale15To59Num", (double) result.getProperty("PplnFemale15To59Num"));
			//
			jsonBldrPpl.add("PplnAll60PlusNum", (double) result.getProperty("PplnAll60PlusNum"));
			jsonBldrPpl.add("PplnMale60PlusNum", (double) result.getProperty("PplnMale60PlusNum"));
			jsonBldrPpl.add("PplnFemale60PlusNum", (double) result.getProperty("PplnFemale60PlusNum"));
			
			JsonObject jsonPpl = jsonBldrPpl.build();
			jsonArBldrPpln.add(jsonPpl);
		}
		
		jsonBldrPplnList.add("PplnList", jsonArBldrPpln);
		JsonObject jsonPplnList = jsonBldrPplnList.build();
		
		return jsonPplnList;

	}
	
	public JsonObject getPplnJson(Ppln oPpln) {
 
		JsonObjectBuilder jsonBldrPpl = Json.createObjectBuilder();
		
		DatastoreService dsD4S = DatastoreServiceFactory.getDatastoreService();
		
		Query query = new Query("Ppln");
		
		Filter CntryFilter = new FilterPredicate("CntryId", FilterOperator.EQUAL, oPpln.getCntryId());
		Filter ClndrFilter =   new FilterPredicate("ClndrId", FilterOperator.EQUAL, oPpln.getClndrId());
 		Filter PplFilter =   CompositeFilterOperator.and(CntryFilter, ClndrFilter );

		query.setFilter(PplFilter);

		// Use PreparedQuery interface to retrieve results
		PreparedQuery pq = dsD4S.prepare(query);
		
		for (Entity result : pq.asIterable()) {
			  jsonBldrPpl.add("PplnId", (int) (long) result.getProperty("PplnId"));
 			  jsonBldrPpl.add("ClndrId", (int) (long) result.getProperty("ClndrId"));
			  jsonBldrPpl.add("CntryId", (int) (long) result.getProperty("CntryId"));
				//
				jsonBldrPpl.add("PplnAllNum", (double) result.getProperty("PplnAllNum"));
				jsonBldrPpl.add("PplnMaleNum", (double) result.getProperty("PplnMaleNum"));
				jsonBldrPpl.add("PplnFemaleNum", (double) result.getProperty("PplnFemaleNum"));
				//
				jsonBldrPpl.add("PplnAll0To14Num", (double) result.getProperty("PplnAll0To14Num"));
				jsonBldrPpl.add("PplnMale0To14Num", (double) result.getProperty("PplnMale0To14Num"));
				jsonBldrPpl.add("PplnFemale0To14Num", (double) result.getProperty("PplnFemale0To14Num"));
				//
				jsonBldrPpl.add("PplnAll15To59Num", (double) result.getProperty("PplnAll15To59Num"));
				jsonBldrPpl.add("PplnMale15To59Num", (double) result.getProperty("PplnMale15To59Num"));
				jsonBldrPpl.add("PplnFemale15To59Num", (double) result.getProperty("PplnFemale15To59Num"));
				//
				jsonBldrPpl.add("PplnAll60PlusNum", (double) result.getProperty("PplnAll60PlusNum"));
				jsonBldrPpl.add("PplnMale60PlusNum", (double) result.getProperty("PplnMale60PlusNum"));
				jsonBldrPpl.add("PplnFemale60PlusNum", (double) result.getProperty("PplnFemale60PlusNum"));  	 		}
		
		JsonObject jsonPpln = jsonBldrPpl.build();
		
		return jsonPpln;
	}

	public Ppln getPpln(String ListType, Ppln pPpln) {

		Ppln oPpln = new Ppln();
		oPpln.setPplnId(0);
		
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query query = new Query("Ppln");
		Filter PplnIdFilter, CntryIdFilter, ClndrIdFilter;
		
		switch (ListType) {
		case Ppln_ByPplnId:
			PplnIdFilter = new FilterPredicate("PplnId",
					FilterOperator.EQUAL, pPpln.getPplnId());
			query.setFilter(PplnIdFilter);
			break;
		case Ppln_ByPplnBk:
			ClndrIdFilter = new FilterPredicate("ClndrId",
					FilterOperator.EQUAL, pPpln.getClndrId());
			CntryIdFilter = new FilterPredicate("CntryId",
					FilterOperator.EQUAL, pPpln.getCntryId());
			Filter PplnBkFilter =
					  CompositeFilterOperator.and(CntryIdFilter, ClndrIdFilter);

			query.setFilter(PplnBkFilter);
			break;
		default:
			PplnIdFilter = new FilterPredicate("PplnId",
					FilterOperator.EQUAL, pPpln.getPplnId());
			query.setFilter(PplnIdFilter);
			break;
		}
		

		// Use PreparedQuery interface to retrieve results
		PreparedQuery pq = datastore.prepare(query);

		for (Entity result : pq.asIterable()) {
			oPpln.setPplnId((int) (long) result.getProperty("PplnId")); 
			oPpln.setClndrId((int) (long) result.getProperty("ClndrId")); 
			oPpln.setYearCd((int) (long) result.getProperty("YearCd")); 
			oPpln.setCntryId((int) (long) result.getProperty("CntryId")); 
			//
			oPpln.setPplnAllNum((double) result.getProperty("PplnAllNum")); 
			oPpln.setPplnMaleNum((double) result.getProperty("PplnMaleNum")); 
			oPpln.setPplnFemaleNum((double) result.getProperty("PplnFemaleNum")); 
			//
			oPpln.setPplnAll0To14Num((double) result.getProperty("PplnAll0To14Num")); 
			oPpln.setPplnMale0To14Num((double) result.getProperty("PplnMale0To14Num")); 
			oPpln.setPplnFemale0To14Num((double) result.getProperty("PplnFemale0To14Num")); 
			//
			oPpln.setPplnAll15To59Num((double) result.getProperty("PplnAll15To59Num")); 
			oPpln.setPplnMale15To59Num((double) result.getProperty("PplnMale15To59Num")); 
			oPpln.setPplnFemale15To59Num((double) result.getProperty("PplnFemale15To59Num")); 
			//
			oPpln.setPplnAll60PlusNum((double) result.getProperty("PplnAll60PlusNum")); 
			oPpln.setPplnMale60PlusNum((double) result.getProperty("PplnMale60PlusNum")); 
			oPpln.setPplnFemale60PlusNum((double) result.getProperty("PplnFemale60PlusNum")); 
 
		}

		return oPpln;

	}
	
	public Integer getMaxPplnId() {
		DatastoreService dsD4S = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("Ppln").addSort("PplnId",
				SortDirection.DESCENDING);

		Integer MaxPplnId = 0;

		// Use PreparedQuery interface to retrieve results
		PreparedQuery pq = dsD4S.prepare(query);

		for (Entity result : pq.asIterable()) {
			MaxPplnId = (int) (long) result.getProperty("PplnId")  ;
			break;
		}

		dsD4S = null;
		
		if (MaxPplnId == null)
		{
			MaxPplnId = 0;
		}
		
		return MaxPplnId;
	}
	
	public void addPpln(Ppln oPpln)
	{
		Integer MaxPplnId = getMaxPplnId();

		oPpln.setPplnId(MaxPplnId + 1);
		
		DatastoreService dsD4S = DatastoreServiceFactory.getDatastoreService();
		Transaction txn = dsD4S.beginTransaction(); 		
 		Entity ePpln =    new Entity("Ppln", oPpln.getPplnId());
 		ePpln.setProperty("PplnId", oPpln.getPplnId());
 		ePpln.setProperty("ClndrId", oPpln.getClndrId());
 		ePpln.setProperty("CntryId", oPpln.getCntryId());
 		//
 		ePpln.setProperty("PplnAllNum", oPpln.getPplnAllNum());
 		ePpln.setProperty("PplnMaleNum", oPpln.getPplnMaleNum());
 		ePpln.setProperty("PplnFemaleNum", oPpln.getPplnFemaleNum());
 		//
 		ePpln.setProperty("PplnAll0To14Num", oPpln.getPplnAll0To14Num());
 		ePpln.setProperty("PplnMale0To14Num", oPpln.getPplnMale0To14Num());
 		ePpln.setProperty("PplnFemale0To14Num", oPpln.getPplnFemale0To14Num());
 		//
 		ePpln.setProperty("PplnAll15To59Num", oPpln.getPplnAll15To59Num());
 		ePpln.setProperty("PplnMale15To59Num", oPpln.getPplnMale15To59Num());
 		ePpln.setProperty("PplnFemale15To59Num", oPpln.getPplnFemale15To59Num());
 		//
 		ePpln.setProperty("PplnAll60PlusNum", oPpln.getPplnAll60PlusNum());
 		ePpln.setProperty("PplnMale60PlusNum", oPpln.getPplnMale60PlusNum());
 		ePpln.setProperty("PplnFemale60PlusNum", oPpln.getPplnFemale60PlusNum());
   		
 		dsD4S.put(ePpln);
 		 txn.commit();
	}
	
	public void updatePpln(Ppln oPpln)
	{
		DatastoreService dsD4S = DatastoreServiceFactory.getDatastoreService();
		
 		Entity ePpln = new Entity("Ppln", oPpln.getPplnId());
 		ePpln.setProperty("PplnId", oPpln.getPplnId());
 		ePpln.setProperty("ClndrId", oPpln.getClndrId());
 		ePpln.setProperty("CntryId", oPpln.getCntryId());
 		ePpln.setProperty("PplnAllNum", oPpln.getPplnAllNum());
 		//
 		ePpln.setProperty("PplnAllNum", oPpln.getPplnAllNum());
 		ePpln.setProperty("PplnMaleNum", oPpln.getPplnMaleNum());
 		ePpln.setProperty("PplnFemaleNum", oPpln.getPplnFemaleNum());
 		//
 		ePpln.setProperty("PplnAll0To14Num", oPpln.getPplnAll0To14Num());
 		ePpln.setProperty("PplnMale0To14Num", oPpln.getPplnMale0To14Num());
 		ePpln.setProperty("PplnFemale0To14Num", oPpln.getPplnFemale0To14Num());
 		//
 		ePpln.setProperty("PplnAll15To59Num", oPpln.getPplnAll15To59Num());
 		ePpln.setProperty("PplnMale15To59Num", oPpln.getPplnMale15To59Num());
 		ePpln.setProperty("PplnFemale15To59Num", oPpln.getPplnFemale15To59Num());
 		//
 		ePpln.setProperty("PplnAll60PlusNum", oPpln.getPplnAll60PlusNum());
 		ePpln.setProperty("PplnMale60PlusNum", oPpln.getPplnMale60PlusNum());
 		ePpln.setProperty("PplnFemale60PlusNum", oPpln.getPplnFemale60PlusNum());		 		
 		dsD4S.put(ePpln);		
	}
	
	public void deletePplnRec(Ppln oPpln)
	{
		//DatastoreService dsD4S = DatastoreServiceFactory.getDatastoreService();
	//	Key ClndrKey = KeyFactory.createKey("Clndr", oPpln.getClndrId());
		//Entity country = new Entity("CNTRY", ClndrKey);
		 		
	//	dsD4S.delete(ClndrKey);		
	}



}
