import java.util.LinkedList;


public class Reaction {
	private Molecule compound;
	private LinkedList<Molecule> reagents = new LinkedList<Molecule>();
	
	public Reaction(Molecule compound, Molecule reagent1, Molecule reagent2) {
		this.compound = compound;
		reagents.add(reagent1);
		reagents.add(reagent2);
	}
	
	public Reaction(Molecule compound, Molecule reagent1, Molecule reagent2, Molecule reagent3) {
		this.compound = compound;
		reagents.add(reagent1);
		reagents.add(reagent2);
		reagents.add(reagent3);
	}
	
	public LinkedList<Molecule> getReagents() {
		return reagents;
	}
	
	public Molecule getCompound() {
		return compound;
	}
}
