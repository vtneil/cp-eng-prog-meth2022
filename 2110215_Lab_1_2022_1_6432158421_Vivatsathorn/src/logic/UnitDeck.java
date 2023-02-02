package logic;

import java.util.ArrayList;
import java.util.Objects;

public class UnitDeck {
	private ArrayList<CardCounter> cardsInDeck;
	private String deckName;

	public UnitDeck(String deckName) {
		super();
		cardsInDeck = new ArrayList<CardCounter>();
		setDeckName(deckName);
		
	}

	public ArrayList<CardCounter> getCardsInDeck() {
		return cardsInDeck;
	}

	public void setCardsInDeck(ArrayList<CardCounter> cardsInDeck) {
		this.cardsInDeck = cardsInDeck;
	}

	public String getDeckName() {
		return deckName;
	}

	public void setDeckName(String deckName) {
		this.deckName = (deckName.isBlank())? "Untitled Deck" : deckName;
	}

	public void addCard(UnitCard newCard, int count) {
		if (count <= 0)
			return;
		
		if (cardsInDeck.size() == 0) {
			CardCounter newCardCounter = new CardCounter(newCard, count);
			cardsInDeck.add(newCardCounter);
			return;
		}

		int idx = this.getIndexOfCard(newCard);

		if (idx != -1) {
			cardsInDeck.get(idx).setCount(cardsInDeck.get(idx).getCount() + count);
			return;
		}

		CardCounter newCardCounter = new CardCounter(newCard, count);
		cardsInDeck.add(newCardCounter);
	}

	public void removeCard(UnitCard toRemove, int count) {
		if (count <= 0)
			return;

		int idx = this.getIndexOfCard(toRemove);

		if (idx != -1) {
			int cardCountCheck = cardsInDeck.get(idx).getCount() - count;
			if (cardCountCheck > 0) {
				cardsInDeck.get(idx).setCount(cardCountCheck);
				return;
			}
			cardsInDeck.remove(idx);
		}
	}

	public int cardCount() {
		int counter = 0;
		for (CardCounter tempCardCounter : cardsInDeck) {
			counter += tempCardCounter.getCount();
		}

		return counter;
	}

	public boolean existsInDeck(UnitCard card) {
		for (CardCounter tempCardCounter : cardsInDeck) {
			if (tempCardCounter.getCard().equals(card)) {
				if (tempCardCounter.getCount() > 0)
					return true;
				else
					return false;
			}
		}

		return false;
	}

	private int getIndexOfCard(UnitCard card) {
		int deckSize = cardsInDeck.size();
		int idx = 0;

		for (; idx < deckSize; idx++) {
			if (cardsInDeck.get(idx).getCard().equals(card)) {
				return idx;
			}
		}

		return -1;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cardsInDeck, deckName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UnitDeck other = (UnitDeck) obj;
		return Objects.equals(deckName, other.deckName);
	}
	
	
}
