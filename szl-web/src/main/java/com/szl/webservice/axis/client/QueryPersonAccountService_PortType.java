/**
 * QueryPersonAccountService_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.szl.webservice.axis.client;

public interface QueryPersonAccountService_PortType extends java.rmi.Remote {
    public java.lang.String changePassword(java.lang.String userID, java.lang.String oldpassword, java.lang.String newpassword) throws java.rmi.RemoteException;
    public java.lang.String queryReport(java.lang.String userID, java.lang.String password, java.lang.String queryCondition) throws java.rmi.RemoteException;
    public java.lang.String testConnection() throws java.rmi.RemoteException;
}
