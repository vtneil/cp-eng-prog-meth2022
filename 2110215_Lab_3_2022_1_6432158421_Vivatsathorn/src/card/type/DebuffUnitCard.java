package card.type;

import card.base.UnitCard;
import player.Player;

public class DebuffUnitCard extends UnitCard {
	private int debuffPower;
	
	public DebuffUnitCard(String name, String flavorText, int bloodCost, int power, int health, int debuffPower) {
		super(name, flavorText, bloodCost, power, health);
		this.setDebuffPower(debuffPower);
	}

	public int getDebuffPower() {
		return debuffPower;
	}

	public void setDebuffPower(int level) {
		this.debuffPower = (level < 0) ? 0 : level;
	}

	@Override
	public int attackUnit(UnitCard unitCard) {
		int returnValue = (this.getPower() > unitCard.getHealth()) ? unitCard.getHealth() : this.getPower();
		unitCard.setHealth(unitCard.getHealth() - this.getPower());
		unitCard.setPower(unitCard.getPower() - this.getDebuffPower());
		return returnValue;
	}
}
