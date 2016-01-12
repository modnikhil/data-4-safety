package com.d4s.bil.who;

import java.util.ArrayList;
import java.util.Date;

import com.d4s.bil.Clndr.Clndr;
import com.d4s.bil.Clndr.ClndrSvc;
import com.d4s.bil.Cntry.Cntry;
import com.d4s.bil.Cntry.CntrySvc;
import com.d4s.bil.DeathCause.DeathCause;
import com.d4s.bil.DeathCause.DeathCauseSvc;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public class DeathBulkDataSvc {

	String strDeathData = "";
	String strDeathCntryData = "";


	public void updateDeathBulkData() {
		DeathBulkData oDeathBulkData = new DeathBulkData();
		// Process 2002 data
		strDeathData =  oDeathBulkData.strDeathData2002_1_25;
		strDeathCntryData = oDeathBulkData.strDeathCntryData2002;
		updateDeathData();
		//
		strDeathData =  oDeathBulkData.strDeathData2002_26_50;
		strDeathCntryData = oDeathBulkData.strDeathCntryData2002;
		updateDeathData();
	}

	public void updateDeathData() {
		ClndrSvc oClndrSvc = new ClndrSvc();
		CntrySvc oCntrySvc = new CntrySvc();
		DeathSvc oDeathSvc = new DeathSvc();
		DeathCauseSvc oDeathCauseSvc = new DeathCauseSvc();
		Death oDeath, pDeath;
		Clndr pClndr = new Clndr();
		Clndr oClndr;
		Cntry pCntry = new Cntry();
		Cntry oCntry;
		DeathCause pDeathCause = new DeathCause();
		DeathCause oDeathCause;
		String Gender, AgeType, YearCd;
				
		Integer ReadCount = 0;
		Integer UpdateCount = 0;
		Integer AddCount = 0;


		String[] arDeathCntry = strDeathCntryData.split("\\|");

		String[] arDeath = strDeathData.split("\\|");
		int size = arDeath.length;
		for (int i = 0; i < size; i++) {
			ReadCount = ReadCount + 1;

			String[] arDeathRec = arDeath[i].split(":");
			Gender = arDeathRec[0];
			AgeType = arDeathRec[3];
			YearCd = arDeathRec[4];
			// Get Calendar data
			pClndr.setYearCd(Integer.valueOf(arDeathRec[4].trim()));
			oClndr = oClndrSvc.getClndr(ClndrSvc.Clndr_ByYearCd, pClndr);
			// If calendar not found then write error
			if (oClndr.getClndrId() <= 0) {
				// write error
				DatastoreService dsD4S = DatastoreServiceFactory
						.getDatastoreService();

				Entity eBulkError = new Entity("BulkError");
				eBulkError.setProperty("Table", "DeathCause");
				eBulkError.setProperty("ErrDesc", "Year code not found");
				eBulkError.setProperty("YearCd", arDeathRec[2]);
				eBulkError.setProperty("CntryWHOCd", arDeathRec[1]);
				eBulkError.setProperty("CntryNm", arDeathRec[0]);

				dsD4S.put(eBulkError);
				continue;
			}
			// Get death cause data
			pDeathCause.setDeathCauseGBDCd(arDeathRec[1].trim());
			oDeathCause = oDeathCauseSvc.getDeathCause(
					DeathCauseSvc.DeathCause_ByDeathCauseGBDCd, pDeathCause);
			// If death cause not found then write error
			if (oDeathCause.getDeathCauseId() <= 0) {
				// write error
				DatastoreService dsD4S = DatastoreServiceFactory
						.getDatastoreService();

				Entity eBulkError = new Entity("BulkError");
				eBulkError.setProperty("Table", "DeathCause");
				eBulkError.setProperty("ErrDesc", "Death cause code not found");
				eBulkError.setProperty("DeathCauseGBDCd", arDeathRec[1]);
				eBulkError.setProperty("DeathCauseNm", arDeathRec[2]);

				dsD4S.put(eBulkError);
				continue;
			}
			// Loop through country & Death Cause
			int DeathCntrySize = arDeathCntry.length;
			for (int j = 0; j < DeathCntrySize; j++) {
				String[] arDeathCntryRec = arDeathCntry[j].split(":");

				// Get country data
				pCntry.setCntryWHOCd(arDeathCntryRec[1].trim());
				oCntry = oCntrySvc
						.getCntry(CntrySvc.Cntry_ByCntryWHOCd, pCntry);
				// If country not found then write error
				if (oCntry.getCntryId() <= 0) {
					// write error
					DatastoreService dsD4S = DatastoreServiceFactory
							.getDatastoreService();

					Entity eBulkError = new Entity("BulkError");
					eBulkError.setProperty("Table", "DeathCause");
					eBulkError.setProperty("ErrDesc",
							"Country WHO code not found");
					eBulkError.setProperty("YearCd", arDeathRec[4].trim());
					eBulkError.setProperty("DeathCauseGBDCd",
							arDeathRec[1].trim());
					eBulkError.setProperty("CntryWHOCd", arDeathCntryRec[1]);
					eBulkError.setProperty("CntryNm", arDeathCntryRec[0]);

					dsD4S.put(eBulkError);
					continue;
				}

				pDeath = new Death();
				pDeath.setClndrId(oClndr.getClndrId());
				pDeath.setCntryId(oCntry.getCntryId());
				pDeath.setDeathCauseId(oDeathCause.getDeathCauseId());
				oDeath = oDeathSvc.getDeath(DeathSvc.Death_ByDeathBk, pDeath);
				// Update death record since record exists for
				// DeathCause/Year/Country business key.
				if (oDeath.getDeathId() > 0) {
					oDeath.setClndrId(oClndr.getClndrId());
					oDeath.setCntryId(oCntry.getCntryId());
					oDeath.setDeathCauseId(oDeathCause.getDeathCauseId());

					// All Ages
					if (AgeType.equalsIgnoreCase("A0")) {
						if (Gender.equalsIgnoreCase("Persons")) {
							oDeath.setDeathAllNum(Double
									.valueOf(arDeathRec[j + 5].trim()));
						} else if (Gender.equalsIgnoreCase("Males")) {
							oDeath.setDeathMaleNum(Double
									.valueOf(arDeathRec[j + 5].trim()));
						} else if (Gender.equalsIgnoreCase("Females")) {
							oDeath.setDeathFemaleNum(Double
									.valueOf(arDeathRec[j + 5].trim()));
						}
						// Age 0 to 14
					} else if (AgeType.equalsIgnoreCase("A1")) {
						if (Gender.equalsIgnoreCase("Persons")) {
							oDeath.setDeathAll0To14Num(Double
									.valueOf(arDeathRec[j + 5].trim()));
						} else if (Gender.equalsIgnoreCase("Males")) {
							oDeath.setDeathMale0To14Num(Double
									.valueOf(arDeathRec[j + 5].trim()));
						} else if (Gender.equalsIgnoreCase("Females")) {
							oDeath.setDeathFemale0To14Num(Double
									.valueOf(arDeathRec[j + 5].trim()));
						}
					}
					// Age 15 to 59
					else if (AgeType.equalsIgnoreCase("A2")) {
						if (Gender.equalsIgnoreCase("Persons")) {
							oDeath.setDeathAll15To59Num(Double
									.valueOf(arDeathRec[j + 5].trim()));
						} else if (Gender.equalsIgnoreCase("Males")) {
							oDeath.setDeathMale15To59Num(Double
									.valueOf(arDeathRec[j + 5].trim()));
						} else if (Gender.equalsIgnoreCase("Females")) {
							oDeath.setDeathFemale15To59Num(Double
									.valueOf(arDeathRec[j + 5].trim()));
						}
					}
					// Age 60 plus
					else if (AgeType.equalsIgnoreCase("A3")) {
						if (Gender.equalsIgnoreCase("Persons")) {
							oDeath.setDeathAll60PlusNum(Double
									.valueOf(arDeathRec[j + 5].trim()));
						} else if (Gender.equalsIgnoreCase("Males")) {
							oDeath.setDeathMale60PlusNum(Double
									.valueOf(arDeathRec[j + 5].trim()));
						} else if (Gender.equalsIgnoreCase("Females")) {
							oDeath.setDeathFemale60PlusNum(Double
									.valueOf(arDeathRec[j + 5].trim()));
						}
					}
					// If it is year 2008 then recalculate all ages data
					if (YearCd.equalsIgnoreCase("2008")) {
						oDeath.setDeathFemaleNum(oDeath.getDeathFemale0To14Num() + oDeath.getDeathFemale15To59Num() + oDeath.getDeathFemale60PlusNum());
						oDeath.setDeathMaleNum(oDeath.getDeathMale0To14Num() + oDeath.getDeathMale15To59Num() + oDeath.getDeathMale60PlusNum());
						oDeath.setDeathAllNum(oDeath.getDeathMaleNum() + oDeath.getDeathFemaleNum());
					}

					// Update death record
					oDeathSvc.updateDeath(oDeath);
					UpdateCount = UpdateCount + 1;
					
					// Add record since no record found for this business key
					// (DeathCause/Year/Country).
				} else {
					oDeath.setClndrId(oClndr.getClndrId());
					oDeath.setCntryId(oCntry.getCntryId());
					oDeath.setDeathCauseId(oDeathCause.getDeathCauseId());
					//
					oDeath.setDeathAllNum(0.0);
					oDeath.setDeathMaleNum(0.0);
					oDeath.setDeathFemaleNum(0.0);
					//
					oDeath.setDeathAll0To14Num(0.0);
					oDeath.setDeathMale0To14Num(0.0);
					oDeath.setDeathFemale0To14Num(0.0);
					//
					oDeath.setDeathAll15To59Num(0.0);
					oDeath.setDeathMale15To59Num(0.0);
					oDeath.setDeathFemale15To59Num(0.0);
					//
					oDeath.setDeathAll60PlusNum(0.0);
					oDeath.setDeathMale60PlusNum(0.0);
					oDeath.setDeathFemale60PlusNum(0.0);
					
					// All Ages
					if (AgeType.equalsIgnoreCase("A0")) {
						if (Gender.equalsIgnoreCase("Persons")) {
							oDeath.setDeathAllNum(Double
									.valueOf(arDeathRec[j + 5].trim()));
						} else if (Gender.equalsIgnoreCase("Males")) {
							oDeath.setDeathMaleNum(Double
									.valueOf(arDeathRec[j + 5].trim()));
						} else if (Gender.equalsIgnoreCase("Females")) {
							oDeath.setDeathFemaleNum(Double
									.valueOf(arDeathRec[j + 5].trim()));
						}
						// Age 0 to 14
					} else if (AgeType.equalsIgnoreCase("A1")) {
						if (Gender.equalsIgnoreCase("Persons")) {
							oDeath.setDeathAll0To14Num(Double
									.valueOf(arDeathRec[j + 5].trim()));
						} else if (Gender.equalsIgnoreCase("Males")) {
							oDeath.setDeathMale0To14Num(Double
									.valueOf(arDeathRec[j + 5].trim()));
						} else if (Gender.equalsIgnoreCase("Females")) {
							oDeath.setDeathFemale0To14Num(Double
									.valueOf(arDeathRec[j + 5].trim()));
						}
					}
					// Age 15 to 59
					else if (AgeType.equalsIgnoreCase("A2")) {
						if (Gender.equalsIgnoreCase("Persons")) {
							oDeath.setDeathAll15To59Num(Double
									.valueOf(arDeathRec[j + 5].trim()));
						} else if (Gender.equalsIgnoreCase("Males")) {
							oDeath.setDeathMale15To59Num(Double
									.valueOf(arDeathRec[j + 5].trim()));
						} else if (Gender.equalsIgnoreCase("Females")) {
							oDeath.setDeathFemale15To59Num(Double
									.valueOf(arDeathRec[j + 5].trim()));
						}
					}
					// Age 60 plus
					else if (AgeType.equalsIgnoreCase("A3")) {
						if (Gender.equalsIgnoreCase("Persons")) {
							oDeath.setDeathAll60PlusNum(Double
									.valueOf(arDeathRec[j + 5].trim()));
						} else if (Gender.equalsIgnoreCase("Males")) {
							oDeath.setDeathMale60PlusNum(Double
									.valueOf(arDeathRec[j + 5].trim()));
						} else if (Gender.equalsIgnoreCase("Females")) {
							oDeath.setDeathFemale60PlusNum(Double
									.valueOf(arDeathRec[j + 5].trim()));
						}
					}
					// If it is year 2008 then recalculate all ages data
					if (YearCd.equalsIgnoreCase("2008")) {
						oDeath.setDeathFemaleNum(oDeath.getDeathFemale0To14Num() + oDeath.getDeathFemale15To59Num() + oDeath.getDeathFemale60PlusNum());
						oDeath.setDeathMaleNum(oDeath.getDeathMale0To14Num() + oDeath.getDeathMale15To59Num() + oDeath.getDeathMale60PlusNum());
						oDeath.setDeathAllNum(oDeath.getDeathMaleNum() + oDeath.getDeathFemaleNum());
					}

					// Add death record
					oDeathSvc.addDeath(oDeath);
					
					AddCount = AddCount + 1;
				}

			}

		}
		
		Entity eJobLog = new Entity("JobLog");
		eJobLog.setProperty("Table", "Death");
		eJobLog.setProperty("Run Date", new Date().toString() );
		eJobLog.setProperty("ReadCnt", ReadCount);
		eJobLog.setProperty("AddCnt", AddCount);
		eJobLog.setProperty("UpdateCnt", UpdateCount);

	}
}
