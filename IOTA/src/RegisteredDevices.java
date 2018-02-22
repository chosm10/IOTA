
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

		AddDevice(entranceDoor);
		AddDevice(kitchenDoor);
		AddDevice(porchMotionSensor);
		AddDevice(hallwayLight);
	}
	public void AddDevice(Device device) {   // 사용할 장치를 등록한다.
		deviceMap.put(device.GetDevName(), device);
		deviceMapList.add(device.GetDevName());
	}
	public void DeleteDevice(String devName) {  // 장치의 등록을 해제한다.
		deviceMap.remove(devName);
		deviceMapList.remove(devName);
	}
	public Device GetDevice(String devName) {  // 장치의 이름으로 장치를 가져온다.
		return this.deviceMap.get(devName);
	}
	public Property GetDeviceProperty(String devName) {  //장치의 이름으로 장치의 Property를 가져온다.
		return this.deviceMap.get(devName).GetProperty();
	}
	public ArrayList<String> GetDeviceMapList() {   // 등록된 장치의 맵을 반복하는데 사용한다.
		return this.deviceMapList;
	}
	public boolean IsRegisteredDevice(String devName) {   //장치의 이름으로 등록되 있는지 확인한다.
		return deviceMap.containsKey(devName);
	}
}
