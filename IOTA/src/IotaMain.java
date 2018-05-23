
import java.util.Scanner;

public class IotaMain {
	RegisteredDevices devices;
	Evaluation eval;
	static SystemTimeCheck time;
	Scanner input;
	RuleSet ruleset;

	public static void main(String[] args) throws InterruptedException {
		// Scanner input = new Scanner(System.in);
		IotaMain main = new IotaMain();
		main.devices = new RegisteredDevices();
		main.input = new Scanner(System.in);
		

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
		AnyActions as2 = new AnyActions();
		as2.addAction(a21);
		Rule rule2 = new Rule(e2, p2, as2);
		/// Rule 3 for Timer
		NormalEvent e3 = NormalEvent.From(main.devices.GetDevice("HallwayLight").GetEventElement("Switch"), "On"); // Event
		CompPredicate p3 = CompPredicate.CompEqual(main.devices.GetDevice("EntranceDoor"), "Lock", "Locked"); // Condition

		CompPredicate p3timer = CompPredicate.CompEqual(main.devices.GetDevice("KitchenDoor"), "Switch", "On"); // TimerCondition
		OneAction a3 = new OneAction(main.devices.GetDevice("KitchenDoor"), "Switch", "Off"); // TimerAction
		OneAction a4 = new OneAction(main.devices.GetDevice("PorchMotionSensor"), "Switch", "Off");
		AnyActions acs = new AnyActions(); // 액션을 한번에 여러개 실행.
		acs.addAction(a3);
		acs.addAction(a4);
		Rule rule3Timer = new Rule(new NormalEvent(), new ConstPredicate(true), acs); // TimerRuleSet

		TimerAction ta3 = new TimerAction(rule3Timer, "5", 3); // TimerAction Set

		Rule rule3 = new Rule(e3, p3, ta3); // Rule 3 for Timer

		/// rule 4 지정 시간 이벤트
		TimerEvent te1 = new TimerEvent("10:14:00", 10, -1); // Event
		CompPredicate p4 = CompPredicate.CompEqual(main.devices.GetDevice("KitchenDoor"), "Switch", "On"); // Condition

		OneAction a5 = new OneAction(main.devices.GetDevice("KitchenDoor"), "Switch", "Off"); // TimerAction

		CompPredicate p5 = CompPredicate.CompEqual(main.devices.GetDevice("KitchenDoor"), "Switch", "Off"); // Condition

		OneAction a6 = new OneAction(main.devices.GetDevice("KitchenDoor"), "Switch", "On"); // TimerAction

		Rule rule4 = new Rule(te1, p4, a5);
		Rule rule5 = new Rule(te1, p5, a6);

		//// Rule Set
		main.ruleset = new RuleSet();
		main.ruleset.add(rule1);
		main.ruleset.add(rule2);
		main.ruleset.add(rule3);
		main.ruleset.add(rule4);
		main.ruleset.add(rule5);

		//// rule

		/// evaluation, timer Set

		main.eval = new Evaluation(main.ruleset);
		main.time = new SystemTimeCheck(main);
		time.SetVirtualTimer();

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
				System.out.println(main.time.TimeToString);
				main.time.VirtaulTimerPLUS();
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
