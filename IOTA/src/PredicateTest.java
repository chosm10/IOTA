import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PredicateTest {
	static RegisteredDevices devices;

	@Test
	void test() {
		Door entranceDoor = new Door("EntranceDoor"); //장치명, 초기 속성값
		Door kitchenDoor = new Door("KitchenDoor");
		MotionSensor porchMotionSensor = new MotionSensor("PorchMotionSensor");
		HallwayLight hallwayLight = new HallwayLight("HallwayLight1");
		
		devices = new RegisteredDevices();
		devices.AddDevice(entranceDoor);
		devices.AddDevice(kitchenDoor);
		devices.AddDevice(porchMotionSensor);
		devices.AddDevice(hallwayLight);
		
		//Predicate 확인
		CompPredicate cp1 = new CompPredicate(devices.GetDevice("EntranceDoor"), "Lock", 1, "Locked"); // predicate 작동 확인
		CompPredicate cp2 = new CompPredicate(devices.GetDevice("PorchMotionSensor"), "Switch", 1, "On");
		LogicalPredicate cp3 = new LogicalPredicate(cp1, 2, cp2);
		LogicalPredicate cp4 = new LogicalPredicate(3, cp2);
		ConstPredicate cp5 = new ConstPredicate(false);
		
		assert(cp1.CheckPredicate() == true);
		assert(cp2.CheckPredicate() == true);
		assert(cp3.CheckPredicate() == true);
		assert(cp4.CheckPredicate() == false);
		assert(cp5.CheckPredicate() == false);
	}

}
