

import java.util.ArrayList;
import java.util.HashMap;
public class RegisteredDevices {
	HashMap<String, Device> deviceMap; //장치의 이름을 key로 value를 장치로 한다.
	ArrayList<String> deviceMapList; // 장치명(key)을 관리하는 list, 해시맵을 반복하는데 사용
	
	public RegisteredDevices() {
		deviceMap = new HashMap<>();
		deviceMapList = new ArrayList<>();
		
		Door entranceDoor = new Door("EntranceDoor"); //장치명, 초기 속성값
		Door kitchenDoor = new Door("KitchenDoor");
		MotionSensor porchMotionSensor = new MotionSensor("PorchMotionSensor");
		HallwayLight hallwayLight = new HallwayLight("HallwayLight");

		addDevice(entranceDoor);
		addDevice(kitchenDoor);
		addDevice(porchMotionSensor);
		addDevice(hallwayLight);
	}
	public void addDevice(Device device) {   // 사용할 장치를 등록한다.
		deviceMap.put(device.getDevName(), device);
		deviceMapList.add(device.getDevName());
	}
	public void deleteDevice(String deviceName) {  // 장치의 등록을 해제한다.
		deviceMap.remove(deviceName);
		deviceMapList.remove(deviceName);
	}
	public Device getDevice(String deviceName) {  // 장치의 이름으로 장치를 가져온다.
		return this.deviceMap.get(deviceName);
	}
	public Property getDeviceProperty(String deviceName) {  //장치의 이름으로 장치의 Property를 가져온다.
		return this.deviceMap.get(deviceName).getProperty();
	}
	public ArrayList<String> getDeviceMapList() {   // 등록된 장치의 맵을 반복하는데 사용한다.
		return this.deviceMapList;
	}
	public boolean isRegisteredDevice(String deviceName) {   //장치의 이름으로 등록되 있는지 확인한다.
		return deviceMap.containsKey(deviceName);
	}
}

