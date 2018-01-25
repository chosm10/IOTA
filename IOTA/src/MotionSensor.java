
public class MotionSensor extends Device{
	public MotionSensor(String devName, String field) {
		super(devName, field);
		property.AddPropertyValue("On");
		property.AddPropertyValue("Off");
	}
}
