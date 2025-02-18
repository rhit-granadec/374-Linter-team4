package org.CPS2.JUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import org.CPS2.Application.AppCommunicator;

class MyTestCases {

	@Test
	void testUC1() {
		System.out.println("------------Testing UC1 (SimpleController)------------------");
		System.out.println("Status should be: Your coffee has been prepared. Please pick up your desired condiments.");
		
		JSONParser myParser = new JSONParser();
		JSONObject jason;
		String phoner = "{\r\n" + 
				"  \"order\": { \"orderID\": 1,\r\n" + 
				"            \"address\": {\r\n" + 
				"                  \"street\": \"3 S. Walnut\",\r\n" + 
				"                  \"ZIP\": 47803\r\n" + 
				"            },\r\n" + 
				"            \"drink\": \"Americano\",\r\n" + 
				"            \"condiments\": [\r\n" + 
				"                {\"name\": \"Sugar\", \"qty\": 1},\r\n" + 
				"                {\"name\": \"Cream\", \"qty\": 2}\r\n" + 
				"            ]\r\n" + 
				"            }\r\n" + 
				"}";
		try {
			jason = (JSONObject) myParser.parse(phoner);
			AppCommunicator c = new AppCommunicator();
			c.recieveMessageFromPhone(jason);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testUC2() {
		System.out.println("------------Testing UC2 (AdvancedController)------------------");
		System.out.println("Status should be: Your coffee has been prepared with your desired options");
		JSONParser myParser = new JSONParser();
		JSONObject jason;
		String phoner = "{\r\n" + 
				"  \"order\": { \"orderID\": 1,\r\n" + 
				"            \"address\": {\r\n" + 
				"                  \"street\": \"200 N Main\",\r\n" + 
				"                  \"ZIP\": 47803\r\n" + 
				"            },\r\n" + 
				"            \"drink\": \"Americano\",\r\n" + 
				"            \"condiments\": [\r\n" + 
				"                {\"name\": \"Sugar\", \"qty\": 1},\r\n" + 
				"                {\"name\": \"Cream\", \"qty\": 2}\r\n" + 
				"            ]\r\n" + 
				"            }\r\n" + 
				"}";
		try {
			jason = (JSONObject) myParser.parse(phoner);
			AppCommunicator c = new AppCommunicator();
			c.recieveMessageFromPhone(jason);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testError() {
		System.out.println("------------Testing Exception Flow-----------------");
		System.out.println("Status should be: There was an error processing your order. Please try the order again.");
		JSONParser myParser = new JSONParser();
		JSONObject jason;
		String phoner = "{\r\n" + 
				"  \"order\": { \"orderID\": 2,\r\n" + 
				"            \"address\": {\r\n" + 
				"                  \"street\": \"5500 Wabash Ave\",\r\n" + 
				"                  \"ZIP\": 47803\r\n" + 
				"            },\r\n" + 
				"            \"drink\": \"Expresso\"\r\n" + 
				"            }\r\n" + 
				"}";
		try {
			jason = (JSONObject) myParser.parse(phoner);
			AppCommunicator c = new AppCommunicator();
			c.recieveMessageFromPhone(jason);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
