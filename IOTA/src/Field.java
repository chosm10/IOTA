
public class Field implements EventElement{
	String old_value;
	String current_value;
	String fieldType;

	public Field(String value) {
		old_value = value;
		current_value = value;
		if(IsStringDouble(value)) {
			fieldType = "Double";
		} else {
			fieldType = "String";
		}
	}
	public void FieldChange(String changedValue) { // 필드 값을 바꾸려면 바뀌기 전 값을 옛날 값으로 바뀔 값을 현재 값으로 한다.
		old_value = current_value;
		current_value = changedValue;
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
		return old_value;
	}
	public String GetCurrentValue() { //현재 필드의 값을 반환
		return current_value;
	}
	public String GetType() {
		return "Field";
	}
}
