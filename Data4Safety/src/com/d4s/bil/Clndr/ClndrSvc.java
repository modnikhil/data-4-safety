package com.d4s.bil.Clndr;

import java.util.Date;

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
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Transaction;

 

public class ClndrSvc {

	public static final String ClndrList_ByClndrAll = "ClndrList_ByClndrAll";
//
	public static final String Clndr_ByClndrId = "Clndr_ByClndrId";
	public static final String Clndr_ByYearCd = "Clndr_ByYearCd";

	public JsonObject getClndrListJson(String ListType, Integer pClndrId) {

		JsonObjectBuilder jsonBldrClndrList = Json.createObjectBuilder();
		JsonArrayBuilder jsonArBldrClndr = Json.createArrayBuilder();
		DatastoreService dsD4S = DatastoreServiceFactory
				.getDatastoreService();

		Query query = new Query("Clndr");

		Filter ClndrFilter = new FilterPredicate("ClndrId",
				FilterOperator.EQUAL, pClndrId);
		Filter ClndrCmptFilter;

		switch (ListType) {
		case Clndr_ByClndrId:
			ClndrCmptFilter = ClndrFilter;
			break;
		default:
			ClndrCmptFilter = ClndrFilter;
			break;
		}

		switch (ListType) {
		case ClndrList_ByClndrAll:
			break;
		default:
			query.setFilter(ClndrCmptFilter);
			break;
		}

		// Use PreparedQuery interface to retrieve results
		PreparedQuery pq = dsD4S.prepare(query);

		for (Entity result : pq.asIterable()) {
			JsonObjectBuilder jsonBldrClndr = Json.createObjectBuilder();
			jsonBldrClndr.add("ClndrId", (Long) result.getProperty("ClndrId"));
		//	jsonBldrClndr.add("ClndrDt", (String) result.getProperty("ClndrDt"));
			jsonBldrClndr.add("YearEndCd",
					(String) result.getProperty("YearEndCd"));
			jsonBldrClndr.add("YearCd", (Long) result.getProperty("YearCd"));
			JsonObject jsonClndr = jsonBldrClndr.build();
			jsonArBldrClndr.add(jsonClndr);

		}

		jsonBldrClndrList.add("ClndrList", jsonArBldrClndr);
		JsonObject jsonClndrList = jsonBldrClndrList.build();

		return jsonClndrList;
	}

	public JsonObject getClndrJson(Clndr oClndr) {

		JsonObjectBuilder jsonBldrClndr = Json.createObjectBuilder();

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query query = new Query("Clndr");

		Filter ClndrFilter = new FilterPredicate("ClndrId",
				FilterOperator.EQUAL, oClndr.getClndrId());
 
		query.setFilter(ClndrFilter);

		// Use PreparedQuery interface to retrieve results
		PreparedQuery pq = datastore.prepare(query);

		for (Entity result : pq.asIterable()) {
			jsonBldrClndr.add("ClndrId", (Long) result.getProperty("ClndrId"));
			jsonBldrClndr.add("YearCd",
					(Long) result.getProperty("YearCd"));
			jsonBldrClndr.add("YearEndCd", (String) result.getProperty("YearEndCd"));
		}

		JsonObject jsonClndr = jsonBldrClndr.build();

		return jsonClndr;

	}

	public Clndr getClndr(String ListType, Clndr pClndr) {

		Clndr oClndr = new Clndr();
		oClndr.setClndrId(0);
		
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query query = new Query("Clndr");
		Filter ClndrFilter;
		
		switch (ListType) {
		case Clndr_ByClndrId:
			ClndrFilter = new FilterPredicate("ClndrId",
					FilterOperator.EQUAL, pClndr.getClndrId());
			break;
		case Clndr_ByYearCd:
			ClndrFilter = new FilterPredicate("YearCd",
					FilterOperator.EQUAL, pClndr.getYearCd());
			break;
		default:
			ClndrFilter = new FilterPredicate("ClndrId",
					FilterOperator.EQUAL, pClndr.getClndrId());
			break;
		}
		
		query.setFilter(ClndrFilter);

		// Use PreparedQuery interface to retrieve results
		PreparedQuery pq = datastore.prepare(query);

		for (Entity result : pq.asIterable()) {
			oClndr.setClndrId((int) (long) result.getProperty("ClndrId")); 
			oClndr.setYearCd((int) (long) result.getProperty("YearCd")); 
			oClndr.setYearEndCd((String) result.getProperty("YearEndCd")); 			
		}

		return oClndr;
	}
	


	public void addClndrRec(Clndr oClndr)
	{

		DatastoreService dsD4S = DatastoreServiceFactory
				.getDatastoreService();
		
		Transaction txn = dsD4S.beginTransaction();

		Entity eClndr = new Entity("Clndr", oClndr.getClndrId());
		eClndr.setProperty("ClndrId", oClndr.getClndrId());
	//	eClndr.setProperty("ClndrDt", oClndr.getClndrDt());
		eClndr.setProperty("YearCd", oClndr.getYearCd());
		eClndr.setProperty("YearEndCd", oClndr.getYearEndCd());

		dsD4S.put(eClndr);
		txn.commit();

	}
		
	public void updateClndrRec(Clndr oClndr) {
		
		DatastoreService dsD4S = DatastoreServiceFactory
				.getDatastoreService();

		Entity eClndr = new Entity("Clndr", oClndr.getClndrId());
		eClndr.setProperty("ClndrId", oClndr.getClndrId());
	//	eClndr.setProperty("ClndrDt", oClndr.getClndrDt());
		eClndr.setProperty("YearCd", oClndr.getYearCd());
		eClndr.setProperty("YearEndCd", oClndr.getYearEndCd());

		dsD4S.put(eClndr);
	}
		
	public void deleteClndrRec(Clndr oClndr)
	{
		//DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		//Key ClndrKey = KeyFactory.createKey("Clndr", oClndr.getClndrId());
		//Entity country = new Entity("CNTRY", ClndrKey);
		 		
		//datastore.delete(ClndrKey);		
	}



}
