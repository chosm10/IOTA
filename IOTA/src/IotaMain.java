import java.util.Scanner;

public class IotaMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while(true) {
			System.out.print("Device: ");
			Scanner input = new Scanner(System.in);
			String str1 = input.nextLine();
			
			System.out.print("Select: "); // 바꿀 값이 Filed인지 Timer인지
			//Scanner select = new Scanner(System.in);
			String str2 = input.nextLine();
			
			System.out.print("Value: "); // 바꿀 값이 Filed인지 Timer인지
			//Scanner value = new Scanner(System.in);
			String str3 = input.nextLine();
		}
	}
}
