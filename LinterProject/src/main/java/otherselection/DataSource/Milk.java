package otherselection.DataSource;

public class Milk extends OrderDecorator {
	private static final String COMPONENT_NAME = "milk";
	private static final boolean IS_OPTION = false; 

	public Milk(OrderComponent son, int quantity) {
		super(son, IS_OPTION, COMPONENT_NAME, quantity);
		
	}
}
