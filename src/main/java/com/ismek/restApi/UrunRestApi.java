package com.ismek.restApi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.ismek.entity.BaseReturn;
import com.ismek.entity.Kullanici;
import com.ismek.entity.Urun;
import com.ismek.enums.HataTipi;
import com.ismek.util.SendEmail;
import com.ismek.util.SessionUtil;

@Path("urunRestApi")
public class UrunRestApi {
	
	private Session session;
	
	public UrunRestApi() {
		session = SessionUtil.getInstance();
	}
	
	@GET
    @Path("/getUrunList")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public BaseReturn<List<Urun>> getUrunList(){
		BaseReturn<List<Urun>> result = new BaseReturn<List<Urun>>(HataTipi.SUCCESS.getCode(), HataTipi.SUCCESS.getMessage(), null);
		List<Urun> uruns = new ArrayList<Urun>();
		try {
			Criteria criteria = session.createCriteria(Urun.class);
			criteria.add(Restrictions.ge("basTarih", new Date()));
			criteria.add(Restrictions.le("bitTarih", new Date()));
			uruns=session.createCriteria(Urun.class).list();
			result.setData(uruns);
		}
		catch(Exception e) {
			result.setCode(HataTipi.SYSTEM_ERROR.getCode());
			result.setMessage(HataTipi.SYSTEM_ERROR.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	@GET
    @Path("/getUrunListByCount")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public BaseReturn<List<Urun>> getUrunListByCount(@QueryParam("recordsCount") long recordsCount){
		BaseReturn<List<Urun>> result = new BaseReturn<List<Urun>>(HataTipi.SUCCESS.getCode(), HataTipi.SUCCESS.getMessage(), null);
		List<Urun> uruns = new ArrayList<Urun>();
		try {
			Criteria count = session.createCriteria(Urun.class);
	        count.setProjection(Projections.rowCount());
	        Long total = (Long) count.uniqueResult();
			
	        Criteria criteria = session.createCriteria(Urun.class);
	        criteria.setFirstResult((int)(total-recordsCount));
	        criteria.setMaxResults((int)recordsCount);
			
			uruns=criteria.list();
			result.setData(uruns);
		}
		catch(Exception e) {
			result.setCode(HataTipi.SYSTEM_ERROR.getCode());
			result.setMessage(HataTipi.SYSTEM_ERROR.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	@POST
    @Path("/saveUser")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public BaseReturn<Kullanici> saveUser(Kullanici kullanici){
		BaseReturn<Kullanici> result = new BaseReturn<Kullanici>(HataTipi.SUCCESS.getCode(), HataTipi.SUCCESS.getMessage(), kullanici);
		kullanici.setCdate(new Date());
		kullanici.setGuid(UUID.randomUUID().toString());
        try {
            session.beginTransaction();
            session.saveOrUpdate(kullanici);
            session.getTransaction().commit();
            SendEmail.sendEmail(kullanici.getEmail(), "http://159.203.162.166:8081/IndirimliUrunlerWS/rest/urunRestApi/uyelikAktivasyon?guid="+kullanici.getGuid());
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(HataTipi.SYSTEM_ERROR.getCode());
			result.setMessage(HataTipi.SYSTEM_ERROR.getMessage());
        }
        return result;
	}
	
	@POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public BaseReturn<Kullanici> login(Kullanici kullanici){
		BaseReturn<Kullanici> result = new BaseReturn<Kullanici>(HataTipi.SUCCESS.getCode(), HataTipi.SUCCESS.getMessage(), null);
        try {
        	Kullanici reskullanici = (Kullanici) session.createCriteria(Kullanici.class).
                    add(Restrictions.eq("email", kullanici.getEmail())).
                    add(Restrictions.eq("sifre", kullanici.getSifre())).uniqueResult();
        	if (reskullanici != null) {
        		if (reskullanici.isAktifMi()) {
        			result.setData(reskullanici);
				}else{
					result.setCode(HataTipi.NO_ACTIVATION.getCode());
					result.setMessage(HataTipi.NO_ACTIVATION.getMessage());
				}
				
			}else{
				result.setCode(HataTipi.LOGIN_FAILURE.getCode());
				result.setMessage(HataTipi.LOGIN_FAILURE.getMessage());
			}
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(HataTipi.SYSTEM_ERROR.getCode());
			result.setMessage(HataTipi.SYSTEM_ERROR.getMessage());
        }
        return result;
	}
	
	@POST
    @Path("/saveUrun")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public BaseReturn<Urun> saveUrun(Urun urun){
		BaseReturn<Urun> result = new BaseReturn<Urun>(HataTipi.SUCCESS.getCode(), HataTipi.SUCCESS.getMessage(), urun);
        try {
            session.beginTransaction();
            session.saveOrUpdate(urun);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(HataTipi.SYSTEM_ERROR.getCode());
			result.setMessage(HataTipi.SYSTEM_ERROR.getMessage());
        }
        return result;
	}
	
	@GET
    @Path("/uyelikAktivasyon")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public BaseReturn<String> uyelikAktivasyon(@QueryParam(value="guid") String guid){
		BaseReturn<String> result = new BaseReturn<String>(HataTipi.SUCCESS_AKTIVASYON.getCode(), HataTipi.SUCCESS_AKTIVASYON.getMessage(), null);
		try {
			Kullanici kullanici = (Kullanici) session.createCriteria(Kullanici.class).
            add(Restrictions.eq("guid", guid)).uniqueResult();
			kullanici.setAktifMi(true);
			session.getTransaction().begin();
			session.saveOrUpdate(kullanici);
			session.getTransaction().commit();
		}
		catch(Exception e) {
			result.setCode(HataTipi.SYSTEM_ERROR.getCode());
			result.setMessage(HataTipi.SYSTEM_ERROR.getMessage());
			e.printStackTrace();
		}
		return result;
	}

}
