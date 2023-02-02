package logic;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class CardUtil {

	public static boolean isExistsInList(UnitCard card, ArrayList<UnitCard> list) {
		return (list.indexOf(card) != -1) ? true : false;
	}

	public static boolean isExistsInList(UnitDeck deck, ArrayList<UnitDeck> list) {
		return (list.indexOf(deck) != -1) ? true : false;
	}

	public static boolean cardExistsInDeckList(ArrayList<UnitDeck> deckList, UnitCard cardToTest) {
		for (UnitDeck tempUnitDeck : deckList) {
			if (tempUnitDeck.existsInDeck(cardToTest))
				return true;
		}
		return false;
	}

	public static ArrayList<UnitCard> getCardsFromFile(String filename) {
		File fileToRead = new File(filename);
		ArrayList<UnitCard> cardsFromFile = new ArrayList<UnitCard>();
		try {
			Scanner scanner = new Scanner(fileToRead);
			while (scanner.hasNextLine()) {
				String[] lines = scanner.nextLine().split(",");
				String name = lines[0];
				int bloodCost = Integer.parseInt(lines[1]);
				int power = Integer.parseInt(lines[2]);
				int health = Integer.parseInt(lines[3]);
				String flavorText = lines[4];
				
				UnitCard tempUnitCard = new UnitCard(name, bloodCost, power, health, flavorText);
				
				cardsFromFile.add(tempUnitCard);
			}
			
			scanner.close();
			return cardsFromFile;
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	public static void printCardList(ArrayList<UnitCard> cardList, boolean verbose) {
		for (int i = 0; i < cardList.size(); i++) {
			System.out.println(i + ") " + cardList.get(i));
			if (verbose) {
				System.out.println("Blood Cost: " + cardList.get(i).getBloodCost());
				System.out.println(cardList.get(i).getFlavorText());
				if (i < cardList.size() - 1)
					System.out.println("-----");
			}
		}
	}

	public static void printDeck(UnitDeck ud) {
		if (ud.getCardsInDeck().size() == 0) {
			System.out.println("EMPTY DECK");
		} else {
			for (CardCounter cc : ud.getCardsInDeck()) {
				System.out.println(cc);
			}
		}

		System.out.println("Total Cards: " + ud.cardCount());
	}

	public static void printDeckList(ArrayList<UnitDeck> deckList) {
		for (int i = 0; i < deckList.size(); i++) {
			System.out.println(i + ") " + deckList.get(i).getDeckName());
			printDeck(deckList.get(i));
			if (i < deckList.size() - 1)
				System.out.println("-----");
		}
	}
}
