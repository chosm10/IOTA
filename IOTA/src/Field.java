
public class Field {
	String old_value;
	String new_value;
	String fieldType;

	public Field(String value) {
		old_value = value;
		new_value = value;
		if(IsStringDouble(value)) {
			fieldType = "Double";
		} else {
			fieldType = "String";
		}
	}
	public void FieldChange(String changedValue) {
		new_value = changedValue;
	}
	public static boolean IsStringDouble(String value) {
		try {
			Double.parseDouble(value);
			return true;
		} catch(NumberFormatException e) {
			
			return false;
		}

	} 
}
