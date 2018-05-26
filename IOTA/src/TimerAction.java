
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.plaf.synth.SynthStyleFactory;

public class TimerAction implements Action { // 액션을 객체로 받아 타이머를 이용한다.
	Timer timer;
	String string;

	public TimerAction(Timer timer, String string) {
		this.timer = timer;
		this.string = string;
	}

	public void PerformAction() {
		if (string.equals("ON")) {
			System.out.println("Timer On");
			timer.TimerStart();
		}
		
		if (string.equals("OFF")&&!timer.StartTime.equals("null")) {
			System.out.println("Timer Off");
			timer.TimerStop();
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
		return false;
	}

	public String ActionName() {
		return this.ActionType() + " ";
	}

}
