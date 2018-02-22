import java.util.ArrayList;
import java.util.List;

public class RuleSet {
	ArrayList<Rule> ruleSet;
	ArrayList<Event> eventHandlerList;
	ArrayList<Predicate> predicateList;
	ArrayList<Action> actionList;

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
	public ArrayList<Event> GetEventHandlerList() {
		return this.eventHandlerList;
	}
	public ArrayList<Predicate> GetPredicateList() {
		return this.predicateList;
	}
	public ArrayList<Action> GetActionList() {
		return this.actionList;
	}
}
