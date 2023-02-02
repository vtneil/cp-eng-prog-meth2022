package test.student;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import card.base.UnitCard;
import card.type.DebuffUnitCard;

public class TestDebuffUnitCard {
	DebuffUnitCard debuffUnit1;
	UnitCard debuffUnit2;
	UnitCard debuffUnit3;
	DebuffUnitCard debuffUnit4;
	DebuffUnitCard oppoUnit1;

	
	@BeforeEach
	void setUp() {
		debuffUnit1 = new DebuffUnitCard("Debuff1" , "Alpha" , 0 , 2 , 1, 1);
		debuffUnit2 = new DebuffUnitCard("Debuff2" , "Delta" , 2 , 1 , 3, 2);
		debuffUnit3 = new DebuffUnitCard("Debuff3" , "Omicron" , 1 , 3 , 1, 3 );
		debuffUnit4 = new DebuffUnitCard("Debuff4" , "Omicron" , -1 , -3 , -1, -1 );
		oppoUnit1 = new DebuffUnitCard("Opponent", "I'm an opponent", 0 , 5, 5,0);
        
	}
	
	@Test
	void testConstructor() {
		
		assertEquals("Debuff1", debuffUnit1.getName());
		assertEquals("Alpha", debuffUnit1.getFlavorText());
		assertEquals(0, debuffUnit1.getBloodCost());
		assertEquals(2, debuffUnit1.getPower());
		assertEquals(1, debuffUnit1.getHealth());
		assertEquals(1, debuffUnit1.getDebuffPower());
	}
	
	@Test
	void testConstructorNegativeValue() {
		
		assertEquals("Debuff4", debuffUnit4.getName());
		assertEquals("Omicron", debuffUnit4.getFlavorText());
		assertEquals(0, debuffUnit4.getBloodCost());
		assertEquals(0, debuffUnit4.getPower());
		assertEquals(0, debuffUnit4.getHealth());
		assertEquals(0, debuffUnit4.getDebuffPower());
	}
	
	@Test
	void testSetDebuffPower() {
		debuffUnit1.setDebuffPower(-999);
		assertEquals(0, debuffUnit1.getDebuffPower());
	}
	
	@Test
	void testAttack() {
		assertEquals(2, debuffUnit1.attackUnit(oppoUnit1));
		assertEquals(3, oppoUnit1.getHealth());
		assertEquals(4, oppoUnit1.getPower());
		
		DebuffUnitCard oppoUnit2 = new DebuffUnitCard("Opponent", "I'm an opponent", 0 , 5, 5,0);
		assertEquals(1, debuffUnit2.attackUnit(oppoUnit2));
		assertEquals(4, oppoUnit2.getHealth());
		assertEquals(3, oppoUnit2.getPower());
		
		DebuffUnitCard oppoUnit3 = new DebuffUnitCard("Opponent", "I'm an opponent", 0 , 5, 5,0);
		assertEquals(3, debuffUnit3.attackUnit(oppoUnit3));
		assertEquals(2, oppoUnit3.getHealth());
		assertEquals(2, oppoUnit3.getPower());
	}

}

