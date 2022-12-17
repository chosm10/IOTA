

public class Field implements EventElement {
	private String old_value;
	private String current_value;
	private String fieldType;
	private boolean trigger = false;
	private int actionCount = 0;

	private String fieldName;
	private String devName;

	public Field(String value, String FieldName, String devName) {
		this.old_value = value;
		this.current_value = value;
		if (isStringDouble(value)) {
			this.fieldType = "Double";
		} else {
			this.fieldType = "String";
		}
		this.fieldName = FieldName;
		this.devName = devName;
	}

	public void fieldChange(String changedValue) { // 필드 값을 바꾸려면 바뀌기 전 값을 옛날 값으로 바뀔 값을 현재 값으로 한다.
		if (!getCurrentValue().equals(changedValue)) {
			this.old_value = current_value;
			this.current_value = changedValue;
			this.actionCount++;
		
		if (!getOldValue().equals(getCurrentValue()))
			this.triggerOn();
		}
	}

	public boolean isStringDouble(String value) {
		try {
			Double.parseDouble(value);
			return true;
		} catch (NumberFormatException e) {

			return false;
		}
	}

	public String getOldValue() { // 바뀌기 전의 필드 값을 반환
		return this.old_value;
	}

	public String getCurrentValue() { // 현재 필드의 값을 반환
		return this.current_value;
	}

	public String getFieldName() {
		return this.fieldName;
	}

	public void triggerOn() {
		this.trigger = true;
	}

	public void triggerOff() {
		this.trigger = false;
	}

	public String getDevName() {
		return this.devName;
	}

	public String printEventState() {
		return this.devName + " " + this.fieldName;
	}

	@Override
	public boolean isTriggered() {

		return this.trigger;
	}

}