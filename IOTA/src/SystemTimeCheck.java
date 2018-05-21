
import java.util.Timer;
import java.util.TimerTask;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class SystemTimeCheck { // 시스템 시간을 계속 받아오는 쓰레드.

	private IotaMain main;

	Calendar cal = Calendar.getInstance();
	////// Used To get CurrentTime
	Date CurrentTime;
	String TimeToString;
	SimpleDateFormat timeLog = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	//// Used to get TimerEndTime
	Date date2;
	String dateTime2;
	SimpleDateFormat sdformat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	Calendar forEndTime = Calendar.getInstance();;

	public SystemTimeCheck(IotaMain main) { // main을 받아서 evaluation과 registerdDevices의 상태를 체크한다.

		this.main = main;
	}

	public void SetTimer() {
		Timer timer = new Timer();

		TimerTask task = new TimerTask() {
			public void run() {
				cal = Calendar.getInstance();

				CurrentTime = new Date();
				cal.setTime(CurrentTime);
				TimeToString = timeLog.format(CurrentTime);

				
				main.eval.Evaluate(main.devices);
				

			}
		};
		timer.schedule(task, 0, 200); // 타이머 갱신 간격, 1000 = 1s
	}

	public String getEndTime(int i) {
		forEndTime = cal;
		forEndTime.add(Calendar.SECOND, i);
		dateTime2 = sdformat2.format(forEndTime.getTime());

		return dateTime2;

	}

}