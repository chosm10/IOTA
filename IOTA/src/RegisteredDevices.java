import java.util.ArrayList;
import java.util.List;

public class RegisteredDevices {
	List<Device> deviceList;
	public RegisteredDevices() {
		deviceList = new ArrayList<>();
	}
	public void addDevice(Device device) {
		deviceList.add(device);
	}
}
