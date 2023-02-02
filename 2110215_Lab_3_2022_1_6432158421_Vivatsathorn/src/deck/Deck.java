package deck;

import card.base.Card;

public class Deck {
	private String name;
	private int deckSize;
	private Card[] deckList;

	public Deck(String name, Card[] deckList) {
		this.setName(name);
		this.setDeckList(deckList);
		this.setDeckSize(deckList.length);
	}

	public int insertCard(Card card) throws InsertCardFailedException {
		int count = 0;
		for (int i = 0; i < deckSize; i++) {
			Card eCard = deckList[i];
			if (eCard != null && eCard.equals(card))
				count++;
		}

		if (count >= 4)
			throw new InsertCardFailedException("You can only put 4 of the same cards into the deck");
		
		Card[] newDeckList = new Card[deckSize + 1];
		for(int i = 0; i < deckSize; i++)
			newDeckList[i] = deckList[i];
		deckSize += 1;
		newDeckList[deckSize - 1] = card;
		deckList = newDeckList;
		return deckSize;
	}

	public Card removeCard(int slotNumber) throws RemoveCardFailedException {
		if (this.deckList.length <= slotNumber)
			throw new RemoveCardFailedException("Number you insert exceed deck size");
		if (this.deckList[slotNumber] == null)
			throw new RemoveCardFailedException("There is no card in that slot");
		
		Card[] newDeckList = new Card[deckSize - 1];
		Card removedCard = null;
		for (int i = 0, j = 0; i < deckSize; i++, j++) {
			if (i == slotNumber) {
				removedCard = deckList[i];
				j--;
				continue;
			}
			newDeckList[j] = deckList[i];
		}
		deckSize--;
		deckList = newDeckList;
		return removedCard;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("{").append(this.getName()).append("}").append("(").append(this.getDeckSize())
				.append(" deck size)").toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDeckSize() {
		return deckSize;
	}

	public void setDeckSize(int deckSize) {
		this.deckSize = deckSize;
	}

	public Card[] getDeckList() {
		return deckList;
	}

	public void setDeckList(Card[] deckList) {
		this.deckList = deckList;
	}
	
	
}
