import java.util.ArrayList;
import java.util.List;

public class RuleSet {
	List<Rule> ruleSet;
	
	public RuleSet() {
		this.ruleSet = new ArrayList<>();
	}
	public void add(Rule r) {
		ruleSet.add(r);
	}
}
