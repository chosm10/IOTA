
public class NotInitializeException extends RuntimeException{
	public NotInitializeException(String msg) {
		super(msg);
	}
	public NotInitializeException(Exception e) {
		super(e);
	}
}
