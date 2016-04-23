/**
 * QueryPersonAccountServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.szl.webservice.axis.client;

@SuppressWarnings("serial")
public class QueryPersonAccountServiceServiceLocator extends org.apache.axis.client.Service implements QueryPersonAccountServiceService {

    public QueryPersonAccountServiceServiceLocator() {
    }


    public QueryPersonAccountServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public QueryPersonAccountServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for QueryPersonAccountService
    private java.lang.String QueryPersonAccountService_address = "http://localhost:8080/WebServiceProject/service/axis/queryPersonAccountService";

    public java.lang.String getQueryPersonAccountServiceAddress() {
        return QueryPersonAccountService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String QueryPersonAccountServiceWSDDServiceName = "QueryPersonAccountService";

    public java.lang.String getQueryPersonAccountServiceWSDDServiceName() {
        return QueryPersonAccountServiceWSDDServiceName;
    }

    public void setQueryPersonAccountServiceWSDDServiceName(java.lang.String name) {
        QueryPersonAccountServiceWSDDServiceName = name;
    }

    public QueryPersonAccountService_PortType getQueryPersonAccountService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(QueryPersonAccountService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getQueryPersonAccountService(endpoint);
    }

    public QueryPersonAccountService_PortType getQueryPersonAccountService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            QueryPersonAccountServiceSoapBindingStub _stub = new QueryPersonAccountServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getQueryPersonAccountServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setQueryPersonAccountServiceEndpointAddress(java.lang.String address) {
        QueryPersonAccountService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    @SuppressWarnings("rawtypes")
	public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (QueryPersonAccountService_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                QueryPersonAccountServiceSoapBindingStub _stub = new QueryPersonAccountServiceSoapBindingStub(new java.net.URL(QueryPersonAccountService_address), this);
                _stub.setPortName(getQueryPersonAccountServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    @SuppressWarnings("rawtypes")
	public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("QueryPersonAccountService".equals(inputPortName)) {
            return getQueryPersonAccountService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://impl.axis.service.test.credit.yinker.com", "QueryPersonAccountServiceService");
    }

    @SuppressWarnings("rawtypes")
	private java.util.HashSet ports = null;

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://impl.axis.service.test.credit.yinker.com", "QueryPersonAccountService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("QueryPersonAccountService".equals(portName)) {
            setQueryPersonAccountServiceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
