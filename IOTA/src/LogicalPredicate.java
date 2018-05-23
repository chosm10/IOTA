import java.util.ArrayList;

public class LogicalPredicate implements Predicate {
	public static final int AND = 1; 
	public static final int OR = 2; 
	public static final int NOT = 3; 
	private Predicate predicate1;
	private int op;
	private Predicate predicate2;

	public LogicalPredicate(int op, Predicate predicate1) throws RuntimeException { //NOT 일때 사용
		if(op != 3)
			throw new RuntimeException(op + "는 잘못된 연산 번호 입니다: NOT 연산의 연산 번호는 3입니다.");
		this.op = op;
		this.predicate1 = predicate1;
	}
	public LogicalPredicate(Predicate predicate1, int op, Predicate predicate2) throws RuntimeException {
		if((op != 1) && (op != 2))
			throw new RuntimeException(op + "는 잘못된 연산 번호 입니다: AND 연산의 연산 번호는 1, OR은 2입니다.");
		this.predicate1 = predicate1;
		this.op = op;
		this.predicate2 = predicate2;
	}
	public boolean CheckPredicate() {
		switch(op) {
		case 1 :
			return predicate1.CheckPredicate() && predicate2.CheckPredicate();
		case 2 :
			return predicate1.CheckPredicate() || predicate2.CheckPredicate();
		case 3 :
			return !predicate1.CheckPredicate();//NOT 연산을 사용 할 때
		}
		return false;
	}

	
}