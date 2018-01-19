import java.util.ArrayList;
import java.util.List;

public class Property {
	List<String> values;
	public Property() {
		values = new ArrayList<>();
	}
	public List<String> GetValues() {
		return this.values;
	}
	public void AddPropertyValue(String value) {
		this.values.add(value);
	}
	
}
