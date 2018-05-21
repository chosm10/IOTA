

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimerEvent implements Event {
	Calendar cal;
	
	String EventName;
	boolean trigger = false;

	//// Using To get TimerEndTime
	Date date2;

	private String time; // 타이머 이벤트용 // 형식 :: "HH:mm:ss"
	SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	SimpleDateFormat timeFormat2 = new SimpleDateFormat("HH:mm:ss");
	private int cycle; // 반복 주기 // 초단위로 설정
	int count; // 반복 횟수

	public TimerEvent(String time, int cycle, int count) {
		this.time = time;
		this.cycle = cycle;
		this.count = count;
		
		this.EventName = time + cycle + count;

	}

	public boolean IsEventTriggered() {

		if (timeFormat2.format(IotaMain.time.CurrentTime).equals(time)) {
			cal = IotaMain.time.cal;
			cal.add(Calendar.SECOND, cycle);
			date2 = cal.getTime();
			time = timeFormat.format(date2);
			if (count == -1) {
				this.trigger = true;
				return trigger;
			}
			if (count > 0) {
				count--;
				this.trigger = true;
				return trigger;
			}
			return trigger;
		} else
			return trigger;
	}

	@Override
	public String EventLog() {
		// TODO Auto-generated method stub
		return EventName;
	}

	@Override
	public EventElement GetElement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void TriggerOff() {
		// TODO Auto-generated method stub
		this.trigger = false;
	}

}
