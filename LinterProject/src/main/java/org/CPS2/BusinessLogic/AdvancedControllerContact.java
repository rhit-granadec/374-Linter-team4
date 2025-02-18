package org.CPS2.BusinessLogic;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import org.CPS2.DataSource.OrderComponent;
import org.CPS2.Testing.TestController;

public class AdvancedControllerContact implements ControllerContactBehavior {

	@Override
	public String sendOrder(OrderComponent order, int controllerId) {
		// TODO There is no controller so we have a fake method for testing. 
		boolean orderSuccess = true; //change to false to test a failure
		

		
		JSONObject command = createCommandJSON(order, controllerId);
		TestController controller = new TestController(); //for testing purposes
		try {
			JSONObject response = controller.recieveOrder(command);
			JSONObject drinkresponse = (JSONObject) response.get("drinkresponse");
			long status = (long) drinkresponse.get("status");
			
			if(status == 0) {
				return "Your coffee has been prepared with your desired options";
			}else {
				return "There was an error processing your order. Please try the order again.";
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "There was an error processing your order. Please try the order again.";
		}	

	}

	@SuppressWarnings("unchecked")
	private JSONObject createCommandJSON(OrderComponent order, int controllerId) {		
		JSONObject command = new JSONObject();
		command.put("controller_id", controllerId);
		command.put("orderID", Integer.valueOf(0));
		command.put("DrinkName", order.getDrinkName());
		command.put("Requesttype", "Advanced");
		command.put("Options", order.getIngredients());
		
		JSONObject testerino = order.getIngredients();
		
		return command;
	}
}
