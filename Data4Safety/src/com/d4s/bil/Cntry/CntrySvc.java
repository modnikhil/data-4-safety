package com.d4s.bil.Cntry;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import com.d4s.bil.Clndr.Clndr;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.CompositeFilter;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.Transaction;
 

public class CntrySvc {
	public static final String CntryList_ByCntryAll = "CntryList_ByCntryAll";
	//
	public static final String Cntry_ByCntryId = "Cntry_ByCntryId";
	public static final String Cntry_ByCntryWHOCd = "Cntry_ByCntryWHOCd";

	public JsonObject getCntryListJson(String ListType, Integer pCntryId) {

		JsonObjectBuilder jsonBldrCntryList = Json.createObjectBuilder();
		JsonArrayBuilder jsonArBldrCntry = Json.createArrayBuilder();
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query query = new Query("Cntry");

		Filter CntryFilter = new FilterPredicate("CntryId",
				FilterOperator.EQUAL, pCntryId);
		Filter PplFilter;

		switch (ListType) {
		case Cntry_ByCntryId:
			PplFilter = CntryFilter;
			break;
		default:
			PplFilter = CntryFilter;
			break;
		}

		switch (ListType) {
		case CntryList_ByCntryAll:
			break;
		default:
			query.setFilter(PplFilter);
			break;
		}

		// Use PreparedQuery interface to retrieve results
		PreparedQuery pq = datastore.prepare(query);

		for (Entity result : pq.asIterable()) {
			JsonObjectBuilder jsonBldrCntry = Json.createObjectBuilder();
			jsonBldrCntry.add("CntryId", (Long) result.getProperty("CntryId"));
			jsonBldrCntry.add("CntryWHOCd",
					(String) result.getProperty("CntryWHOCd"));
			jsonBldrCntry.add("CntryISOCd",
					(String) result.getProperty("CntryISOCd"));
			jsonBldrCntry
					.add("CntryNm", (String) result.getProperty("CntryNm"));
			JsonObject jsonCntry = jsonBldrCntry.build();
			jsonArBldrCntry.add(jsonCntry);

		}

		jsonBldrCntryList.add("CntryList", jsonArBldrCntry);
		JsonObject jsonCntryList = jsonBldrCntryList.build();

		return jsonCntryList;
	}

	public JsonObject getCntryJson(Cntry oCntry) {

		JsonObjectBuilder jsonBldrCntry = Json.createObjectBuilder();

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query query = new Query("Cntry");

		Filter CntryFilter = new FilterPredicate("CntryId",
				FilterOperator.EQUAL, oCntry.getCntryId());
 
		query.setFilter(CntryFilter);

		// Use PreparedQuery interface to retrieve results
		PreparedQuery pq = datastore.prepare(query);

		for (Entity result : pq.asIterable()) {
			jsonBldrCntry.add("CntryId", (Long) result.getProperty("CntryId"));
			jsonBldrCntry.add("CntryWHOCd",
					(String) result.getProperty("CntryWHOCd"));
			jsonBldrCntry.add("CntryISOCd",
					(String) result.getProperty("CntryISOCd"));
			jsonBldrCntry
					.add("CntryNm", (String) result.getProperty("CntryNm"));
		}

		JsonObject jsonCntry = jsonBldrCntry.build();

		return jsonCntry;

	}

	public Cntry getCntry(String ListType, Cntry pCntry) {

		Cntry oCntry = new Cntry();
		oCntry.setCntryId(0);
		
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query query = new Query("Cntry");
		Filter CntryFilter;
		
		switch (ListType) {
		case Cntry_ByCntryId:
			CntryFilter = new FilterPredicate("CntryId",
					FilterOperator.EQUAL, pCntry.getCntryId());
			break;
		case Cntry_ByCntryWHOCd:
			CntryFilter = new FilterPredicate("CntryWHOCd",
					FilterOperator.EQUAL, pCntry.getCntryWHOCd());
			break;
		default:
			CntryFilter = new FilterPredicate("CntryId",
					FilterOperator.EQUAL, pCntry.getCntryId());
			break;
		}
		
		query.setFilter(CntryFilter);

		// Use PreparedQuery interface to retrieve results
		PreparedQuery pq = datastore.prepare(query);

		for (Entity result : pq.asIterable()) {
			oCntry.setCntryId((int) (long) result.getProperty("CntryId")); 
			oCntry.setCntryWHOCd((String) result.getProperty("CntryWHOCd")); 
			oCntry.setCntryISOCd((String) result.getProperty("CntryISOCd")); 
			oCntry.setCntryNm((String) result.getProperty("CntryNm")); 
		}

		return oCntry;

	}

	public Integer getMaxCntryId() {
		DatastoreService dsD4S = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("Cntry").addSort("CntryId",
				SortDirection.DESCENDING);

		Integer MaxCntryId = 0;

		// Use PreparedQuery interface to retrieve results
		PreparedQuery pq = dsD4S.prepare(query);

		for (Entity result : pq.asIterable()) {
			MaxCntryId = (int) (long) result.getProperty("CntryId");
			break;
		}

		if (MaxCntryId == null)
		{
			MaxCntryId = 0;
		}
		dsD4S = null;

		return MaxCntryId;
	}

	public void addCntry(Cntry oCntry) {
		Integer MaxCntryId = getMaxCntryId();

		oCntry.setCntryId(MaxCntryId + 5);

		DatastoreService dsD4S = DatastoreServiceFactory
				.getDatastoreService();
		
		Transaction txn = dsD4S.beginTransaction();
		
		Entity country = new Entity("Cntry", oCntry.getCntryId());
		country.setProperty("CntryId", oCntry.getCntryId());
		country.setProperty("CntryWHOCd", oCntry.getCntryWHOCd());
		country.setProperty("CntryISOCd", oCntry.getCntryISOCd());
		country.setProperty("CntryNm", oCntry.getCntryNm());

		dsD4S.put(country);
		txn.commit();
		 
	}

	public void updateCntry(Cntry oCntry) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Entity country = new Entity("Cntry", oCntry.getCntryId());
		country.setProperty("CntryId", oCntry.getCntryId());
		country.setProperty("CntryWHOCd", oCntry.getCntryWHOCd());
		country.setProperty("CntryISOCd", oCntry.getCntryISOCd());
		country.setProperty("CntryNm", oCntry.getCntryNm());

		datastore.put(country);

	}

	public void deleteCntry(Cntry oCntry) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		// Key CntryKey = KeyFactory.createKey("Cntry", oCntry.getCntryId());
		// Entity country = new Entity("CNTRY", CntryKey);

		// datastore.delete(CntryKey);
	}

//	public void convertCntry() {
//
//		DatastoreService datastore = DatastoreServiceFactory
//				.getDatastoreService();
//
//		// /
////		Entity CNTRY;
////		CNTRY = new Entity("CNTRY", 1);
////		CNTRY.setProperty("CNTRY_ID", 1);
////		CNTRY.setProperty("CNTRY_WHO_CD", "CNTRY_WHO_CD-1");
////		CNTRY.setProperty("CNTRY_ISO_CD", "CNTRY_ISO_CD-1");
////		CNTRY.setProperty("CNTRY_NM", "CNTRY_NM-1");
////		datastore.put(CNTRY);
////
////		CNTRY = new Entity("CNTRY", 2);
////		CNTRY.setProperty("CNTRY_ID", 2);
////		CNTRY.setProperty("CNTRY_WHO_CD", "CNTRY_WHO_CD-2");
////		CNTRY.setProperty("CNTRY_ISO_CD", "CNTRY_ISO_CD-2");
////		CNTRY.setProperty("CNTRY_NM", "CNTRY_NM-2");
////		datastore.put(CNTRY);
//
//		// /
//		Query query = new Query("CNTRY");
//
//		// Use PreparedQuery interface to retrieve results
//		PreparedQuery pq = datastore.prepare(query);
//
//		for (Entity result : pq.asIterable()) {
//			Cntry newCntry = new Cntry();
//			newCntry.setCntryId(0);
//			newCntry.setCntryISOCd((String) result.getProperty("CNTRY_ISO_CD"));
//			newCntry.setCntryNm((String) result.getProperty("CNTRY_NM"));
//			newCntry.setCntryWHOCd((String) result.getProperty("CNTRY_WHO_CD"));
//			addCountryRec(newCntry);
//
//		}
//
//	}

}
