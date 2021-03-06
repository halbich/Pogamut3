package cz.cuni.amis.pogamut.ut2004.bot.navigation;

import cz.cuni.amis.pogamut.ut2004.bot.UT2004BotTest;
import org.junit.Test;

/**
 *
 * @author Peta Michalik
 */
public class UT2004Test043_DMPlunge_Jumpdown extends UT2004BotTest {

	@Override
	protected String getMapName() {
		return "DM-Plunge";
	}

	@Override
	protected String getGameType() {
		return "BotDeathMatch";
	}

        @Test
	public void test43_jumpdown_1_time() {
		startTest(
			// use NavigationTestBot for the test
			NavigationTestBot.class,
			// timeout: 1 minute
			1,
			// test movement between        start: DM-Plunge.PathNode278, end: DM-Plunge.PathNode161 number of repetitions   both ways
			new NavigationTestBotParameters("DM-Plunge.PathNode278",      "DM-Plunge.PathNode161",    1,                        false)
		);
	}

        @Test
	public void test43_jumpdown_20_time() {


		startTest(
			// use NavigationTestBot for the test
			NavigationTestBot.class,
			// timeout: 10 minutes
			10,
			// test movement between        start: DM-Plunge.PathNode278, end: DM-Plunge.PathNode161 number of repetitions   both ways
			new NavigationTestBotParameters("DM-Plunge.PathNode278",      "DM-Plunge.PathNode161",    20,                        false)
		);
	}

}