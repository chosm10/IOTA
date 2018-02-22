
public class Rule {
	Event event;
	Predicate predicate;
	Action action;
	
	public Rule(Event event, Predicate predicate, Action action) {
		this.event = event;
		this.predicate = predicate;
		this.action = action;
	}
	public Event GetEventHandler() {
		return this.event;
	}
	public Predicate GetPredicate() {
		return this.predicate;
	}
	public Action GetAction() {
		return this.action;
	}
}
