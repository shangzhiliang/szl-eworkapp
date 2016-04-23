package com.szl.webservice.axis.client;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;


public class TestWebService {
	public static void main(String[] args) {
		
		axisClientTest();
		
	}
	
	/**
	 */
	@SuppressWarnings("unused")
	private static void cxfClientAccessAxisService(){
	       JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
	       
	       factory.setServiceClass(QueryPersonAccountService_PortType.class);
//	       
	       factory.setAddress("");
//	       
	       QueryPersonAccountService_PortType srvice =
	    		   (QueryPersonAccountService_PortType) factory.create();
//	       
	       System.out.println("#############Client getUserByName##############");
	       
		   try {
				System.out.println("user:"+srvice.queryReport("", "", ""));
		   } catch (Exception e) {
				e.printStackTrace();
		   }
	       
	       System.out.println("#############Client getUserByName##############");
	}
	
	/**
	 */
	private static void axisClientTest(){
		QueryPersonAccountServiceServiceLocator
		service = new QueryPersonAccountServiceServiceLocator();
		
		try {
			QueryPersonAccountService_PortType iservice = service.getQueryPersonAccountService();
			
			String xml = iservice.queryReport("", "", "");
			
			System.out.println("xml:"+xml);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
