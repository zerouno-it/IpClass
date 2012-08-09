/**
 * Class to handle IPv4 addresses. 
 * 
 * 
 * @author 	Alejandro Amaral
 * @version	1.0 08 Aug. 2012
 *
 */
public class IpAddress {
	
	/** IPv4 in dotted decimal notation */
	private String ipAddress;
	
	/** IPv4 in decimal notation */
	private long decimalIp;
	
	/** IPv4 first octet from left to right */
	private short _1stOctet;
	
	/** IPv4 second octet from left to right */
	private short _2ndOctet;
	
	/** IPv4 third octet from left to right */
	private short _3rdOctet;
	
	/** IPv4 fourth octet from left to right */
	private short _4thOctet;
	
	public IpAddress(String ip) {
		this.SetIP(ip);
	}
	
	public long GetDecimal() {
		return decimalIp;
	}
	
	public short Get1stOctet() {
		return _1stOctet;
	}
	
	public short Get2ndOctet() {
		return _2ndOctet;
	}
	
	public short Get3rdOctet() {
		return _3rdOctet;
	}
	
	public short Get4thOctet() {
		return _4thOctet;
	}
	
	public boolean SetIP(String ip) {
		if (this.CheckFormat(ip)) {
			ipAddress = ip;
			decimalIp = this.toDecimal(ip);
			this.SplitAddress(ip);
			return true;
		}
		else {
			ipAddress = "0.0.0.0";
			decimalIp = 0;
			return false;
		}
	}
	
	public String GetIP() {
		return ipAddress;
	}
	
	private boolean CheckFormat(String ip) {
		return (ip.matches("([0-9]{1,3}.){3}[0-9]{1,3}"));
	}
	
	private void SplitAddress(String ip) {
		String[] octets;
		octets = ip.split("\\.");
		_1stOctet = Short.parseShort(octets[0]);
		_2ndOctet = Short.parseShort(octets[1]);
		_3rdOctet = Short.parseShort(octets[2]);
		_4thOctet = Short.parseShort(octets[3]);
	}
	
	private long toDecimal(String ip) {
		String[] octets;
		long i = 0;
		octets = ip.split("\\.");
		for (int n = 0; n <= 3; n++) {
			i <<= 8;
			i |= Long.parseLong(octets[n]);
		}
		return i;
	}

}
