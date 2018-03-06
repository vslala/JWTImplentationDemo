package com.bma.jwt.app.vo;

import java.util.Date;

public class JWTPayload<E> {
	private Date exp;
	private Date iat;
	private String aud;
	private String iss;
	private E data;
	public Date getExp() {
		return exp;
	}
	public void setExp(Date exp) {
		this.exp = exp;
	}
	public Date getIat() {
		return iat;
	}
	public void setIat(Date iat) {
		this.iat = iat;
	}
	public String getAud() {
		return aud;
	}
	public void setAud(String aud) {
		this.aud = aud;
	}
	public String getIss() {
		return iss;
	}
	public void setIss(String iss) {
		this.iss = iss;
	}
	public E getData() {
		return data;
	}
	public void setData(E data) {
		this.data = data;
	}
	
	
}
