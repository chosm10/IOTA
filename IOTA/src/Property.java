
import java.util.ArrayList;
import java.util.HashMap;

public class Property {
	private HashMap<String, ArrayList<String>> properties;
	public Property() {
		this.properties = new HashMap<>();
	}
	public HashMap<String, ArrayList<String>> getProperties() { 
		return this.properties;
	}
	public void addProperty(String property, ArrayList<String> values) {
		this.properties.put(property, values);
	}
	public boolean isRegisteredProperty(String property) { 
		//등록된 property인지 확인하는 메소드 ex)Lock, Switch ...
		return properties.containsKey(property);
	}
	public boolean isRegisteredPropertyState(String property, String state) { 
		//등록된 property 값인지 확인하는 메소드 ex)Locked, UnLocked, On, Off ...
		return properties.get(property).contains(state);
	}
	
}