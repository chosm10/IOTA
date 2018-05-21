
public interface EventElement {
	public String GetOldValue();
	public String GetCurrentValue();
	public String GetType();
	public String GetFieldName();
	public String GetDevName();
	public String PrintEventState();
	public boolean IsStringDouble(String value);
	public void TriggerOn();
	public void TriggerOff();
	public boolean GetTrigger();
	

}
