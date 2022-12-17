
public interface EventElement {
	public String getOldValue();
	public String getCurrentValue();
	public String getFieldName();
	public String getDevName();
	public String printEventState();
	public boolean isStringDouble(String value);
	public void triggerOn();
	public void triggerOff();
	public boolean isTriggered();
	

}
