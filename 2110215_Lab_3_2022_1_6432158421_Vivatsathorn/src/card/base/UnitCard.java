package card.base;

import player.Player;

public abstract class UnitCard extends Card  {
	private int power;
	private int health;

	public UnitCard(String name, String flavorText, int bloodCost, int power, int health) {
		super(name, flavorText, bloodCost);
		this.setPower(power);
		this.setHealth(health);
	}
	
	public abstract int attackUnit(UnitCard u);

	public int attackPlayer(Player opponent) {
		opponent.takeDamage(power);
		return power;
	}
	
	@Override
	public String toString() {
		return super.getName() + "\tCost: " + this.getBloodCost() +"\t(POW: " + this.getPower() + ", HP: " + this.getHealth() + ")";
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = (power < 0) ? 0 : power;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = (health < 0) ? 0 : health;
	}
}
