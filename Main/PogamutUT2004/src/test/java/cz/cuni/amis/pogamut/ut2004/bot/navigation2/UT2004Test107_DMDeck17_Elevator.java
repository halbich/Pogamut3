package cz.cuni.amis.pogamut.ut2004.bot.navigation2;

import cz.cuni.amis.pogamut.ut2004.bot.UT2004BotTest;
import org.junit.Test;

/**
 *
 * @author Peta Michalik
 */
public class UT2004Test107_DMDeck17_Elevator extends UT2004BotTest {

	@Override
	protected String getMapName() {
		return "DM-Deck17";
	}

	@Override
	protected String getGameType() {
		return "BotDeathMatch";
	}

        @Test
	public void test107_elevator_1_time() {
		startTest(
			// use NavigationTestBot for the test
			Navigation2TestBot.class,
			// timeout: 1 minute
			1,
			// test movement between        start: DM-Deck17.InventorySpot303, end: DM-Deck17.PlayerStart35 number of repetitions   both ways
			new Navigation2TestBotParameters("DM-Deck17.InventorySpot303",      "DM-Deck17.PlayerStart35",    1,                        true)
		);
	}

        @Test
	public void test107_elevator_20_time() {


		startTest(
			// use NavigationTestBot for the test
			Navigation2TestBot.class,
			// timeout: 10 minutes
			10,
			// test movement between        start: DM-Deck17.InventorySpot303, end: DM-Deck17.PlayerStart35 number of repetitions   both ways
			new Navigation2TestBotParameters("DM-Deck17.InventorySpot303",      "DM-Deck17.PlayerStart35",    20,                        true)
		);
	}

}
