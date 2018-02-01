import java.util.ArrayList;
import java.util.List;

public class RuleSet {
	List<Rule> ruleSet;
	List<Event> eventHandlerList;
	List<Predicate> predicateList;
	List<Action> actionList;
	
	public RuleSet() {
		this.ruleSet = new ArrayList<>();
		this.eventHandlerList = new ArrayList<>();
		this.predicateList = new ArrayList<>();
		this.actionList = new ArrayList<>();
	}
	public void add(Rule r) {
		ruleSet.add(r);
		eventHandlerList.add(r.GetEventHandler());
		predicateList.add(r.GetPredicate());
		actionList.add(r.GetAction());
	}
}
