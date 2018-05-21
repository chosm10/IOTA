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
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		for (String devName : devices.GetDeviceMapList()) {
			for (String fieldName : devices.GetDevice(devName).GetFieldList())
				System.out.println("      " + devName + "\t      " + fieldName + "\t: "
						+ devices.GetDevice(devName).GetCurrentState(fieldName));

			if (devName.equals(devices.GetDeviceMapList().get(devices.GetDeviceMapList().size() - 1))) {

				System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			} else
				System.out.println("-----------------------------------------------------------------------");
		}

	}
}