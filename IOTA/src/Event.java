
public interface Event {

	public boolean IsEventTriggered();

	public String EventLog();

	public EventElement GetElement();

	public void TriggerOff();
}
