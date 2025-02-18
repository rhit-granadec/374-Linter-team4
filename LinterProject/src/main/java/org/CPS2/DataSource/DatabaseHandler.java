package org.CPS2.DataSource;

public class DatabaseHandler {
	String dbConnection;
	private static final int SIMPLE = 1;
	private static final int ADVANCED = 2;
	private static final int PROGRAMMABLE = 3;

	public DatabaseHandler() {
		this.dbConnection = "Fake connection. There isn't any database."; //TODO: Add database
	}
	
	public int getControllerTypeFromAddress(String address) {
		//pretend this queries the database
		if(address.equals("200 N Main")) {
			return ADVANCED;
		}else if (address.equals("3 S. Walnut")) {
			return SIMPLE;
		} else if(address.equals("3 S. Pogger")){
			return PROGRAMMABLE;
		}else {
	
			return -1;
		}
	}

	public int getControllerIdFromAddress(String address) {
//		System.out.println(address);
		//pretend this queries the database
		if(address.equals("200 N Main")) {
			return SIMPLE;
		}else if (address.equals("3 S. Walnut")) {
			return ADVANCED;
		} else if(address.contentEquals("3 S. Pogger")){
			return PROGRAMMABLE;
		}else {
			return -1;
		}
	}
}
