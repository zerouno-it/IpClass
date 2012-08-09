
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Test");
		IpAddress ip;
		ip = new IpAddress();
		ip.SetIP("192.168.1.1");
		System.out.println( ip.GetDecimal());

	}

}
