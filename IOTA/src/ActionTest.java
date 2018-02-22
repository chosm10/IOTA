import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ActionTest {
	static RegisteredDevices devices;

	@Test
	void test() {
		Door kitchenDoor = new Door("KitchenDoor");
		MotionSensor porchMotionSensor = new MotionSensor("PorchMotionSensor");

		devices = new RegisteredDevices();
		devices.AddDevice(kitchenDoor);
		devices.AddDevice(porchMotionSensor);


		//Action »Æ¿Œ
		OneAction action1 = new OneAction(kitchenDoor, "Lock", "UnLocked");
		action1.PerformAction();
		assert(devices.GetDevice("KitchenDoor").GetCurrentState("Lock").equals("UnLocked"));
		
		OneAction action2 = new OneAction(kitchenDoor, "Lock", "Locked");
		OneAction action3 = new OneAction(porchMotionSensor, "Switch", "On");
		AnyActions actions1 = new AnyActions();
		actions1.addAction(action2);
		actions1.addAction(action3);
		actions1.PerformAction();

		assert(devices.GetDevice("KitchenDoor").GetCurrentState("Lock").equals("Locked"));
		assert(devices.GetDevice("PorchMotionSensor").GetCurrentState("Switch").equals("On"));
	}

}
