import java.util.Scanner;

public class IotaMain { //3개의 장치만 사용하고 있다는 가정 하에 simulation, 콘솔로 바꾸려는 장치 상태를 입력하면 바뀌고 Device에 Stop을 입력하면 반복을 멈추고 장치들의 상태 출력
	static RegisteredDevices devices;
	
	public static void main(String[] args) {
		// 여기에 IOTA 프로그래밍 하는 것 처럼 프로그래밍 한다.
		/*eval은 현재 메인 스레드와 별개의 스레드로 따로 작동하게 해야겠다.
		eval.setDaemon(true);
		eval.start();
		 */
		Scanner input = new Scanner(System.in);
		
		Door entranceDoor = new Door("EntranceDoor", "Locked"); //장치명, 초기 속성값
		Door kitchenDoor = new Door("KitchenDoor", "Locked");
		MotionSensor motionSensor = new MotionSensor("MotionSensor1", "Off");
		HallwayLight hallwayLight = new HallwayLight("HallwayLight1", "Off");
		
		devices = new RegisteredDevices();
		devices.AddDevice(entranceDoor);
		devices.AddDevice(kitchenDoor);
		devices.AddDevice(motionSensor);
		devices.AddDevice(hallwayLight);
		
		CompPredicate cp1 = new CompPredicate(entranceDoor, "Field", 1, "Locked"); // predicate 작동 확인
		CompPredicate cp2 = new CompPredicate(motionSensor, "Field", 1, "On");
		LogicalPredicate cp3 = new LogicalPredicate(cp1, 2, cp2);
		LogicalPredicate cp4 = new LogicalPredicate(3, cp2);
		ConstPredicate cp5 = new ConstPredicate(false);
		System.out.println(cp1.CheckPredicate());
		System.out.println(cp2.CheckPredicate());
		System.out.println(cp3.CheckPredicate());
		System.out.println(cp4.CheckPredicate());
		System.out.println(cp5.CheckPredicate());
		while(true) {
			for(String devName : devices.GetDeviceMapList()) {
				System.out.println(devName + " : " + devices.GetDevice(devName).GetCurrentState());
			} 
			
			System.out.print("Device: ");
			String device = input.nextLine();
			if(device.equals("Stop")) { // Stop이 입력 되면 스레드 중지
				System.out.println("IOTA가 종료 되었습니다.");
				break;
			}
 
			System.out.print("State: "); // 바꿀 값이 Filed인지 Timer인지
			String state = input.nextLine();

			EventTrigger(device, state);
			//System.out.println(cp.CheckPredicate());
		}
	}

	public IotaMain() {
	}
	public static void EventTrigger(String devName, String state) throws RuntimeException {
		if(!devices.IsRegisteredDevice(devName)) //등록된 장치인지 확인
			throw new RuntimeException(devName + "는 등록된 장치명이 아닙니다. 장치명을 다시 확인해 주세요.");
		else if(!devices.GetDeviceProperty(devName).IsRegisteredProperty(state)) //등록된 property 값을 입력했는지 확인
			throw new RuntimeException(state + "는 장치에 등록된 속성 값이 아닙니다. 속성 값을 다시 확인해 주세요.");
		
		//여기까지 오면 등록된 장치의 등록된 property를 바꾸는게 된다.
		devices.GetDevice(devName).DeviceFieldChange(state);
	}
}
