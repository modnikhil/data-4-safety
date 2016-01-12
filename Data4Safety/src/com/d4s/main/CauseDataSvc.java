package com.d4s.main;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public class CauseDataSvc {
	
	public void createCause(String CauseId, String CauseGBDCd, String CauseNm)
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity cause = new Entity("CAUSE");
		cause.setProperty("CAUSE_KEY_ID", CauseId);
		cause.setProperty("CAUSE_GBD_CD", CauseGBDCd);
		cause.setUnindexedProperty("CAUSE_NM", CauseNm);
		
		datastore.put(cause);

		
	}
	
	public void updateCause()
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity cause = new Entity("CNTRY");
		cause.setProperty("CNTRY_CD", "USA");
		cause.setProperty("CNTRY_KEY_ID", "1");
		cause.setUnindexedProperty("CNTRY_NM", "United States of America");
		
		datastore.put(cause);

		
	}
	
	public void deleteCause()
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity cause = new Entity("CNTRY");
		cause.setProperty("CNTRY_CD", "USA");
		cause.setProperty("CNTRY_KEY_ID", "1");
		cause.setUnindexedProperty("CNTRY_NM", "United States of America");
		
		datastore.put(cause);

		
	}



}
