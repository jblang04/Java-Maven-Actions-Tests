package org.bennettweb.rps.game;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.bennettweb.rps.hand.Hand;
import org.bennettweb.rps.hand.Hand.HandCompareResult;
import org.bennettweb.rps.player.Player;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TwoPlayerEngineTest {
	
	private TwoPlayerEngine classUnderTest;
	
	@Before
	public void setup() {
		classUnderTest = new TwoPlayerEngine();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDetermineWinnerNull() {
		classUnderTest.determineWinner(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDetermineWinnerEmptyList() {
		classUnderTest.determineWinner(new ArrayList<Player>());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDetermineWinner1Player() {
		List<Player> players = new ArrayList<Player>();
		players.add(Mockito.mock(Player.class));
		classUnderTest.determineWinner(players);
	}
	
	@Test
	public void testDetermineWinnerPlayer1Wins() {
		List<Player> players = new ArrayList<Player>();
		Player p1 = Mockito.mock(Player.class);
		Player p2 = Mockito.mock(Player.class);
		players.add(p1);
		players.add(p2);
		Hand p1Hand = Mockito.mock(Hand.class);
		Hand p2Hand = Mockito.mock(Hand.class);
		Mockito.when(p1.draw()).thenReturn(p1Hand);
		Mockito.when(p2.draw()).thenReturn(p2Hand);
		Mockito.when(p1Hand.beats(p2Hand)).thenReturn(HandCompareResult.Win);
		Player winner = classUnderTest.determineWinner(players);
		Assert.assertEquals(p1, winner);
	}
	
	@Test
	public void testDetermineWinnerPlayer2Wins() {
		List<Player> players = new ArrayList<Player>();
		Player p1 = Mockito.mock(Player.class);
		Player p2 = Mockito.mock(Player.class);
		players.add(p1);
		players.add(p2);
		Hand p1Hand = Mockito.mock(Hand.class);
		Hand p2Hand = Mockito.mock(Hand.class);
		Mockito.when(p1.draw()).thenReturn(p1Hand);
		Mockito.when(p2.draw()).thenReturn(p2Hand);
		Mockito.when(p1Hand.beats(p2Hand)).thenReturn(HandCompareResult.Lose);
		Player winner = classUnderTest.determineWinner(players);
		Assert.assertEquals(p2, winner);
	}
	
	@Test
	public void testDetermineWinnerDraw() {
		List<Player> players = new ArrayList<Player>();
		Player p1 = Mockito.mock(Player.class);
		Player p2 = Mockito.mock(Player.class);
		players.add(p1);
		players.add(p2);
		Hand p1Hand = Mockito.mock(Hand.class);
		Hand p2Hand = Mockito.mock(Hand.class);
		Mockito.when(p1.draw()).thenReturn(p1Hand);
		Mockito.when(p2.draw()).thenReturn(p2Hand);
		Mockito.when(p1Hand.beats(p2Hand)).thenReturn(HandCompareResult.Draw);
		Player winner = classUnderTest.determineWinner(players);
		Assert.assertNull(winner);
	}

}
