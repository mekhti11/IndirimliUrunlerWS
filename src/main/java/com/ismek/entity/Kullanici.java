package com.ismek.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name="TBL_KULLANICI")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Kullanici implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6973058946071915609L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private long id;
	@Column
	private String adi;
	@Column
	private String soyadi;
	@Column
	private Date cdate;
	@Column
	private String email;
	@Column
	private String sifre;
	@Column
	private String guid;
	@Column
	private boolean aktifMi = false;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAdi() {
		return adi;
	}
	public void setAdi(String adi) {
		this.adi = adi;
	}
	public String getSoyadi() {
		return soyadi;
	}
	public void setSoyadi(String soyadi) {
		this.soyadi = soyadi;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSifre() {
		return sifre;
	}
	public void setSifre(String sifre) {
		this.sifre = sifre;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public boolean isAktifMi() {
		return aktifMi;
	}
	public void setAktifMi(boolean aktifMi) {
		this.aktifMi = aktifMi;
	}
	

}
