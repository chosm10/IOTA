
public class Event {	
	private EventElement eventElement;  // EventElement 인터페이스를 구현한 필드 클래스와 타이머 클래스를 인자로 받음.
	private String n1, n2;
	public Event(EventElement eventElement) {  // 초기화 방법: Event e1 = new Event(Door.GetField()); Event e2 = new Event(Door.GetTimer());
		this.eventElement = eventElement;
		this.n1 = null;
		this.n2 = null;
	}
	public Event(EventElement eventElement, String n1) {  // 초기화 방법: Event e1 = new Event(Door.GetField()); Event e2 = new Event(Door.GetTimer());
		this.eventElement = eventElement;
		this.n1 = n1;
		this.n2 = null;
	}
	public Event(EventElement eventElement, String n1, String n2) {  // 초기화 방법: Event e1 = new Event(Door.GetField()); Event e2 = new Event(Door.GetTimer());
		this.eventElement = eventElement;
		this.n1 = n1;
		this.n2 = n2;
	}
	public static Event UnConditional(EventElement eventElement) {
		return new Event(eventElement);
	}
	public static Event From(EventElement eventElement, String n1) {
		return new Event(eventElement, n1);
	}
	public static Event FromTo(EventElement eventElement, String n1, String n2) {
		return new Event(eventElement, n1, n2);
	}
	public Boolean IsEventTriggered() { // 전, 후 값 상관 없이 값이 변했는지	
		if(this.n1 == null && this.n2 == null) {
			if(!this.eventElement.GetOldValue().equals(this.eventElement.GetCurrentValue())) {
				return true;
			}
		}
		else if(this.n1 != null && this.n2 == null) {
			if((this.eventElement.GetOldValue().equals(n1)) && (!this.eventElement.GetOldValue().equals(this.eventElement.GetCurrentValue()))) {
				return true;
			}		
		}
		else if(this.n1 != null && this.n2 != null) {
			if((this.eventElement.GetOldValue().equals(this.n1)) && (this.eventElement.GetCurrentValue().equals(this.n2))) {
				return true;
			}		
		}
		return false;
	}
}
