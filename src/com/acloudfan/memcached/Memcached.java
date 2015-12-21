package com.acloudfan.memcached;

import java.io.IOException;


import net.spy.memcached.AddrUtil;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.ConnectionFactoryBuilder.Protocol;
import net.spy.memcached.auth.AuthDescriptor;
import net.spy.memcached.auth.PlainCallbackHandler;

/**
 * @author acloudfan
 * 
 * Singleton utility class for making calls to Memcached
 * For Testing HARDCODED the credentials - REPLACE with VCAP_SERVICES.credentials
 * In production application DO NOT HARDCODE
 */
public class Memcached {

	// Instance - singleton
	private static MemcachedClient memInstance;
	
	// Initialize the singleton instance
	public static void init(){
		// To setup the authentication
		AuthDescriptor ad = new AuthDescriptor(new String[] { "PLAIN" },
				new PlainCallbackHandler("REPLACE WITH USER", "REPLACE WITH PASSWORD"));
		try{
			
			// Singleton instance creation
			String memcahedUrl = "PROVIDE URL IN HOST;PORT";
			
			 memInstance = new MemcachedClient(
					new ConnectionFactoryBuilder().setProtocol(Protocol.BINARY).setAuthDescriptor(ad).build(),
					AddrUtil.getAddresses(memcahedUrl));

		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	// Set the cache nv
	public static void set(String key,int timeout, String value) {
		if(memInstance == null) init();
			memInstance.set(key, timeout, value);
	}
	// get the cache
	public static String get(String key){
		if(memInstance == null) init();
		return (String) memInstance.get(key);
	}
}
