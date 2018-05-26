
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Evaluation {
	private RuleSet ruleSet;

	private RuleSet forCheckRule;
	private ArrayList<Rule> EventHandlerList;
	private ArrayList<Rule> PredicateList;

	public Evaluation(RuleSet ruleSet) {
		this.ruleSet = ruleSet;
		this.EventHandlerList = new ArrayList<>();
		this.PredicateList = new ArrayList<>();
		this.PredicateList = new ArrayList<>();

	}

	public void Evaluate(RegisteredDevices devices) {
		EventCheck(ruleSet);

		PredicateCheck(EventHandlerList);

		Action(PredicateList, devices);

		EventCheck(ruleSet);

		if (!EventHandlerList.isEmpty()) // 이벤트가 발생했다면 다시 처음 단계로
			for (Rule rule : EventHandlerList) {
				ArrayList<String> CheckEventType = new ArrayList<>();
				CheckEventType.add(rule.GetEventHandler().EventType());
				if (!CheckEventType.contains("Timer"))

					Evaluate(devices);
			}
		else
			return;

	}

	public void EventCheck(RuleSet ruleSet) {

		for (Event event : ruleSet.EventCheck) {

			if (event.IsEventTriggered()) {
				if (!EventHandlerList.contains(event))
					EventHandlerList.addAll(ruleSet.RuleSet.get(event));
				else
					continue;
			}
		}
	}

	public void PredicateCheck(ArrayList<Rule> EventHandlerList) {
		while (!EventHandlerList.isEmpty()) {
			Rule rule = EventHandlerList.get((int) (Math.random() * (EventHandlerList.size())));
			if (rule.GetPredicate().CheckPredicate() && !PredicateList.contains(rule))
				PredicateList.add(rule);
			rule.GetEventHandler().TriggerOff();
			EventHandlerList.remove(rule);

			if (EventHandlerList.isEmpty())
				break;
		}

	}

	public void Action(ArrayList<Rule> PredicateList, RegisteredDevices devices) {
		while (!PredicateList.isEmpty()) {
			Rule rule = PredicateList.get((int) (Math.random() * (PredicateList.size())));
			rule.GetAction().PerformAction();
			if (rule.GetAction().ActionComplete()) {
				System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
				System.out.println(devices.GetDeviceMapList().toString());
				DeviceStatePrinter.print(devices);
			}
			PredicateList.remove(rule);
		}
	}
}