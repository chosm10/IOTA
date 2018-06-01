
import java.util.ArrayList;

public class MotionSensor extends Device{
	public MotionSensor(String devName) {
		super(devName);
		ArrayList<String> switches = new ArrayList<>();
		switches.add("On");
		switches.add("Off");
		property.addProperty("Switch", switches);
		addUsingField("Switch"); // 여러 사용가능한 property중 Switch만 사용한다.
	}
}
