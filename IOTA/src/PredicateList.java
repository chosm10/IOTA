
import java.util.ArrayList;
////////////////////////룰 하나에 다중 조건을 넣고 싶을 때 리스트로 구현.
public class PredicateList implements Predicate{
	ArrayList<Predicate> predicateList;
	ArrayList<Boolean> CheckList;
	public PredicateList() {
		this.predicateList = new ArrayList<>();
		this.CheckList = new ArrayList<>();
	}
	public void add(Predicate predicate) {
		this.predicateList.add(predicate);
		
		for(Predicate pred : predicateList) {
			CheckList.add(pred.CheckPredicate());
		}
	}
	@Override
	public boolean CheckPredicate() {
	
		if(!CheckList.contains(false))
			return true;
		else
			return false;
		
	}

}