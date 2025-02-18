package Application;
import java.util.LinkedList;
import java.util.Queue;

import org.json.simple.JSONObject;

import BusinessLogic.OrderCreator;
import BusinessLogic.OrderObserver;; 

public class AppCommunicator implements OrderObserver{
	OrderCreator orderCreator;
	Queue<JSONObject> orderQueue = new LinkedList<JSONObject>();

	
	public AppCommunicator() {
		super();
		this.orderCreator = new OrderCreator();
		
	}
	
	@Override
	public void update(String message) {
		this.notifyPhoneApp(message);
		
	}

	
	@SuppressWarnings("unchecked")
	private JSONObject notifyPhoneApp(String message) {
		//This is to simulate pushing a message to the app for testing
		
		JSONObject userResponse = new JSONObject();
		userResponse.put("orderID", new Integer(0));
		userResponse.put("status-message", message);
		System.out.println("Message sent to phone: " + userResponse);
		return userResponse;
		
	}
	
	public void recieveMessageFromPhone(JSONObject message) {
		//This is to simulate getting a message from the phone app for testing
		orderQueue.add(message);
		System.out.println("Recieved Order From App: "+ message);
	}
	
	
	public void executeOrders() {
		//This is to simulate getting a message from the phone app for testing
		
		this.orderCreator.registerObserver(this);
		
		for(JSONObject order : orderQueue) {
			this.orderCreator.processOrder(order);
		}
		
	}

}
