package com.d4s.main;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public class DeathFactDataSvc {
	
	public void createDeathFact(String CountryId, String CauseId, String CalendarId, String DeathNum, String DALYNum)
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity death_fact = new Entity("DEATH_FACT");
		death_fact.setProperty("CNTRY_KEY_ID", CountryId);
		death_fact.setProperty("CAUSE_KEY_ID", CauseId);
		death_fact.setProperty("CLNDR_KEY_ID", CalendarId);
		death_fact.setUnindexedProperty("DEATH_NUM", DeathNum);
		death_fact.setUnindexedProperty("DALY_NUM", DALYNum);
		
		
		datastore.put(death_fact);

		
	}
	
	public void updateEntity()
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity death_fact = new Entity("CNTRY");
		death_fact.setProperty("CNTRY_CD", "USA");
		death_fact.setProperty("CNTRY_KEY_ID", "1");
		death_fact.setUnindexedProperty("CNTRY_NM", "United States of America");
		
		datastore.put(death_fact);

		
	}
	
	public void deleteEntity()
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity death_fact = new Entity("CNTRY");
		death_fact.setProperty("CNTRY_CD", "USA");
		death_fact.setProperty("CNTRY_KEY_ID", "1");
		death_fact.setUnindexedProperty("CNTRY_NM", "United States of America");
		
		datastore.put(death_fact);

		
	}



}
