package org.bennettweb.rps.hand;

import junit.framework.Assert;

import org.bennettweb.rps.hand.Hand.HandCompareResult;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link PaperHand}
 * 
 * @author Steve
 */
public class PaperHandTest {
	
	private PaperHand classUnderTest;
	
	@Before
	public void setup() {
		classUnderTest = new PaperHand();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testBeatsNull() {
		classUnderTest.beats(null);
	}

	@Test
	public void testBeatsRock() {
		RockHand rock = new RockHand();
		Assert.assertEquals(HandCompareResult.Win, classUnderTest.beats(rock));
	}
	
	@Test
	public void testBeatsPaper() {
		PaperHand paper = new PaperHand();
		Assert.assertEquals(HandCompareResult.Draw, classUnderTest.beats(paper));
	}
	
	@Test
	public void testBeatsScissors() {
		ScissorsHand scissors = new ScissorsHand();
		Assert.assertEquals(HandCompareResult.Lose, classUnderTest.beats(scissors));
	}

}
