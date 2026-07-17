package de.g3ll3rt.jpconn;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import org.pcloud.PCloudAPIDebug;

public class JPCloudResponse {
	Hashtable <String, String> responseTable;
	String attrNotFound = "UNKNOWN";
	public JPCloudResponse(Object obj) {
      PCloudAPIDebug.print(obj);
	  this.responseTable = parseResponse(obj);
	}
	
	public String getValue(String attrName) {
		return responseTable.get(attrName);
	}
	
	public String toString() {
		StringBuffer strBufferOut = new StringBuffer();
		final String CR_LF = new String("\n");
		
		Enumeration<String> e = responseTable.keys();
		  while (e.hasMoreElements()) {
	          String key = e.nextElement();
	          String value = responseTable.get(key);
	  		  strBufferOut.append(key);
	  		  strBufferOut.append("=");
	  		  strBufferOut.append(value);
	  		  strBufferOut.append(CR_LF);
		  }
		
		return strBufferOut.toString();
	}
	
	private Hashtable<String, String> parseResponse(Object obj) {
		Hashtable <String, String> responseTable = new Hashtable <String, String> ();
		
	    if (obj.getClass()==String.class){
	    	responseTable.put("error", obj.toString());
	    }else if (obj.getClass()==Hashtable.class){
		  Iterator it = ((Map)obj).entrySet().iterator();
		  while (it.hasNext()){
		        Map.Entry pair=(Map.Entry)it.next();
		        responseTable.put(pair.getKey().toString(), pair.getValue().toString());
		    }    
		}
		
		else if (obj.getClass()==Object[].class) {
			Object[] arr=(Object[])obj;
			for (int i=0; i<arr.length; i++){
		        if (i>0) {
		        	responseTable.putAll(parseResponse(obj));
		        }
		      }
		}
		
		return responseTable;
	}

}
