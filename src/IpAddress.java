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



	/** Get the decimal representation of the IPv4
	 *	@return The decimal value of the IPv4
	 */
	public long GetDecimal() {
		return decimalIp;
	}



	/** Get the first octet (xxx.---.---.---) from left to right in decimal notation
	 * 	@return The value of the 1st octet
	 */
	public short Get1stOctet() {
		return _1stOctet;
	}

	/** Get the second octet (---.xxx.---.---) from left to right in decimal notation
	 *	@return The value of the 2nd octet 
	 */
	public short Get2ndOctet() {
		return _2ndOctet;
	}

	/** Get the third octet (---.---.xxx.---) from left to right in decimal notation
	 *	@return The value of the 3rd octet 
	 */
	public short Get3rdOctet() {
		return _3rdOctet;
	}

	/** Get the fourth octet (---.---.---.xxx) from left to right in decimal notation
	 * 	@return The value of the 4th octet 
	 */
	public short Get4thOctet() {
		return _4thOctet;
	}

	

	/** Set the working IP. If the format of ip is not in dotted decimal
	 * 	then its set to 0.0.0.0
	 * 
	 * 	@param 	ip - The IP address in dotted decimal format. Must not be NULL
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
	
	
	/**
	 * Show the previous address from the current address
	 * @return The string of the previous address in dotted decimal format
	 */
	public String PrevAddress(){
		return this.MoveAddress(false, 1);
	}
	
	/**
	 * Set the current IP to the next address
	 */
	public void MoveToNext() {
		this.SetIP(this.NextAddress());
	}
	
	/**
	 * Set the current IP to the previous address
	 */
	public void MoveToPrevious() {
		this.SetIP(this.PrevAddress());
	}

	/**
	 * Show the IP address up or below of the current address according the value in positions
	 * @param moveForward - Determine the direction of movement. If true, the movement is ascendant, else is descendant
	 * @param positions	- How many positions move relatively to current IP. Must be any value between 1 and 255
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
			i = ((i + pos) - 255) - 1;							// Take the difference between actual ip, pos and max value. Substract 1 because IP addresses are zero based
			j += 1;												// Add 1 to next octet
		}
		else if ((i + pos) < 0) {								// The position is below the min value
			i = ((i + pos) + 255) + 1;							// Same as above but add 1 to compensate the zero
			j -= 1;
		}
		else { i += pos; }										// The position is between the min a max values

		
		if (j > 255) {											// Same principle as 4th octet, if over 255
			j = 0;												// the value is 0
			k += 1;												// and carry 1 to next octet
		}
		else if (j < 0) {										// If below the minimun
			j = 255;											// Set to 255
			k -= 1;												// Substract 1 to 2nd octet
		}
		
		if (k > 255) {											// Same as above
			k = 0;
			l += 1;
		}
		else if (k < 0) {
			k = 255;
			l -= 1;
		}
		
		
		if ((l > 255) || (l < 0)) {								// If reach the min or max values
			l = 0;												// reset all values to 0
			k = 0;
			j = 0;
			i = 0;
		}
		
		return (l + "." + k + "." + j + "." + i);				// Return the IPv4 in dotted decimal format

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


	/**
	 * Populate the octets from the string of an IPv4 dotted decimal address
	 * @param ip - The IPv4 address
	 */
	private void SplitAddress(String ip) {
		if (this.CheckFormat(ip)) {
			String[] octets;
			octets = ip.split("\\.");
			_1stOctet = Short.parseShort(octets[0]);
			_2ndOctet = Short.parseShort(octets[1]);
			_3rdOctet = Short.parseShort(octets[2]);
			_4thOctet = Short.parseShort(octets[3]);	
		}
		else {
			_1stOctet = 0;
			_2ndOctet = 0;
			_3rdOctet = 0;
			_4thOctet = 0;
		}
	}

	
	/**
	 * Convert an IPv4 dotted decimal address to decimal notation
	 * @param ip - The IPv4 address to convert
	 * @return The decimal representation of the IPv4
	 */
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