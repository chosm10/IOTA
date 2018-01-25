import java.util.Scanner;

public class IotaMain { //3개의 장치만 사용하고 있다는 가정 하에 simulation, 콘솔로 바꾸려는 장치 상태를 입력하면 바뀌고 Device에 Stop을 입력하면 반복을 멈추고 장치들의 상태 출력
	static MotionSensor motionSensor;
	static Door door;
	static HallwayLight hallwayLight;
	static RegisteredDevices devices;
	
	public static void main(String[] args) {
		// 여기에 IOTA 프로그래밍 하는 것 처럼 프로그래밍 한다.
		/*eval은 현재 메인 스레드와 별개의 스레드로 따로 작동하게 해야겠다.
		eval.setDaemon(true);
		eval.start();
		 */
		IotaMain main = new IotaMain();
		Scanner input = new Scanner(System.in);
		
		door = new Door("Door1", "Locked");
		motionSensor = new MotionSensor("MotionSensor1", "Off");
		hallwayLight = new HallwayLight("HallwayLight1", "Off");
		
		devices = new RegisteredDevices();
		devices.addDevice(door);
		devices.addDevice(motionSensor);
		devices.addDevice(hallwayLight);
		
		while(true) {
			System.out.println("MotionSensor: " + motionSensor.GetCurrentState());
			System.out.println("Door: " + door.GetCurrentState());
			System.out.println("HallwayLight: " + hallwayLight.GetCurrentState());
			
			System.out.print("Device: ");
			String device = input.nextLine();
			if(device.equals("Stop")) { // Stop이 입력 되면 스레드 중지
				System.out.println("IOTA가 종료 되었습니다.");
				break;
			}
 
			System.out.print("State: "); // 바꿀 값이 Filed인지 Timer인지
			String state = input.nextLine();

			EventTrigger(device, state);
		}
	}

	public IotaMain() {
	}
	public static void EventTrigger(String device, String state) throws RuntimeException{ // ex)사람이 들어와서 motion sensor가 on 된것을 반영한다.
		switch(device) { 
		case "MotionSensor" : 
				switch(state) {
				case "On" :
					motionSensor.f.FieldChange("On");
					break;
				case "Off" :
					motionSensor.f.FieldChange("Off");
					break;
				default : 
					throw new RuntimeException("Please submit \"On\" or \"Off\".");
				}
				break;
		case "Door" : 
				switch(state) {
				case "Locked" :
					door.f.FieldChange("Locked");
					break;
				case "UnLocked" :
					door.f.FieldChange("UnLocked");
					break;
				default : 
					throw new RuntimeException("Please submit \"Locked\" or \"UnLocked\".");
				}
				break;
		case "HallwayLight" : 
				switch(state) {
				case "On" :
					hallwayLight.f.FieldChange("On");
					break;
				case "Off" :
					hallwayLight.f.FieldChange("Off");
					break;
				default : 
					throw new RuntimeException("Please submit \"On\" or \"Off\".");
				}
				break;
		default :
			throw new RuntimeException("Unregistered Device is used.");
		}
	}
}
