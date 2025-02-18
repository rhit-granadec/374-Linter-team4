package org.CPS2.BusinessLogic;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import org.CPS2.DataSource.OrderComponent;
import org.CPS2.DataSource.DatabaseHandler;

import org.json.simple.parser.JSONParser;

public class OrderCreator {
	private ArrayList<OrderObserver> observers;
	DatabaseHandler myDB;
	OrderFactory orderFactory;
	
	public OrderCreator() {
		this.observers = new ArrayList<OrderObserver>();
		this.myDB = new DatabaseHandler();
		this.orderFactory = new OrderFactory();
	}


	void notifyObservers(String notification) {
		for (OrderObserver ob : this.observers) {
			ob.update(notification);
		}

	}
	
	public void registerObserver(OrderObserver funnyObserver) {
		this.observers.add(funnyObserver);
	}
	
	public void removeObserver(OrderObserver sillyObserver) {
		this.observers.remove(sillyObserver);
	}
	
	public void processOrder(JSONObject message) {
		OrderComponent myOrder = createOrderFromJSON(message);
		
		int controllerType = myDB.getControllerTypeFromAddress(myOrder.getAddress()); 
		int controllerId = myDB.getControllerIdFromAddress(myOrder.getAddress());
		
		ControllerContactBehavior controllerContactBehavior;
		switch(controllerType) {
			case 1:
				controllerContactBehavior = new SimpleControllerContact();
				break;
			case 2: 
				controllerContactBehavior = new AdvancedControllerContact();
				break;
			case 3:
				controllerContactBehavior = new ProgrammableControllerContact();
				break;
			default:
				controllerContactBehavior = new ProgrammableControllerContact();
		}
		
		notifyObservers(controllerContactBehavior.sendOrder(myOrder, controllerId));
			

	}
	
	private OrderComponent createOrderFromJSON(JSONObject orderJSON) {
		
		
		JSONObject order = (JSONObject) orderJSON.get("order");
		JSONArray condiments = (JSONArray) order.get("condiments");
		HashMap<String, Integer> condimentMap = new HashMap<String, Integer>();
		String drinkName = (String) order.get("drink");
		

		JSONObject address = (JSONObject) order.get("address");
		String streetAddress = (String) address.get("street");
		
		if(condiments != null) {
			for(int i = 0; i<condiments.size(); i++) {
				JSONObject cond = (JSONObject) condiments.get(i);
				String condName = (String) cond.get("name");
				long condQty = (long) cond.get("qty");
				condimentMap.put(condName, (int) condQty);
			}
		}

		OrderComponent myOrder = this.orderFactory.createDrink(drinkName, streetAddress, condimentMap);
		


		
		
		
		return myOrder;
	}
}

