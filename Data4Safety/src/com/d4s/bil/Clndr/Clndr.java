package com.d4s.bil.Clndr;

 
import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;


@PersistenceCapable
public class Clndr {

	
	@Persistent
 	private Integer ClndrId;
	@Persistent
 	private Date ClndrDt ;
	@Persistent
 	private Integer YearCd ;
	@Persistent
 	private String YearEndCd ;
	 

	public void setClndrId(Integer pClndrId) {
		this.ClndrId = pClndrId;
	}

	public Integer getClndrId() {
		return ClndrId;
	}

	public void setClndrDt(Date pClndrDt) {
		this.ClndrDt = pClndrDt;
	}

	public Date getClndrDt() {
		return ClndrDt;
	}
	
	public void setYearCd (Integer pYearCd) {
		this.YearCd  = pYearCd;
	}

	public Integer getYearCd () {
		return YearCd;
	}		
	
	public void setYearEndCd(String pYearEndCd) {
		this.YearEndCd = pYearEndCd;
	}

	public String getYearEndCd() {
		return YearEndCd;
	}

 
}
