package entity.counter;

import logic.Player;
import entity.base.Choppable;
import entity.base.Ingredient;

public class ChoppingBoard extends Counter {
	public ChoppingBoard() {
		super("Chopping Board");
	}
	
	@Override
	public void interact(Player p) {
		if (!super.isPlacedContentEmpty()) {
			super.interact(p);
		} else if (!p.isHandEmpty() && p.getHoldingItem() instanceof Ingredient) {
			if (p.getHoldingItem() instanceof Choppable)
				((Choppable) p.getHoldingItem()).chop();
			super.interact(p);
		}
	}
}
