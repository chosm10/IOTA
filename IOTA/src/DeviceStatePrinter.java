import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class DeviceStatePrinter {
	private static RegisteredDevices devices;
	private static Stack<RegisteredDevices> stateHistory = new Stack<>();
	public DeviceStatePrinter() {
	}
	public static void print(RegisteredDevices registeredDevices) {
		stateHistory.push(registeredDevices);
		devices = registeredDevices;
		System.out.println("---------------------------");
		for(String devName : devices.GetDeviceMapList()) {
			for(String fieldName : devices.GetDevice(devName).GetFieldList())
				System.out.println(devName + "." +fieldName + " : " + devices.GetDevice(devName).GetCurrentState(fieldName));
		}
		System.out.println("---------------------------");
	}
}
