package com.szl.tools.xmlHanler;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 城市
 * @author lx
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CityRoot")
public class City {

	@XmlElementWrapper(name = "counties")
	@XmlElement(name = "counties")
	private List<County> counties;

	public List<County> getCounties() {
		return counties;
	}

	public void setCounties(List<County> counties) {
		this.counties = counties;
	}
	
}
