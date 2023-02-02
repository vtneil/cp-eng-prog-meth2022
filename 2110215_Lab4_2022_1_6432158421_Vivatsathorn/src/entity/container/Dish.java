package entity.container;

import entity.base.Container;
import entity.base.Ingredient;
import logic.StringUtil;

public class Dish extends Container {
	private int dirty;

	public Dish() {
		this(0);
	}

	public Dish(int dirty) {
		super("Dish", 4);
		this.setDirty(dirty);
	}
	
	public void clean(int amount) {
		this.setDirty(this.getDirty() - amount);
	}

	@Override
	public boolean verifyContent(Ingredient i) {
		return !this.isDirty() && i.isEdible();
	}
	
	@Override
	public String toString() {
		if (this.isDirty())
			return StringUtil.formatNamePercentage(super.getName(), this.getDirty());
		else
			return super.toString();
	}

	public boolean isDirty() {
		return this.getDirty() > 0;
	}

	public int getDirty() {
		return dirty;
	}

	public void setDirty(int dirty) {
		this.dirty = (dirty > 0) ? dirty : 0;
		if (this.isDirty())
			super.setName("Dirty Dish");
		else
			super.setName("Dish");
	}
}
