package org.CPS2.DataSource;

public class DarkRoast extends OrderDecorator {
	private static final String COMPONENT_NAME = "darkroast";
	private static final boolean IS_OPTION = false; 

	public DarkRoast(OrderComponent son, int quantity) {
		super(son, IS_OPTION, COMPONENT_NAME, quantity);
	}
}
