
import java.util.ArrayList;

public class Door extends Device{
	public Door(String devName) {
		super(devName);
		ArrayList<String> locks = new ArrayList<>();
		locks.add("Locked");
		locks.add("UnLocked");
		property.addProperty("Lock", locks);
		ArrayList<String> switches = new ArrayList<>();
		switches.add("On");
		switches.add("Off");
		property.addProperty("Switch", switches);
		addUsingField("Lock");
		addUsingField("Switch");
	}
}