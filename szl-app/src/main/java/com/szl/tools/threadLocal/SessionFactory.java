package com.szl.tools.threadLocal;

import org.apache.cxf.transport.Session;
import org.apache.cxf.transport.http.HTTPSession;


public class SessionFactory {

	private static final ThreadLocal<Session> localSession = new ThreadLocal<Session>(){
		protected Session initialValue() {
			Session session = new HTTPSession(null);
			localSession.set(session);
			return localSession.get();
		};
	};
	
	public Session getSession(){
		Session session = (Session)localSession.get();
		if(session == null){
			session = new HTTPSession(null);
			localSession.set(session);
		}
		
		return localSession.get();
	}
}
