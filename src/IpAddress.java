/**
 * Class to handle IPv4 addresses. 
 * 
 * 
 * @author 	Alejandro Amaral
 * @version	1.0 08 Aug. 2012
 *
 */
public class IpAddress {
	
	/** Holds the current IPv4 in dotted decimal notation */
	private String ipAddress;
	
	/** Holds the current IPv4 in decimal notation */
	private long decimalIp;
	
	public boolean SetIP(String ip) {
		if (this.CheckFormat(ip)) {
			this.ipAddress = ip;
			this.decimalIp = this.toDecimal(ip);
			return true;
		}
		else {
			this.ipAddress = "0.0.0.0";
			this.decimalIp = 0;
			return false;
		}
	}
	
	public String GetIP() {
		return this.ipAddress;
	}
	
	private boolean CheckFormat(String ip) {
		return (ip.matches("([0-9]{1,3}.){3}[0-9]{1,3}"));
	}

	public long GetDecimal() {
		return this.decimalIp;
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
