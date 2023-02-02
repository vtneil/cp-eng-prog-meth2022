package entity.counter;

import entity.base.Updatable;
import entity.base.Item;
import entity.container.Pan;
import logic.Player;

public class Stove extends Counter implements Updatable {
	public Stove() {
		super("Stove");
	}
	
	public Stove(Item content) {
		this();
		super.setPlacedContent(content);
	}
	
	@Override
	public void interact(Player p) {
		if (!super.isPlacedContentEmpty())
			super.interact(p);
		else if (p.getHoldingItem() instanceof Pan)
			super.interact(p);
	}

	@Override
	public void update() {
		if (super.getPlacedContent() instanceof Pan)
			((Pan) super.getPlacedContent()).cook();
		
	}
}
