import ProofNode.ANDnode;
import ProofNode.ORnode;

public class ProofSearch {
	public void evaluate(ORnode node) {
		boolean isAvailable = false;
		if (isAvailable) {// if reagent is available
			node.setPN(0);
			node.setDN(Integer.MAX_VALUE);
		} else {
			expand(node);
			for (int i = 0; i < node.getChildren(); i++) {
				expand(node.getChildren().get(i));
				node.getChildren().get(i).setPN(1);
				node.getChildren().get(i).setDN(1);
			}
			int pn = 1;
			int dn = node.getChildren().size();
			node.setPN(pn);
			node.setDN(dn);
		}

	}
	
	public void evaluate(ANDnode node) {
		expand(node);
		
	}

	public void expand(ANDnode node) {
		for (int i = 0; i < node.getANDvalue().size(); i++) {// nr of
																// reagents
			ProofNode tempNode = new ProofNode(false, node);
			tempNode.setORvalue(node.getANDvalue().get(i));
		}

	}

	public void expand(ORnode node) {
		for (int i = 0; i < node.getPosReactions().size(); i++) {// nr of
																	// reactions
			ProofNode tempNode = new ProofNode(true, node);
			tempNode.setANDvalue(node.getPosReactions().get(i).getReagents());
			node.getChildren().add(tempNode);
		}
	}

	public ProofNode selectMostProving(ProofNode node) {
		while (!node.getChildren().isEmpty()) {
			int i;
			if (node.getType()) {// AND
				i = 1;
				while (node.getChildren().get(i).getDN() != node.getDN()) {
					i++;
				}
			} else {// OR
				i = 1;
				while (node.getChildren().get(i).getPN() != node.getPN()) {
					i++;
				}
			}
			selectMostProving(node.getChildren().get(i));
		}
		return node;
	}

	public void updateAncestors(ProofNode node) {
		if (node.getParent() != null) {
			node.getParent().calculateDisproofNumber();
			node.getParent().calculateProofNumber();
			updateAncestors(node.getParent());
		}
	}

	public boolean proofNumberSearch(ORnode root) {
		evaluate(root);
		while ((root.getPN() != 0) && (root.getDN() != 0)) { // &&
																// ResourcesAvailable
			ProofNode mostProving = selectMostProving(root);
			evaluate(mostProving);
			updateAncestors(mostProving);
		}
		if (root.getPN() == 0) {
			root.setValue(true);
		} else if (root.getDN() == 0) {
			root.setValue(false);
		}
		return root.proved();
	}

}
