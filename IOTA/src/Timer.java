import java.util.Calendar;
public class Timer implements EventElement{
	int StartTime;
	int CurrentTime;
	int CloseTime;
	
	public Timer(int time) {
		StartTime = time;
	}
	public boolean IsChangedTime() {
		boolean change = (CurrentTime != StartTime);
		
		if(CurrentTime != StartTime) {
			return true;
		}
		return false;
	}
	public String GetOldValue() {
		return Integer.toString(StartTime);
	}
	public String GetNewValue() {
		return Integer.toString(CloseTime);
	}
	public String GetType() {
		return "Timer";
	}
}
