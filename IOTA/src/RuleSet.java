
import java.util.ArrayList;
import java.util.HashMap;


public class RuleSet {

	HashMap<Event, ArrayList<Rule>> ruleSet; //이벤트를 key로 설정하여 해당하는 룰들을 정리
	ArrayList<Event> eventList;
	ArrayList<Rule> ruleList;

	public RuleSet() {

		this.ruleSet = new HashMap<>();
		this.eventList = new ArrayList<>();
		this.ruleList = new ArrayList<>();

	}

	public void add(Rule r) {
		if (!ruleSet.containsKey(r.getEventHandler())) {
			ruleSet.put(r.getEventHandler(), new ArrayList<>());
			
		}
		ruleSet.get(r.getEventHandler()).add(r);
		eventList.add(r.getEventHandler());
		ruleList.add(r);
	}
}
