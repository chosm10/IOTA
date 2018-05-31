
import java.util.ArrayList;



public class Evaluation {
	private RuleSet ruleSet;
	private ArrayList<Rule> EventHandlerList;
	private ArrayList<Rule> PredicateList;

	public Evaluation(RuleSet ruleSet) {
		this.ruleSet = ruleSet;
		this.EventHandlerList = new ArrayList<>();
		this.PredicateList = new ArrayList<>();
		this.PredicateList = new ArrayList<>();

	}

	public void Evaluate(RegisteredDevices devices) {
		EventCheck(this.ruleSet);

		while (!EventHandlerList.isEmpty()) {

			PredicateCheck(EventHandlerList);
			Action(PredicateList, devices);
			EventCheck(this.ruleSet);

			if (!EventHandlerList.isEmpty()) {
				ArrayList<String> CheckEventType = new ArrayList<>();
				for (Rule rule : EventHandlerList) {
					CheckEventType.add(rule.GetEventHandler().EventType());
				}
				if (!CheckEventType.contains("Normal"))
					break;
			} else
				break;
		}
	}

	public void EventCheck(RuleSet ruleSet) {

		for (Event event : ruleSet.EventCheck) {
			if (event.IsEventTriggered()) {
				if (!EventHandlerList.contains(ruleSet.RuleSet.get(event)))
					EventHandlerList.addAll(ruleSet.RuleSet.get(event));
				else
					continue;
				event.TriggerOff();
			}
		}
		
		PrintEventHandler();
	}

	public void PredicateCheck(ArrayList<Rule> EventHandlerList) {
		while (!EventHandlerList.isEmpty()) {
			Rule rule = EventHandlerList.get((int) (Math.random() * (EventHandlerList.size())));
			if (rule.GetPredicate().CheckPredicate() && !PredicateList.contains(rule))
				PredicateList.add(rule);
			EventHandlerList.remove(rule);
		}
		if (!PredicateList.isEmpty())
			System.out.println("---\nEventHandler\t-> Predicate\t= " + PredicateList.toString());
	}

	public void Action(ArrayList<Rule> PredicateList, RegisteredDevices devices) {
		while (!PredicateList.isEmpty()) {
			Rule rule = PredicateList.get((int) (Math.random() * (PredicateList.size())));
			rule.GetAction().PerformAction();
			System.out.println("---\nPredicate\t-> Action\t= " + rule.GetAction().ActionName());
			if (rule.GetAction().ActionComplete())
				PrintConsole(devices);

			PredicateList.remove(rule);
		}

	}

	public void PrintEventHandler() {
		if (!EventHandlerList.isEmpty()) {
			ArrayList<String> CheckEventType = new ArrayList<>();
			for (Rule rule : EventHandlerList) {

				CheckEventType.add(rule.GetEventHandler().EventType());
			}
			if (!CheckEventType.contains("Timer")) {
				
				System.out.println("Rule\t\t-> EventHandler\t= " + EventHandlerList.toString());
			}
		}
	}

	public void PrintConsole(RegisteredDevices devices) {
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		System.out.println(devices.GetDeviceMapList().toString());
		DeviceStatePrinter.print(devices);
	}

}