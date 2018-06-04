
import java.text.SimpleDateFormat;

public class TimerEvent implements Event {
	Timer timer;
	String eventType = "Timer";
	
	String EventTime = "null";
	String TimerType = "null";
	SimpleDateFormat ChangeTimeFormat = new SimpleDateFormat("HH:mm:ss");

	public TimerEvent(Timer timer) {
		this.timer = timer;
		this.TimerType = "TimerEvent";
	}

	public TimerEvent(Timer timer, String CurrentTime) {
		this.timer = timer;
		this.EventTime = CurrentTime;
		this.TimerType = "ClockEvent";
	}

	public static TimerEvent Timer(Timer timer) {
		return new TimerEvent(timer);
	}

	public static TimerEvent Clock(Timer timer, String CurrentTime) {
		return new TimerEvent(timer, CurrentTime);
	}

	public boolean isEventHandler() {
		switch (TimerType) {
		case "TimerEvent":
			if (timer.StartTime.equals("null")) {
				return false;
			}
			return true;
			
		case "ClockEvent":
			if (EventTime.equals(ChangeTimeFormat.format(IotaMain.time.currentTime)))
				return true;
		default:
			return false;
		}

	}

	public String getEventLog() {
		return this.eventType + "이벤트";
	}
	public String getEventType() {
		return this.eventType;
	}

	@Override
	public EventElement getElement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void triggerOff() {
		// TODO Auto-generated method stub

	}

}
