package DataSource;

public class Cream extends OrderDecorator {
	private static final String COMPONENT_NAME = "cream";
	private static final boolean IS_OPTION = true; 

	public Cream(OrderComponent son, int quantity) {
		super(son, IS_OPTION, COMPONENT_NAME, quantity);
		
	}
}
