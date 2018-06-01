
import java.util.Scanner;

public class IotaMain {
	RegisteredDevices devices;
	Evaluation eval;
	static SystemTimeCheck time;
	Scanner input;
	

	public static void main(String[] args) throws InterruptedException {
		// Scanner input = new Scanner(System.in);
		IotaMain main = new IotaMain();
		main.devices = new RegisteredDevices();
		main.input = new Scanner(System.in);
		RuleSet ruleset;

		/// Rule 1
		NormalEvent event1 = NormalEvent.From(main.devices.getDevice("EntranceDoor").getEventElement("Lock"), "Locked");
		CompPredicate predicate1 = CompPredicate.CompEqual(main.devices.getDevice("EntranceDoor"), "Lock", "UnLocked");
		OneAction action1 = new OneAction(main.devices.getDevice("HallwayLight"), "Switch", "Off");
		Rule rule1 = new Rule(event1, predicate1, action1);
		/// Rule 2
		NormalEvent event2 = NormalEvent.FromTo(main.devices.getDevice("HallwayLight").getEventElement("Switch"), "On",
				"Off");
		CompPredicate predicate2 = CompPredicate.CompEqual(main.devices.getDevice("KitchenDoor"), "Lock", "Locked");
		OneAction action2 = new OneAction(main.devices.getDevice("KitchenDoor"), "Lock", "UnLocked");
		TimerAction action22 = new TimerAction(main.devices.getDevice("PorchMotionSensor").Timer, "ON");
		AnyActions actions2 = new AnyActions();
		actions2.addAction(action2);
		actions2.addAction(action22);
		Rule rule2 = new Rule(event2, predicate2, actions2);
		/// Rule 3 for Timer
		
		TimerEvent timerEvent1 = TimerEvent.Timer((main.devices.getDevice("PorchMotionSensor").Timer));
		TimerPredicate timerPredicate1 = new TimerPredicate(main.devices.getDevice("PorchMotionSensor").Timer, "5");
		AnyActions timerActions = new AnyActions();
		OneAction timerAction1 = new OneAction(main.devices.getDevice("PorchMotionSensor"), "Switch", "Off");
		TimerAction timerAction2 = new TimerAction(main.devices.getDevice("PorchMotionSensor").Timer, "OFF");
		timerActions.addAction(timerAction1);
		timerActions.addAction(timerAction2);
		
		Rule rule3 = new Rule(timerEvent1, timerPredicate1, timerActions);

		//// Rule Set
		ruleset= new RuleSet();
		ruleset.add(rule1);
		ruleset.add(rule2);
		ruleset.add(rule3);

	
		main.eval = new Evaluation(ruleset);
		IotaMain.time = new SystemTimeCheck(main);
		
		time.setVirtualTimer();
		//time.SetTimer();
	
		/// 콘솔 창 출력
		printConsole(main);

	}

	public IotaMain() {

	}

	public void getEventHandler(String devName, String field, String state) throws RuntimeException {
		if (!devices.isRegisteredDevice(devName)) { // 등록된 장치인지 확인

			System.out.println(devName + "는 등록된 장치명이 아닙니다. 장치명을 다시 확인해 주세요.");
			return;
			// throw new RuntimeException(devName + "는 등록된 장치명이 아닙니다. 장치명을 다시 확인해 주세요.");
		}
		if (!devices.getDeviceProperty(devName).isRegisteredProperty(field)) {// 등록된 property 값을 입력했는지 확인
			System.out.println(field + "는 장치에 등록된 속성이 아닙니다. 속성을 다시 확인해 주세요.");
			return;
			// throw new RuntimeException(field + "는 장치에 등록된 속성이 아닙니다. 속성을 다시 확인해 주세요.");
		}
		if (!devices.getDeviceProperty(devName).isRegisteredPropertyState(field, state)) {// 등록된 property 값을 입력했는지 확인
			System.out.println(state + "는 장치에 등록된 속성 값이 아닙니다. 속성 값을 다시 확인해 주세요.");
			return;
			// throw new RuntimeException(state + "는 장치에 등록된 속성 값이 아닙니다. 속성 값을 다시 확인해
			// 주세요.");
		}
		// 입력한 정보를 이용해서 해당하는 액션을 구성한다.
		Action newAction = new OneAction(devices.getDevice(devName), field, state);

		// 액션이 일어나는 필드를 확인하고, 액션이 일어날지 안일어날지를 결정한다.
		if (!devices.getDevice(devName).getEventElement(field).getCurrentValue().equals(state)) {
			newAction.performAction();
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			System.out.println(devices.getDeviceMapList().toString());
			DeviceStatePrinter.print(devices);

		}

	}

	public static void printConsole(IotaMain main) {
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		System.out.println(main.devices.getDeviceMapList().toString());
		DeviceStatePrinter.print(main.devices);
		

		while (true) {
			
			System.out.print("Device: ");
			String device = main.input.nextLine();
			if (device.equals("Stop")) { // Stop이 입력 되면 스레드 중지
				System.out.println("IOTA가 종료 되었습니다.");
				break;
			}
			if (device.equals("Log")) { // 지금까지 변한 디바이스의 상태정보 출력
				for (String string : TimeLog.actionLog) {
					System.out.println(string);
				}
				continue;
			}
			if (device.equals("+")) {
				main.time.virtaulTimerPLUS();
				System.out.println(main.time.TimeToString);
				continue;
			}

			System.out.print("Field: ");
			String field = main.input.nextLine();

			System.out.print("State: "); 
			String state = main.input.nextLine();

			main.getEventHandler(device, field, state);

			main.eval.evaluate(main.devices);

		}

	}
}
