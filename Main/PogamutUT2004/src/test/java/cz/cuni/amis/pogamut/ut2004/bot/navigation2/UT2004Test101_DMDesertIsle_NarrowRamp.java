package cz.cuni.amis.pogamut.ut2004.bot.navigation2;

import cz.cuni.amis.pogamut.ut2004.bot.UT2004BotTest;
import org.junit.Test;

/**
 *
 * @author Peta Michalik
 */
public class UT2004Test101_DMDesertIsle_NarrowRamp extends UT2004BotTest {

	@Override
	protected String getMapName() {
		return "DM-DesertIsle";
	}

	@Override
	protected String getGameType() {
		return "BotDeathMatch";
	}

        @Test
	public void test101_narrow_1_time() {
		startTest(
			// use NavigationTestBot for the test
			Navigation2TestBot.class,
			// timeout: 1 minute
			1,
			// test movement between        start: DM-DesertIsle.PathNode106, end: DM-DesertIsle.PathNode102 number of repetitions   both ways
			new Navigation2TestBotParameters("DM-DesertIsle.PathNode106",      "DM-DesertIsle.PathNode102",    1,                        true)
		);
	}

        @Test
	public void test101_narrow_20_time() {


		startTest(
			// use NavigationTestBot for the test
			Navigation2TestBot.class,
			// timeout: 12 minutes
			12,
			// test movement between        start: DM-DesertIsle.PathNode106, end: DM-DesertIsle.PathNode102 number of repetitions   both ways
			new Navigation2TestBotParameters("DM-DesertIsle.PathNode106",      "DM-DesertIsle.PathNode102",    20,                        true)
		);
	}

}
