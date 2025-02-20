package otherselection.Testing;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TestController {
	//For testing purposes to simulate sending a command to a controller
	public JSONObject recieveOrder(JSONObject jason) throws ParseException {
		int controllerId =  (int) jason.get("controller_id");
		
		System.out.println("Instruction Recieved by Controller: " + jason.toString());

		
		JSONObject response;
		JSONParser myParser = new JSONParser();
		

		
		if(controllerId == 1) {
			response = (JSONObject) myParser.parse("{\r\n" + 
					"  \"drinkresponse\": {\r\n" + 
					"    \"orderID\": 1,\r\n" + 
					"    \"status\": 0\r\n" + 
					"  }\r\n" + 
					"}");
		}else if(controllerId == 2) {
			response = (JSONObject) myParser.parse("{\r\n" + 
					"  \"drinkresponse\": {\r\n" + 
					"    \"orderID\": 1,\r\n" + 
					"    \"status\": 0\r\n" + 
					"  }\r\n" + 
					"}"); 
		}else if(controllerId == 3){
			response = (JSONObject) myParser.parse("{\r\n" + 
					"  \"drinkresponse\": {\r\n" + 
					"    \"orderID\": 1,\r\n" + 
					"    \"status\": 0\r\n" + 
					"  }\r\n" + 
					"}"); 
		}else {
		

			response = (JSONObject) myParser.parse("{\r\n" + 
					"  \"drinkresponse\": {\r\n" + 
					"    \"orderID\": 3,\r\n" + 
					"    \"status\": 1,\r\n" + 
					"    \"errordesc\": \"Machine broke.\",\r\n" + 
					"    \"errorcode\": 26\r\n" + 
					"  }\r\n" + 
					"}");
		}
		

			return response;
	

	}
	
}
