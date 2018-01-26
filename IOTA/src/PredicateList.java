import java.util.ArrayList;

public class PredicateList {
	ArrayList<Predicate> predicateList;
	public PredicateList() {
		this.predicateList = new ArrayList<>();
	}
	public void addPredicate(Predicate predicate) {
		this.predicateList.add(predicate);
	}
}
