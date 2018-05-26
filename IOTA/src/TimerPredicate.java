
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
			System.out.println(this.EndTime);
		}
		
		if(IotaMain.time.TimeToString.equals(EndTime))
			return true;
		else
			return false;
	}

}
