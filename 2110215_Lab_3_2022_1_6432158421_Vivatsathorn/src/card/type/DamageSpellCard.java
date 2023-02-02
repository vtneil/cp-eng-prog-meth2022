package card.type;

import card.base.SpellCard;
import card.base.UnitCard;

public class DamageSpellCard extends SpellCard {
	private int damage;

	public DamageSpellCard(String name, String flavorText, int bloodCost, boolean isBurstSpeed, int damage) {
		super(name, flavorText, bloodCost, isBurstSpeed);
		this.setDamage(damage);
	}

	@Override
	public void castSpell(UnitCard unitCard) {
		unitCard.setHealth(unitCard.getHealth() - damage);
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = (damage < 1) ? 1 : damage;
	}
}