import java.util.ArrayList;
public class Actions {  //한번에 여러 액션들을 하게 하는 액션들을 갖고 있고 실행하는 클래스
	ArrayList<Action> actions;

	public Actions() {
		actions = new ArrayList<>();
	}
	public void addAction(Action action) {
		actions.add(action);
	}
	public void PerformActions() { 
		for(Action action : this.actions) {
			action.GetActionDevice().DeviceFieldChange(action.GetActionValue());
		}
	}
}
