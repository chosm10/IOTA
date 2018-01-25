
public class Event {
	private EventElement eventElement;  // EventElement 인터페이스를 구현한 필드 클래스와 타이머 클래스를 인자로 받음.

	public Event(EventElement eventElement) {  // 초기화 방법: Event e1 = new Event(Door.GetField()); Event e2 = new Event(Door.GetTimer());
		this.eventElement = eventElement;
	}
	public Boolean IsEventTriggered() { // 전, 후 값 상관 없이 값이 변했는지
		if(eventElement.GetOldValue() != eventElement.GetCurrentValue()) {
			return true;
		}
		else {
			return false;
		}
	}
	public Boolean IsEventTriggered(String n) { // 필드나 타이머의 값의 전 값이 n에서 바꼈는지
		if((this.eventElement.GetOldValue() == n) && (this.eventElement.GetOldValue() != this.eventElement.GetCurrentValue())) {
			return true;
		}		
		else {
			return false;
		}
	}
	public Boolean IsEventTirggered(String n1, String n2) { // 필드나 타이머의 값의 바뀌기 전 값과 바뀐 후 값이 n1에서 n2로 바꼈는지
		if((this.eventElement.GetOldValue() == n1) && (this.eventElement.GetCurrentValue() == n2)) {
			return true;
		}		
		else {
			return false;
		}
	}
}
