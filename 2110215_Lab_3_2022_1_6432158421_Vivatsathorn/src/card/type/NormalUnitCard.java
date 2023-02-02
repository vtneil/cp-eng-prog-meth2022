package card.type;

import card.base.UnitCard;
import player.Player;

public class NormalUnitCard extends UnitCard {
	public NormalUnitCard(String name, String flavorText, int bloodCost, int power, int health) {
		super(name, flavorText, bloodCost, power, health);
	}
	
	@Override
	public int attackUnit(UnitCard unitCard) {
		int returnValue = (this.getPower() > unitCard.getHealth()) ? unitCard.getHealth() : this.getPower();
		unitCard.setHealth(unitCard.getHealth() - this.getPower());
		return returnValue;
	}
	
}

