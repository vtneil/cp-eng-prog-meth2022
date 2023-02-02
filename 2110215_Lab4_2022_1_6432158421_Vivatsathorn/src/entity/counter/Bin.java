package entity.counter;

import logic.Player;
import entity.base.Ingredient;
import entity.base.Container;

public class Bin extends Counter {
	public Bin() {
		super("Bin");
	}
	
	@Override
	public void interact(Player p) {
		if (p.isHandEmpty())
			return;
		if (p.getHoldingItem() instanceof Ingredient)
			p.setHoldingItem(null);
		if (p.getHoldingItem() instanceof Container)
			((Container) p.getHoldingItem()).clearContent();
	}
}
