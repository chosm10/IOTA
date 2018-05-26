import java.util.ArrayList;
import java.util.HashMap;

public class Device {
	protected Property property; // 장치의 On, Off 같은 것을 리스트에 관리한다.(On->5 같은 에러를 막기 위해 사용)
	protected String devName; // Door1, Door2 같은 장치의 이름
	protected HashMap<String, Field> fields;
	protected ArrayList<String> fieldList; // 맵 반복을 위해 존재
	protected Timer Timer;
	public Device(String devName) {

		this.devName = devName;
		this.property = new Property();
		fields = new HashMap<>();
		fieldList = new ArrayList<>();
		this.Timer = new Timer();
	}

	public void AddUsingField(String fieldName) throws RuntimeException { // Property는 사용 가능한 속성들이고, Field는 실제 사용하는 것들
		if (!this.property.IsRegisteredProperty(fieldName))
			throw new RuntimeException(fieldName + "은 사용 가능한 필드가 아닙니다. 장치의 Property를 확인하세요.");
		// 필드 맵에 property의 맵에서 fieldName으로 property를 찾고, 그것에 첫번째로 등록된 value를 주어 필드를
		// 생성한다.
		fields.put(fieldName, new Field(property.GetProperties().get(fieldName).get(0), fieldName, this.GetDevName()));
		fieldList.add(fieldName);
	}

	public ArrayList<String> GetFieldList() {
		return this.fieldList;
	}

	public Property GetProperty() {
		return this.property;
	}

	public String GetDevName() { // 장치의 이름을 얻는 메소드
		return this.devName;
	}

	public EventElement GetEventElement(String element) throws RuntimeException {

		if (!fields.containsKey(element))
			throw new RuntimeException(element + "는 이 장치에 생성되지 않았습니다.");
		return this.fields.get(element);
	}

	public void DeviceFieldChange(String fieldName, String changedValue) {
		fields.get(fieldName).FieldChange(changedValue);
	}

	public String GetCurrentState(String fieldName) throws NotInitializeException {
		if (this.fields.get(fieldName) == null) {
			throw new NotInitializeException("필드 값이 초기화 되지 않았습니다.");// lock 필드의 값이 초기화 안 됬는데 사용하려고 하면 에러
		}
		return this.fields.get(fieldName).GetCurrentValue();
	}
}