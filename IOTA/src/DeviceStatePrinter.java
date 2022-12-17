
//import java.util.Scanner;
//import java.util.Stack;

public class DeviceStatePrinter {
	private static RegisteredDevices devices;
	//private static Stack<RegisteredDevices> stateHistory = new Stack<>();

	public static void print(RegisteredDevices registeredDevices) {
		//stateHistory.push(registeredDevices);
		devices = registeredDevices;
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		for (String devName : devices.getDeviceMapList()) {
			for (String fieldName : devices.getDevice(devName).getFieldList())
				System.out.println("      " + devName + "\t      " + fieldName + "\t: "
						+ devices.getDevice(devName).getCurrentState(fieldName));

			if (devName.equals(devices.getDeviceMapList().get(devices.getDeviceMapList().size() - 1))) {

				System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			} else
				System.out.println("-----------------------------------------------------------------------");
		}

	}
}