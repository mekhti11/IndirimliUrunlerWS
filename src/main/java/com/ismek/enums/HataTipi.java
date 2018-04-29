package com.ismek.enums;

public enum HataTipi {
	
	SUCCESS(1000,"İşlem başarılı"),
	SYSTEM_ERROR(1001,"Sistemsel bir arıza meydana geldi. Lütfen daha sonra tekrar deneyin!"),
	SUCCESS_AKTIVASYON(1002,"Aktivasyon işleminin başarılı bir şekilde gerçekleştirildi."),
	LOGIN_FAILURE(1003,"Kullanıcı bilgileri yanlış!"),
	NO_ACTIVATION(1004,"Lütfen üyeliğinizi aktifleştirin!");
	
	private int code;
	private String message;
	
	private HataTipi(int code,String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
	

}
