
public class OneAction implements Action {
	protected Device device;
	protected String field;
	protected String action;
	protected String name;
	protected boolean actionComplete = false;

	public OneAction(Device device, String field, String action) throws RuntimeException {
		if (!device.getProperty().isRegisteredProperty(field))
			throw new RuntimeException(field + " : 등록되지 않은  필드로는 변경 할 수 없습니다.");
		else if (!device.getProperty().isRegisteredPropertyState(field, action))
			throw new RuntimeException(action + " : 등록되지 않은  속성 값으로는 변경 할 수 없습니다.");
		this.device = device;
		this.field = field;
		this.action = action;
		this.name = this.device.getDevName() + " " + this.field + " "
				+ this.device.getEventElement(field).getCurrentValue() + " >> " + this.action;
	}

	// GetActionDevice(), GetActionValue()는 Actions에서 여러 액션을 한번에 수행 할 때 사용한다.
	public Device GetActionDevice() {
		return this.device;
	}

	public String GetActionValue() {
		return this.action;
	}

	public void performAction() {
		if (!this.device.getEventElement(this.field).getCurrentValue().equals(action)) {
			this.device.deviceFieldChange(this.field, action);
			actionComplete = true;
			TimeLog.actionLog.add(IotaMain.time.TimeToString + " " + this.getActionName());
		}

	}

	@Override
	public boolean isCompleted() {
		// TODO Auto-generated method stub
		return this.actionComplete;
	}

	public String getActionName() {
		this.name = this.device.getDevName() + "." + this.field +
					"["+ this.device.getEventElement(field).getOldValue() + " >> " + this.action + "]";
		return this.name;

	}

}