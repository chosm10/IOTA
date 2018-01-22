
public class Rule {
	EventHandler eventHandler;
	Predicate predicate;
	Action action;
	
	public Rule(EventHandler eventHandler, Predicate predicate, Action action) {
		this.eventHandler = eventHandler;
		this.predicate = predicate;
		this.action = action;
	}
	public EventHandler GetEventHandler() {
		return this.eventHandler;
	}
	public Predicate GetPredicate() {
		return this.predicate;
	}
	public Action GetAction() {
		return this.action;
	}
}
