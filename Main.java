package projet.blockchain;

public class Main {

	public static void main(String[] args) {
		blockchain Blockchain = new blockchain(4);
		Blockchain.addBlock(Blockchain.newBlock("Infos 1"));
		Blockchain.addBlock(Blockchain.newBlock("Infos 2"));
		Blockchain.addBlock(Blockchain.newBlock("Inofs 3"));
		Blockchain.addBlock(Blockchain.newBlock("Infos 4"));
		
		System.out.println(Blockchain);
		Blockchain.addBlock(new Block(15,"aaaa",System.currentTimeMillis(),"Block is invalid"));
		System.out.println("blockChian valid ? " + Blockchain.isBlockChainValid());
		System.out.println(Blockchain);

	}

}
