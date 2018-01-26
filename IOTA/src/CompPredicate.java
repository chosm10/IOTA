
public class CompPredicate implements Predicate {
	public static final int EQUAL = 1; // =
	public static final int LESS_THAN = 2; // <
	private String oprnd1, oprnd2;
	private int op;
	private Device device;
	
	public CompPredicate(Device device, String oprnd1, int op, String oprnd2) throws RuntimeException {   //oprnd1은 필드나 타이머, oprnd2는 필드나 상수
		super();
		if((oprnd1 != "Field") && (oprnd1 != "Timer"))
			throw new RuntimeException(oprnd1 + "은 Field나 Timer여야 합니다.");
		else if((op != LESS_THAN) && (op != EQUAL))
			throw new RuntimeException(op + "는 등록된 연산자가 아닙니다. 1: =, 2: < 중 하나를 사용하세요.");
		else if(!device.GetProperty().IsRegisteredProperty(oprnd2) && !device.f.IsStringDouble(oprnd2))
			throw new RuntimeException(oprnd2 + "는 장치에 등록된 Property나 상수여야 합니다.");
		
		this.device = device;
		this.oprnd1 = oprnd1;
		this.op = op;
		this.oprnd2 = oprnd2;
	}
	public boolean CheckPredicate() { 
		String currentElementValue = device.GetEventElement(oprnd1).GetCurrentValue(); //oprand1의 현재 값
		switch(op) {
		case 1 :
			return currentElementValue == oprnd2;
		case 2 :
			return Double.parseDouble(currentElementValue) < Double.parseDouble(oprnd2);
		}
		return false;
	}
}
