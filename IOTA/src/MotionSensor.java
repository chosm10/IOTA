
public class MotionSensor extends Device{
	public MotionSensor(String n) {
		super(n);
		property.AddPropertyValue("On");
		property.AddPropertyValue("Off");
	}
}
