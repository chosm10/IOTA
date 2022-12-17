public interface Event {

	public boolean isEventHandler();
	
	public String getEventType();

	public String getEventLog();

	public EventElement getElement();

	public void triggerOff();
}
