

public class TimerAction implements Action { // 액션을 객체로 받아 타이머를 이용한다.
	Timer timer;
	String string;

	public TimerAction(Timer timer, String string) {
		this.timer = timer;
		this.string = string;
	}

	public void PerformAction() {
		if (string.equals("ON")) {
			//System.out.println("Timer On");
			timer.TimerStart();
			TimeLog.actionLog.add(IotaMain.time.TimeToString + " "+ActionName());
		}
		
		if (string.equals("OFF")&&!timer.StartTime.equals("null")) {
			//System.out.println("Timer Off");
			timer.TimerStop();
			TimeLog.actionLog.add(IotaMain.time.TimeToString + " "+ActionName());
		}

	}

	@Override
	public boolean ActionComplete() {
		// TODO Auto-generated method stub
		return false;
	}

	public String ActionName() {
		return "Timer" + "." +string;
	}

}
