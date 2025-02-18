package DataSource;

import org.json.simple.JSONObject;

public abstract class OrderDecorator implements OrderComponent {
	public boolean isOption;
	protected OrderComponent son; 
	protected String name;
	protected int quantity;
	
	public OrderDecorator(OrderComponent son, boolean isOption, String name, int quantity) {
		this.isOption = isOption;
		this.son = son;
		this.name = name;
		this.quantity = quantity; 
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getIngredients() {
		JSONObject ingrdnts = (JSONObject) son.getIngredients();
		ingrdnts.put(name, quantity);
		
		return ingrdnts;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getOptions() {
		if(this.isOption) {
			return (JSONObject) son.getOptions().put(name, quantity);
		}
		else {
			return son.getOptions();
		}
	}
	public String getInstructions() {
		return son.getInstructions();
	}
	public String getDrinkName() {
		return son.getDrinkName();
	}
	
	public String getAddress() {
		return this.son.getAddress();
	}
}
