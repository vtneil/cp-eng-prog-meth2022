package entity.ingredient;

import entity.base.Ingredient;
import logic.StringUtil;
import entity.base.Cookable;

public class Egg extends Ingredient implements Cookable {
	private int cookedPercentage;
	
	public Egg() {
		super("Egg");
		this.cookedPercentage = 0;
	}

	@Override
	public void cook() {
		this.setCookedPercentage(this.getCookedPercentage() + 12);
		if (this.getCookedPercentage() <= 50) {
			super.setName("Raw Egg");
			super.setEdible(false);
		} else if (this.getCookedPercentage() <= 80) {
			super.setName("Sunny Side Egg");
			super.setEdible(true);
		} else if (this.getCookedPercentage() <= 100) {
			super.setName("Fried Egg");
			super.setEdible(true);
		} else {
			super.setName("Burnt Egg");
			super.setEdible(false);
		}
	}

	@Override
	public boolean isBurnt() {
		return this.getCookedPercentage() > 100;
	}
	
	@Override
	public String toString() {
		return StringUtil.formatNamePercentage(super.getName(), this.getCookedPercentage());
	}

	public int getCookedPercentage() {
		return this.cookedPercentage;
	}

	public void setCookedPercentage(int cookedPercentage) {
		this.cookedPercentage = cookedPercentage;
	}
}
