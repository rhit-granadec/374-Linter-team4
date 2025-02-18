package DataSource;

import org.json.simple.JSONObject;

public class BaseOrder implements OrderComponent {
	private String drinkName;
	private String instructions;
	private String address;
	
	public BaseOrder(String drinkName, String instructions, String address) {
		this.drinkName = drinkName;
		this.instructions = instructions;
		this.address = address;
	}
	
	
	
	
	@Override
	public JSONObject getIngredients() {
		return new JSONObject();
	}

	@Override
	public JSONObject getOptions() {
		// TODO Auto-generated method stub
		return new JSONObject();
	}

	@Override
	public String getInstructions() {
		// TODO Auto-generated method stub
		return instructions;
	}

	@Override
	public String getDrinkName() {
		return drinkName;
	}
	
	public String getAddress() {
		return this.address;
	}

}
