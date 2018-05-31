
public class TimerPredicate implements Predicate{
	Timer timer;
	String EndTime ="null";
	String count;
	public TimerPredicate(Timer timer, String count) {
		this.timer = timer;
		this.count = count;
	}

	@Override
	public boolean CheckPredicate() {
		// TODO Auto-generated method stub
		if(this.EndTime.equals("null")) {
			this.EndTime = IotaMain.time.getEndTime(Integer.parseInt(count));
		}
		if(IotaMain.time.TimeToString.equals(EndTime)) {
			this.EndTime="null";
			return true;
		}
		else
			return false;
	}

}
