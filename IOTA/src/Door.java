
public class Door extends Device{
	public Door(String n) {
		super(n);
		property.AddPropertyValue("Locked");
		property.AddPropertyValue("UnLocked");
	}
}
