
public class Rule {
	EventHandler el;
	Predicate p;
	Action a;
	
	public Rule(EventHandler el, Predicate p, Action a) {
		this.el = el;
		this.p = p;
		this.a = a;
	}
}
