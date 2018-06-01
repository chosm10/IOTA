
import java.util.ArrayList;
import java.util.HashMap;


public class RuleSet {

	HashMap<Event, ArrayList<Rule>> RuleSet; //이벤트를 key로 설정하여 해당하는 룰들을 정리
	ArrayList<Event> EventCheck;
	ArrayList<Rule> RuleList;

	public RuleSet() {

		this.RuleSet = new HashMap<>();
		this.EventCheck = new ArrayList<>();
		this.RuleList = new ArrayList<>();

	}

	public void add(Rule r) {
		if (!RuleSet.containsKey(r.getEventHandler())) {
			RuleSet.put(r.getEventHandler(), new ArrayList<>());
			
		}
		RuleSet.get(r.getEventHandler()).add(r);
		EventCheck.add(r.getEventHandler());
		RuleList.add(r);
	}
}
