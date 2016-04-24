package com.szl.tools.xmlHanler;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.junit.Test;


public class TestBeanListToXml {

	@Test
	public void test() throws Exception {
		
		JavaBeanList beanList = new JavaBeanList();
		
		Province province = new Province();
		
		County county = new County();
		county.setCounts("1234");
		county.setName("献县");
		
		List<County> counties = new ArrayList<County>();
		counties.add(county);
		
		City citys = new City();
		citys.setCounties(counties);
		City citys2 = new City();
		citys2.setCounties(counties);
		List<City> listCity = new ArrayList<City>();
		listCity.add(citys);
		listCity.add(citys2);
		
		province.setCitys(listCity);
		
		beanList.setProvince(province);
		
		String xml = RequestBeanToStr(beanList);
		System.out.println(xml);
	}

	
    public  String RequestBeanToStr(JavaBeanList beanList) throws Exception{
        	StringWriter writer = new StringWriter();
        	JAXBContext context = JAXBContext.newInstance(JavaBeanList.class);  
            Marshaller marshaller = context.createMarshaller(); 
			marshaller.marshal(beanList, writer);
			return writer.toString();
	}
}
