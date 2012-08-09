/**
 * Class to handle IPv4 addresses. 
 * 
 * 
 * @author 	Alejandro Amaral
 * @version	1.0 08 Aug. 2012
 *
 */
public class IpAddress {
	
	
	private String ipAddress;	// IPv4 in dotted decimal notation
	
	private long decimalIp;		// IPv4 in decimal notation
	
	private short _1stOctet;	// IPv4 first octet from left to right
	private short _2ndOctet;	// IPv4 second octet from left to right
	private short _3rdOctet;	// IPv4 third octet from left to right
	private short _4thOctet;	// IPv4 fourth octet from left to right
	
	
	
	/** Default constructor
	 * 
	 *	@param ip The IPv4 in dotted decimal notation. Must not be NULL 
	 * 
	 */
	public IpAddress(String ip) {
		this.SetIP(ip);
	}
	
	
	
	/** Get the decimal representation of the IPv4 */
	public long GetDecimal() {
		return decimalIp;
	}
	
	
	
	/** Get the first octet (xxx.---.---.---) from left to right in decimal */
	public short Get1stOctet() {
		return _1stOctet;
	}
	
	/** Get the second octet (---.xxx.---.---) from left to right in decimal */
	public short Get2ndOctet() {
		return _2ndOctet;
	}
	
	/** Get the third octet (---.---.xxx.---) from left to right in decimal */
	public short Get3rdOctet() {
		return _3rdOctet;
	}
	
	/** Get the fourth octet (---.---.---.xxx) from left to right in decimal */
	public short Get4thOctet() {
		return _4thOctet;
	}
	
	
	/** Set the working IP. If the format of ip is not in dotted decimal
	 * 	then its set to 0.0.0.0
	 * 
	 * 	@param 	ip
	 * 	@return The result of IPv4 format check. True is the format is correct, false if its not
	 */
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
