package BusinessLogic;

import DataSource.OrderComponent;

public interface ControllerContactBehavior {
	String sendOrder(OrderComponent order, int controllerId);
}
