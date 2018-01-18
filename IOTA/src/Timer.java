
public class Timer implements EventElement{
	int StartTime;
	int CurrentTime;
	int FinishTime;
	
	public Timer() {
		this.StartTime = 0;
	}
	public Timer(int time) {
		this.StartTime = time;
	}
	public void Start(int StartTime) {
		this.StartTime = StartTime;
	}
	public void Stop(int FinishTime) {
		this.FinishTime = FinishTime;
	}
	public String GetOldValue() {
		return Integer.toString(StartTime);
	}
	public String GetCurrentValue() {
		return Integer.toString(FinishTime);
	}
	public String GetType() {
		return "Timer";
	}
}
