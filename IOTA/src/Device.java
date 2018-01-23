
public class Device {
	Property property;
	Field f;
	Timer m;
	
	public Device(String n) {
		this.f = new Field(n);
		this.property = new Property();
		this.m = null;
	}
	public Property GetProperty() {
		return this.property;
	}
	public void SetTimer(int time) {
		m = new Timer(time);
	}
	public Field GetField() throws NotInitializeException{
		if(f == null) {
			throw new NotInitializeException("필드 값이 초기화 되지 않았습니다.");// lock 필드의 값이 초기화 안 됬는데 사용하려고 하면 에러
		}
		return this.f;
	}
	public Timer GetTimer() {
		if(m == null) {
			throw new NotInitializeException("타이머가 초기화 되지 않았습니다.");// m 필드의 값이 초기화 안 됬는데 사용하려고 하면 에러
		}
		return this.m;
	}
}
