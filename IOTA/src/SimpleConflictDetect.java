
public class SimpleConflictDetect {
	RegisteredDevices OldDevices;
	RegisteredDevices NewDevices;
	Action action;
	public SimpleConflictDetect(RegisteredDevices devices, Action action) {
		this.NewDevices = devices;
		this.action = action;
	}

	
	public void GetNewState(RegisteredDevices devices) {
		this.OldDevices = this.NewDevices;
		this.NewDevices = devices;
	}
}
