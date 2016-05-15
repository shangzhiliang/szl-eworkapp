package com.szl.tools.xmlHanler;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 省份
 * @author lx
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Province")
public class Province {

	@XmlElementWrapper(name = "citysList")
	private List<City> citys;

	public List<City> getCitys() {
		return citys;
	}

	public void setCitys(List<City> citys) {
		this.citys = citys;
	}
	
}
