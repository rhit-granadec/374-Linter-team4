package DataSource;

public class Sugar extends OrderDecorator {
	private static final String COMPONENT_NAME = "sugar";
	private static final boolean IS_OPTION = true; 

	public Sugar(OrderComponent son, int quantity) {
		super(son, IS_OPTION, COMPONENT_NAME, quantity);
		
	}
}
