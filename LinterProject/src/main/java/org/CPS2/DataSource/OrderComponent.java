package DataSource;

import org.json.simple.JSONObject;

public interface OrderComponent {
	public JSONObject getIngredients();
	public JSONObject getOptions();
	public String getInstructions();
	public String getDrinkName();	
	public String getAddress();
}
