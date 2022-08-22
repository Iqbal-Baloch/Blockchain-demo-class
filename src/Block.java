import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class Block 
{
	private String hash ;
	private String previousHash;
	private String data ;
	private long timeStamp ;
	private int nonce ;
	
	public Block(String data, String previousHash, long timeStamp) {
		this.data = data ;
		this.previousHash = previousHash ;
		this.timeStamp = timeStamp ;
		this.hash = calculateBlockHash() ;
	}
	
	public String calculateBlockHash() {
		String dataToHash = this.previousHash + 
				Long.toString(this.timeStamp) + 
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

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int getNonce() {
		return nonce;
	}

	public void setNonce(int nonce) {
		this.nonce = nonce;
	}
}
