import java.util.Scanner;

public class IotaMain { //3개의 장치만 사용하고 있다는 가정 하에 simulation, 콘솔로 바꾸려는 장치 상태를 입력하면 바뀌고 Device에 Stop을 입력하면 반복을 멈추고 장치들의 상태 출력
	MotionSensor motionSensor;
	Door door;
	HallwayLight hallwayLight;

	public static void main(String[] args) {
		// 여기에 IOTA 프로그래밍 하는 것 처럼 프로그래밍 한다.
		/*
		Evaluation eval = new Evaluation(new RuleSet());
		eval.setDaemon(true);
		eval.start();
		 */
		IotaMain main = new IotaMain();
		
		while(true) {
			Scanner input = new Scanner(System.in);

			System.out.print("Device: ");
			String device = input.nextLine();
			if(device.equals("Stop"))
				break;

			System.out.print("Select: "); // 바꿀 값이 Filed인지 Timer인지
			String select = input.nextLine();

			System.out.print("Value: "); // 바꿀 값이 Filed인지 Timer인지
			String value = input.nextLine();
			
			main.EventTriggered(device, select, value);
		}
		
		System.out.println("MotionSensor: " + main.GetMotionSensorState());
		System.out.println("Door: " + main.GetDoorState());
		System.out.println("HallwayLight: " + main.GetHallwayLightState());
	}

	public IotaMain() {
		motionSensor = new MotionSensor("Off");
		door = new Door("Locked");
		hallwayLight = new HallwayLight("Off");
	}
	public String GetMotionSensorState() {
		return motionSensor.f.GetCurrentValue();
	}
	public String GetDoorState() {
		return door.f.GetCurrentValue();
	}
	public String GetHallwayLightState() {
		return hallwayLight.f.GetCurrentValue();
	}
	public void EventTriggered(String device, String select, String value) throws RuntimeException{ // ex)사람이 들어와서 motion sensor가 on 된것을 반영한다.
		switch(device) { //select가 Timer일 때는 아직 비워둠.
		case "MotionSensor" : 
			switch(select) {
			case "Field" :
				switch(value) {
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
			case "Timer" :
				break;
			default :
				throw new RuntimeException("Please submit \"Field\" or \"Timer\".");
			}
			break;
		case "Door" : 
			switch(select) {
			case "Field" :
				switch(value) {
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
			case "Timer" :
				break;
			default :
				throw new RuntimeException("Please submit \"Field\" or \"Timer\".");
			}
			break;
		case "HallwayLight" : 
			switch(select) {
			case "Field" :
				switch(value) {
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
			case "Timer" :
				break;
			default :
				throw new RuntimeException("Please submit \"Field\" or \"Timer\".");
			}
			break;
		default :
			throw new RuntimeException("Unregistered Device is used.");
		}
	}
}
