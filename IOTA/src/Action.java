
public class Action {
	Device device;
	
	public Action(Device device, String action) {
		this.device = device;
		device.GetField().FieldChange(action);
	}
}
