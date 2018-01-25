import java.util.ArrayList;
import java.util.List;

public class Actions {  //한번에 여러 액션들을 하게 하는 액션들을 갖고 있고 실행하는 클래스
	List<Action> actionList;
	public Actions() {
		actionList = new ArrayList<>();
	}
	public void addAction(Action action) {
		actionList.add(action);
	}
}
