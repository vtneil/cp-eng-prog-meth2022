package card.type;

import card.base.UnitCard;
import player.Player;

public class VenomUnitCard extends UnitCard {
	public VenomUnitCard(String name, String flavorText, int bloodCost, int power, int health) {
		super(name, flavorText, bloodCost, power, health);
	}

	@Override
	public int attackUnit(UnitCard unitCard) {
		int returnValue = unitCard.getHealth();
		unitCard.setHealth(0);
		return returnValue;
	}

	public int dead(Player player) {
		return this.attackPlayer(player);
	}
}
