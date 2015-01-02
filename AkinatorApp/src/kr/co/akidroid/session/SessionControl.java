package kr.co.akidroid.session;

import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;

//肺弊牢 技记包府
	public class SessionControl
	{
	    static public DefaultHttpClient httpclient = null;
	    static public List<Cookie> cookies;
	 
	    public static HttpClient getHttpclient() 
	    {
	        if( httpclient == null){
	         SessionControl.setHttpclient(new DefaultHttpClient());
	        }
	       return httpclient;
	    }
	 
	    public static void setHttpclient(DefaultHttpClient httpclient) {
	        SessionControl.httpclient = httpclient;
	     }
	} 
