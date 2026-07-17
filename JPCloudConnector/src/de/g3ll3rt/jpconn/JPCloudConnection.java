package de.g3ll3rt.jpconn;

import java.util.Hashtable;

import org.pcloud.PCloudAPI;
import org.pcloud.PCloudAPIDebug;

public class JPCloudConnection {
	private boolean connectionState = false;
	private String clientId;
	private String clientSecret;
	private String clientCode;
	private String clientToken;

	public boolean isConnected() {
		return connectionState;
	}
	
	public JPCloudResponse writeArchive() {
		return new JPCloudResponse("Method not implemented");
	}
	
	public JPCloudResponse logout() {
		JPCloudResponse resp;
		
	    try{
	      PCloudAPI conn=new PCloudAPI(true);  //SSL = true
	      Hashtable <String, Object> params=new Hashtable <String, Object> ();
	      params.put("access_token", this.clientToken);
	      resp = new JPCloudResponse(conn.sendCommand("logout", params)); //userinfo
	      this.clientToken = "";
	      this.connectionState = false;
	    }
	    catch(Exception e){
	      resp = new JPCloudResponse(e.toString());
	    }
	    return resp;
	}
	
	public JPCloudResponse getUserInfo() {
		JPCloudResponse resp;
		
	    try{
	      PCloudAPI conn=new PCloudAPI(true);  //SSL = true
	      Hashtable <String, Object> params=new Hashtable <String, Object> ();
	      params.put("access_token", this.clientToken);
	      resp = new JPCloudResponse(conn.sendCommand("userinfo", params)); //userinfo
	    }
	    catch(Exception e){
	      resp = new JPCloudResponse(e.toString());
	    }
	    return resp;
	}
	
	public JPCloudResponse getDirectory() {
		JPCloudResponse resp;
		try{
		      PCloudAPI conn=new PCloudAPI(true);  //SSL = true
		      Hashtable <String, Object> params=new Hashtable <String, Object> ();
		      params.put("access_token", this.clientToken);
		      params.put("folderid", "0");
		      resp = new JPCloudResponse(conn.sendCommand("listfolder", params)); //userinfo
		    }
		    catch(Exception e){
		      resp = new JPCloudResponse(e.toString());
		    }
		    return resp;
	}
	
	public JPCloudResponse connect(String clientId, String clientSecret, String clientCode) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.clientCode = clientCode;
		
		Object respObj;
		JPCloudResponse resp;
	    try{
	      PCloudAPI conn=new PCloudAPI(true);  //SSL = true
	      Hashtable <String, Object> params=new Hashtable <String, Object> ();
	      params.put("client_id", clientId); //client id
	      params.put("client_secret", clientSecret); //secret
	      params.put("code", clientCode); //code from auth website
	      respObj = (conn.sendCommand("oauth2_token", params)); //authorize
	      resp = new JPCloudResponse(respObj);
	      this.clientToken = resp.getValue("access_token");
	    }
	    catch(Exception e){
	      resp = new JPCloudResponse(e.toString());
	    }
	    
	    if (this.clientToken != null) {
	    	connectionState = true;
	    }
	    
	    return resp;
	}
}
