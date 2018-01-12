import java.util.Calendar;
public class Timer {
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
}
