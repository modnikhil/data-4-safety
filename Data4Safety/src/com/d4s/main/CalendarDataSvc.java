package com.d4s.main;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public class CalendarDataSvc {
	
	public void createCalendar(String CalendarId, String CalendarYr)
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity calendar = new Entity("CLNDR");
		
		calendar.setProperty("CLNDR_KEY_ID", CalendarId);
		calendar.setProperty("CLNDR_YEAR", CalendarYr);
		
		
		datastore.put(calendar);

		
	}
	
	public void updateEntity()
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity calendar = new Entity("CNTRY");
		calendar.setProperty("CNTRY_CD", "USA");
		calendar.setProperty("CNTRY_KEY_ID", "1");
		calendar.setUnindexedProperty("CNTRY_NM", "United States of America");
		
		datastore.put(calendar);

		
	}
	
	public void deleteEntity()
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity calendar = new Entity("CNTRY");
		calendar.setProperty("CNTRY_CD", "USA");
		calendar.setProperty("CNTRY_KEY_ID", "1");
		calendar.setUnindexedProperty("CNTRY_NM", "United States of America");
		
		datastore.put(calendar);

		
	}



}
