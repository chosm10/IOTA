
public class Field implements EventElement{
	private String old_value;
	private String current_value;
	private String fieldType;

	public Field(String value) {
		this.old_value = value;
		this.current_value = value;
		if(IsStringDouble(value)) {
			this.fieldType = "Double";
		} 
		else {
			this.fieldType = "String";
		}
	}
	public void FieldChange(String changedValue) { // 필드 값을 바꾸려면 바뀌기 전 값을 옛날 값으로 바뀔 값을 현재 값으로 한다.
		this.old_value = current_value;
		this.current_value = changedValue;
	}
	public static boolean IsStringDouble(String value) {
		try {
			Double.parseDouble(value);
			return true;
		} catch(NumberFormatException e) {

			return false;
		}
	}
	public String GetOldValue() { //바뀌기 전의 필드 값을 반환
		return this.old_value;
	}
	public String GetCurrentValue() { //현재 필드의 값을 반환
		return this.current_value;
	}
	public String GetType() {
		return "Field";
	}
}
