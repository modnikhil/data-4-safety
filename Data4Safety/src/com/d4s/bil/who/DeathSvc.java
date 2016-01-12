package com.d4s.bil.who;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import com.d4s.bil.Clndr.Clndr;
import com.d4s.bil.Clndr.ClndrSvc;
import com.d4s.bil.Cntry.Cntry;
import com.d4s.bil.Cntry.CntrySvc;
import com.d4s.bil.DeathCause.DeathCause;
import com.d4s.bil.DeathCause.DeathCauseSvc;
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
 

public class DeathSvc {

	public static final String DeathList_ByCntryClndr = "DeathList_ByCntryClndr";
	public static final String DeathList_ByCntryDeathCause = "DeathList_ByCntryDeathCause";
	public static final String DeathList_ByClndrDeathCause = "DeathList_ByClndrDeathCause";
//
	public static final String Death_ByDeathId = "Death_ByDeathId";
	public static final String Death_ByDeathBk = "Death_ByDeathBk";

	public JsonObject getDeathListJson(String ListType, Integer pCntryId, Integer pClndrId, Integer pDeathCauseId) {

		JsonObjectBuilder jsonBldrDeathList = Json.createObjectBuilder();
		JsonArrayBuilder jsonArBldrDeath = Json.createArrayBuilder();
		Cntry oCntry ;	 		  
		CntrySvc oCntrySvc   = new CntrySvc();
		Clndr oClndr ;	 		  
		ClndrSvc oClndrSvc   = new ClndrSvc();
		DeathCause oDeathCause ;	 		  
		DeathCauseSvc oDeathCauseSvc   = new DeathCauseSvc();
 		
		DatastoreService dsD4S = DatastoreServiceFactory.getDatastoreService();
		
		Query query = new Query("Death");
		
		Filter CntryFilter = new FilterPredicate("CntryId", FilterOperator.EQUAL, pCntryId);
		Filter ClndrFilter =   new FilterPredicate("ClndrId", FilterOperator.EQUAL, pClndrId);
		Filter DeathCauseFilter =   new FilterPredicate("DeathCauseId", FilterOperator.EQUAL, pDeathCauseId);
		Filter DeathFilter;
		
		switch (ListType) {  
		case DeathList_ByCntryClndr:
			DeathFilter =   CompositeFilterOperator.and(CntryFilter, ClndrFilter);
            break;
		case DeathList_ByCntryDeathCause:
			DeathFilter =   CompositeFilterOperator.and(CntryFilter, DeathCauseFilter);
            break;
		case DeathList_ByClndrDeathCause:
			DeathFilter =   CompositeFilterOperator.and(ClndrFilter, DeathCauseFilter);
            break;    
        default: 
    		DeathFilter =   CompositeFilterOperator.and(CntryFilter, ClndrFilter);
            break;
		}

		query.setFilter(DeathFilter);
		
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
			// Get death cause details
			oDeathCause = new DeathCause();
			oDeathCause.setDeathCauseId((int) (long) result.getProperty("DeathCauseId"));
			JsonObject jsonDeathCause = oDeathCauseSvc.getDeathCauseJson(DeathCauseSvc.DeathCause_ByDeathCauseId, oDeathCause);

 		  
		  JsonObjectBuilder jsonBldrDeath = Json.createObjectBuilder();
		    jsonBldrDeath.add("DeathId", (int) (long) result.getProperty("DeathId"));
			jsonBldrDeath.add("ClndrId", (int) (long) result.getProperty("ClndrId"));
			jsonBldrDeath.add("YearCd",    jsonClndr.getInt("YearCd"));
			jsonBldrDeath.add("CntryId", (int) (long) result.getProperty("CntryId"));
			jsonBldrDeath.add("CntryWHOCd",    jsonCntry.getString("CntryWHOCd"));
			jsonBldrDeath.add("CntryISOCd", jsonCntry.getString("CntryISOCd"));
			jsonBldrDeath.add("CntryNm", jsonCntry.getString("CntryNm"));
			jsonBldrDeath.add("DeathCauseId", (int) (long) result.getProperty("DeathCauseId"));
			jsonBldrDeath.add("DeathCauseGrpCd",    jsonDeathCause.getString("DeathCauseGrpCd"));
			jsonBldrDeath.add("DeathCauseGBDCd", jsonDeathCause.getString("DeathCauseGBDCd"));
			jsonBldrDeath.add("DeathCauseNm", jsonDeathCause.getString("DeathCauseNm"));
			jsonBldrDeath.add("DeathAllNum", (double) result.getProperty("DeathAllNum"));
			jsonBldrDeath.add("DeathMaleNum", (double) result.getProperty("DeathMaleNum"));
			jsonBldrDeath.add("DeathFemaleNum", (double) result.getProperty("DeathFemaleNum"));
			JsonObject jsonDeath = jsonBldrDeath.build();
			jsonArBldrDeath.add(jsonDeath);
 		}
		

		jsonBldrDeathList.add("DeathList", jsonArBldrDeath);

		JsonObject jsonClndrList = jsonBldrDeathList.build();

 
		
		return jsonClndrList;

	}
	
	public JsonObject getDeathJson(Death oDeath) {
 
		JsonObjectBuilder jsonBldrDeath = Json.createObjectBuilder();
		
		DatastoreService dsD4S = DatastoreServiceFactory.getDatastoreService();
		
		Query query = new Query("Death");
		
		Filter CntryFilter = new FilterPredicate("CntryId", FilterOperator.EQUAL, oDeath.getCntryId());
		Filter ClndrFilter =   new FilterPredicate("ClndrId", FilterOperator.EQUAL, oDeath.getClndrId());
		Filter DeathCauseFilter =   new FilterPredicate("DeathCauseId", FilterOperator.EQUAL, oDeath.getDeathCauseId());
		Filter DeathFilter =   CompositeFilterOperator.and(CntryFilter, ClndrFilter, DeathCauseFilter);

		query.setFilter(DeathFilter);
		
		// Use PreparedQuery interface to retrieve results
		PreparedQuery pq = dsD4S.prepare(query);

		for (Entity result : pq.asIterable()) {
			jsonBldrDeath.add("DeathId", (int) (long) result.getProperty("DeathId"));
			jsonBldrDeath.add("ClndrId", (int) (long) result.getProperty("ClndrId"));
			jsonBldrDeath.add("CntryId", (int) (long) result.getProperty("CntryId"));
			jsonBldrDeath.add("DeathCauseId", (int) (long) result.getProperty("DeathCauseId"));
			jsonBldrDeath.add("DeathAllNum", (double) result.getProperty("DeathAllNum"));
	 		}
		
		JsonObject jsonDeath = jsonBldrDeath.build();
 
		return jsonDeath;

	}
	

	public Death getDeath(String ListType, Death pDeath) {

		Death oDeath = new Death();
		oDeath.setDeathId(0);
		
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query query = new Query("Death");
		Filter DeathIdFilter, CntryIdFilter, ClndrIdFilter, DeathCauseIdFilter;
		
		switch (ListType) {
		case Death_ByDeathId:
			DeathIdFilter = new FilterPredicate("DeathId",
					FilterOperator.EQUAL, pDeath.getDeathId());
			query.setFilter(DeathIdFilter);
			break;
		case Death_ByDeathBk:
			ClndrIdFilter = new FilterPredicate("ClndrId",
					FilterOperator.EQUAL, pDeath.getClndrId());
			CntryIdFilter = new FilterPredicate("CntryId",
					FilterOperator.EQUAL, pDeath.getCntryId());
			DeathCauseIdFilter = new FilterPredicate("DeathCauseId",
					FilterOperator.EQUAL, pDeath.getDeathCauseId());
			Filter DeathBkFilter =
					  CompositeFilterOperator.and(CntryIdFilter, ClndrIdFilter, DeathCauseIdFilter);

			query.setFilter(DeathBkFilter);
			break;
		default:
			DeathIdFilter = new FilterPredicate("DeathId",
					FilterOperator.EQUAL, pDeath.getDeathId());
			query.setFilter(DeathIdFilter);
			break;
		}
		

		// Use PreparedQuery interface to retrieve results
		PreparedQuery pq = datastore.prepare(query);

		for (Entity result : pq.asIterable()) {
			oDeath.setDeathId((int) (long) result.getProperty("DeathId")); 
			oDeath.setClndrId((int) (long) result.getProperty("ClndrId")); 
			oDeath.setYearCd((int) (long) result.getProperty("YearCd")); 
			oDeath.setCntryId((int) (long) result.getProperty("CntryId")); 
			oDeath.setCntryNm((String) result.getProperty("CntryNm")); 
			oDeath.setCntryWHOCd((String) result.getProperty("CntryWHOCd")); 
			oDeath.setCntryISOCd((String) result.getProperty("CntryISOCd")); 
			oDeath.setDeathCauseId((int) (long) result.getProperty("DeathCauseId")); 
			oDeath.setDeathCauseNm((String) result.getProperty("DeathCauseNm")); 
			oDeath.setDeathCauseGBDCd((String) result.getProperty("DeathCauseGBDCd")); 
			oDeath.setDeathCauseGrpCd((String) result.getProperty("DeathCauseGrpCd")); 
			//
			oDeath.setDeathAllNum((double) result.getProperty("DeathAllNum")); 
			oDeath.setDeathMaleNum((double) result.getProperty("DeathMaleNum")); 
			oDeath.setDeathFemaleNum((double) result.getProperty("DeathFemaleNum")); 
			//
			oDeath.setDeathAll0To14Num((double) result.getProperty("DeathAll0To14Num")); 
			oDeath.setDeathMale0To14Num((double) result.getProperty("DeathMale0To14Num")); 
			oDeath.setDeathFemale0To14Num((double) result.getProperty("DeathFemale0To14Num")); 
			//
			oDeath.setDeathAll15To59Num((double) result.getProperty("DeathAll15To59Num")); 
			oDeath.setDeathMale15To59Num((double) result.getProperty("DeathMale15To59Num")); 
			oDeath.setDeathFemale15To59Num((double) result.getProperty("DeathFemale15To59Num")); 
			//
			oDeath.setDeathAll60PlusNum((double) result.getProperty("DeathAll60PlusNum")); 
			oDeath.setDeathMale60PlusNum((double) result.getProperty("DeathMale60PlusNum")); 
			oDeath.setDeathFemale60PlusNum((double) result.getProperty("DeathFemale60PlusNum")); 
 
		}

		return oDeath;

	}
	
	public Integer getMaxDeathId() {
		DatastoreService dsD4S = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("Death").addSort("DeathId",
				SortDirection.DESCENDING);

		Integer MaxDeathId = 0;

		// Use PreparedQuery interface to retrieve results
		PreparedQuery pq = dsD4S.prepare(query);

		for (Entity result : pq.asIterable()) {
			MaxDeathId = (int) (long) result.getProperty("DeathId")  ;
			break;
		}

		dsD4S = null;
		
		if (MaxDeathId == null)
		{
			MaxDeathId = 0;
		}
		
		return MaxDeathId;
	}
	
	public void addDeath(Death oDeath)
	{
		Integer MaxDeathId = getMaxDeathId();

		oDeath.setDeathId(MaxDeathId + 1);
		
		DatastoreService dsD4S = DatastoreServiceFactory.getDatastoreService();
		Transaction txn = dsD4S.beginTransaction();
		 		
 		Entity eDeath =    new Entity("Death", oDeath.getDeathId());
 		eDeath.setProperty("DeathId", oDeath.getDeathId());
 		eDeath.setProperty("ClndrId", oDeath.getClndrId());
 		eDeath.setProperty("CntryId", oDeath.getCntryId());
 		eDeath.setProperty("DeathCauseId", oDeath.getDeathCauseId());
 		//
 		eDeath.setProperty("DeathAllNum", oDeath.getDeathAllNum());
 		eDeath.setProperty("DeathMaleNum", oDeath.getDeathMaleNum());
 		eDeath.setProperty("DeathFemaleNum", oDeath.getDeathFemaleNum());
 		//
 		eDeath.setProperty("DeathAll0To14Num", oDeath.getDeathAll0To14Num());
 		eDeath.setProperty("DeathMale0To14Num", oDeath.getDeathMale0To14Num());
 		eDeath.setProperty("DeathFemale0To14Num", oDeath.getDeathFemale0To14Num());
 		//
 		eDeath.setProperty("DeathAll15To59Num", oDeath.getDeathAll15To59Num());
 		eDeath.setProperty("DeathMale15To59Num", oDeath.getDeathMale15To59Num());
 		eDeath.setProperty("DeathFemale15To59Num", oDeath.getDeathFemale15To59Num());
 		//
 		eDeath.setProperty("DeathAll60PlusNum", oDeath.getDeathAll60PlusNum());
 		eDeath.setProperty("DeathMale60PlusNum", oDeath.getDeathMale60PlusNum());
 		eDeath.setProperty("DeathFemale60PlusNum", oDeath.getDeathFemale60PlusNum());
 		
 		dsD4S.put(eDeath);
		txn.commit();

	}
	
	public void updateDeath(Death oDeath)
	{
		DatastoreService dsD4S = DatastoreServiceFactory.getDatastoreService();
		
 		Entity eDeath = new Entity("Death", oDeath.getDeathId());
 		eDeath.setProperty("DeathId", oDeath.getDeathId());
 		eDeath.setProperty("ClndrId", oDeath.getClndrId());
 		eDeath.setProperty("CntryId", oDeath.getCntryId());
 		eDeath.setProperty("DeathCauseId", oDeath.getDeathCauseId());
 		//
 		eDeath.setProperty("DeathAllNum", oDeath.getDeathAllNum());
 		eDeath.setProperty("DeathMaleNum", oDeath.getDeathMaleNum());
 		eDeath.setProperty("DeathFemaleNum", oDeath.getDeathFemaleNum());
 		//
 		eDeath.setProperty("DeathAll0To14Num", oDeath.getDeathAll0To14Num());
 		eDeath.setProperty("DeathMale0To14Num", oDeath.getDeathMale0To14Num());
 		eDeath.setProperty("DeathFemale0To14Num", oDeath.getDeathFemale0To14Num());
 		//
 		eDeath.setProperty("DeathAll15To59Num", oDeath.getDeathAll15To59Num());
 		eDeath.setProperty("DeathMale15To59Num", oDeath.getDeathMale15To59Num());
 		eDeath.setProperty("DeathFemale15To59Num", oDeath.getDeathFemale15To59Num());
 		//
 		eDeath.setProperty("DeathAll60PlusNum", oDeath.getDeathAll60PlusNum());
 		eDeath.setProperty("DeathMale60PlusNum", oDeath.getDeathMale60PlusNum());
 		eDeath.setProperty("DeathFemale60PlusNum", oDeath.getDeathFemale60PlusNum());		 
		
 		dsD4S.put(eDeath);

		
	}
	
	public void deleteDeath(Death oDeath)
	{
		//DatastoreService dsD4S = DatastoreServiceFactory.getDatastoreService();
		//Key ClndrKey = KeyFactory.createKey("Death", oDeath.getClndrId());
 		 		
		//dsD4S.delete(ClndrKey);		
	}



}
