package org.bennettweb.rps.player;

import java.io.IOException;

import junit.framework.Assert;

import org.bennettweb.rps.hand.Hand;
import org.bennettweb.rps.hand.HandChoiceException;
import org.bennettweb.rps.hand.HandFactory;
import org.bennettweb.rps.hand.RockHand;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class CommandLineHumanPlayerTest {
	
	private CommandLineHumanPlayer classUnderTest;
	
	@Before
	public void setup() {
		classUnderTest = new CommandLineHumanPlayer();
	}

	@Test
	public void testChoose() throws PlayerException, IOException, HandChoiceException {
		CommandLineIO cio = Mockito.mock(CommandLineIO.class);
		HandFactory handFactory = Mockito.mock(HandFactory.class);
		RockHand rockHand = Mockito.mock(RockHand.class);
		
		classUnderTest.commandLineIO = cio;
		classUnderTest.handFactory = handFactory;
		
		String choice = "rock";
		Mockito.when(cio.readLine()).thenReturn(choice);
		Mockito.when(handFactory.createHand(choice)).thenReturn(rockHand);
		
		classUnderTest.choose();
		Assert.assertNotNull(classUnderTest.chosenHand);
		Assert.assertEquals(rockHand, classUnderTest.chosenHand);
	}

	@Test
	public void testDraw() {
		Hand hand = Mockito.mock(Hand.class);
		classUnderTest.chosenHand = hand;
		Assert.assertEquals(hand, classUnderTest.draw());
	}

}
