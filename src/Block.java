import java.security.MessageDigest;
import java.sql.Timestamp;
import java.nio.charset.StandardCharsets;


public class Block 
{
	private String hash ;
	private String previousHash;
	private String data ;
	private Timestamp timeStamp ;
	private int nonce ;
	
	public Block(String data, String previousHash) {
		this.data = data ;
		this.previousHash = previousHash ;
		this.timeStamp = new Timestamp(System.currentTimeMillis());
		this.hash = calculateBlockHash() ;
	}
	
	public String calculateBlockHash() {
		String dataToHash = this.previousHash + 
				this.timeStamp.toString() + 
				Integer.toString(this.nonce) +
				this.data ;
		MessageDigest digest = null ;
		byte[] bytes = null ;
		try {
	        digest = MessageDigest.getInstance("SHA-256");
	        bytes = digest.digest(dataToHash.getBytes(StandardCharsets.UTF_8));
	    } catch (Exception ex) {
	       System.out.println(ex.getMessage());
	    }
	    StringBuffer buffer = new StringBuffer();
	    for (byte b : bytes) {
	        buffer.append(String.format("%02x", b));
	    }
	    return buffer.toString();
	}
	
	public String mineBlock(int prefix) {
		nonce = 0 ;
	    String prefixString = new String(new char[prefix]).replace('\0', '0');
	    while (!hash.substring(0, prefix).equals(prefixString)) {
	        nonce++;
	        hash = calculateBlockHash();
	    }
	    return hash;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getPreviousHash() {
		return previousHash;
	}

	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int getNonce() {
		return nonce;
	}

	public void setNonce(int nonce) {
		this.nonce = nonce;
	}
}
