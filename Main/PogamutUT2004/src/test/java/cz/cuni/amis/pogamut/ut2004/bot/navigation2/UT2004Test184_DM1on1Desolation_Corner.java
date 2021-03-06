package cz.cuni.amis.pogamut.ut2004.bot.navigation2;

import cz.cuni.amis.pogamut.ut2004.bot.UT2004BotTest;
import org.junit.Test;

/**
 *
 * @author Peta Michalik
 */
public class UT2004Test184_DM1on1Desolation_Corner extends UT2004BotTest {

	@Override
	protected String getMapName() {
		return "DM-1on1-Desolation";
	}

	@Override
	protected String getGameType() {
		return "BotDeathMatch";
	}

        @Test
	public void test184_corner_1_time() {
		startTest(
			// use NavigationTestBot for the test
			Navigation2TestBot.class,
			// timeout: 1 minute
			1,
			// test movement between        start: DM-1on1-Desolation.PlayerStart8, end: DM-1on1-Desolation.PathNode17 number of repetitions   both ways
			new Navigation2TestBotParameters("DM-1on1-Desolation.PlayerStart8",      "DM-1on1-Desolation.PathNode17",    1,                        true)
		);
	}

        @Test
	public void test184_corner_20_time() {


		startTest(
			// use NavigationTestBot for the test
			Navigation2TestBot.class,
			// timeout: 4 minutes
			4,
			// test movement between        start: DM-1on1-Desolation.PlayerStart8, end: DM-1on1-Desolation.PathNode17 number of repetitions   both ways
			new Navigation2TestBotParameters("DM-1on1-Desolation.PlayerStart8",      "DM-1on1-Desolation.PathNode17",    20,                        true)
		);
	}

}
