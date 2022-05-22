package org.bennettweb.rps.player;

import junit.framework.Assert;

import org.bennettweb.rps.hand.Hand;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class ComputerPlayerTest {
	
	private ComputerPlayer classUnderTest;

	@Before
	public void setup() {
		classUnderTest = new ComputerPlayer("Comp1");
	}

	@Test
	public void testChoose() {
		classUnderTest.choose();
		Assert.assertNotNull(classUnderTest.chosenHand);
	}

	@Test
	public void testDraw() {
		Hand hand = Mockito.mock(Hand.class);
		classUnderTest.chosenHand = hand;
		Assert.assertEquals(hand, classUnderTest.draw());		
	}

}
