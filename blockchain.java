package projet.blockchain;

import java.util.ArrayList;
import java.util.List;

public class blockchain {
	private int difficulty;
	private List<Block> blocks;
	
	public blockchain(int difficulty) {
		this.difficulty=difficulty;
		blocks =new ArrayList<>();
		Block b= new Block(0,null,System.currentTimeMillis(),"First block");
		b.mineBlock(difficulty);
		blocks.add(b);
	}
	public int getDifficulty(){
		return difficulty;
	}
	public Block latestBlock() {
		return blocks.get(blocks.size()-1); // return le dernier block
	}
	
	public Block newBlock(String data) {
		Block latestBlock= latestBlock();
		return new Block(latestBlock.getIndex()+1,latestBlock.getHash(),System.currentTimeMillis(),data);
	}
	public void addBlock(Block b) {
		if(b!=null) {
			b.mineBlock(difficulty);
			blocks.add(b);
		}
	}
	
	public boolean isFirstBlockValid() {
		Block firstBlock = blocks.get(0);
		
		if(firstBlock.getIndex()!=0) {
			return false;
		}
		if(firstBlock.getPreviousHash()!= null) {
			return false;
		}
		if(firstBlock.getHash()== null || !Block.calculateHash(firstBlock).equals(firstBlock.getHash())){
			return false;
		}
		return true;
	}
	
	public boolean isValidNewBlock(Block newBlock , Block previousBlock) {
		
		if(newBlock != null && previousBlock != null) {
			if(previousBlock.getIndex()+1 != newBlock.getIndex()) {
				return false;
			}
		    if(newBlock.getPreviousHash() == null || newBlock.getPreviousHash().equals(previousBlock.getHash())) {
			return false;
		    }
		    if(newBlock.getHash()== null|| !Block.calculateHash(newBlock).equals(previousBlock.getHash())) {
			return false;
		    }
		    return true;
	    }
		return false;
    }
	
	public boolean isBlockChainValid() {
		if(!isFirstBlockValid()) {
			return false;
		}
		for(int i=1;i<blocks.size();i++) {
			Block currentBlock = blocks.get(i);
			Block previousblock = blocks.get(i-1);
			if(!isValidNewBlock(currentBlock,previousblock)) {
				return false;
			}
		}
		return true;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		for(Block block:blocks) {
			builder.append(block).append("\n");
		}
		return builder.toString();
	}
}
