import java.util.ArrayList;
import java.util.List;

public class Property {
	private ArrayList<String> values;
	
	public Property() {
		this.values = new ArrayList<>();
	}
	public String GetValue(int n) { //n이 0이며
		return this.values.get(n);
	}
	public void AddPropertyValue(String value) {
		this.values.add(value);
	}
	public boolean IsRegisteredProperty(String property) { //등록된 property인지 확인하는 메소드
		return values.contains(property);
	}
	
}
