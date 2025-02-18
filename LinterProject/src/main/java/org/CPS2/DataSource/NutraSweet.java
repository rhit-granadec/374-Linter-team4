package org.CPS2.DataSource;

public class NutraSweet extends OrderDecorator {
	private static final String COMPONENT_NAME = "nutrasweet";
	private static final boolean IS_OPTION = true; 

	public NutraSweet(OrderComponent son, int quantity) {
		super(son, IS_OPTION, COMPONENT_NAME, quantity);
	}
}
