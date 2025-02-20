package otherselection.BusinessLogic;

import otherselection.DataSource.OrderComponent;

public interface ControllerContactBehavior {
	String sendOrder(OrderComponent order, int controllerId);
}
