
public class Rule {
	Event event;
	Predicate predicate;
	Action action;
	
	public Rule(Event event, Predicate predicate, Action action) {
		this.event = event;
		this.predicate = predicate;
		this.action = action;
	}
	public Event getEventHandler() {
		return this.event;
	}
	public Predicate getPredicate() {
		return this.predicate;
	}
	public Action getAction() {
		return this.action;
	}
}
