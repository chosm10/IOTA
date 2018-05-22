
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.plaf.synth.SynthStyleFactory;

public class TimerAction implements Action { // 액션을 객체로 받아 타이머를 이용한다.
	Rule rule;
	// Action action;
	String timer;
	String StartTime = "null";
	String EndTime;
	int count; // 액션을 몇 번 반복할 지 정하는 값

	boolean TimerEnd = false; // 타이머가 끝났는 지 알 수 있다.
	boolean ActionComplete = false;

	public TimerAction(Rule rule, String timer, int count) {
		this.rule = rule;
		this.timer = timer;
		this.count = count;
	}

	public void setTimer() { // 타이머가 시작된 시간을 저장, 끝날 시간을 계산.
		
		this.StartTime = IotaMain.time.TimeToString;
		this.EndTime = IotaMain.time.getEndTime(Integer.parseInt(timer));
		this.TimerEnd = false; // 타이머가 시작됨
	}
	
	public void PerformAction() {
		if (this.StartTime.equals("null")) { // 타이머가 설정되어 있지 않다면 타이머 설정
			setTimer();
			TimeLog.actionLog.add(IotaMain.time.TimeToString + " " + this.ActionName() + " 타이머 시작");
			System.out.println(IotaMain.time.TimeToString + " " + this.ActionName() + " 타이머 시작");

		}
		if (IotaMain.time.TimeToString.equals(this.EndTime)) {
			TimeLog.actionLog.add(IotaMain.time.TimeToString + " " + this.ActionName() + " 타이머 종료");
			if (this.rule.GetPredicate().CheckPredicate()) {
				this.rule.GetAction().PerformAction();
				
				System.out.println("타이머 이벤트 실행완료 : " + this.EndTime + " " + this.rule.GetAction().ActionName());
				
			}
			this.StartTime = "null";
			this.TimerEnd = true; // 타이머 종료

		}
		
	}

	@Override
	public String ActionType() {
		// TODO Auto-generated method stub
		return "Timer";
	}

	@Override
	public boolean ActionComplete() {
		// TODO Auto-generated method stub
		return this.TimerEnd;
	}

	public String ActionName() {
		return this.ActionType() + " " + this.rule.GetAction().ActionName();
	}

	@Override
	public ArrayList<Action> ForAnyAction() {
		// TODO Auto-generated method stub
		return null;
	}

}
