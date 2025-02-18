package org.CPS2;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.CPS2.Application.AppCommunicator;

public class Main {
	

	public static void main(String[] args) {
		System.out.println("------------Testing UC3 (Programmable)------------------");
		System.out.println("Status should be: Your coffee has been prepared with the specified recipie.");
		
		JSONParser myParser = new JSONParser();
		JSONObject jason;
		String phoner = "{\r\n" + 
				"  \"order\": { \"orderID\": 1,\r\n" + 
				"            \"address\": {\r\n" + 
				"                  \"street\": \"3 S. Pogger\",\r\n" + 
				"                  \"ZIP\": 47803\r\n" + 
				"            },\r\n" + 
				"            \"drink\": \"Pumpkin Spice\",\r\n" + 
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
}
