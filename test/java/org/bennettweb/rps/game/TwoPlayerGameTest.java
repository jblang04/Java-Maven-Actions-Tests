package org.bennettweb.rps.game;

import junit.framework.Assert;

import org.bennettweb.rps.player.Player;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TwoPlayerGameTest {

	private TwoPlayerGame classUnderTest;

	@Before
	public void setup() {
		classUnderTest = new TwoPlayerGame();
	}

	@Test
	public void testAddOnePlayer() throws Exception {
		Player player = Mockito.mock(Player.class);
		classUnderTest.addPlayer(player);
		Assert.assertEquals(1, classUnderTest.players.size());
		Assert.assertTrue(classUnderTest.players.contains(player));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddNullPlayer() throws Exception {
		classUnderTest.addPlayer(null);
	}

	@Test
	public void testAddTwoPlayers() throws Exception {
		Player player1 = Mockito.mock(Player.class);
		Player player2 = Mockito.mock(Player.class);
		classUnderTest.addPlayer(player1);
		classUnderTest.addPlayer(player2);
		Assert.assertEquals(2, classUnderTest.players.size());
		Assert.assertTrue(classUnderTest.players.contains(player1));
		Assert.assertTrue(classUnderTest.players.contains(player2));
	}

	@Test
	public void testAddThreePlayers() throws Exception {
		Player player1 = Mockito.mock(Player.class);
		Player player2 = Mockito.mock(Player.class);
		Player player3 = Mockito.mock(Player.class);
		classUnderTest.addPlayer(player1);
		classUnderTest.addPlayer(player2);
		try {
			classUnderTest.addPlayer(player3);
			Assert.fail("Managed to add too many players to the game");
		} catch (GameException e) {
			// test passed
		}
	}

	@Test
	public void testAddSamePlayerTwice() throws Exception {
		Player player = Mockito.mock(Player.class);
		classUnderTest.addPlayer(player);
		classUnderTest.addPlayer(player);
		Assert.assertEquals(1, classUnderTest.players.size());
		Assert.assertTrue(classUnderTest.players.contains(player));
	}

	@Test
	public void testSetNumberOfRound() {
		classUnderTest.setNumberOfRound(1);
		Assert.assertEquals(1, classUnderTest.numberOfRounds);
	}

	@Test
	public void testSetNumberOfRoundEven() {
		classUnderTest.setNumberOfRound(4);
		Assert.assertEquals(4, classUnderTest.numberOfRounds);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetNumberOfRoundZero() {
		classUnderTest.setNumberOfRound(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetNumberOfRoundNegative() {
		classUnderTest.setNumberOfRound(-1);
	}

	@Test
	public void testPlay() throws Exception {
		// initialise
		Player player1 = Mockito.mock(Player.class);
		Player player2 = Mockito.mock(Player.class);
		
		TwoPlayerEngine engine = Mockito.mock(TwoPlayerEngine.class);
		ResultReporter reporter = Mockito.mock(ResultReporter.class);
		
		int numberOfRounds = 3;
		classUnderTest.players.add(player1);
		classUnderTest.players.add(player2);
		classUnderTest.numberOfRounds = numberOfRounds;
		classUnderTest.engine = engine;
		classUnderTest.resultReporter = reporter;

		Mockito.when(engine.determineWinner(classUnderTest.players)).thenReturn(player1);
		
		// play
		classUnderTest.play();

		// check that the game played correctly.
		Mockito.verify(player1).initialise();
		Mockito.verify(player2).initialise();
		Mockito.verify(player1, Mockito.times(3)).choose();
		Mockito.verify(player2, Mockito.times(3)).choose();
		for (int i=1; i<=numberOfRounds; i++) {
			Mockito.verify(reporter).report(i, player1);
		}
		Mockito.verify(reporter, Mockito.times(3)).reportChoices(classUnderTest.players);
		Mockito.verify(reporter).summarize();
		
	}

	@Test(expected = GameException.class)
	public void testPlayNoPlayers() throws Exception {
		classUnderTest.players.clear();
		classUnderTest.play();
	}

	@Test(expected = GameException.class)
	public void testPlayOnePlayers() throws Exception {
		Player player = Mockito.mock(Player.class);
		classUnderTest.players.add(player);
		classUnderTest.play();
	}

}
