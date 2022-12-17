
public class TimerPredicate implements Predicate{
	Timer timer;
	String endTime ="null";
	String timerValue;
	public TimerPredicate(Timer timer, String timerValue) {
		this.timer = timer;
		this.timerValue = timerValue;
	}

	@Override
	public boolean checkPredicate() {

		if(this.endTime.equals("null")) {
			this.endTime = IotaMain.time.getEndTime(Integer.parseInt(timerValue));
		}
		if(IotaMain.time.timeLog.equals(endTime)) {
			this.endTime="null";
			return true;
		}
		return false;
	}

}
