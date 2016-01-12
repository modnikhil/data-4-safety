package com.d4s.bil.Cntry;

import java.util.ArrayList;
import java.util.Date;

import com.d4s.bil.Clndr.Clndr;
import com.d4s.bil.Clndr.ClndrSvc;
import com.d4s.bil.Cntry.Cntry;
import com.d4s.bil.Cntry.CntrySvc;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;


public class CntryBulkDataSvc {

	// Requires CntryNm:CntryWHOCd:CntryWHOCd format separated by delimiter |.
	String strCntryData = "Afghanistan:3010:AFG|Albania:4005:ALB|Algeria:1010:DZA|Andorra:4008:AND|Angola:1020:AGO|Antigua and Barbuda:2010:ATG|Argentina:2020:ARG|Armenia:4007:ARM|Australia:5020:AUS|Austria:4010:AUT|Azerbaijan:4012:AZE|Bahamas:2030:BHS|Bahrain:3020:BHR|Bangladesh:3025:---|Barbados:2040:BRB|Belarus:4018:BLR|Belgium:4020:BEL|Belize:2045:BLZ|Benin:1025:BEN|Bhutan:3027:BTN|Bolivia:2060:BOL|Bosnia and Herzegovina:4025:BIH|Botswana:1030:BWA|Brazil:2070:BRA|Brunei Darussalam:3030:BRN|Bulgaria:4030:BGR|Burkina Faso:1035:BFA|Burundi:1040:BDI|Cambodia:3050:KHM|Cameroon:1045:CMR|Canada:2090:CAN|Cape Verde:1060:CPV|Central African Republic:1070:CAF|Chad:1080:TCD|Chile:2120:CHL|China:3068:CHN|Colombia:2130:COL|Comoros:1090:COM|Congo:1100:COG|Cook Islands:5060:COK|Costa Rica:2140:CRI|Croatia:4038:HRV|Cuba:2150:CUB|Cyprus:3080:CYP|Czech Republic:4045:CZE|C“te d'Ivoire:1115:CIV|Democratic People's Republic of Korea:3083:PRK|Democratic Republic of the Congo:1555:COD|Denmark:4050:DNK|Djibouti:1120:DJI|Dominica:2160:DMA|Dominican Republic:2170:DOM|Ecuador:2180:ECU|Egypt:1125:EGY|El Salvador:2190:SLV|Equatorial Guinea:1130:GNQ|Eritrea:1135:ERI|Estonia:4055:EST|Ethiopia:1140:ETH|Fiji:5070:FJI|Finland:4070:FIN|France:4080:FRA|Gabon:1160:GAB|Gambia:1170:GMB|Georgia:4084:GEO|Germany:4085:DEU|Ghana:1180:GHA|Greece:4140:GRC|Grenada:2230:GRD|Guatemala:2250:GTM|Guinea:1190:GIN|Guinea-Bissau:1192:GNB|Guyana:2260:GUY|Haiti:2270:HTI|Honduras:2280:HND|Hungary:4150:HUN|Iceland:4160:ISL|India:3100:IND|Indonesia:3110:IDN|Iran (Islamic Republic of):3130:IRN|Iraq:3140:IRQ|Ireland:4170:IRL|Israel:3150:ISR|Italy:4180:ITA|Jamaica:2290:JAM|Japan:3160:JPN|Jordan:3170:JOR|Kazakhstan:4182:KAZ|Kenya:1220:KEN|Kiribati:5105:KIR|Kuwait:3190:KWT|Kyrgyzstan:4184:KGZ|Lao People's Democratic Republic:3200:LAO|Latvia:4186:LVA|Lebanon:3210:LBN|Lesotho:1230:LSO|Liberia:1240:LBR|Libya:1250:LBY|Lithuania:4188:LTU|Luxembourg:4190:LUX|Macedonia (The former Yugoslav Republic of):4195:MKD|Madagascar:1260:MDG|Malawi:1270:MWI|Malaysia:3236:MYS|Maldives:3255:MDV|Mali:1280:MLI|Malta:4200:MLT|Marshall Islands:5107:MHL|Mauritania:1290:MRT|Mauritius:1300:MUS|Mexico:2310:MEX|Micronesia (Federated States of):5108:FSM|Monaco:4205:MCO|Mongolia:3260:MNG|Montenegro:4207:MNE|Morocco:1310:MAR|Mozambique:1320:MOZ|Myanmar:3270:MMR|Namibia:1325:NAM|Nauru:5110:NRU|Nepal:3280:NPL|Netherlands:4210:NLD|New Zealand:5150:NZL|Nicaragua:2340:NIC|Niger:1330:NER|Nigeria:1340:NGA|Niue:5170:NIU|Norway:4220:NOR|Oman:3285:OMN|Pakistan:3290:PAK|Palau:5180:PLW|Panama:2350:PAN|Papua New Guinea:5195:PNG|Paraguay:2360:PRY|Peru:2370:PER|Philippines:3300:PHL|Poland:4230:POL|Portugal:4240:PRT|Qatar:3320:QAT|Republic of Korea:3325:KOR|Republic of Moldova:4260:MDA|Romania:4270:ROU|Russian Federation:4272:RUS|Rwanda:1370:RWA|Saint Kitts and Nevis:2385:KNA|Saint Lucia:2400:LCA|Saint Vincent and the Grenadines:2420:VCT|Samoa:5197:WSM|San Marino:4275:SMR|Sao Tome and Principe:1385:STP|Saudi Arabia:3340:SAU|Senegal:1390:SEN|Serbia:4273:SRB|Serbia and Montenegro:4350:---|Seychelles:1400:SYC|Sierra Leone:1410:SLE|Singapore:3350:---|Slovakia:4274:SVK|Slovenia:4276:SVN|Solomon Islands:5198:SLB|Somalia:1420:SOM|South Africa:1430:ZAF|Spain:4280:ESP|Sri Lanka:3365:LKA|Sudan:1470:SDN|Suriname:2430:SUR|Swaziland:1480:SWZ|Sweden:4290:SWE|Switzerland:4300:CHE|Syrian Arab Republic:3370:SYR|Tajikistan:4301:TJK|Thailand:3380:THA|Timor-Leste:3087:TLS|Togo:1510:TGO|Tonga:5200:---|Trinidad and Tobago:2440:---|Tunisia:1520:TUN|Turkey:3400:TUR|Turkmenistan:4302:TKM|Tuvalu:5205:TUV|Uganda:1530:UGA|Ukraine:4303:UKR|United Arab Emirates:3405:ARE|United Kingdom:4308:GBR|United Republic of Tanzania:1546:TZA|United States of America:2450:USA|Uruguay:2460:URY|Uzbekistan:4335:UZB|Vanuatu:5207:VUT|Venezuela (Bolivarian Republic of):2470:VEN|Viet Nam:3408:VNM|Yemen:3420:YEM|Zambia:1560:ZMB|Zimbabwe:1570:ZWE|";
 				
	public void updateCntryBulkData() {
		CntrySvc oCntrySvc = new CntrySvc();
		Cntry oCntry, pCntry;
 
		Integer ReadCount = 0;
		Integer UpdateCount = 0;
		Integer AddCount = 0;
		
		String[] arCntry = strCntryData.split("\\|");
		int size = arCntry.length;
		for (int i = 0; i < size; i++) {
			ReadCount = ReadCount + 1;
			String[] arCntryRec = arCntry[i].split(":");
 
			pCntry = new Cntry();
 			pCntry.setCntryWHOCd(arCntryRec[1].trim());
			oCntry = oCntrySvc.getCntry(CntrySvc.Cntry_ByCntryWHOCd, pCntry);
			if (oCntry.getCntryId() > 0) {
				oCntry.setCntryId(oCntry.getCntryId());
				oCntry.setCntryWHOCd(arCntryRec[1].trim());
				oCntry.setCntryISOCd(arCntryRec[2].trim());
				oCntry.setCntryNm(arCntryRec[0].trim());

				oCntrySvc.updateCntry(oCntry);
				UpdateCount = UpdateCount + 1;
			} else {
				oCntry.setCntryId(0);
				oCntry.setCntryWHOCd(arCntryRec[1].trim());
				oCntry.setCntryISOCd(arCntryRec[2].trim());
				oCntry.setCntryNm(arCntryRec[0].trim());
				
				oCntrySvc.addCntry(oCntry);
				AddCount = AddCount + 1;
			}
		}
		
		// write error
		DatastoreService dsD4S = DatastoreServiceFactory
				.getDatastoreService();

		Entity eJobLog = new Entity("JobLog");
		eJobLog.setProperty("Table", "Cntry");
		eJobLog.setProperty("Run Date", new Date().toString() );
		eJobLog.setProperty("ReadCnt", ReadCount);
		eJobLog.setProperty("AddCnt", AddCount);
		eJobLog.setProperty("UpdateCnt", UpdateCount);

		dsD4S.put(eJobLog);

	}
}
