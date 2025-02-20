package otherselection.JUnitTests;

//import static org.junit.jupiter.api.Assertions.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
//import org.junit.jupiter.api.Test;

import otherselection.Application.AppCommunicator;

class Milestone3Test {

//	@Test
	void testUC3() {
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
			c.executeOrders();
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
//	@Test
	void testUC4() {
		System.out.println("------------Testing UC4(Programmable with instructions)------------------");
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
			c.executeOrders();
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
//	@Test
	void testError() {
		System.out.println("------------Testing Exception Flow-----------------");
		System.out.println("Status should be: There was an error processing your order. Please try the order again.");
		JSONParser myParser = new JSONParser();
		JSONObject jason;
		String phoner = "{\r\n" + 
				"  \"order\": { \"orderID\": 2,\r\n" + 
				"            \"address\": {\r\n" + 
				"                  \"street\": \"12345 Poggers St\",\r\n" + 
				"                  \"ZIP\": 47803\r\n" + 
				"            },\r\n" + 
				"            \"drink\": \"Expresso\"\r\n" + 
				"            }\r\n" + 
				"}";
		try {
			jason = (JSONObject) myParser.parse(phoner);
			AppCommunicator c = new AppCommunicator();
			c.recieveMessageFromPhone(jason);
			c.executeOrders();
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}


}
