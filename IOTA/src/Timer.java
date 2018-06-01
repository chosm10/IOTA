
public class Timer {

	String timer;
	String StartTime = "null";

	public void start() { // 타이머가 시작된 시간을 저장, 끝날 시간을 계산.

		if (StartTime.equals("null"))
			this.StartTime = IotaMain.time.TimeToString;
	
	}

	public void stop() {
		if (!StartTime.equals("null"))
			this.StartTime = "null";
	}

}
