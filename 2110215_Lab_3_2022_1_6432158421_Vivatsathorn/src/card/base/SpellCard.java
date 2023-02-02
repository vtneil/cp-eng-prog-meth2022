package card.base;

public abstract class SpellCard extends Card {
	private boolean isBurstSpeed;

	public SpellCard(String name, String flavorText, int bloodCost, boolean isBurstSpeed) {
		super(name, flavorText, bloodCost);
		this.setBurstSpeed(isBurstSpeed);
	}

	public abstract void castSpell(UnitCard unitCard);

	@Override
	public String toString() {
		return this.getName() + "\tCost: " + this.getBloodCost() + "\t(" + (this.getFlavorText()) + ")";
	}

	public boolean isBurstSpeed() {
		return isBurstSpeed;
	}

	public void setBurstSpeed(boolean isBurstSpeed) {
		this.isBurstSpeed = isBurstSpeed;
	}
}
