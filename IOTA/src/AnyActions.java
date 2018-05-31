import java.util.ArrayList;

public class AnyActions implements Action { // 한번에 여러 액션들을 하게 하는 액션들을 갖고 있고 실행하는 클래스
	ArrayList<Action> actions;
	ArrayList<Boolean> actionsComplete;

	public AnyActions() {
		actions = new ArrayList<>();
		actionsComplete = new ArrayList<>();
	}

	public void addAction(Action action) {
		actions.add(action);
	}

	@Override
	public boolean ActionComplete() {
		for (Action action : actions) {
			actionsComplete.add(action.ActionComplete());
		}
		if (actionsComplete.contains(true))
			return true;
		else
			return false;
	}

	public String ActionName() {
		ArrayList<String> ActionNames = new ArrayList<>();
		for (Action action : actions)
			ActionNames.add(action.ActionName());
		return ActionNames.toString();
	}

	@Override
	public void PerformAction() {
		for (Action ac : actions)
			ac.PerformAction();
		// TODO Auto-generated method stub
	}

}
