import java.util.ArrayList;

public class HallwayLight extends Device{
	public HallwayLight(String devName) {
		super(devName);
		ArrayList<String> switches = new ArrayList<>();
		switches.add("On");
		switches.add("Off");
		property.addProperty("Switch", switches);
		addUsingField("Switch");
	}
}
