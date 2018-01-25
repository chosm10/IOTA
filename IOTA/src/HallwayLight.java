
public class HallwayLight extends Device{
	public HallwayLight(String devName, String field) {
		super(devName, field);
		property.AddPropertyValue("On");
		property.AddPropertyValue("Off");
	}
}
