
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IpAddress ip;
		ip = new IpAddress("255.255.255.230");
		
		for (int i = 1; i <= 255; i++) {
			ip.MoveToNext();
			System.out.println( ip.GetIP());	
		}
		

	}

}
