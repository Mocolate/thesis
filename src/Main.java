
public class Main {
	
	public static void main(String[] args) {
		ProofNode root = new ORnode(null);
		
		ProofSearch search = new ProofSearch();
		search.proofNumberSearch(root);
	}
}
