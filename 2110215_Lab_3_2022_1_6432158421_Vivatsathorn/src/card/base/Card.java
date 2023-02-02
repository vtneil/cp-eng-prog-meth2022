package card.base;

public abstract class Card implements Cloneable {
	private String name;
	private String flavorText;
	private int bloodCost;

	public Card(String name, String flavorText, int bloodCost) {
		this.name = name;
		this.flavorText = flavorText;
		this.bloodCost = (bloodCost < 0) ? 0 : bloodCost;
	}

	public abstract String toString();

	public String getName() {
		return name;
	}

	public int getBloodCost() {
		return bloodCost;
	}

	public String getFlavorText() {
		return flavorText;
	}

	public boolean equals(Card other) {
		return this.getName().equals(other.getName());
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}