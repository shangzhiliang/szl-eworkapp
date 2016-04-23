package com.org.decp.xml.xstream;

import java.io.Writer;

import com.org.decp.xml.Convert;
import com.org.decp.xml.ValidatorFactory;
import com.org.decp.xml.validate.DefautValidatorFactory;
import com.org.decp.xml.validate.ObjectHolder;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class XStreamConvert<T> implements Convert<T>{

	protected static String PREFIX_CDATA = "<![CDATA[";
	  protected static String SUFFIX_CDATA = "]]>";
	  private XStream xstream;
	  private String xml;

	  public ValidatorFactory<T> getValidatorFactory()
	  {
	    return new DefautValidatorFactory<T>();
	  }

	  public XStreamConvert()
	  {
	    this.xstream = initXStream(true);
	  }

	  public XStreamConvert(boolean isAddCDATA)
	  {
	    this.xstream = initXStream(isAddCDATA);
	  }

	  public XStreamConvert(String xmlStr, XStream xstream)
	  {
	    this.xstream = initXStream(true);
	    this.xml = xmlStr;
	  }

	  public XStreamConvert(String xmlStr, XStream xstream, boolean isAddCDATA)
	  {
	    this.xstream = initXStream(isAddCDATA);
	    this.xml = xmlStr;
	  }

	  public XStream initXStream(boolean isAddCDATA)
	  {
	    XStream xstream = null;
	    if (isAddCDATA) {
	      xstream = new XStream(
	        new XppDriver() {
	        public HierarchicalStreamWriter createWriter(Writer out) {
	          return new PrettyPrintWriter(out)
	          {
	            public void startNode(String name, Class clazz)
	            {
	              super.startNode(name, clazz);

	              name.equals("xml");
	            }

	            protected void writeText(QuickWriter writer, String text)
	            {
	              if ((text.startsWith(XStreamConvert.PREFIX_CDATA)) && 
	                (text.endsWith(XStreamConvert.SUFFIX_CDATA)))
	                writer.write(text);
	              else {
	                super.writeText(writer, text);
	              }
	            }
	          };
	        }
	      });
	    }
	    else {
	      xstream = new XStream();
	    }

	    return xstream;
	  }

	  public String toXml(ObjectHolder<T> objectHolder)
	  {
	    this.xstream.processAnnotations(objectHolder.getData().getClass());
	    return this.xstream.toXML(objectHolder.getData());
	  }

	  public T toBean(String xmlStr, ObjectHolder<T> objectHolder)
	  {
	    this.xstream.processAnnotations(objectHolder.getData().getClass());
	    return (T) this.xstream.fromXML(xmlStr);
	  }

	  public XStream getXstream() {
	    return this.xstream;
	  }

	  public String getXml() {
	    return this.xml;
	  }

	  public void setXml(String xml)
	  {
	    this.xml = xml;
	  }

}
