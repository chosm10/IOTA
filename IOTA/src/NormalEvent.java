
public class NormalEvent implements Event {
	private String EventType = "Normal";
	private EventElement eventElement; // EventElement 인터페이스를 구현한 필드 클래스와 타이머 클래스를 인자로 받음.
	private String n1, n2;
	private String caseType;

	public NormalEvent(EventElement eventElement) { // 초기화 방법: Event e1 = new Event(Door.GetField());
		// Event e2 = new Event(Door.GetTimer());
		this.eventElement = eventElement;
		this.n1 = null;
		this.n2 = null;
		this.caseType = " UnConditional 이벤트 ";

	}

	public NormalEvent() {
		this.caseType = "null";

	}

	public NormalEvent(EventElement eventElement, String n, int FromTo) { // 초기화 방법: Event e1 = new
																			// Event(Door.GetField());
		// Event e2 =new Event(Door.GetTimer());
		switch (FromTo) {
		case 0:// From

			this.eventElement = eventElement;
			this.n1 = n;
			this.n2 = null;
			this.caseType = " From 이벤트 ";
			// System.out.println(this.eventElement.GetCurrentValue());
			break;

		case 1:// To
			this.eventElement = eventElement;
			this.n1 = null;
			this.n2 = n;
			this.caseType = " To 이벤트 ";
			break;

		default:
			break;
		}
	}

	public NormalEvent(EventElement eventElement, String n1, String n2) { // 초기화 방법: Event e1 = new
																			// Event(Door.GetField());
		// Event e2 = new Event(Door.GetTimer());
		this.eventElement = eventElement;
		this.n1 = n1;
		this.n2 = n2;
		this.caseType = " FromTo 이벤트 ";

	}
	
	public static NormalEvent UnConditional(EventElement eventElement) {

		return new NormalEvent(eventElement);
	}

	public static NormalEvent From(EventElement eventElement, String n1) {
		return new NormalEvent(eventElement, n1, 0);
	}

	public static NormalEvent FromTo(EventElement eventElement, String n1, String n2) {
		return new NormalEvent(eventElement, n1, n2);
	}

	public static NormalEvent To(EventElement eventElement, String n2) {
		return new NormalEvent(eventElement, n2, 1);

	}

	public boolean isEventHandler() { // 전, 후 값 상관 없이 값이 변했는지

		if (!this.eventElement.getOldValue().equals(this.eventElement.getCurrentValue())
				&& this.eventElement.isTriggered()) {
			switch (caseType) {

			case " From 이벤트 ":
				if ((this.eventElement.getOldValue().equals(n1))) {
				//	System.out.println(EventLog());
					return true;

				} else
					return false;
			case " To 이벤트 ":
				if (this.eventElement.getCurrentValue().equals(n2)) {
				//	System.out.println(EventLog());
					return true;
				} else
					return false;
			case " FromTo 이벤트 ":
				if ((this.eventElement.getOldValue().equals(this.n1))
						&& (this.eventElement.getCurrentValue().equals(this.n2))) {
				//	System.out.println(EventLog());
					return true;
				} else
					return false;

			case " UnConditional 이벤트 ":
				//System.out.println(EventLog());
				return true;
			}
		}

		if (caseType.equals("null"))
			return true;

		return false;
	}

	public void triggerOff() {
		this.getElement().triggerOff();
	}

	public EventElement getElement() {
		return this.eventElement;
	}

	public String getEventLog() {
		return (this.eventElement.printEventState() + caseType);
	}

	@Override
	public String getEventType() {
		// TODO Auto-generated method stub
		return this.EventType;
	}
}