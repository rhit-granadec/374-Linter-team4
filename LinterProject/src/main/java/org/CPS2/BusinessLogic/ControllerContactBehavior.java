package org.CPS2.BusinessLogic;

import org.CPS2.DataSource.OrderComponent;

public interface ControllerContactBehavior {
	String sendOrder(OrderComponent order, int controllerId);
}
