package com.ismek.entity;

import com.google.gson.Gson;

public class Test {
	
	public static void main(String[] args) {
		
		Gson gson = new Gson();
		Kullanici kullanici = new Kullanici();
		kullanici.setAdi("Yahya");
		kullanici.setSoyadi("Can");
		kullanici.setEmail("yahyacan55@gmail.com");
		kullanici.setSifre("12345");
		System.out.println(gson.toJson(kullanici));
		
	}

}
