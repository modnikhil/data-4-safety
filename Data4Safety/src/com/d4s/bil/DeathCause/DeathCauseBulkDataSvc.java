package com.d4s.bil.DeathCause;

import java.util.ArrayList;
import java.util.Date;

import com.d4s.bil.Clndr.Clndr;
import com.d4s.bil.Clndr.ClndrSvc;
import com.d4s.bil.DeathCause.DeathCause;
import com.d4s.bil.DeathCause.DeathCauseSvc;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public class DeathCauseBulkDataSvc {

	// Requires DeathCauseGrpCd:DeathCauseNm:DeathCauseWHOCd:DeathCause3Nm:DeathCause3WHOCd:
	//          DeathCause2Nm:DeathCause2WHOCd:DeathCause1Nm:DeathCause1WHOCd:DeathCause0Nm:DeathCause0WHOCd 
	//          format separated by delimiter |.
	String strDeathCauseData = "0:All Causes:W000:--:--:--:--:--:--:--:--|1:Communicable, maternal, perinatal and nutritional conditions:W001:--:--:--:--:--:--:All Causes:W000|1:Noncommunicable diseases:W059:--:--:--:--:--:--:All Causes:W000|1:Injuries:W148:--:--:--:--:--:--:All Causes:W000|2:Infectious and parasitic diseases:W002:--:--:--:--:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|2:Respiratory infections:W038:--:--:--:--:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|2:Maternal conditions:W042:--:--:--:--:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|2:Perinatal conditions (h):W049:--:--:--:--:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|2:Nutritional deficiencies:W053:--:--:--:--:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|2:Other neoplasms:W078:--:--:--:--:Noncommunicable diseases:W059:All Causes:W000|2:Diabetes mellitus:W079:--:--:--:--:Noncommunicable diseases:W059:All Causes:W000|2:Endocrine disorders:W080:--:--:--:--:Noncommunicable diseases:W059:All Causes:W000|2:Neuropsychiatric conditions:W081:--:--:--:--:Noncommunicable diseases:W059:All Causes:W000|2:Sense organ diseases:W098:--:--:--:--:Noncommunicable diseases:W059:All Causes:W000|2:Cardiovascular diseases:W104:--:--:--:--:Noncommunicable diseases:W059:All Causes:W000|2:Respiratory diseases:W111:--:--:--:--:Noncommunicable diseases:W059:All Causes:W000|2:Digestive diseases:W115:--:--:--:--:Noncommunicable diseases:W059:All Causes:W000|2:Genitourinary diseases:W120:--:--:--:--:Noncommunicable diseases:W059:All Causes:W000|2:Skin diseases:W124:--:--:--:--:Noncommunicable diseases:W059:All Causes:W000|2:Musculoskeletal diseases:W125:--:--:--:--:Noncommunicable diseases:W059:All Causes:W000|2:Congenital anomalies:W131:--:--:--:--:Noncommunicable diseases:W059:All Causes:W000|2:Oral conditions:W143:--:--:--:--:Noncommunicable diseases:W059:All Causes:W000|2:Unintentional injuries:W149:--:--:--:--:Injuries:W148:All Causes:W000|2:Intentional injuries:W156:--:--:--:--:Injuries:W148:All Causes:W000|2:Malignant neoplasms:W060:--:--:--:--:Noncommunicable diseases:W059:All Causes:W000|3:Tuberculosis:W003:--:--:Infectious and parasitic diseases:W002:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|3:STDs excluding HIV:W004:--:--:Infectious and parasitic diseases:W002:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|3:HIV/AIDS:W009:--:--:Infectious and parasitic diseases:W002:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|3:Diarrhoeal diseases:W010:--:--:Infectious and parasitic diseases:W002:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|3:Childhood-cluster diseases:W011:--:--:Infectious and parasitic diseases:W002:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|3:Meningitis:W017:--:--:Infectious and parasitic diseases:W002:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|3:Hepatitis B (g):W018:--:--:Infectious and parasitic diseases:W002:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|3:Hepatitis C (g):W019:--:--:Infectious and parasitic diseases:W002:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|3:Malaria:W020:--:--:Infectious and parasitic diseases:W002:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|3:Tropical-cluster diseases:W021:--:--:Infectious and parasitic diseases:W002:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|3:Leprosy:W028:--:--:Infectious and parasitic diseases:W002:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|3:Dengue:W029:--:--:Infectious and parasitic diseases:W002:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|3:Japanese encephalitis:W030:--:--:Infectious and parasitic diseases:W002:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|3:Trachoma:W031:--:--:Infectious and parasitic diseases:W002:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|3:Intestinal nematode infections:W032:--:--:Infectious and parasitic diseases:W002:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|3:Lower respiratory infections:W039:--:--:Respiratory infections:W038:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|3:Upper respiratory infections:W040:--:--:Respiratory infections:W038:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|3:Otitis media:W041:--:--:Respiratory infections:W038:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|3:Prematurity and low birth weight:W050:--:--:Perinatal conditions (h):W049:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|3:Birth asphyxia and birth trauma:W051:--:--:Perinatal conditions (h):W049:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|3:Neonatal infections and other conditions (i):W052:--:--:Perinatal conditions (h):W049:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|3:Protein-energy malnutrition:W054:--:--:Nutritional deficiencies:W053:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|3:Iodine deficiency:W055:--:--:Nutritional deficiencies:W053:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|3:Vitamin A deficiency:W056:--:--:Nutritional deficiencies:W053:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|3:Iron-deficiency anaemia:W057:--:--:Nutritional deficiencies:W053:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|3:Mouth and oropharynx cancers:W061:--:--:Malignant neoplasms:W060:Noncommunicable diseases:W059:All Causes:W000|3:Oesophagus cancer:W062:--:--:Malignant neoplasms:W060:Noncommunicable diseases:W059:All Causes:W000|3:Stomach cancer:W063:--:--:Malignant neoplasms:W060:Noncommunicable diseases:W059:All Causes:W000|3:Colon and rectum cancers:W064:--:--:Malignant neoplasms:W060:Noncommunicable diseases:W059:All Causes:W000|3:Liver cancer:W065:--:--:Malignant neoplasms:W060:Noncommunicable diseases:W059:All Causes:W000|3:Pancreas cancer:W066:--:--:Malignant neoplasms:W060:Noncommunicable diseases:W059:All Causes:W000|3:Trachea, bronchus, lung cancers:W067:--:--:Malignant neoplasms:W060:Noncommunicable diseases:W059:All Causes:W000|3:Melanoma and other skin cancers:W068:--:--:Malignant neoplasms:W060:Noncommunicable diseases:W059:All Causes:W000|3:Breast cancer:W069:--:--:Malignant neoplasms:W060:Noncommunicable diseases:W059:All Causes:W000|3:Cervix uteri cancer:W070:--:--:Malignant neoplasms:W060:Noncommunicable diseases:W059:All Causes:W000|3:Corpus uteri cancer:W071:--:--:Malignant neoplasms:W060:Noncommunicable diseases:W059:All Causes:W000|3:Ovary cancer:W072:--:--:Malignant neoplasms:W060:Noncommunicable diseases:W059:All Causes:W000|3:Prostate cancer:W073:--:--:Malignant neoplasms:W060:Noncommunicable diseases:W059:All Causes:W000|3:Bladder cancer:W074:--:--:Malignant neoplasms:W060:Noncommunicable diseases:W059:All Causes:W000|3:Lymphomas, multiple myeloma:W075:--:--:Malignant neoplasms:W060:Noncommunicable diseases:W059:All Causes:W000|3:Leukaemia:W076:--:--:Malignant neoplasms:W060:Noncommunicable diseases:W059:All Causes:W000|3:Unipolar depressive disorders:W082:--:--:Neuropsychiatric conditions:W081:Noncommunicable diseases:W059:All Causes:W000|3:Bipolar disorder:W083:--:--:Neuropsychiatric conditions:W081:Noncommunicable diseases:W059:All Causes:W000|3:Schizophrenia:W084:--:--:Neuropsychiatric conditions:W081:Noncommunicable diseases:W059:All Causes:W000|3:Epilepsy:W085:--:--:Neuropsychiatric conditions:W081:Noncommunicable diseases:W059:All Causes:W000|3:Alcohol use disorders:W086:--:--:Neuropsychiatric conditions:W081:Noncommunicable diseases:W059:All Causes:W000|3:Alzheimer and other dementias:W087:--:--:Neuropsychiatric conditions:W081:Noncommunicable diseases:W059:All Causes:W000|3:Parkinson disease:W088:--:--:Neuropsychiatric conditions:W081:Noncommunicable diseases:W059:All Causes:W000|3:Multiple sclerosis:W089:--:--:Neuropsychiatric conditions:W081:Noncommunicable diseases:W059:All Causes:W000|3:Drug use disorders:W090:--:--:Neuropsychiatric conditions:W081:Noncommunicable diseases:W059:All Causes:W000|3:Post-traumatic stress disorder:W091:--:--:Neuropsychiatric conditions:W081:Noncommunicable diseases:W059:All Causes:W000|3:Obsessive-compulsive disorder:W092:--:--:Neuropsychiatric conditions:W081:Noncommunicable diseases:W059:All Causes:W000|3:Panic disorder:W093:--:--:Neuropsychiatric conditions:W081:Noncommunicable diseases:W059:All Causes:W000|3:Insomnia (primary):W094:--:--:Neuropsychiatric conditions:W081:Noncommunicable diseases:W059:All Causes:W000|3:Migraine:W095:--:--:Neuropsychiatric conditions:W081:Noncommunicable diseases:W059:All Causes:W000|3:Glaucoma:W099:--:--:Sense organ diseases:W098:Noncommunicable diseases:W059:All Causes:W000|3:Cataracts:W100:--:--:Sense organ diseases:W098:Noncommunicable diseases:W059:All Causes:W000|3:Refractive errors:W101:--:--:Sense organ diseases:W098:Noncommunicable diseases:W059:All Causes:W000|3:Hearing loss, adult onset:W102:--:--:Sense organ diseases:W098:Noncommunicable diseases:W059:All Causes:W000|3:Macular degeneration and other (j):W103:--:--:Sense organ diseases:W098:Noncommunicable diseases:W059:All Causes:W000|3:Rheumatic heart disease:W105:--:--:Cardiovascular diseases:W104:Noncommunicable diseases:W059:All Causes:W000|3:Hypertensive heart disease:W106:--:--:Cardiovascular diseases:W104:Noncommunicable diseases:W059:All Causes:W000|3:Ischaemic heart disease:W107:--:--:Cardiovascular diseases:W104:Noncommunicable diseases:W059:All Causes:W000|3:Cerebrovascular disease:W108:--:--:Cardiovascular diseases:W104:Noncommunicable diseases:W059:All Causes:W000|3:Inflammatory heart diseases (k):W109:--:--:Cardiovascular diseases:W104:Noncommunicable diseases:W059:All Causes:W000|3:Chronic obstructive pulmonary disease:W112:--:--:Respiratory diseases:W111:Noncommunicable diseases:W059:All Causes:W000|3:Asthma:W113:--:--:Respiratory diseases:W111:Noncommunicable diseases:W059:All Causes:W000|3:Peptic ulcer disease:W116:--:--:Digestive diseases:W115:Noncommunicable diseases:W059:All Causes:W000|3:Cirrhosis of the liver:W117:--:--:Digestive diseases:W115:Noncommunicable diseases:W059:All Causes:W000|3:Appendicitis:W118:--:--:Digestive diseases:W115:Noncommunicable diseases:W059:All Causes:W000|3:Nephritis and nephrosis:W121:--:--:Genitourinary diseases:W120:Noncommunicable diseases:W059:All Causes:W000|3:Benign prostatic hypertrophy:W122:--:--:Genitourinary diseases:W120:Noncommunicable diseases:W059:All Causes:W000|3:Rheumatoid arthritis:W126:--:--:Musculoskeletal diseases:W125:Noncommunicable diseases:W059:All Causes:W000|3:Osteoarthritis:W127:--:--:Musculoskeletal diseases:W125:Noncommunicable diseases:W059:All Causes:W000|3:Dental caries:W144:--:--:Oral conditions:W143:Noncommunicable diseases:W059:All Causes:W000|3:Periodontal disease:W145:--:--:Oral conditions:W143:Noncommunicable diseases:W059:All Causes:W000|3:Edentulism:W146:--:--:Oral conditions:W143:Noncommunicable diseases:W059:All Causes:W000|3:Road traffic accidents:W150:--:--:Unintentional injuries:W149:Injuries:W148:All Causes:W000|3:Poisonings:W151:--:--:Unintentional injuries:W149:Injuries:W148:All Causes:W000|3:Falls:W152:--:--:Unintentional injuries:W149:Injuries:W148:All Causes:W000|3:Fires:W153:--:--:Unintentional injuries:W149:Injuries:W148:All Causes:W000|3:Drownings:W154:--:--:Unintentional injuries:W149:Injuries:W148:All Causes:W000|3:Other unintentional injuries:W155:--:--:Unintentional injuries:W149:Injuries:W148:All Causes:W000|3:Self-inflicted injuries:W157:--:--:Intentional injuries:W156:Injuries:W148:All Causes:W000|3:Violence:W158:--:--:Intentional injuries:W156:Injuries:W148:All Causes:W000|3:War:W159:--:--:Intentional injuries:W156:Injuries:W148:All Causes:W000|4:Syphilis:W005:STDs excluding HIV:W004:Infectious and parasitic diseases:W002:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|4:Chlamydia:W006:STDs excluding HIV:W004:Infectious and parasitic diseases:W002:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|4:Gonorrhoea:W007:STDs excluding HIV:W004:Infectious and parasitic diseases:W002:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|4:Pertussis:W012:Childhood-cluster diseases:W011:Infectious and parasitic diseases:W002:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|4:Poliomyelitis:W013:Childhood-cluster diseases:W011:Infectious and parasitic diseases:W002:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|4:Diphtheria:W014:Childhood-cluster diseases:W011:Infectious and parasitic diseases:W002:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|4:Measles:W015:Childhood-cluster diseases:W011:Infectious and parasitic diseases:W002:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|4:Tetanus:W016:Childhood-cluster diseases:W011:Infectious and parasitic diseases:W002:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|4:Trypanosomiasis:W022:Tropical-cluster diseases:W021:Infectious and parasitic diseases:W002:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|4:Chagas disease:W023:Tropical-cluster diseases:W021:Infectious and parasitic diseases:W002:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|4:Schistosomiasis:W024:Tropical-cluster diseases:W021:Infectious and parasitic diseases:W002:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|4:Leishmaniasis:W025:Tropical-cluster diseases:W021:Infectious and parasitic diseases:W002:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|4:Lymphatic filariasis:W026:Tropical-cluster diseases:W021:Infectious and parasitic diseases:W002:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|4:Onchocerciasis:W027:Tropical-cluster diseases:W021:Infectious and parasitic diseases:W002:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|4:Ascariasis:W033:Intestinal nematode infections:W032:Infectious and parasitic diseases:W002:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|4:Trichuriasis:W034:Intestinal nematode infections:W032:Infectious and parasitic diseases:W002:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|4:Hookworm disease:W035:Intestinal nematode infections:W032:Infectious and parasitic diseases:W002:Communicable, maternal, perinatal and nutritional conditions:W001:All Causes:W000|";
							
	public void updateDeathCauseBulkData() {
		DeathCauseSvc oDeathCauseSvc = new DeathCauseSvc();
		DeathCause oDeathCause, pDeathCause;
		
		Integer ReadCount = 0;
		Integer UpdateCount = 0;
		Integer AddCount = 0;

 
		String[] arDeathCause = strDeathCauseData.split("\\|");
		int size = arDeathCause.length;
		for (int i = 0; i < size; i++) {
			ReadCount = ReadCount + 1;

			String[] arDeathCauseRec = arDeathCause[i].split(":");
 

			pDeathCause = new DeathCause();
 			pDeathCause.setDeathCauseGBDCd(arDeathCauseRec[2].trim());
			oDeathCause = oDeathCauseSvc.getDeathCause(DeathCauseSvc.DeathCause_ByDeathCauseGBDCd, pDeathCause);
			if (oDeathCause.getDeathCauseId() > 0) {
				oDeathCause.setDeathCauseId(oDeathCause.getDeathCauseId());
				oDeathCause.setDeathCauseGrpCd(arDeathCauseRec[0].trim());
				oDeathCause.setDeathCauseNm(arDeathCauseRec[1].trim());
				oDeathCause.setDeathCauseGBDCd(arDeathCauseRec[2].trim());
				
			 	DeathCause oDeathCauseGrp0 = new DeathCause();
				DeathCause oDeathCauseGrp1 = new DeathCause();
				DeathCause oDeathCauseGrp2 = new DeathCause();
				DeathCause oDeathCauseGrp3 = new DeathCause();
								
				if (arDeathCauseRec[0].trim().equalsIgnoreCase("0")) {
					//Get group 3 details
					oDeathCause.setDeathCauseGrp3Id(0);
					//Get group 2 details
					oDeathCause.setDeathCauseGrp2Id(0);
					//Get group 1 details
		 			oDeathCause.setDeathCauseGrp1Id(0);
					//Get group 0 details
		 			oDeathCause.setDeathCauseGrp0Id(0);
				} else if (arDeathCauseRec[0].trim().equalsIgnoreCase("1")) {
					//Get group 3 details
					oDeathCause.setDeathCauseGrp3Id(0);
					//Get group 2 details
					oDeathCause.setDeathCauseGrp2Id(0);
					//Get group 1 details
		 			oDeathCause.setDeathCauseGrp1Id(0);
					//Get group 0 details
					pDeathCause.setDeathCauseGBDCd(arDeathCauseRec[10].trim());
					oDeathCauseGrp0 = oDeathCauseSvc.getDeathCause(DeathCauseSvc.DeathCause_ByDeathCauseGBDCd, pDeathCause);
					oDeathCause.setDeathCauseGrp0Id(oDeathCauseGrp0.getDeathCauseId());
				} else if (arDeathCauseRec[0].trim().equalsIgnoreCase("2")) {
					//Get group 3 details
					oDeathCause.setDeathCauseGrp3Id(0);
					//Get group 2 details
					oDeathCause.setDeathCauseGrp2Id(0);
					//Get group 1 details
					pDeathCause.setDeathCauseGBDCd(arDeathCauseRec[8].trim());
					oDeathCauseGrp1 = oDeathCauseSvc.getDeathCause(DeathCauseSvc.DeathCause_ByDeathCauseGBDCd, pDeathCause);
					oDeathCause.setDeathCauseGrp1Id(oDeathCauseGrp1.getDeathCauseId());
				//	oDeathCause.setDeathCauseGrp0Id(oDeathCauseGrp1.getDeathCauseGrp0Id());
				} else if (arDeathCauseRec[0].trim().equalsIgnoreCase("3")) {
					oDeathCause.setDeathCauseGrp3Id(0);
					//Get group 2 details
					pDeathCause.setDeathCauseGBDCd(arDeathCauseRec[6].trim());
					oDeathCauseGrp2 = oDeathCauseSvc.getDeathCause(DeathCauseSvc.DeathCause_ByDeathCauseGBDCd, pDeathCause );
					oDeathCause.setDeathCauseGrp2Id(oDeathCauseGrp2.getDeathCauseId());
					//Get group 1 details
				//	pDeathCause.setDeathCauseId(oDeathCauseGrp2.getDeathCauseGrp1Id());
				//	oDeathCauseGrp1 = oDeathCauseSvc.getDeathCause(DeathCauseSvc.DeathCause_ByDeathCauseId, pDeathCause );
				//	oDeathCause.setDeathCauseGrp1Id(oDeathCauseGrp1.getDeathCauseId());
				//	oDeathCause.setDeathCauseGrp0Id(oDeathCauseGrp1.getDeathCauseGrp0Id());
				} else if (arDeathCauseRec[0].trim().equalsIgnoreCase("4")) {
					//Get group 3 details
					pDeathCause.setDeathCauseGBDCd(arDeathCauseRec[4].trim());
					oDeathCauseGrp3 = oDeathCauseSvc.getDeathCause(DeathCauseSvc.DeathCause_ByDeathCauseGBDCd, pDeathCause );
					oDeathCause.setDeathCauseGrp3Id(oDeathCauseGrp3.getDeathCauseId());
					//Get group 2 details
				//	pDeathCause.setDeathCauseId(oDeathCauseGrp3.getDeathCauseGrp2Id());
				//	oDeathCauseGrp2 = oDeathCauseSvc.getDeathCause(DeathCauseSvc.DeathCause_ByDeathCauseId, pDeathCause );
				//	oDeathCause.setDeathCauseGrp2Id(oDeathCauseGrp2.getDeathCauseId());
					//Get group 1 details
				//	pDeathCause.setDeathCauseId(oDeathCauseGrp2.getDeathCauseGrp1Id());
				//	oDeathCauseGrp1 = oDeathCauseSvc.getDeathCause(DeathCauseSvc.DeathCause_ByDeathCauseId, pDeathCause );
				//	oDeathCause.setDeathCauseGrp1Id(oDeathCauseGrp1.getDeathCauseId());
				//	oDeathCause.setDeathCauseGrp0Id(oDeathCauseGrp1.getDeathCauseGrp0Id());
				}

				oDeathCauseSvc.updateDeathCause(oDeathCause);
				
				UpdateCount = UpdateCount + 1;
			} else {
				oDeathCause.setDeathCauseId(0);
				oDeathCause.setDeathCauseGrpCd(arDeathCauseRec[0].trim());
				oDeathCause.setDeathCauseNm(arDeathCauseRec[1].trim());
				oDeathCause.setDeathCauseGBDCd(arDeathCauseRec[2].trim());
				oDeathCause.setDeathCauseGrp3Id(0);
				oDeathCause.setDeathCauseGrp2Id(0);
				oDeathCause.setDeathCauseGrp1Id(0);
				oDeathCause.setDeathCauseGrp0Id(0);
				
				DeathCause oDeathCauseGrp0 = new DeathCause();
				DeathCause oDeathCauseGrp1 = new DeathCause();
				DeathCause oDeathCauseGrp2 = new DeathCause();
				DeathCause oDeathCauseGrp3 = new DeathCause();
								
				if (arDeathCauseRec[0].trim().equalsIgnoreCase("0")) {
					//Get group 3 details
					oDeathCause.setDeathCauseGrp3Id(0);
					//Get group 2 details
					oDeathCause.setDeathCauseGrp2Id(0);
					//Get group 1 details
		 			oDeathCause.setDeathCauseGrp1Id(0);
					//Get group 0 details
		 			oDeathCause.setDeathCauseGrp0Id(0);
				} else if (arDeathCauseRec[0].trim().equalsIgnoreCase("1")) {
					//Get group 3 details
					oDeathCause.setDeathCauseGrp3Id(0);
					//Get group 2 details
					oDeathCause.setDeathCauseGrp2Id(0);
					//Get group 1 details
		 			oDeathCause.setDeathCauseGrp1Id(0);
					//Get group 0 details
					pDeathCause.setDeathCauseGBDCd(arDeathCauseRec[10].trim());
					oDeathCauseGrp0 = oDeathCauseSvc.getDeathCause(DeathCauseSvc.DeathCause_ByDeathCauseGBDCd, pDeathCause);
					oDeathCause.setDeathCauseGrp0Id(oDeathCauseGrp0.getDeathCauseId());
				} else if (arDeathCauseRec[0].trim().equalsIgnoreCase("2")) {
					//Get group 3 details
					oDeathCause.setDeathCauseGrp3Id(0);
					//Get group 2 details
					oDeathCause.setDeathCauseGrp2Id(0);
					//Get group 1 details
					pDeathCause.setDeathCauseGBDCd(arDeathCauseRec[8].trim());
					oDeathCauseGrp1 = oDeathCauseSvc.getDeathCause(DeathCauseSvc.DeathCause_ByDeathCauseGBDCd, pDeathCause);
					oDeathCause.setDeathCauseGrp1Id(oDeathCauseGrp1.getDeathCauseId());
				//	oDeathCause.setDeathCauseGrp0Id(oDeathCauseGrp1.getDeathCauseGrp0Id());
				} else if (arDeathCauseRec[0].trim().equalsIgnoreCase("3")) {
					oDeathCause.setDeathCauseGrp3Id(0);
					//Get group 2 details
					pDeathCause.setDeathCauseGBDCd(arDeathCauseRec[6].trim());
					oDeathCauseGrp2 = oDeathCauseSvc.getDeathCause(DeathCauseSvc.DeathCause_ByDeathCauseGBDCd, pDeathCause );
					oDeathCause.setDeathCauseGrp2Id(oDeathCauseGrp2.getDeathCauseId());
					//Get group 1 details
				//	pDeathCause.setDeathCauseId(oDeathCauseGrp2.getDeathCauseGrp1Id());
				//	oDeathCauseGrp1 = oDeathCauseSvc.getDeathCause(DeathCauseSvc.DeathCause_ByDeathCauseId, pDeathCause );
				//	oDeathCause.setDeathCauseGrp1Id(oDeathCauseGrp1.getDeathCauseId());
				//	oDeathCause.setDeathCauseGrp0Id(oDeathCauseGrp1.getDeathCauseGrp0Id());
				} else if (arDeathCauseRec[0].trim().equalsIgnoreCase("4")) {
					//Get group 3 details
					pDeathCause.setDeathCauseGBDCd(arDeathCauseRec[4].trim());
					oDeathCauseGrp3 = oDeathCauseSvc.getDeathCause(DeathCauseSvc.DeathCause_ByDeathCauseGBDCd, pDeathCause );
					oDeathCause.setDeathCauseGrp3Id(oDeathCauseGrp3.getDeathCauseId());
					//Get group 2 details
				//	pDeathCause.setDeathCauseId(oDeathCauseGrp3.getDeathCauseGrp2Id());
				//	oDeathCauseGrp2 = oDeathCauseSvc.getDeathCause(DeathCauseSvc.DeathCause_ByDeathCauseId, pDeathCause );
				//	oDeathCause.setDeathCauseGrp2Id(oDeathCauseGrp2.getDeathCauseId());
					//Get group 1 details
				//	pDeathCause.setDeathCauseId(oDeathCauseGrp2.getDeathCauseGrp1Id());
				//	oDeathCauseGrp1 = oDeathCauseSvc.getDeathCause(DeathCauseSvc.DeathCause_ByDeathCauseId, pDeathCause );
				//	oDeathCause.setDeathCauseGrp1Id(oDeathCauseGrp1.getDeathCauseId());
				//	oDeathCause.setDeathCauseGrp0Id(oDeathCauseGrp1.getDeathCauseGrp0Id());
				}
				
				oDeathCauseSvc.addDeathCause(oDeathCause);
				
				AddCount = AddCount + 1;
			}
		}
		
		Entity eJobLog = new Entity("JobLog");
		eJobLog.setProperty("Table", "DeathCause");
		eJobLog.setProperty("Run Date", new Date().toString() );
		eJobLog.setProperty("ReadCnt", ReadCount);
		eJobLog.setProperty("AddCnt", AddCount);
		eJobLog.setProperty("UpdateCnt", UpdateCount);

	}
}
