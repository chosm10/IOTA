import java.util.ArrayList;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> a = new ArrayList<>();
		a.add("Hello");
		a.add("World");
		System.out.println(a.get(1));
		Field f = new Field("String");
		Scanner sc = new Scanner(System.in);
		String s = "Happy";
		System.out.println(s.substring(0,1));
		while(true) {
			String n = sc.nextLine();
			System.out.println(n);
			if(n.equals("stop") ) 
				break;
		}
		System.out.println(s.substring(0,1));
	}

}
