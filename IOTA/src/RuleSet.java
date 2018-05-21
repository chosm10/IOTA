
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RuleSet {

	HashMap<Event, ArrayList<Rule>> RuleSet = new HashMap<>(); //이벤트를 key로 설정하여 해당하는 룰들을 정리
	ArrayList<Event> EventCheck = new ArrayList<>();
	ArrayList<Rule> RuleList = new ArrayList<>();

	public RuleSet() {

		this.RuleSet = new HashMap<>();

		this.RuleList = new ArrayList<>();

	}

	public void add(Rule r) {
		if (!RuleSet.containsKey(r.GetEventHandler())) {
			RuleSet.put(r.GetEventHandler(), new ArrayList<>());
		}

		RuleSet.get(r.GetEventHandler()).add(r);
	}
}
