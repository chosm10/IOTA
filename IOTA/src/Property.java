import java.util.ArrayList;
import java.util.List;

public class Property {
	private ArrayList<String> values;
	public Property() {
		this.values = new ArrayList<>();
	}
	public String GetValue(int n) { //n¿Ã 0¿Ã∏Á
		return this.values.get(n);
	}
	public void AddPropertyValue(String value) {
		this.values.add(value);
	}
	
}
