package projet.blockchain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.lang.String;

public class Block {
		private int index;
		private String previousHash;
		private long timestamp;
		private String hash;
		private int nonce;
		private String data;
		
		public Block(int index, String previousHash, long timestamp, String data) {
			super();
			this.index = index;
			this.previousHash = previousHash;
			this.timestamp = timestamp;
			this.data = data;
			nonce=0;
			hash=Block.calculateHash(this);
		}

		public String toString() {
			StringBuilder  builder = new StringBuilder();
			builder.append("Block #").append(index).append(" [previousHash : ").append(previousHash)
			.append(",").append("timestamp :").append(new Date(timestamp)).append(",").append("data :")
			.append(data).append(" ,").append("Hash :").append(hash).append("]");
			return builder.toString();
		}
		public static String calculateHash(Block block) {
			if(block != null) {
				MessageDigest digest=null;
				try {
					digest = MessageDigest.getInstance("SHA-256");
				} catch (NoSuchAlgorithmException e) {
					return null;
				}
				
				String txt =block.str();
				final byte bytes[]=digest.digest(txt.getBytes());
				final StringBuilder builder= new StringBuilder();
				
				for(final byte b : bytes) {
					String hex =Integer.toHexString(0xff & b);
					
				    if (hex.length()==1) {
				    	builder.append(0);
				    }
				    builder.append(hex);
				}
				       	
				return builder.toString();
			}
			return null;
				
		}
		
		public void mineBlock(int difficulty) {
			nonce=0;
			while(!getHash().substring(0, difficulty).equals(Utils.zeros(difficulty))) {
				nonce++;
				hash=Block.calculateHash(this);
				
			}
		}

		private String str() {
			return index + timestamp + data + previousHash +nonce;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		public String getPreviousHash() {
			return previousHash;
		}

		public void setPreviousHash(String previousHash) {
			this.previousHash = previousHash;
		}

		public long getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(long timestamp) {
			this.timestamp = timestamp;
		}

		public String getHash() {
			return hash;
		}

		public void setHash(String hash) {
			this.hash = hash;
		}

		public String getData() {
			return data;
		}

		public void setData(String data) {
			this.data = data;
		}
		
		
}