
import java.util.ArrayList;

public class Door extends Device{
	public Door(String devName) {
		super(devName);
		ArrayList<String> locks = new ArrayList<>();
		locks.add("Locked");
		locks.add("UnLocked");
		property.AddProperty("Lock", locks);
		ArrayList<String> switches = new ArrayList<>();
		switches.add("On");
		switches.add("Off");
		property.AddProperty("Switch", switches);
		AddUsingField("Lock");
		AddUsingField("Switch");
	}
}