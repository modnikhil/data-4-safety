package com.d4s.bil.Cntry;

 
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;


@PersistenceCapable
public class Cntry {

	
	@Persistent
 	private Integer CntryId;
	@Persistent
 	private String CntryWHOCd;
	@Persistent
 	private String CntryISOCd;
	@Persistent
 	private String CntryNm;

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

 
}
