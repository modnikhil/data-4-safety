package com.d4s.bil.who;

 
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;


@PersistenceCapable
public class Ppln {

	@Persistent
 	private Integer PplnId;
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
	//
	@Persistent
 	private Double PplnAllNum ;
	@Persistent
 	private Double PplnMaleNum ;
	@Persistent
 	private Double PplnFemaleNum ;
	@Persistent
 	private Double PplnAll0To14Num ;
	@Persistent
 	private Double PplnMale0To14Num ;
	@Persistent
 	private Double PplnFemale0To14Num ;
	@Persistent
 	private Double PplnAll15To59Num ;
	@Persistent
 	private Double PplnMale15To59Num ;
	@Persistent
 	private Double PplnFemale15To59Num ;
	@Persistent
 	private Double PplnAll60PlusNum ;
	@Persistent
 	private Double PplnMale60PlusNum ;
	@Persistent
 	private Double PplnFemale60PlusNum ;
	 
	public void setPplnId(Integer pPplnId) {
		this.PplnId = pPplnId;
	}

	public Integer getPplnId() {
		return PplnId;
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
	
	public void setPplnAllNum (Double pPplnAllNum) {
		this.PplnAllNum  = pPplnAllNum;
	}

	public Double getPplnAllNum () {
		return PplnAllNum;
	}

	public Double getPplnMaleNum() {
		return PplnMaleNum;
	}

	public void setPplnMaleNum(Double pplnMaleNum) {
		PplnMaleNum = pplnMaleNum;
	}

	public Double getPplnFemaleNum() {
		return PplnFemaleNum;
	}

	public void setPplnFemaleNum(Double pplnFemaleNum) {
		PplnFemaleNum = pplnFemaleNum;
	}

	public Double getPplnAll0To14Num() {
		return PplnAll0To14Num;
	}

	public void setPplnAll0To14Num(Double pplnAll0To14Num) {
		PplnAll0To14Num = pplnAll0To14Num;
	}

	public Double getPplnMale0To14Num() {
		return PplnMale0To14Num;
	}

	public void setPplnMale0To14Num(Double pplnMale0To14Num) {
		PplnMale0To14Num = pplnMale0To14Num;
	}

	public Double getPplnFemale0To14Num() {
		return PplnFemale0To14Num;
	}

	public void setPplnFemale0To14Num(Double pplnFemale0To14Num) {
		PplnFemale0To14Num = pplnFemale0To14Num;
	}

	public Double getPplnAll15To59Num() {
		return PplnAll15To59Num;
	}

	public void setPplnAll15To59Num(Double pplnAll15To59Num) {
		PplnAll15To59Num = pplnAll15To59Num;
	}

	public Double getPplnMale15To59Num() {
		return PplnMale15To59Num;
	}

	public void setPplnMale15To59Num(Double pplnMale15To59Num) {
		PplnMale15To59Num = pplnMale15To59Num;
	}

	public Double getPplnFemale15To59Num() {
		return PplnFemale15To59Num;
	}

	public void setPplnFemale15To59Num(Double pplnFemale15To59Num) {
		PplnFemale15To59Num = pplnFemale15To59Num;
	}

	public Double getPplnAll60PlusNum() {
		return PplnAll60PlusNum;
	}

	public void setPplnAll60PlusNum(Double pplnAll60PlusNum) {
		PplnAll60PlusNum = pplnAll60PlusNum;
	}

	public Double getPplnMale60PlusNum() {
		return PplnMale60PlusNum;
	}

	public void setPplnMale60PlusNum(Double pplnMale60PlusNum) {
		PplnMale60PlusNum = pplnMale60PlusNum;
	}

	public Double getPplnFemale60PlusNum() {
		return PplnFemale60PlusNum;
	}

	public void setPplnFemale60PlusNum(Double pplnFemale60PlusNum) {
		PplnFemale60PlusNum = pplnFemale60PlusNum;
	}
	
	
 
}
