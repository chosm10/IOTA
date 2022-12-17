import java.util.ArrayList;
import java.util.List;

public class AnyActions implements Action { // 한번에 여러 액션들을 하게 하는 액션들을 갖고 있고 실행하는 클래스
	List<Action> actions;
	List<Boolean> actionsComplete;

	public AnyActions() {
		actions = new ArrayList<>();
		actionsComplete = new ArrayList<>();
	}

	public void addAction(Action action) {
		actions.add(action);
	}

	@Override
	public boolean isCompleted() {
		for (Action action : actions) {
			actionsComplete.add(action.isCompleted());
		}
		if (actionsComplete.contains(true))
			return true;
		return false;
	}

	public String getActionName() {
		ArrayList<String> actionNames = new ArrayList<>();
		for (Action action : actions)
			actionNames.add(action.getActionName());
		return actionNames.toString();
	}

	@Override
	public void performAction() {
		for (Action action : actions)
			action.performAction();
		// TODO Auto-generated method stub
	}

}
