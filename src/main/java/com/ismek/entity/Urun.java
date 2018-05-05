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
@Table(name="TBL_URUN")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Urun implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4687002716611716277L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private long id;
	
	@Column
	private String adi;
	
	@Column
	private double fiyat;
	
	@Column
	private double indirimliFiyat;
	
	@Column
	private Kullanici kullanici;
	
	@Column
	private double enlem;
	
	@Column
	private double boylam;
	
	@Column
	private String firmaAdi;
	
	@Column
	private Date cdate;
	
	@Column
	private Date basTarih;
	
	@Column
	private Date bitTarih;
	
	public Urun() {
		
	}
	
	public Urun(long id, String adi, double fiyat) {
		super();
		this.id = id;
		this.adi = adi;
		this.fiyat = fiyat;
	}
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
	public double getFiyat() {
		return fiyat;
	}
	public void setFiyat(double fiyat) {
		this.fiyat = fiyat;
	}
	public double getIndirimliFiyat() {
		return indirimliFiyat;
	}
	public void setIndirimliFiyat(double indirimliFiyat) {
		this.indirimliFiyat = indirimliFiyat;
	}
	public Kullanici getKullanici() {
		return kullanici;
	}
	public void setKullanici(Kullanici kullanici) {
		this.kullanici = kullanici;
	}
	public double getEnlem() {
		return enlem;
	}
	public void setEnlem(double enlem) {
		this.enlem = enlem;
	}
	public double getBoylam() {
		return boylam;
	}
	public void setBoylam(double boylam) {
		this.boylam = boylam;
	}
	public String getFirmaAdi() {
		return firmaAdi;
	}
	public void setFirmaAdi(String firmaAdi) {
		this.firmaAdi = firmaAdi;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	public Date getBasTarih() {
		return basTarih;
	}
	public void setBasTarih(Date basTarih) {
		this.basTarih = basTarih;
	}
	public Date getBitTarih() {
		return bitTarih;
	}
	public void setBitTarih(Date bitTarih) {
		this.bitTarih = bitTarih;
	}

}
