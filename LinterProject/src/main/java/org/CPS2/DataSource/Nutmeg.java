package DataSource;

public class Nutmeg extends OrderDecorator {
	private static final String COMPONENT_NAME = "nutmeg";
	private static final boolean IS_OPTION = false; 

	public Nutmeg(OrderComponent son, int quantity) {
		super(son, IS_OPTION, COMPONENT_NAME, quantity);
		
	}
}
