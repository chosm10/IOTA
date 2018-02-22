
public class ConstPredicate implements Predicate {
	boolean constValue;
	public ConstPredicate(boolean value) {   // 처음 설정한 참, 거짓만 반환하는 클래스
		this.constValue = value;
	}
	public boolean CheckPredicate() {
		return this.constValue;
	}
}
