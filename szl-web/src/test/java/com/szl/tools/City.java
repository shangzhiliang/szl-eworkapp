package com.szl.tools;

public class City {

	private int CityId;
	private String CityName;
	public int getCityId() {
		return CityId;
	}
	public void setCityId(int cityId) {
		this.CityId = cityId;
	}
	public String getCityName() {
		return CityName;
	}
	public void setCityName(String cityName) {
		this.CityName = cityName;
	}
	
	public String toString(){
		
		return "{cityId:"+CityId+" cityName:"+CityName+"}";
		
	}
}
