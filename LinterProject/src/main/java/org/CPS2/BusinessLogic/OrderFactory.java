package org.CPS2.BusinessLogic;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.CPS2.DataSource.*;

public class OrderFactory {


	public OrderComponent createDrink(String componentName, String address, HashMap<String, Integer> conds) {
		switch(componentName) {
		case "Americano":
			OrderComponent myOrder = new DarkRoast(new BaseOrder("Americano", "", address), 1);
			return (OrderComponent) this.addCondiments(conds, myOrder);
	
			
		case "Espresso":
			return (OrderComponent) this.addCondiments(conds, new BaseOrder("Espresso", "", address));
			
		case "Pumpkin Spice":
			OrderComponent order = new BaseOrder("Pumpkin Spice", "Add coffee, Add pumpkin spice, Add cream, Mix, Top with nutmeg", address);
			order = new PumpkinSpice(order, 1);
			order = new Cream(order, 1);
			order = new Nutmeg(order, 1);
			return order;
		default:
			return (OrderComponent) new BaseOrder("Americano", "", address);
		}
		
	}
	
	private OrderComponent addCondiment(String condimentName, int quantity, OrderComponent drink) {
		if(condimentName.equals("NutraSweet")) {
			return new NutraSweet(drink, quantity);
		}else if(condimentName.equals("Sugar")) {
			return new Sugar(drink, quantity);
		}else if(condimentName.equals("Cream")) {
			return new Cream(drink, quantity);
		}else {
			return drink;
		}
	}
	
	private OrderComponent addCondiments(HashMap<String, Integer> conds, OrderComponent drink) {
		Set<String> myConds = conds.keySet();
		
		OrderComponent myFunnyDrink = drink;
		
		for (String cond : myConds) {
			myFunnyDrink = addCondiment(cond, conds.get(cond), myFunnyDrink);
		}
		
		return myFunnyDrink;
	}

}
