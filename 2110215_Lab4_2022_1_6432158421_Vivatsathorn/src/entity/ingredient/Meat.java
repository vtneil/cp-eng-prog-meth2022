package entity.ingredient;

import entity.base.Ingredient;
import entity.base.Choppable;
import entity.base.Cookable;
import logic.StringUtil;

public class Meat extends Ingredient implements Choppable, Cookable{
	private boolean chopState;
	private int cookedPercentage;
	
	public Meat() {
		super("Meat");
		this.chopState = false;
		this.cookedPercentage = 0;
	}

	@Override
	public void cook() {
		if (!this.isChopped()) {
			this.setCookedPercentage(this.getCookedPercentage() + 10);
			if (this.getCookedPercentage() <= 50) {
				super.setName("Raw Meat");
				super.setEdible(false);
			} else if (this.getCookedPercentage() <= 80) {
				super.setName("Medium Rare Steak");
				super.setEdible(true);
			} else if (this.getCookedPercentage() <= 100) {
				super.setName("Well Done Steak");
				super.setEdible(true);
			} else {
				super.setName("Burnt Steak");
				super.setEdible(false);
			}
		} else {
			this.setCookedPercentage(this.getCookedPercentage() + 15);
			if (this.getCookedPercentage() <= 80) {
				super.setName("Raw Burger");
				super.setEdible(false);
			} else if (this.getCookedPercentage() <= 100) {
				super.setName("Cooked Burger");
				super.setEdible(true);
			} else {
				super.setName("Burnt Burger");
				super.setEdible(false);
			}
		}
		
	}

	@Override
	public boolean isBurnt() {
		return this.getCookedPercentage() > 100;
	}

	@Override
	public void chop() {
		if (!this.isChopped() && this.getCookedPercentage() == 0) {
			this.chopState = true;
			super.setName("Minced Meat");
		}
		return;
	}

	@Override
	public boolean isChopped() {
		return this.chopState;
	}
	
	@Override
	public String toString() {
		return StringUtil.formatNamePercentage(super.getName(), this.getCookedPercentage());
	}

	public int getCookedPercentage() {
		return cookedPercentage;
	}

	public void setCookedPercentage(int cookedPercentage) {
		this.cookedPercentage = cookedPercentage;
	}
}
