import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RegisteredDevices {
	HashMap<String, Device> devices;
	public RegisteredDevices() {
		devices = new HashMap<>();
	}
	public void addDevice(String key, Device device) {
		devices.put(key, device);
	}
	public Device getDevice(String key) {
		return this.devices.get(key);
	}
}
