package com.d4s.bil.DeathCause;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import com.d4s.bil.DeathCause.DeathCause;
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
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.datastore.Query.SortDirection;
 

public class DeathCauseSvc {
	public static final String DeathCauseList_ByDeathCauseId = "DeathCauseList_ByDeathCauseId";
	public static final String DeathCauseList_ByDeathCauseAll = "DeathCauseList_ByDeathCauseAll";
	public static final String DeathCauseList_ByDeathCauseGrp0 = "DeathCauseList_ByDeathCauseGrp0";
	public static final String DeathCauseList_ByDeathCauseGrp1 = "DeathCauseList_ByDeathCauseGrp1";
	public static final String DeathCauseList_ByDeathCauseGrp2 = "DeathCauseList_ByDeathCauseGrp2";
	public static final String DeathCauseList_ByDeathCauseGrp3 = "DeathCauseList_ByDeathCauseGrp3";
	public static final String DeathCauseList_ByDeathCauseGrp4 = "DeathCauseList_ByDeathCauseGrp4";
//
	public static final String DeathCause_ByDeathCauseId = "DeathCause_ByDeathCauseId";
	public static final String DeathCause_ByDeathCauseGBDCd = "DeathCause_ByDeathCauseGBDCd";

	public JsonObject getDeathCauseListJson(String ListType) {

		DeathCause pDeathCause = new DeathCause();
		DeathCause oDeathCauseGrp0 = new DeathCause();
		DeathCause oDeathCauseGrp1 = new DeathCause();
		DeathCause oDeathCauseGrp2 = new DeathCause();
		DeathCause oDeathCauseGrp3 = new DeathCause();
		DeathCauseSvc oDeathCauseSvc   = new DeathCauseSvc();
		
		JsonObjectBuilder jsonBldrDeathCauseList = Json.createObjectBuilder();
		JsonArrayBuilder jsonArBldrDeathCause = Json.createArrayBuilder();
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query query = new Query("DeathCause");

		Filter fltrDeathCauseGrp ;
		Filter fltrDeathCause;

		switch (ListType) {
		case DeathCauseList_ByDeathCauseAll:
			break;
		case DeathCauseList_ByDeathCauseGrp0:
			fltrDeathCauseGrp  = new FilterPredicate("DeathCauseGrpCd", FilterOperator.EQUAL, "0");
			fltrDeathCause = fltrDeathCauseGrp;
			query.setFilter(fltrDeathCause);
			break;
		case DeathCauseList_ByDeathCauseGrp1:
			fltrDeathCauseGrp  = new FilterPredicate("DeathCauseGrpCd", FilterOperator.EQUAL, "1");
			fltrDeathCause = fltrDeathCauseGrp;
			query.setFilter(fltrDeathCause);
			break;
		case DeathCauseList_ByDeathCauseGrp2:
			fltrDeathCauseGrp  = new FilterPredicate("DeathCauseGrpCd", FilterOperator.EQUAL, "2");
			fltrDeathCause = fltrDeathCauseGrp;
			query.setFilter(fltrDeathCause);
			break;
		case DeathCauseList_ByDeathCauseGrp3:
			fltrDeathCauseGrp  = new FilterPredicate("DeathCauseGrpCd", FilterOperator.EQUAL, "3");
			fltrDeathCause = fltrDeathCauseGrp;
			query.setFilter(fltrDeathCause);			
			break;
		case DeathCauseList_ByDeathCauseGrp4:
			fltrDeathCauseGrp  = new FilterPredicate("DeathCauseGrpCd", FilterOperator.EQUAL, "4");
			fltrDeathCause = fltrDeathCauseGrp;
			query.setFilter(fltrDeathCause);		
		default:
			fltrDeathCauseGrp  = new FilterPredicate("DeathCauseGrpCd", FilterOperator.EQUAL, "4");
			fltrDeathCause = fltrDeathCauseGrp;
			query.setFilter(fltrDeathCause);		
			break;
		}

		// Use PreparedQuery interface to retrieve results
		PreparedQuery pq = datastore.prepare(query);

		for (Entity result : pq.asIterable()) {
			// Get death cause group 0 details
			pDeathCause  = new DeathCause();
			oDeathCauseGrp0 = new DeathCause();
			pDeathCause.setDeathCauseId((int) (long) result.getProperty("DeathCauseGrp0Id"));
			if (pDeathCause.getDeathCauseId() > 0) {
				oDeathCauseGrp0 = oDeathCauseSvc.getDeathCause(DeathCause_ByDeathCauseId, pDeathCause);
			} else {
				oDeathCauseGrp0.setDeathCauseGBDCd("--"); 
				oDeathCauseGrp0.setDeathCauseNm("--"); 
			}
			// Get death cause group 1 details
			pDeathCause  = new DeathCause();
			oDeathCauseGrp1 = new DeathCause();
			pDeathCause.setDeathCauseId((int) (long) result.getProperty("DeathCauseGrp1Id"));
			if (pDeathCause.getDeathCauseId() > 0) {
				oDeathCauseGrp1 = oDeathCauseSvc.getDeathCause(DeathCause_ByDeathCauseId, pDeathCause);
			} else {
				oDeathCauseGrp1.setDeathCauseGBDCd("--"); 
				oDeathCauseGrp1.setDeathCauseNm("--"); 
			}
			// Get death cause group 2 details
			pDeathCause  = new DeathCause();
			oDeathCauseGrp2 = new DeathCause();
			pDeathCause.setDeathCauseId((int) (long) result.getProperty("DeathCauseGrp2Id"));
			if (pDeathCause.getDeathCauseId() > 0) {
				oDeathCauseGrp2 = oDeathCauseSvc.getDeathCause(DeathCause_ByDeathCauseId, pDeathCause);
			} else {
				oDeathCauseGrp2.setDeathCauseGBDCd("--"); 
				oDeathCauseGrp2.setDeathCauseNm("--"); 
			}
			// Get death cause group 3 details
			pDeathCause  = new DeathCause();
			oDeathCauseGrp3 = new DeathCause();
			pDeathCause.setDeathCauseId((int) (long) result.getProperty("DeathCauseGrp3Id"));
			if (pDeathCause.getDeathCauseId() > 0) {
				oDeathCauseGrp3 = oDeathCauseSvc.getDeathCause(DeathCause_ByDeathCauseId, pDeathCause);
			} else {
				oDeathCauseGrp3.setDeathCauseGBDCd("--"); 
				oDeathCauseGrp3.setDeathCauseNm("--"); 
			}

			
			JsonObjectBuilder jsonBldrDeathCause = Json.createObjectBuilder();
			jsonBldrDeathCause.add("DeathCauseId", (int)(long) result.getProperty("DeathCauseId"));
			jsonBldrDeathCause.add("DeathCauseGBDCd", (String) result.getProperty("DeathCauseGBDCd"));
			jsonBldrDeathCause.add("DeathCauseNm", (String) result.getProperty("DeathCauseNm"));
			jsonBldrDeathCause.add("DeathCauseGrpCd", (String) result.getProperty("DeathCauseGrpCd"));
			//
			jsonBldrDeathCause.add("DeathCauseGrp0Id", (int)(long) result.getProperty("DeathCauseGrp0Id"));
			jsonBldrDeathCause.add("DeathCauseGrp0GBDCd", oDeathCauseGrp0.getDeathCauseGBDCd());
			jsonBldrDeathCause.add("DeathCauseGrp0Nm", oDeathCauseGrp0.getDeathCauseNm());
			//
			jsonBldrDeathCause.add("DeathCauseGrp1Id", (int)(long) result.getProperty("DeathCauseGrp1Id"));
			jsonBldrDeathCause.add("DeathCauseGrp1GBDCd", oDeathCauseGrp1.getDeathCauseGBDCd());
			jsonBldrDeathCause.add("DeathCauseGrp1Nm", oDeathCauseGrp1.getDeathCauseNm());
			//
			jsonBldrDeathCause.add("DeathCauseGrp2Id", (int)(long) result.getProperty("DeathCauseGrp2Id"));
			jsonBldrDeathCause.add("DeathCauseGrp2GBDCd", oDeathCauseGrp2.getDeathCauseGBDCd());
			jsonBldrDeathCause.add("DeathCauseGrp2Nm", oDeathCauseGrp2.getDeathCauseNm());
			//
			jsonBldrDeathCause.add("DeathCauseGrp3Id", (int)(long) result.getProperty("DeathCauseGrp3Id"));
			jsonBldrDeathCause.add("DeathCauseGrp3GBDCd", oDeathCauseGrp3.getDeathCauseGBDCd());
			jsonBldrDeathCause.add("DeathCauseGrp3Nm",  oDeathCauseGrp3.getDeathCauseNm());
			JsonObject jsonDeathCause = jsonBldrDeathCause.build();
			jsonArBldrDeathCause.add(jsonDeathCause);

		}

		jsonBldrDeathCauseList.add("DeathCauseList", jsonArBldrDeathCause);
		JsonObject jsonDeathCauseList = jsonBldrDeathCauseList.build();

		return jsonDeathCauseList;
	}

	
	public Integer getMaxDeathCauseId() {
		DatastoreService dsD4S = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("DeathCause").addSort("DeathCauseId",
				SortDirection.DESCENDING);

		Integer MaxDeathCauseId = 0;

		// Use PreparedQuery interface to retrieve results
		PreparedQuery pq = dsD4S.prepare(query);

		for (Entity result : pq.asIterable()) {
			MaxDeathCauseId = (int) (long) result.getProperty("DeathCauseId");
			break;
		}

		dsD4S = null;
		
		if (MaxDeathCauseId == null)
		{
			MaxDeathCauseId = 0;
		}
		
		return MaxDeathCauseId;
	}

	public JsonObject getDeathCauseJson(String ListType, DeathCause pDeathCause) {

		JsonObjectBuilder jsonBldrDeathCause = Json.createObjectBuilder();

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query query = new Query("DeathCause");
		Filter DeathCauseFilter;
		
		switch (ListType) {
		case DeathCause_ByDeathCauseId:
			DeathCauseFilter = new FilterPredicate("DeathCauseId",
					FilterOperator.EQUAL, pDeathCause.getDeathCauseId());
			break;
		case DeathCause_ByDeathCauseGBDCd:
			DeathCauseFilter = new FilterPredicate("CntryWHOCd",
					FilterOperator.EQUAL, pDeathCause.getDeathCauseGBDCd());
			break;
		default:
			DeathCauseFilter = new FilterPredicate("DeathCauseId",
					FilterOperator.EQUAL, pDeathCause.getDeathCauseId());
			break;
		}
		
		query.setFilter(DeathCauseFilter);

		// Use PreparedQuery interface to retrieve results
		PreparedQuery pq = datastore.prepare(query);

		for (Entity result : pq.asIterable()) {
			jsonBldrDeathCause.add("DeathCauseId", (int)(long) result.getProperty("DeathCauseId"));
			jsonBldrDeathCause.add("DeathCauseGBDCd", (String) result.getProperty("DeathCauseGBDCd"));
			jsonBldrDeathCause.add("DeathCauseNm", (String) result.getProperty("DeathCauseNm"));
			jsonBldrDeathCause.add("DeathCauseGrpCd", (String) result.getProperty("DeathCauseGrpCd"));
			//
			jsonBldrDeathCause.add("DeathCauseGrp0Id", (int)(long) result.getProperty("DeathCauseGrp0Id"));
			//jsonBldrDeathCause.add("DeathCauseGrp0GBDCd", (String) result.getProperty("DeathCauseGrp0GBDCd"));
			//jsonBldrDeathCause.add("DeathCauseGrp0Nm", (String) result.getProperty("DeathCauseGrp0Nm"));
			//
			jsonBldrDeathCause.add("DeathCauseGrp1Id", (int)(long) result.getProperty("DeathCauseGrp1Id"));
			//jsonBldrDeathCause.add("DeathCauseGrp1GBDCd", (String) result.getProperty("DeathCauseGrp1GBDCd"));
			//jsonBldrDeathCause.add("DeathCauseGrp1Nm", (String) result.getProperty("DeathCauseGrp1Nm"));
			//
			jsonBldrDeathCause.add("DeathCauseGrp2Id", (int)(long) result.getProperty("DeathCauseGrp2Id"));
			//jsonBldrDeathCause.add("DeathCauseGrp2GBDCd", (String) result.getProperty("DeathCauseGrp2GBDCd"));
			//jsonBldrDeathCause.add("DeathCauseGrp2Nm", (String) result.getProperty("DeathCauseGrp2Nm"));
			//
			jsonBldrDeathCause.add("DeathCauseGrp3Id", (int)(long) result.getProperty("DeathCauseGrp3Id"));
			//jsonBldrDeathCause.add("DeathCauseGrp3GBDCd", (String) result.getProperty("DeathCauseGrp3GBDCd"));
			//jsonBldrDeathCause.add("DeathCauseGrp3Nm", (String) result.getProperty("DeathCauseGrp3Nm"));
		}

		JsonObject jsonDeathCause = jsonBldrDeathCause.build();

		return jsonDeathCause;

	}

	public DeathCause getDeathCause(String ListType, DeathCause pDeathCause) {

		DeathCause rtnDeathCause = new DeathCause();
		rtnDeathCause.setDeathCauseId(0);
 
		DatastoreService dsD4s = DatastoreServiceFactory
				.getDatastoreService();

		Query query = new Query("DeathCause");
		Filter DeathCauseFilter;
		
		switch (ListType) {
		case DeathCause_ByDeathCauseId:
			DeathCauseFilter = new FilterPredicate("DeathCauseId",
					FilterOperator.EQUAL, pDeathCause.getDeathCauseId());
			break;
		case DeathCause_ByDeathCauseGBDCd:
			DeathCauseFilter = new FilterPredicate("DeathCauseGBDCd",
					FilterOperator.EQUAL, pDeathCause.getDeathCauseGBDCd());
			break;
		default:
			DeathCauseFilter = new FilterPredicate("DeathCauseId",
					FilterOperator.EQUAL, pDeathCause.getDeathCauseId());
			break;
		}
		
		query.setFilter(DeathCauseFilter);

		// Use PreparedQuery interface to retrieve results
		PreparedQuery pq = dsD4s.prepare(query);

		for (Entity result : pq.asIterable()) {
			rtnDeathCause.setDeathCauseId((int) (long) result.getProperty("DeathCauseId"));
			rtnDeathCause.setDeathCauseGBDCd( (String) result.getProperty("DeathCauseGBDCd"));
			rtnDeathCause.setDeathCauseNm( (String) result.getProperty("DeathCauseNm"));
			rtnDeathCause.setDeathCauseGrpCd( (String) result.getProperty("DeathCauseGrpCd"));
			//
			rtnDeathCause.setDeathCauseGrp0Id( (int) (long) result.getProperty("DeathCauseGrp0Id"));
			rtnDeathCause.setDeathCauseGrp0GBDCd( (String) result.getProperty("DeathCauseGrp0GBDCd"));
			rtnDeathCause.setDeathCauseGrp0Nm( (String) result.getProperty("DeathCauseGrp0Nm"));
			//
			rtnDeathCause.setDeathCauseGrp1Id( (int) (long) result.getProperty("DeathCauseGrp1Id"));
			rtnDeathCause.setDeathCauseGrp1GBDCd( (String) result.getProperty("DeathCauseGrp1GBDCd"));
			rtnDeathCause.setDeathCauseGrp1Nm( (String) result.getProperty("DeathCauseGrp1Nm"));
			//
			rtnDeathCause.setDeathCauseGrp2Id( (int) (long) result.getProperty("DeathCauseGrp2Id"));
			rtnDeathCause.setDeathCauseGrp2GBDCd( (String) result.getProperty("DeathCauseGrp2GBDCd"));
			rtnDeathCause.setDeathCauseGrp2Nm( (String) result.getProperty("DeathCauseGrp2Nm"));
			//
			rtnDeathCause.setDeathCauseGrp3Id( (int) (long) result.getProperty("DeathCauseGrp3Id"));
			rtnDeathCause.setDeathCauseGrp3GBDCd( (String) result.getProperty("DeathCauseGrp3GBDCd"));
			rtnDeathCause.setDeathCauseGrp3Nm( (String) result.getProperty("DeathCauseGrp3Nm"));
		}

		return rtnDeathCause;
	}

	
	public void addDeathCause(DeathCause oDeathCause)
	{
		Integer MaxDeathCauseId = getMaxDeathCauseId();

		oDeathCause.setDeathCauseId(MaxDeathCauseId + 5);
		
		DeathCause pDeathCause = new DeathCause();
		//DeathCause oDeathCauseGrp0 = new DeathCause();
		DeathCause oDeathCauseGrp1 = new DeathCause();
		DeathCause oDeathCauseGrp2 = new DeathCause();
		DeathCause oDeathCauseGrp3 = new DeathCause();
		
		
		if (oDeathCause.getDeathCauseGrpCd().compareToIgnoreCase("0") == 0) {
			//Get group 3 details
			oDeathCause.setDeathCauseGrp3Id(0);
			//Get group 2 details
			oDeathCause.setDeathCauseGrp2Id(0);
			//Get group 1 details
 			oDeathCause.setDeathCauseGrp1Id(0);
			//Get group 0 details
 			oDeathCause.setDeathCauseGrp0Id(0);
		} else if (oDeathCause.getDeathCauseGrpCd().compareToIgnoreCase("1") == 0) {
			//Get group 3 details
			oDeathCause.setDeathCauseGrp3Id(0);
			//Get group 2 details
			oDeathCause.setDeathCauseGrp2Id(0);
			//Get group 1 details
 			oDeathCause.setDeathCauseGrp1Id(0);
			//Get group 0 details
			//pDeathCause.setDeathCauseId(oDeathCause.getDeathCauseGrp0Id());
			//oDeathCauseGrp0 = getDeathCause(pDeathCause);
		} else if (oDeathCause.getDeathCauseGrpCd().compareToIgnoreCase("2") == 0) {
			//Get group 3 details
			oDeathCause.setDeathCauseGrp3Id(0);
			//Get group 2 details
			oDeathCause.setDeathCauseGrp2Id(0);
			//Get group 1 details
			pDeathCause.setDeathCauseId(oDeathCause.getDeathCauseGrp1Id());
			oDeathCauseGrp1 = getDeathCause(DeathCause_ByDeathCauseId, pDeathCause);
			oDeathCause.setDeathCauseGrp0Id(oDeathCauseGrp1.getDeathCauseGrp0Id());
			//Get group 0 details
			//pDeathCause.setDeathCauseId(oDeathCause.getDeathCauseGrp0Id());
			//oDeathCauseGrp0 = getDeathCause(pDeathCause);
		} else if (oDeathCause.getDeathCauseGrpCd().compareToIgnoreCase("3") == 0) {
			oDeathCause.setDeathCauseGrp3Id(0);
			//Get group 2 details
			pDeathCause.setDeathCauseId(oDeathCause.getDeathCauseGrp2Id());
			oDeathCauseGrp2 = getDeathCause(DeathCause_ByDeathCauseId, pDeathCause);
			oDeathCause.setDeathCauseGrp1Id(oDeathCauseGrp2.getDeathCauseGrp1Id());
			//Get group 1 details
			pDeathCause.setDeathCauseId(oDeathCause.getDeathCauseGrp1Id());
			oDeathCauseGrp1 = getDeathCause(DeathCause_ByDeathCauseId, pDeathCause);
			oDeathCause.setDeathCauseGrp0Id(oDeathCauseGrp1.getDeathCauseGrp0Id());
			//Get group 0 details
			//pDeathCause.setDeathCauseId(oDeathCause.getDeathCauseGrp0Id());
			//oDeathCauseGrp0 = getDeathCause(pDeathCause);
		} else if (oDeathCause.getDeathCauseGrpCd().compareToIgnoreCase("4") == 0) {
			//Get group 3 details
			pDeathCause.setDeathCauseId(oDeathCause.getDeathCauseGrp3Id());
			oDeathCauseGrp3 = getDeathCause(DeathCause_ByDeathCauseId, pDeathCause);
			oDeathCause.setDeathCauseGrp2Id(oDeathCauseGrp3.getDeathCauseGrp2Id());
			//Get group 2 details
			pDeathCause.setDeathCauseId(oDeathCause.getDeathCauseGrp2Id());
			oDeathCauseGrp2 = getDeathCause(DeathCause_ByDeathCauseId, pDeathCause);
			oDeathCause.setDeathCauseGrp1Id(oDeathCauseGrp2.getDeathCauseGrp1Id());
			//Get group 1 details
			pDeathCause.setDeathCauseId(oDeathCause.getDeathCauseGrp1Id());
			oDeathCauseGrp1 = getDeathCause(DeathCause_ByDeathCauseId, pDeathCause);
			oDeathCause.setDeathCauseGrp0Id(oDeathCauseGrp1.getDeathCauseGrp0Id());
			//Get group 0 details
			//pDeathCause.setDeathCauseId(oDeathCause.getDeathCauseGrp0Id());
			//oDeathCauseGrp0 = getDeathCause(pDeathCause);
		}

		DatastoreService dsD4S = DatastoreServiceFactory
				.getDatastoreService();
		
		Transaction txn = dsD4S.beginTransaction();


		Entity eDeathCause = new Entity("DeathCause", oDeathCause.getDeathCauseId());
		eDeathCause.setProperty("DeathCauseId", oDeathCause.getDeathCauseId());
		eDeathCause.setProperty("DeathCauseGBDCd", oDeathCause.getDeathCauseGBDCd());
		eDeathCause.setProperty("DeathCauseNm", oDeathCause.getDeathCauseNm());
		eDeathCause.setProperty("DeathCauseGrpCd", oDeathCause.getDeathCauseGrpCd());
		eDeathCause.setProperty("DeathCauseGrp0Id", oDeathCause.getDeathCauseGrp0Id());
		eDeathCause.setProperty("DeathCauseGrp1Id", oDeathCause.getDeathCauseGrp1Id());
		eDeathCause.setProperty("DeathCauseGrp2Id", oDeathCause.getDeathCauseGrp2Id());
		eDeathCause.setProperty("DeathCauseGrp3Id", oDeathCause.getDeathCauseGrp3Id());

		dsD4S.put(eDeathCause);
		txn.commit();
	}
		
	public void updateDeathCause(DeathCause oDeathCause) {
		
		DeathCause pDeathCause = new DeathCause();
		//DeathCause oDeathCauseGrp0 = new DeathCause();
		DeathCause oDeathCauseGrp1 = new DeathCause();
		DeathCause oDeathCauseGrp2 = new DeathCause();
		DeathCause oDeathCauseGrp3 = new DeathCause();
		
		
		if (oDeathCause.getDeathCauseGrpCd().compareToIgnoreCase("0") == 0) {
			//Get group 3 details
			oDeathCause.setDeathCauseGrp3Id(0);
			//Get group 2 details
			oDeathCause.setDeathCauseGrp2Id(0);
			//Get group 1 details
 			oDeathCause.setDeathCauseGrp1Id(0);
			//Get group 0 details
 			oDeathCause.setDeathCauseGrp0Id(0);
		} else if (oDeathCause.getDeathCauseGrpCd().compareToIgnoreCase("1") == 0) {
			//Get group 3 details
			oDeathCause.setDeathCauseGrp3Id(0);
			//Get group 2 details
			oDeathCause.setDeathCauseGrp2Id(0);
			//Get group 1 details
 			oDeathCause.setDeathCauseGrp1Id(0);
			//Get group 0 details
			//pDeathCause.setDeathCauseId(oDeathCause.getDeathCauseGrp0Id());
			//oDeathCauseGrp0 = getDeathCause(pDeathCause);
		} else if (oDeathCause.getDeathCauseGrpCd().compareToIgnoreCase("2") == 0) {
			//Get group 3 details
			oDeathCause.setDeathCauseGrp3Id(0);
			//Get group 2 details
			oDeathCause.setDeathCauseGrp2Id(0);
			//Get group 1 details
			pDeathCause.setDeathCauseId(oDeathCause.getDeathCauseGrp1Id());
			oDeathCauseGrp1 = getDeathCause(DeathCause_ByDeathCauseId, pDeathCause);
			oDeathCause.setDeathCauseGrp0Id(oDeathCauseGrp1.getDeathCauseGrp0Id());
			//Get group 0 details
			//pDeathCause.setDeathCauseId(oDeathCause.getDeathCauseGrp0Id());
			//oDeathCauseGrp0 = getDeathCause(pDeathCause);
		} else if (oDeathCause.getDeathCauseGrpCd().compareToIgnoreCase("3") == 0) {
			oDeathCause.setDeathCauseGrp3Id(0);
			//Get group 2 details
			pDeathCause.setDeathCauseId(oDeathCause.getDeathCauseGrp2Id());
			oDeathCauseGrp2 = getDeathCause(DeathCause_ByDeathCauseId, pDeathCause);
			oDeathCause.setDeathCauseGrp1Id(oDeathCauseGrp2.getDeathCauseGrp1Id());
			//Get group 1 details
			pDeathCause.setDeathCauseId(oDeathCause.getDeathCauseGrp1Id());
			oDeathCauseGrp1 = getDeathCause(DeathCause_ByDeathCauseId, pDeathCause);
			oDeathCause.setDeathCauseGrp0Id(oDeathCauseGrp1.getDeathCauseGrp0Id());
			//Get group 0 details
			//pDeathCause.setDeathCauseId(oDeathCause.getDeathCauseGrp0Id());
			//oDeathCauseGrp0 = getDeathCause(pDeathCause);
		} else if (oDeathCause.getDeathCauseGrpCd().compareToIgnoreCase("4") == 0) {
			//Get group 3 details
			pDeathCause.setDeathCauseId(oDeathCause.getDeathCauseGrp3Id());
			oDeathCauseGrp3 = getDeathCause(DeathCause_ByDeathCauseId, pDeathCause);
			oDeathCause.setDeathCauseGrp2Id(oDeathCauseGrp3.getDeathCauseGrp2Id());
			//Get group 2 details
			pDeathCause.setDeathCauseId(oDeathCause.getDeathCauseGrp2Id());
			oDeathCauseGrp2 = getDeathCause(DeathCause_ByDeathCauseId, pDeathCause);
			oDeathCause.setDeathCauseGrp1Id(oDeathCauseGrp2.getDeathCauseGrp1Id());
			//Get group 1 details
			pDeathCause.setDeathCauseId(oDeathCause.getDeathCauseGrp1Id());
			oDeathCauseGrp1 = getDeathCause(DeathCause_ByDeathCauseId, pDeathCause);
			oDeathCause.setDeathCauseGrp0Id(oDeathCauseGrp1.getDeathCauseGrp0Id());
			//Get group 0 details
			//pDeathCause.setDeathCauseId(oDeathCause.getDeathCauseGrp0Id());
			//oDeathCauseGrp0 = getDeathCause(pDeathCause);
		}
		
		DatastoreService dsD4S = DatastoreServiceFactory
				.getDatastoreService();

		Entity eDeathCause = new Entity("DeathCause", oDeathCause.getDeathCauseId());
		eDeathCause.setProperty("DeathCauseId", oDeathCause.getDeathCauseId());
		eDeathCause.setProperty("DeathCauseGBDCd", oDeathCause.getDeathCauseGBDCd());
		eDeathCause.setProperty("DeathCauseNm", oDeathCause.getDeathCauseNm());
		eDeathCause.setProperty("DeathCauseGrpCd", oDeathCause.getDeathCauseGrpCd());
		eDeathCause.setProperty("DeathCauseGrp0Id", oDeathCause.getDeathCauseGrp0Id()); 		//
		eDeathCause.setProperty("DeathCauseGrp1Id", oDeathCause.getDeathCauseGrp1Id());
		eDeathCause.setProperty("DeathCauseGrp2Id", oDeathCause.getDeathCauseGrp2Id());
		eDeathCause.setProperty("DeathCauseGrp3Id", oDeathCause.getDeathCauseGrp3Id());
		dsD4S.put(eDeathCause);
	}
	
	public void deleteDeathCause(DeathCause oDeathCause)
	{
		//DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		//Key DeathCauseKey = KeyFactory.createKey("DeathCause", oDeathCause.getDeathCauseId());
		 		
		//datastore.delete(DeathCauseKey);		
	}


}
