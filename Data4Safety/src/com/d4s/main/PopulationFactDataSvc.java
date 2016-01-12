package com.d4s.main;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public class PopulationFactDataSvc {
	
	public void createPopulationFact(String CountryId, String CalendarId, String PopulationNum)
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity population_fact = new Entity("POP_FACT");
		population_fact.setProperty("CNTRY_KEY_ID", CountryId);
		population_fact.setProperty("CLNDR_KEY_ID", CalendarId);
		population_fact.setUnindexedProperty("POP_NUM", PopulationNum);
		
		
		datastore.put(population_fact);

		
	}
	
	public void updateEntity()
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity population_fact = new Entity("CNTRY");
		population_fact.setProperty("CNTRY_CD", "USA");
		population_fact.setProperty("CNTRY_KEY_ID", "1");
		population_fact.setUnindexedProperty("CNTRY_NM", "United States of America");
		
		datastore.put(population_fact);

		
	}
	
	public void deleteEntity()
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity population_fact = new Entity("CNTRY");
		population_fact.setProperty("CNTRY_CD", "USA");
		population_fact.setProperty("CNTRY_KEY_ID", "1");
		population_fact.setUnindexedProperty("CNTRY_NM", "United States of America");
		
		datastore.put(population_fact);

		
	}



}
