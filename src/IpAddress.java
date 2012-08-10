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
	 * 	@param 	ip The IP address in dotted decimal format. Must be not NULL
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

	/** Get the current IP */
	public String GetIP() {
		return ipAddress;
	}

	/**
	 * Show the next address from the current address
	 * @return The string of the next address in dotted decimal format
	 */
	public String NextAddress() {
		return this.MoveAddress(true, 1);
	}
	
	
	public String PrevAddress(){
		return this.MoveAddress(false, 1);
	}

	/**
	 * Show the IP address up or below of the current address according the value in positions
	 * @param moveForward - Determine the direction of movement. If true, the movement is ascendant, else is descendant
	 * @param positions	- How many positions move relatively to current IP.
	 * @return The IPv4 address solicited
	 */
	private String MoveAddress(boolean moveForward, int positions ) {
	
		if ((positions < 1) || (positions > 255)) { return "0.0.0.0"; }

		int l = _1stOctet;
		int k = _2ndOctet;
		int j = _3rdOctet;
		int i = _4thOctet;

		int pos;

		pos = (moveForward) ? positions : (positions * (-1));	// If moveForward is false, sets the position in negative

		if ((i + pos) > 255) {									// The position exceeds the max value of 4th octet 
			i = ((i + pos) - 255) - 1;
			j += 1;
		}
		else if ((i + pos) < 0) {
			i = ((i + pos) + 255) + 1;
			j -= 1;
		}
		else { i += pos; }

		
		if (j > 255) {
			j = 0;
			k += 1;
		}
		else if (j < 0) {
			j = 255;
			k -= 1;
		}
		
		if (k > 255) {
			k = 0;
			l += 1;
		}
		else if (k < 0) {
			k = 255;
			l -= 1;
		}
		
		
		if ((l > 255) || (l < 0)) {
			l = 0;
			k = 0;
			j = 0;
			i = 0;
		}
		
		return (l + "." + k + "." + j + "." + i);

	}

	/** Check if the format of the IP is in dotted decimal (xxx.xxx.xxx.xxx)
	 * 
	 *	@param ip The string to check format 
	 * 	@return True if the format is correct, otherwise, False
	 * 
	 */
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