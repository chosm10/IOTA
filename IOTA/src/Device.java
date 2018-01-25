
public class Device {
	protected Property property;   // 장치의 On, Off 같은 것을 리스트에 관리한다.(On->5 같은 에러를 막기 위해 사용)
	protected Field f;
	protected Timer m;
	
	public Device(String n) {
		this.f = new Field(n);
		this.property = new Property();
	}
	public Property GetProperty() {
		return this.property;
	}
	public void SetTimer(int time) {
		this.m = new Timer(time);
		this.m.Start(time);
	}
	public void StopTimer(int time) {
		this.m.Stop(time);
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
