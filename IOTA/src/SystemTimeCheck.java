
import java.util.Timer;
import java.util.TimerTask;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class SystemTimeCheck { // 시스템 시간을 계속 받아오는 쓰레드.

	private IotaMain main;

	Calendar cal = Calendar.getInstance();
	////// Used To get CurrentTime
	Date currentTime;
	String timeLog;
	SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	//// Used to get TimerEndTime
	Date date2;
	String dateTime2;
	SimpleDateFormat sdformat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	Calendar forEndTime;// = Calendar.getInstance();;

	public SystemTimeCheck(IotaMain main) { // main을 받아서 evaluation과 registerdDevices의 상태를 체크한다.

		this.main = main;
	}

	public void setTimer() {				//실제 시스템 시간을 설정할 때
		Timer timer = new Timer();

		TimerTask task = new TimerTask() {
			public void run() {

				cal = Calendar.getInstance();
				currentTime = new Date();
				cal.setTime(currentTime);
				timeLog = timeFormat.format(currentTime);

				//main.eval.Evaluate(main.devices);

			}
		};
		timer.schedule(task, 0, 200); // 타이머 갱신 간격, 1000 = 1s
	}

	public void setVirtualTimer() {			//가상으로 타이머 이벤트의 경과를 확인하고 싶을 때
		Timer timer = new Timer();
		cal = Calendar.getInstance();

		currentTime = new Date();
		cal.setTime(currentTime);
		timeLog = timeFormat.format(currentTime);

		TimerTask task = new TimerTask() {
			public void run() {
				currentTime = cal.getTime();
				timeLog = timeFormat.format(currentTime);

				main.eval.evaluate(main.devices);
				//System.out.println(TimeToString);
			}
		};
		timer.schedule(task, 0, 200); // 타이머 갱신 간격, 1000 = 1s
	}

	public void virtaulTimerPLUS() {
		cal.add(Calendar.SECOND, 1);

		currentTime = cal.getTime();
		timeLog = timeFormat.format(currentTime);

	}

	public String getEndTime(int i) {
		forEndTime = (Calendar) cal.clone();
		forEndTime.add(cal.SECOND, i);
		dateTime2 = sdformat2.format(forEndTime.getTime());

		return dateTime2;
	}
}