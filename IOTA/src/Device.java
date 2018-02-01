
public class Device {
	protected Property property;   // 장치의 On, Off 같은 것을 리스트에 관리한다.(On->5 같은 에러를 막기 위해 사용)
	protected String devName; // Door1, Door2 같은 장치의 이름
	protected Field f;
	protected Timer m;
	
	public Device(String devName, String field) {
		this.f = new Field(field);
		this.devName = devName;
		this.property = new Property();
	}
	public Property GetProperty() {
		return this.property;
	}
	public String GetDevName() { //장치의 이름을 얻는 메소드
		return this.devName;
	}
	public EventElement GetEventElement(String element)throws RuntimeException { 
		switch(element) {
		case "Field" :
			return this.f;
		case "Timer" :
			return this.m;
		default:
			throw new RuntimeException("Field나 Timer외에 다른 값이 입력되었습니다.");
		}
	}
	public void DeviceFieldChange(String changedValue) {
		f.FieldChange(changedValue);
	}
	public void SetTimer(int time) { // start timer at time
		this.m = new Timer(time);
	}
	public void StopTimer() { //stop timer
		this.m = null;
	}
	public String GetCurrentState() throws NotInitializeException {
		if(this.f == null) {
			throw new NotInitializeException("필드 값이 초기화 되지 않았습니다.");// lock 필드의 값이 초기화 안 됬는데 사용하려고 하면 에러
		}
		return this.f.GetCurrentValue();
	}
	public Timer GetTimer() throws NotInitializeException {
		if(m == null) {
			throw new NotInitializeException("타이머가 초기화 되지 않았습니다.");// m 필드의 값이 초기화 안 됬는데 사용하려고 하면 에러
		}
		return this.m;
	}
}
