
public class IncorrectFieldException extends RuntimeException{
	public IncorrectFieldException(String msg) {
		super(msg);
	}
	public IncorrectFieldException(Exception e) {
		super(e);
	}
}
