package entity.ingredient;

import entity.base.Ingredient;
import entity.base.Choppable;

public class Lettuce extends Ingredient implements Choppable {
	private boolean chopState;
	
	public Lettuce() {
		super("Lettuce");
		super.setEdible(true);
		this.chopState = false;
	}

	@Override
	public void chop() {
		System.out.println("Chopped called!");
		if (!this.isChopped()) {
			this.chopState = true;
			super.setName("Chopped Lettuce");
		}
	}

	@Override
	public boolean isChopped() {
		return this.chopState;
	}

}
