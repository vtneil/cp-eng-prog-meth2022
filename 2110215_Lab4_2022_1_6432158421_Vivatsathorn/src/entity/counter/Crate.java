package entity.counter;

import logic.Player;
import logic.InvalidIngredientException;
import logic.LogicUtil;

public class Crate extends Counter {
	private String myIngredient;
	
	public Crate(String s) {
		super(s + " Crate");
		this.setMyIngredient(s);
	}
	@Override
	public void interact(Player p) {
		if (!p.isHandEmpty() || !super.isPlacedContentEmpty()) {
			super.interact(p);
			return;
		}
		try {
			p.setHoldingItem(LogicUtil.createIngredientFromName(this.getMyIngredient()));
		} catch (InvalidIngredientException e){
			p.setHoldingItem(null);
		}
		
	}

	public String getMyIngredient() {
		return myIngredient;
	}

	public void setMyIngredient(String myIngredient) {
		this.myIngredient = myIngredient;
	}
}
