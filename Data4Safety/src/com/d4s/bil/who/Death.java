package com.d4s.bil.who;

 
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;


@PersistenceCapable
public class Death {

	@Persistent
 	private Integer DeathId;
	@Persistent
 	private Integer ClndrId;
	@Persistent
 	private Integer YearCd;
	@Persistent
 	private Integer CntryId;
	@Persistent
 	private String CntryWHOCd;
	@Persistent
 	private String CntryISOCd;
	@Persistent
 	private String CntryNm;	
	@Persistent
 	private Integer DeathCauseId;
	@Persistent
 	private String DeathCauseGBDCd;
	@Persistent
 	private String DeathCauseNm;
	@Persistent
 	private String DeathCauseGrpCd;
 
	//
	@Persistent
 	private Double DeathAllNum ;
	@Persistent
 	private Double DeathMaleNum ;
	@Persistent
 	private Double DeathFemaleNum ;
	@Persistent
 	private Double DeathAll0To14Num ;
	@Persistent
 	private Double DeathMale0To14Num ;
	@Persistent
 	private Double DeathFemale0To14Num ;
	@Persistent
 	private Double DeathAll15To59Num ;
	@Persistent
 	private Double DeathMale15To59Num ;
	@Persistent
 	private Double DeathFemale15To59Num ;
	@Persistent
 	private Double DeathAll60PlusNum ;
	@Persistent
 	private Double DeathMale60PlusNum ;
	@Persistent
 	private Double DeathFemale60PlusNum ;
	 
	public void setDeathId(Integer pDeathId) {
		this.DeathId = pDeathId;
	}

	public Integer getDeathId() {
		return DeathId;
	}
	
	public void setClndrId(Integer pClndrId) {
		this.ClndrId = pClndrId;
	}

	public Integer getClndrId() {
		return ClndrId;
	}
	
	public void setYearCd (Integer pYearCd) {
		this.YearCd  = pYearCd;
	}

	public Integer getYearCd () {
		return YearCd;
	}	

	public void setDeathCauseId(Integer pDeathCauseId) {
		this.DeathCauseId = pDeathCauseId;
	}

	public Integer getDeathCauseId() {
		return DeathCauseId;
	}
	
	public void setDeathCauseGBDCd(String pDeathCauseGBDCd) {
		this.DeathCauseGBDCd = pDeathCauseGBDCd;
	}

	public String getDeathCauseGBDCd() {
		return DeathCauseGBDCd;
	}		 
		
	public void setDeathCauseNm(String pDeathCauseNm) {
		this.DeathCauseNm = pDeathCauseNm;
	}

	public String getDeathCauseNm() {
		return DeathCauseNm;
	}
	
	public void setDeathCauseGrpCd(String pDeathCauseGrpCd) {
		this.DeathCauseGrpCd = pDeathCauseGrpCd;
	}

	public String getDeathCauseGrpCd() {
		return DeathCauseGrpCd;
	}
	
	public void setCntryId(Integer pCntryId) {
		this.CntryId = pCntryId;
	}

	public Integer getCntryId() {
		return CntryId;
	}

	public void setCntryWHOCd(String pCntryWHOCd) {
		this.CntryWHOCd = pCntryWHOCd;
	}

	public String getCntryWHOCd() {
		return CntryWHOCd;
	}
	
	public void setCntryISOCd(String pCntryISOCd) {
		this.CntryISOCd = pCntryISOCd;
	}

	public String getCntryISOCd() {
		return CntryISOCd;
	}

	public void setCntryNm(String pCntryNm) {
		this.CntryNm = pCntryNm;
	}

	public String getCntryNm() {
		return CntryNm;
	}
	
	public void setDeathAllNum (Double pDeathAllNum) {
		this.DeathAllNum  = pDeathAllNum;
	}

	public Double getDeathAllNum () {
		return DeathAllNum;
	}

	public Double getDeathMaleNum() {
		return DeathMaleNum;
	}

	public void setDeathMaleNum(Double deathMaleNum) {
		DeathMaleNum = deathMaleNum;
	}

	public Double getDeathFemaleNum() {
		return DeathFemaleNum;
	}

	public void setDeathFemaleNum(Double deathFemaleNum) {
		DeathFemaleNum = deathFemaleNum;
	}

	public Double getDeathAll0To14Num() {
		return DeathAll0To14Num;
	}

	public void setDeathAll0To14Num(Double deathAll0To14Num) {
		DeathAll0To14Num = deathAll0To14Num;
	}

	public Double getDeathMale0To14Num() {
		return DeathMale0To14Num;
	}

	public void setDeathMale0To14Num(Double deathMale0To14Num) {
		DeathMale0To14Num = deathMale0To14Num;
	}

	public Double getDeathFemale0To14Num() {
		return DeathFemale0To14Num;
	}

	public void setDeathFemale0To14Num(Double deathFemale0To14Num) {
		DeathFemale0To14Num = deathFemale0To14Num;
	}

	public Double getDeathAll15To59Num() {
		return DeathAll15To59Num;
	}

	public void setDeathAll15To59Num(Double deathAll15To59Num) {
		DeathAll15To59Num = deathAll15To59Num;
	}

	public Double getDeathMale15To59Num() {
		return DeathMale15To59Num;
	}

	public void setDeathMale15To59Num(Double deathMale15To59Num) {
		DeathMale15To59Num = deathMale15To59Num;
	}

	public Double getDeathFemale15To59Num() {
		return DeathFemale15To59Num;
	}

	public void setDeathFemale15To59Num(Double deathFemale15To59Num) {
		DeathFemale15To59Num = deathFemale15To59Num;
	}

	public Double getDeathAll60PlusNum() {
		return DeathAll60PlusNum;
	}

	public void setDeathAll60PlusNum(Double deathAll60PlusNum) {
		DeathAll60PlusNum = deathAll60PlusNum;
	}

	public Double getDeathMale60PlusNum() {
		return DeathMale60PlusNum;
	}

	public void setDeathMale60PlusNum(Double deathMale60PlusNum) {
		DeathMale60PlusNum = deathMale60PlusNum;
	}

	public Double getDeathFemale60PlusNum() {
		return DeathFemale60PlusNum;
	}

	public void setDeathFemale60PlusNum(Double deathFemale60PlusNum) {
		DeathFemale60PlusNum = deathFemale60PlusNum;
	}		 
 
	
}
