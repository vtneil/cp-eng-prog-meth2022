package entity.container;

import entity.base.Container;
import entity.base.Ingredient;
import entity.base.Cookable;

public class Pan extends Container {
	public Pan() {
		super("Pan", 1);
	}
	
	public void cook() {
		if (super.isEmpty())
			return;
		for (Ingredient i : super.getContent()) {
			if (!this.verifyContent(i))
				continue;
			((Cookable) i).cook();
		}
	}

	@Override
	public boolean verifyContent(Ingredient i) {
		return (i instanceof Cookable);
	}
}
