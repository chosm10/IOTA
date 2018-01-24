
public class Timer implements EventElement{
	private int StartTime;
	private int CurrentTime;
	private int FinishTime;
	
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
		return Integer.toString(this.StartTime);
	}
	public String GetCurrentValue() {
		return Integer.toString(this.FinishTime);
	}
	public String GetType() {
		return "Timer";
	}
}
