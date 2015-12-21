package com.acloudfan.memcached;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.Consumes;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/quote")
public class QuoteResource {

	@GET
	public String getQuote() {

		// 'VCAP_APPLICATION' is in JSON format, it contains useful information about a deployed application
		// String envApp = System.getenv("VCAP_APPLICATION");

		// 'VCAP_SERVICES' contains all the credentials of services bound to this application.
		// String envServices = System.getenv("VCAP_SERVICES");
		// JSONObject sysEnv = new JSONObject(System.getenv());
		
		String quote = Memcached.get("quote");
		System.out.println("Memcached received="+quote);
		if(quote == null){
			quote = "Quote expired in memcache!!";
		}
		
		return "{\"quote\":\""+   quote  +"\"}";

	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String postQuote(Object quote) {

		// 'VCAP_APPLICATION' is in JSON format, it contains useful information about a deployed application
		// String envApp = System.getenv("VCAP_APPLICATION");

		// 'VCAP_SERVICES' contains all the credentials of services bound to this application.
		// String envServices = System.getenv("VCAP_SERVICES");
		// JSONObject sysEnv = new JSONObject(System.getenv());
		
//		return "{\"hello\":\"world\"}";
//		JsonBuilderFactory fact = 
		
		
		
		
		JsonReader jsonReader = Json.createReader(new StringReader(quote.toString()));
		JsonObject job = jsonReader.readObject();
		String dat = job.getString("quote");
		jsonReader.close();
		
		// Add to memcached
		
		
		
//		Memcached.set("quote",job.getInt("expiry", 6000), dat);
		Memcached.set("quote",10, dat);
		
		System.out.println("Receieved Quote="+dat);
		
		return quote.toString(); // "{\"hello\":\""+   dat  +"\"}";

	}
}