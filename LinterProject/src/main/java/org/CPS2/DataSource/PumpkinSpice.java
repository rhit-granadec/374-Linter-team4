package org.CPS2.DataSource;

public class PumpkinSpice extends OrderDecorator {
	private static final boolean isOption = false;
	private static final String name = "pumpkinspice"; 
	
	public PumpkinSpice(OrderComponent son, int quantity) {
		super(son, isOption, name, quantity);
	
	}

}
