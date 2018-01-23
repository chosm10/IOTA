
public class Evaluation extends Thread {  //메인 스레드가 끝나면 끝나야 하니까 데몬 스레드로 하자. 
	RuleSet rs;
	
	public Evaluation(RuleSet rs) {
		this.rs = rs;
	}
	
	public void run() {
		while(true) {
			
		}
	}
}
