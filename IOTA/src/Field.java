

public class Field implements EventElement {
	private String old_value;
	private String current_value;
	private String fieldType;
	private boolean Trigger = false;

	private String fieldName;
	private String devName;

	public Field(String value, String FieldName, String devName) {
		this.old_value = value;
		this.current_value = value;
		if (IsStringDouble(value)) {
			this.fieldType = "Double";
		} else {
			this.fieldType = "String";
		}
		this.fieldName = FieldName;
		this.devName = devName;
	}

	public void FieldChange(String changedValue) { // 필드 값을 바꾸려면 바뀌기 전 값을 옛날 값으로 바뀔 값을 현재 값으로 한다.
		if (!GetCurrentValue().equals(changedValue)) {
			this.old_value = current_value;
			this.current_value = changedValue;
		
		if (!GetOldValue().equals(GetCurrentValue()))
			this.TriggerOn();
		}
	}

	public boolean IsStringDouble(String value) {
		try {
			Double.parseDouble(value);
			return true;
		} catch (NumberFormatException e) {

			return false;
		}
	}

	public String GetOldValue() { // 바뀌기 전의 필드 값을 반환
		return this.old_value;
	}

	public String GetCurrentValue() { // 현재 필드의 값을 반환
		return this.current_value;
	}

	public String GetType() {
		return "Field";
	}

	public String GetFieldName() {
		return this.fieldName;
	}

	public void TriggerOn() {
		this.Trigger = true;
	}

	public void TriggerOff() {
		this.Trigger = false;
	}

	public String GetDevName() {
		return this.devName;
	}

	public String PrintEventState() {
		return this.devName + " " + this.fieldName;
	}

	@Override
	public boolean GetTrigger() {

		return this.Trigger;
	}

}