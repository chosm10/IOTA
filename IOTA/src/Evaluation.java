
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.CopyOnWriteArrayList;

public class Evaluation {
	private RuleSet ruleSet;
	private ArrayList<Event> EventSet;
	private ArrayList<Action> actionList;
	ArrayList<Action> TimerActionList;

	public Evaluation(RuleSet ruleSet) {
		this.ruleSet = ruleSet;
		this.EventSet = new ArrayList<>();
		this.actionList = new ArrayList<>();
		this.TimerActionList = new ArrayList<>();
	}

	public void Evaluate(RegisteredDevices devices) {
//이벤트 검사		
		this.EventCheck();																	
//이벤트가 존재한다면 조건 검사
		while( !EventSet.isEmpty()) {
			this.PredicateCheck();

		}
		
//조건을 만족한 룰들을 실행	
		for (Action ac : this.actionList) {

			ac.PerformAction();
///액션에 의해 상태가 변했다면 상태 출력
			if (ac.ActionComplete()) {
				System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
				System.out.println(devices.GetDeviceMapList().toString());
				DeviceStatePrinter.print(devices);
				this.actionList.remove(ac);
			}
			if(this.actionList.contains(ac)) {
				continue;
			}
			else
				break;
			

		}

		//Evaluate(devices);
		this.EventCheck();

	}

	public void EventCheck() {
		for (Event e : ruleSet.RuleSet.keySet()) {

			if (e.IsEventTriggered()) {
				EventSet.add(e);
			}

		}

	}

	public void PredicateCheck() {
		for (Event e : this.EventSet) {				//발생한 이벤트를 확인
			for (Rule rule : ruleSet.RuleSet.get(e)) {	//이벤트마다 룰을 검사

				Predicate p = rule.GetPredicate();
				
				if (p.CheckPredicate()) {
					System.out.println(e.EventLog() + " 조건 만족");				//룰 조건을 만족했는지 확인

					if (rule.GetAction().ActionType().equals("AnyAction")) {
						for (Action action : rule.GetAction().ForAnyAction())
							actionList.add(action);
					}
					else
						actionList.add(rule.GetAction());

					System.out.println(rule.GetAction().ActionType() +  " 액션 대기");	//어떤 액션이 존재하는지 확인

				} else
					System.out.println(e.EventLog() + "조건 실패");

			}
			e.TriggerOff();
		}
		EventSet.clear();
	}

}