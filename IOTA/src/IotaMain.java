
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
		NormalEvent e1 = NormalEvent.From(main.devices.GetDevice("EntranceDoor").GetEventElement("Lock"), "Locked");
		CompPredicate p1 = CompPredicate.CompEqual(main.devices.GetDevice("EntranceDoor"), "Lock", "UnLocked");
		OneAction a1 = new OneAction(main.devices.GetDevice("HallwayLight"), "Switch", "Off");
		Rule rule1 = new Rule(e1, p1, a1);
		/// Rule 2
		NormalEvent e2 = NormalEvent.FromTo(main.devices.GetDevice("HallwayLight").GetEventElement("Switch"), "On",
				"Off");
		CompPredicate p2 = CompPredicate.CompEqual(main.devices.GetDevice("KitchenDoor"), "Lock", "Locked");
		OneAction a21 = new OneAction(main.devices.GetDevice("KitchenDoor"), "Lock", "UnLocked");
		TimerAction ta = new TimerAction(main.devices.GetDevice("PorchMotionSensor").Timer, "ON");
		AnyActions as2 = new AnyActions();
		as2.addAction(a21);
		as2.addAction(ta);
		Rule rule2 = new Rule(e2, p2, as2);
		/// Rule 3 for Timer
		
		TimerEvent te = TimerEvent.Timer((main.devices.GetDevice("PorchMotionSensor").Timer));
		TimerPredicate tp = new TimerPredicate(main.devices.GetDevice("PorchMotionSensor").Timer, "5");
		AnyActions tas = new AnyActions();
		OneAction ta1 = new OneAction(main.devices.GetDevice("PorchMotionSensor"), "Switch", "Off");
		TimerAction ta2 = new TimerAction(main.devices.GetDevice("PorchMotionSensor").Timer, "OFF");
		tas.addAction(ta1);
		tas.addAction(ta2);
		
		Rule rule3 = new Rule(te, tp, tas);

		//// Rule Set
		ruleset= new RuleSet();
		ruleset.add(rule1);
		ruleset.add(rule2);
		ruleset.add(rule3);

	
		main.eval = new Evaluation(ruleset);
		main.time = new SystemTimeCheck(main);
		
		time.SetVirtualTimer();
		//time.SetTimer();
	
		/// 콘솔 창 출력
		Console(main);

	}

	public IotaMain() {

	}

	public void EventTrigger(String devName, String field, String state) throws RuntimeException {
		if (!devices.IsRegisteredDevice(devName)) { // 등록된 장치인지 확인

			System.out.println(devName + "는 등록된 장치명이 아닙니다. 장치명을 다시 확인해 주세요.");
			return;
			// throw new RuntimeException(devName + "는 등록된 장치명이 아닙니다. 장치명을 다시 확인해 주세요.");
		}
		if (!devices.GetDeviceProperty(devName).IsRegisteredProperty(field)) {// 등록된 property 값을 입력했는지 확인
			System.out.println(field + "는 장치에 등록된 속성이 아닙니다. 속성을 다시 확인해 주세요.");
			return;
			// throw new RuntimeException(field + "는 장치에 등록된 속성이 아닙니다. 속성을 다시 확인해 주세요.");
		}
		if (!devices.GetDeviceProperty(devName).IsRegisteredPropertyState(field, state)) {// 등록된 property 값을 입력했는지 확인
			System.out.println(state + "는 장치에 등록된 속성 값이 아닙니다. 속성 값을 다시 확인해 주세요.");
			return;
			// throw new RuntimeException(state + "는 장치에 등록된 속성 값이 아닙니다. 속성 값을 다시 확인해
			// 주세요.");
		}
		// 입력한 정보를 이용해서 해당하는 액션을 구성한다.
		Action action = new OneAction(devices.GetDevice(devName), field, state);

		// 액션이 일어나는 필드를 확인하고, 액션이 일어날지 안일어날지를 결정한다.
		if (!devices.GetDevice(devName).GetEventElement(field).GetCurrentValue().equals(state)) {
			action.PerformAction();
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			System.out.println(devices.GetDeviceMapList().toString());
			DeviceStatePrinter.print(devices);

		}

	}

	public static void Console(IotaMain main) {
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		System.out.println(main.devices.GetDeviceMapList().toString());
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
				main.time.VirtaulTimerPLUS();
				System.out.println(main.time.TimeToString);
				continue;
			}

			System.out.print("Field: ");
			String field = main.input.nextLine();

			System.out.print("State: "); 
			String state = main.input.nextLine();

			main.EventTrigger(device, field, state);

			main.eval.Evaluate(main.devices);

		}

	}
}
