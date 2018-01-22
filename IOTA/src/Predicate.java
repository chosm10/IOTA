
public class Predicate {
	Boolean state;
	public Predicate() {
		this.state = false;
	}
	public Predicate(Boolean state) {   
		/*Predicate은 RuleSet의 모든 Event가 검사된 후에 그 시점에서 조건을 확인 해야 한다. 이렇게 처음부터 조건으로
		 *Boolean을 받으면 Event가 검사된 후 시점의 조건을 확인하는게 아니다. 어떻게 해야 할까?
		*/
		this.state = state;
	}
	public Boolean GetState() {
		return this.state;
	}
}
