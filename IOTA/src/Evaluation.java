
import java.util.ArrayList;

public class Evaluation {
	private RuleSet ruleSet;
	private ArrayList<Rule> eventHandlerList;
	private ArrayList<Rule> predicateList;

	public Evaluation(RuleSet ruleSet) {
		this.ruleSet = ruleSet;
		this.eventHandlerList = new ArrayList<>();
		this.predicateList = new ArrayList<>();
		this.predicateList = new ArrayList<>();

	}

	public void evaluate(RegisteredDevices devices) {
		eventCheck(this.ruleSet);

		while (!eventHandlerList.isEmpty()) {

			predicateCheck(eventHandlerList);
			actionPerform(predicateList, devices);
			eventCheck(this.ruleSet);

			if (eventHandlerList.isEmpty())
				break;

			ArrayList<String> checkEventType = new ArrayList<>();
			for (Rule rule : eventHandlerList) {
				checkEventType.add(rule.getEventHandler().getEventType());
			}
			if (!checkEventType.contains("Normal"))
				break;
		}

	}

	public void eventCheck(RuleSet ruleSet) {

		for (Event event : ruleSet.eventList) {
			if (event.isEventHandler()) {
				if (!eventHandlerList.contains(ruleSet.ruleSet.get(event)))
					eventHandlerList.addAll(ruleSet.ruleSet.get(event));
				else
					continue;
				event.triggerOff();
			}
		}

		printEventHandler();
	}

	public void predicateCheck(ArrayList<Rule> EventHandlerList) {
		while (!EventHandlerList.isEmpty()) {
			Rule rule = EventHandlerList.get((int) (Math.random() * (EventHandlerList.size())));
			if (rule.getPredicate().checkPredicate() && !predicateList.contains(rule))
				predicateList.add(rule);
			EventHandlerList.remove(rule);
		}
		if (!predicateList.isEmpty())
			System.out.println("---\nEventHandler\t-> Predicate\t= " + predicateList.toString());
	}

	public void actionPerform(ArrayList<Rule> PredicateList, RegisteredDevices devices) {
		while (!PredicateList.isEmpty()) {
			Rule rule = PredicateList.get((int) (Math.random() * (PredicateList.size())));
			rule.getAction().performAction();
			System.out.println("---\nPredicate\t-> Action\t= " + rule.getAction().getActionName());
			if (rule.getAction().isCompleted())
				printConsole(devices);

			PredicateList.remove(rule);
		}

	}

	public void printEventHandler() {
		if (!eventHandlerList.isEmpty()) {
			ArrayList<String> CheckEventType = new ArrayList<>();
			for (Rule rule : eventHandlerList) {

				CheckEventType.add(rule.getEventHandler().getEventType());
			}
			if (!CheckEventType.contains("Timer")) {

				System.out.println("Rule\t\t-> EventHandler\t= " + eventHandlerList.toString());
			}
		}
	}

	public void printConsole(RegisteredDevices devices) {
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		System.out.println(devices.getDeviceMapList().toString());
		DeviceStatePrinter.print(devices);
	}

}