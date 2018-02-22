import java.util.ArrayList;
public class AnyActions implements Action {  //한번에 여러 액션들을 하게 하는 액션들을 갖고 있고 실행하는 클래스
	ArrayList<Action> actions;

	public AnyActions() {
		actions = new ArrayList<>();
	}
	public void addAction(Action action) {
		actions.add(action);
	}
	public void PerformAction() { 
		for(Action action : this.actions) {
			action.PerformAction();
		}
	}
}
