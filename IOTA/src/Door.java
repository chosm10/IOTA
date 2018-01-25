
public class Door extends Device{
	public Door(String devName, String field) {
		super(devName, field);
		property.AddPropertyValue("Locked");
		property.AddPropertyValue("UnLocked");
	}
}
