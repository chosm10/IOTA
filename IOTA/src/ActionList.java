import java.util.ArrayList;
import java.util.List;

public class ActionList {
	List<Action> actionlist;
	
	public ActionList() {
		this.actionlist = new ArrayList<>();
	}
	public void add(Action action) {
		actionlist.add(action);
	}
}
