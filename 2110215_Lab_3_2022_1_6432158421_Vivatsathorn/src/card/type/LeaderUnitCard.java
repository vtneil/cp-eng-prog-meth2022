package card.type;

import card.base.UnitCard;
import player.Player;

public class LeaderUnitCard extends NormalUnitCard {
	private int buffPower;
	private int buffHealth;

	public LeaderUnitCard(String name, String flavorText, int bloodCost, int power, int health, int buffPower,
			int buffHealth) {
		super(name, flavorText, bloodCost, power, health);
		this.setBuffPower(buffPower);
		this.setBuffHealth(buffHealth);
	}

	public void buffUnit(UnitCard[] alliesCard) {
		for (UnitCard unitCard : alliesCard) {
			if (unitCard == null)
				continue;
			unitCard.setHealth(unitCard.getHealth() + buffHealth);
			unitCard.setPower(unitCard.getPower() + buffPower);
		}
	}

	public int getBuffPower() {
		return buffPower;
	}

	public void setBuffPower(int buffPower) {
		this.buffPower = (buffPower < 0) ? 0 : buffPower;
	}

	public int getBuffHealth() {
		return buffHealth;
	}

	public void setBuffHealth(int buffHealth) {
		this.buffHealth = (buffHealth < 0) ? 0 : buffHealth;
	}

	@Override
	public String toString() {
		return super.getName() + " (POW: " + super.getPower() + ", HP: " + super.getHealth() + " | POW Inc: "
				+ this.getBuffPower() + ", HP Inc: " + this.getBuffHealth() + ")";
	}
}