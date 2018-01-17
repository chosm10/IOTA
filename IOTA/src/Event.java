
public class Event {
	EventElement eventelement;  // EventElement 인터페이스를 구현한 필드 클래스와 타이머 클래스를 인자로 받음.
	String TriggerCondition;
	public Event(EventElement eventelement, String TriggerCondition) {
		this.eventelement = eventelement;
		this.TriggerCondition = TriggerCondition;
	}
	public Boolean IsEventTriggered() {

		if(TriggerCondition == ".->") { // f[.->], m[.->]
				if(eventelement.GetOldValue() != eventelement.GetNewValue())
					return true;
				else
					return false;
		}
		else if(TriggerCondition == "n->" && eventelement.GetOldValue() == "n") { // f[n->], m[n->]
				if(eventelement.GetOldValue() != eventelement.GetNewValue())
					return true;
				else
					return false;
		}
		else if(TriggerCondition == "n1->n2") {
			
		}

	


	
	return eventelement.GetOldValue() == eventelement.GetNewValue();
}

}
