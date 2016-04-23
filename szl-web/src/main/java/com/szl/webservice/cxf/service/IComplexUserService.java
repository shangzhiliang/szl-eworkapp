/**
 * IComplexUserService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.szl.webservice.cxf.service;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.szl.webservice.model.User;


@WebService
@SOAPBinding(style = Style.RPC)
public interface IComplexUserService {
    public User getUserByName(java.lang.String name) ;
    public void setUser(User arg0);
}
