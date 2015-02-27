import java.util.LinkedList;


public abstract class ProofNode {
	private boolean type; //true = AND-node, false = OR-node
	private int pn;
	private int dn;
	private ProofNode parent;
	private LinkedList<ProofNode> children = new LinkedList<ProofNode>();
	private boolean value; //true = proved, false = disproved
	
	public boolean getType() {return type;}
	
	public int getPN() {
		return pn;
	}
	
	public int getDN() {
		return dn;
	}
	
	public ProofNode getParent() {
		return parent;
	}
	
	public LinkedList<ProofNode> getChildren() {
		return children;
	}
	
	public class ANDnode extends ProofNode {
		private LinkedList<Molecule> ANDvalue = new LinkedList<Molecule>();
		
		public ANDnode(ProofNode parent) {
			super.parent = parent;
			super.type = true;
		}
		
		public LinkedList<Molecule> getANDvalue() {
			return ANDvalue;
		}
		
		public void setANDvalue(LinkedList<Molecule> m) {
			ANDvalue = m;
		}
	}
	
	public class ORnode extends ProofNode {
		private Molecule ORvalue;
		private LinkedList<Reaction> posReactions = new LinkedList<Reaction>();
		
		public ORnode(ProofNode parent) {
			super.parent = parent;
			super.type = false;
		}
		
		public Molecule getORvalue() {
			return ORvalue;
		}
		
		public LinkedList<Reaction> getPosReactions() {
			return posReactions;
		}
		
		public void setORvalue(Molecule m) {
			ORvalue = m;
		}
	}
		
	public boolean proved() {
		return value;
	}
	
	public void setPN(int pn) {
		this.pn = pn;
	}
	
	public void setDN(int dn) {
		this.dn = dn;
	}
	
	public void setValue(boolean proved) {
		value = proved;
	}
		
	public void calculateProofNumber() {
		int tempPN = 0;
		if (type == true) { //AND-node
			for (int i = 0; i<children.size(); i++) {
				tempPN = tempPN + children.get(i).getPN();
			}
		}
		if (type == false) { //OR-node
			for (int i = 0; i<children.size(); i++) {
				tempPN = Math.min(tempPN, children.get(i).getPN());
			}
		}
		pn = tempPN;
	}
	
	public void calculateDisproofNumber() {
		int tempDN = 0;
		if (type == true) {//AND-node
			for (int i = 0; i<children.size(); i++) {
				tempDN = Math.min(tempDN, children.get(i).getDN());
			}
		}
		if (type == false) {//OR-node
			for (int i = 0; i<children.size(); i++) {
				tempDN = tempDN + children.get(i).getDN();
			}
		}
		dn = tempDN;
	}
	
}
