package com.d4s.main;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public class CountryDataSvc {
	
	public void createCountry(String CountryId, String CountryWHOCd,String CountryISOCd, String CountryNm)
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity country = new Entity("CNTRY");
		country.setProperty("CNTRY_KEY_ID", CountryId);
		country.setProperty("CNTRY_WHO_CD", CountryWHOCd);
		country.setProperty("CNTRY_ISO_CD", CountryISOCd);
		country.setUnindexedProperty("CNTRY_NM", CountryNm);
		
		datastore.put(country);

		
	}
	
	public void updateEntity()
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity country = new Entity("CNTRY");
		country.setProperty("CNTRY_CD", "USA");
		country.setProperty("CNTRY_KEY_ID", "1");
		country.setUnindexedProperty("CNTRY_NM", "United States of America");
		
		datastore.put(country);

		
	}
	
	public void deleteEntity()
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity country = new Entity("CNTRY");
		country.setProperty("CNTRY_CD", "USA");
		country.setProperty("CNTRY_KEY_ID", "1");
		country.setUnindexedProperty("CNTRY_NM", "United States of America");
		
		datastore.put(country);

		
	}



}
