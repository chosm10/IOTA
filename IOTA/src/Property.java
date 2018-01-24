import java.util.ArrayList;
import java.util.List;

public class Property {
	private List<String> values;
	public Property() {
		this.values = new ArrayList<>();
	}
	public List<String> GetValues() {
		return this.values;
	}
	public void AddPropertyValue(String value) {
		this.values.add(value);
	}
	
}
