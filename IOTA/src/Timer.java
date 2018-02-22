import java.util.Calendar;

public class Timer implements EventElement { //Timer 스레드가 시작 되고 메인 스레드에서 스레드를 멈출것을 notify해주면 스레드는 멈춘다.(시작시간, 현재시간을 멤버로 갖는다.)
	private int startTime; //IOTA에서 start time at 0 처럼 at 뒤에 적는 숫자
	private int startMinute; //실제 타이머가 start 될 때의 시간 중 분(minute)을 구한다.
	private int virtualTime;
	private Boolean IsStart;
	
	public Timer() {
		this.startTime = 0;
		this.virtualTime = 0;
		this.IsStart = false;
	}
	public void StartTime() {
		this.IsStart = true;
		this.startTime = 0;
		startMinute = Calendar.getInstance().get(Calendar.MINUTE);
	}
	public void StopTime() {
		this.IsStart = false;
		this.startMinute = 0;
	}
	public boolean IsTimePassed(int minute) { // timer = 5 처럼 timer의 값이 시간이 흘러서 5가 되었는지를 알려준다. Predicate 체크 할 때 사용
		int time = Calendar.getInstance().get(Calendar.MINUTE);
		int realPassedTime = time - startMinute;
		int passedTime = minute - startTime; // timer의 시작 시간이 0이 아닐수도 있기 때문에 만약 2에서 시작해서 5가 됬냐고 물어보면 3분이 지나야 참을 반환한다.
		return (realPassedTime == passedTime) ? true : false; //실제 지난 시간과 IOTA에서 시작한 시간, 바뀐 시간의 차가 같으면 참을 반환한다.
	}
	public void ChangeVirtualTime(int virtualTime) { // 가상으로 시간의 흐름을 준다.
		this.virtualTime = virtualTime;
	}
	public void SetVirtualTime() {
		this.IsStart = true;
	}
	public void StopVirtualTime() {
		this.virtualTime = 0;
		this.IsStart = false;
	}
	public boolean IsVirtualTimerStart() {
		if(this.IsStart)
			return true;
		return false;
	}
	public boolean IsVirtualTimePassed(int minute) { // 가상의 시간이 흘렀는지 확인한다.
		return (this.virtualTime == minute) ? true : false;
	}
	public String GetOldValue() {
		return Integer.toString(this.startTime);
	}
	public String GetCurrentValue() {
		int currentTime = Calendar.getInstance().get(Calendar.MINUTE);
		int currentValue = currentTime - startMinute + startTime; //시작 시간부터 흘러서 현재 몇 분인지 반환한다.
		return Integer.toString(currentValue);
	}
	public String GetType() {
		return "Timer";
	}
	public boolean IsStringDouble(String value) {
		try {
			Double.parseDouble(value);
			return true;
		} catch(NumberFormatException e) {

			return false;
		}
	}
}
