

public class TimerAction implements Action { // 액션을 객체로 받아 타이머를 이용한다.
	Timer timer;
	String string;

	public TimerAction(Timer timer, String string) {
		this.timer = timer;
		this.string = string;
	}

	public void performAction() {
		if (string.equals("ON")) {
			//System.out.println("Timer On");
			timer.start();
			TimeLog.actionLog.add(IotaMain.time.TimeToString + " "+getActionName());
		}
		
		if (string.equals("OFF")&&!timer.StartTime.equals("null")) {
			//System.out.println("Timer Off");
			timer.stop();
			TimeLog.actionLog.add(IotaMain.time.TimeToString + " "+getActionName());
		}

	}

	@Override
	public boolean isCompleted() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getActionName() {
		return "Timer" + "." +string;
	}

}
