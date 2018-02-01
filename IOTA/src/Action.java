
public class Action {
	private Device device;
	private String action;
	
	public Action(Device device, String action) throws RuntimeException {  
		if(!device.GetProperty().IsRegisteredProperty(action))
			throw new RuntimeException(action + " : 등록되지 않은  속성으로는 변경 할 수 없습니다.");
		this.device = device;	
		this.action = action;
	}
	//GetActionDevice(), GetActionValue()는 Actions에서 여러 액션을 한번에 수행 할 때 사용한다.
	public Device GetActionDevice() {
		return this.device;
	}
	public String GetActionValue() {
		return this.action;
	}
	public void PerformAction() { 
		this.device.DeviceFieldChange(this.action);
	}
}
