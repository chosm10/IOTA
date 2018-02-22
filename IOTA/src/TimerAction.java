
public class TimerAction implements Action { 
	// Stop m, Start m 처럼 Timer를 작동하는 Action
	//기능은 Timer 클래스에서 메소드를 가져다 사용하면 될 것 같다.
	private Device device;
	private String action;
	
	public TimerAction(Device device, String action) throws RuntimeException { //action은 Start나 Stop		
		this.device = device;
		this.action = action;
	}
	public static TimerAction TimerStart(Device device) {
		return new TimerAction(device, "Start");
	}
	public static TimerAction TimerStop(Device device) {
		return new TimerAction(device, "Stop");
	}
	public void PerformAction() { 
		switch(action) { // start timer at 0, 무조건 0에서만 시작한다는 전제로
		case "Start" :
			this.device.m.SetVirtualTime();
			//this.device.m.StartTime();
			break;
		case "Stop" :
			this.device.m.StopVirtualTime();
			//this.device.m.StopTime();
			break;
		}
	}
}
