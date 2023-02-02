package test.student;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import card.base.SpellCard;
import card.type.DamageSpellCard;
import card.type.NormalUnitCard;

public class TestDamageSpellCard {
	NormalUnitCard unit;

	SpellCard spell0 = new DamageSpellCard("DSpell0", "-1", 1, true, 1);
	DamageSpellCard spell1 = new DamageSpellCard("DSpell1", "-1", 1, true, 1);
	DamageSpellCard spell2 = new DamageSpellCard("DSpell2", "-2", 1, true, 2);
	DamageSpellCard spell3 = new DamageSpellCard("DSpell3", "-3", 2, false, 3);
	DamageSpellCard spellN = new DamageSpellCard("DSpellN", "XX", -2, false, -1);

	@BeforeEach
	public void setUp() {
		unit = new NormalUnitCard("Tanky Unit", "I'm a tanky unit", 0, 2, 10);
	}

	@Test
	void testConstructor() {
		assertEquals("DSpell1", spell1.getName());
		assertEquals("-1", spell1.getFlavorText());
		assertEquals(1, spell1.getBloodCost());
		assertEquals(true, spell1.isBurstSpeed());
		assertEquals(1, spell1.getDamage());
		
		assertEquals("DSpellN", spellN.getName());
		assertEquals("XX", spellN.getFlavorText());
		assertEquals(0, spellN.getBloodCost());
		assertEquals(false, spellN.isBurstSpeed());
		assertEquals(1, spellN.getDamage());
	}

	@Test
	void testSetDamage() {
		spell1.setDamage(88);
		assertEquals(88, spell1.getDamage());
		spell1.setDamage(1);
		assertEquals(1, spell1.getDamage());
		spell1.setDamage(0);
		assertEquals(1, spell1.getDamage());
		spell1.setDamage(-9999);
		assertEquals(1, spell1.getDamage());
	}

	@Test
	void testCastSpell() {
		assertEquals(10, unit.getHealth());
		spell1.castSpell(unit);
		assertEquals(9, unit.getHealth());
		spell2.castSpell(unit);
		assertEquals(7, unit.getHealth());
		spell3.castSpell(unit);
		assertEquals(4, unit.getHealth());
	}

}
