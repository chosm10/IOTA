import java.util.Scanner;

public class IotaMain { 
	private RegisteredDevices devices;

	public static void main(String[] args) throws InterruptedException {
		Scanner input = new Scanner(System.in);
		IotaMain main = new IotaMain();
		main.devices = new RegisteredDevices();
		
		/*
		Event e1 = Event.From(main.devices.GetDevice("EntranceDoor")
				.GetEventElement("Lock"), "Locked");
		CompPredicate p1 = CompPredicate.CompEqual(main.devices
				.GetDevice("EntranceDoor"), "Lock", "UnLocked");
		OneAction a1 = new OneAction(main.devices
				.GetDevice("HallwayLight"), "Switch", "On");
		Rule rule1 = new Rule(e1, p1, a1);

		RuleSet ruleset = new RuleSet();
		ruleset.add(rule1);
		Evaluation eval = new Evaluation(ruleset);
		*/

		Event e1 = Event.From(main.devices
				.GetDevice("EntranceDoor")
				.GetEventElement("Lock"), "UnLocked");
		CompPredicate p1 = CompPredicate.CompEqual(main.devices
				.GetDevice("EntranceDoor"), "Lock", "Locked");
		TimerAction a1 = TimerAction.TimerStart(main.devices
				.GetDevice("HallwayLight"));
		Rule rule1 = new Rule(e1, p1, a1);

		Event e2 = Event.UnConditional(main.devices
				.GetDevice("HallwayLight")
				.GetEventElement("Timer"));
		CompPredicate p2 = CompPredicate.CompEqual(main.devices
				.GetDevice("HallwayLight"), "Timer", "5");
		OneAction a21 = new OneAction(main.devices
				.GetDevice("HallwayLight"), "Switch", "Off");
		TimerAction a22 = TimerAction.TimerStop(main.devices
				.GetDevice("HallwayLight"));
		AnyActions as2 = new AnyActions();
		as2.addAction(a21);
		as2.addAction(a22);
		Rule rule2 = new Rule(e2, p2, as2);

		RuleSet ruleset = new RuleSet();
		ruleset.add(rule1);
		ruleset.add(rule2);
		Evaluation eval = new Evaluation(ruleset);

		while(true) {
			DeviceStatePrinter.print(main.devices);
			
			System.out.print("Device: ");
			String device = input.nextLine();
			if(device.equals("Stop")) { // Stop이 입력 되면 스레드 중지
				System.out.println("IOTA가 종료 되었습니다.");
				break;
			}

			System.out.print("Field: "); 
			String field = input.nextLine();

			System.out.print("State: "); // 
			String state = input.nextLine();

			main.EventTrigger(device, field, state);
			eval.Evaluate(main.devices);
		}
	}

	public IotaMain() {

	}
	public void EventTrigger(String devName, String field, String state) throws RuntimeException {
		if(!devices.IsRegisteredDevice(devName)) //등록된 장치인지 확인
			throw new RuntimeException(devName + "는 등록된 장치명이 아닙니다. 장치명을 다시 확인해 주세요.");
		if(!field.equals("Timer")) {
			if(!devices.GetDeviceProperty(devName).IsRegisteredProperty(field)) //등록된 property 값을 입력했는지 확인
				throw new RuntimeException(field + "는 장치에 등록된 속성이 아닙니다. 속성을 다시 확인해 주세요.");
			if(!devices.GetDeviceProperty(devName).IsRegisteredPropertyState(field, state)) //등록된 property 값을 입력했는지 확인
				throw new RuntimeException(state + "는 장치에 등록된 속성 값이 아닙니다. 속성 값을 다시 확인해 주세요.");
			//여기까지 오면 등록된 장치의 등록된 property를 바꾸는게 된다.
			//여기서 이제 기존에는 필드를 하나만 갖고 있는 거로 생각해서 그냥 device에 필드 1개만 있었지만
			//여러개 필드가 있는걸로 해야하기 때문에 device에 HashMap(String fieldName, Field f)를 만들어서
			//여러개 필드를 각 장치마다 필요하면 추가하고 필드 값을 바꾸는 것도 그 장치의 필드 중 특정 필드를 선택해서 그
			//값을 변경하도록 하고, 이벤트에서 발생했는지 체크 할때도 장치의 특정 필드를 선택해 그것의 값이 바꼇는지 체크하게 하자.
			devices.GetDevice(devName).DeviceFieldChange(field, state);
		}
		else {
			devices.GetDevice(devName).SetVirtualTime(state);
		}
	}
}
