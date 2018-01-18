import java.util.ArrayList;
import java.util.List;

public class EventHandlerList implements Runnable{
	List<Event> eventHandlerList;
	public EventHandlerList() {
		this.eventHandlerList = new ArrayList<>();
	}
	public void add(Event e) {
		eventHandlerList.add(e);
	}
	public void run() {
		while(true) {
			
		}
	}
	
}
