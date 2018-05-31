
public class OneAction implements Action {
	protected Device device;
	protected String field;
	protected String action;
	protected String ActionName;
	protected boolean ActionComplete = false;

	public OneAction(Device device, String field, String action) throws RuntimeException {
		if (!device.GetProperty().IsRegisteredProperty(field))
			throw new RuntimeException(field + " : 등록되지 않은  필드로는 변경 할 수 없습니다.");
		else if (!device.GetProperty().IsRegisteredPropertyState(field, action))
			throw new RuntimeException(action + " : 등록되지 않은  속성 값으로는 변경 할 수 없습니다.");
		this.device = device;
		this.field = field;
		this.action = action;
		this.ActionName = this.device.GetDevName() + " " + this.field + " "
				+ this.device.GetEventElement(field).GetCurrentValue() + " >> " + this.action;
	}

	// GetActionDevice(), GetActionValue()는 Actions에서 여러 액션을 한번에 수행 할 때 사용한다.
	public Device GetActionDevice() {
		return this.device;
	}

	public String GetActionValue() {
		return this.action;
	}

	public void PerformAction() {
		if (!this.device.GetEventElement(this.field).GetCurrentValue().equals(action)) {
			this.device.DeviceFieldChange(this.field, action);
			ActionComplete = true;
			TimeLog.actionLog.add(IotaMain.time.TimeToString + " " + this.ActionName());
		}

	}

	@Override
	public boolean ActionComplete() {
		// TODO Auto-generated method stub
		return this.ActionComplete;
	}

	public String ActionName() {
		this.ActionName = this.device.GetDevName() + "." + this.field +
					"["+ this.device.GetEventElement(field).GetOldValue() + " >> " + this.action + "]";
		return this.ActionName;

	}

}