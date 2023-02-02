package test.student;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import card.base.UnitCard;
import card.type.LeaderUnitCard;

public class TestLeaderUnitCard {
	LeaderUnitCard leaderUnit1;
	UnitCard leaderUnit2;
	LeaderUnitCard leaderUnit3;
	LeaderUnitCard oppoUnit1;
	LeaderUnitCard nUnit1;
	LeaderUnitCard nUnit2;
	LeaderUnitCard nUnit3;
	UnitCard[] unitList;

	@BeforeEach
	void setUp() {
		leaderUnit1 = new LeaderUnitCard("Leader", "He's a Leader", 2, 3, 2, 3, 2);
		leaderUnit2 = new LeaderUnitCard("Boss", "Worse version of Leader", 4, 1, 4, 1, 2);
		leaderUnit3 = new LeaderUnitCard("Leader", "He's a Leader", -1, -3, -2, -3, -2);
		oppoUnit1 = new LeaderUnitCard("Opponent", "I'm an opponent", 0, 1, 5, 0, 0);
		nUnit1 = new LeaderUnitCard("Unit1", "1", 0, 1, 2, 0, 0);
		nUnit2 = new LeaderUnitCard("Unit2", "2", 0, 3, 1, 0, 0);
		nUnit3 = new LeaderUnitCard("Unit3", "3", 0, 2, 4, 0, 0);
		unitList = new UnitCard[] { nUnit1, nUnit2, nUnit3 };
	}

	@Test
	void testConstructor() {

		assertEquals("Leader", leaderUnit1.getName());
		assertEquals("He's a Leader", leaderUnit1.getFlavorText());
		assertEquals(2, leaderUnit1.getBloodCost());
		assertEquals(3, leaderUnit1.getPower());
		assertEquals(2, leaderUnit1.getHealth());
		assertEquals(3, leaderUnit1.getBuffPower());
		assertEquals(2, leaderUnit1.getBuffHealth());

	}

	@Test
	void testConstructorNegativeValue() {

		assertEquals("Leader", leaderUnit3.getName());
		assertEquals("He's a Leader", leaderUnit3.getFlavorText());
		assertEquals(0, leaderUnit3.getBloodCost());
		assertEquals(0, leaderUnit3.getPower());
		assertEquals(0, leaderUnit3.getHealth());
		assertEquals(0, leaderUnit3.getBuffPower());
		assertEquals(0, leaderUnit3.getBuffHealth());

	}

	@Test
	void testSetBuffPower() {
		leaderUnit1.setBuffPower(5);
		assertEquals(5, leaderUnit1.getBuffPower());
		leaderUnit1.setBuffPower(-1);
		assertEquals(0, leaderUnit1.getBuffPower());

	}

	@Test
	void testSetBuffHealth() {
		leaderUnit1.setBuffHealth(9999);
		assertEquals(9999, leaderUnit1.getBuffHealth());
		leaderUnit1.setBuffHealth(-9999);
		assertEquals(0, leaderUnit1.getBuffHealth());
	}

	@Test
	void testAttack() {
		leaderUnit1.attackUnit(oppoUnit1);
		assertEquals(2, oppoUnit1.getHealth());
		leaderUnit2.attackUnit(oppoUnit1);
		assertEquals(1, oppoUnit1.getHealth());
		leaderUnit3.attackUnit(oppoUnit1);
		assertEquals(1, oppoUnit1.getHealth());
		leaderUnit1.attackUnit(oppoUnit1);
		assertEquals(0, oppoUnit1.getHealth());
	}

	@Test
	void testBuffUnit() {
		UnitCard[] unitListTest = new UnitCard[unitList.length];
		for (int i = 0; i < unitList.length; i++)
			try {
				unitListTest[i] = (UnitCard) unitList[i].clone();
			} catch (CloneNotSupportedException e) {
			}

		leaderUnit1.buffUnit(unitList);
		for (int i = 0; i < unitList.length; i++) {
			assertEquals(unitListTest[i].getHealth() + leaderUnit1.getBuffHealth(), unitList[i].getHealth());
			assertEquals(unitListTest[i].getPower() + leaderUnit1.getBuffPower(), unitList[i].getPower());
		}
		leaderUnit3.buffUnit(unitList);
		for (int i = 0; i < unitList.length; i++) {
			assertEquals(unitListTest[i].getHealth() + leaderUnit1.getBuffHealth() + leaderUnit3.getBuffHealth(),
					unitList[i].getHealth());
			assertEquals(unitListTest[i].getPower() + leaderUnit1.getBuffPower() + leaderUnit3.getBuffPower(),
					unitList[i].getPower());
		}
	}
}
