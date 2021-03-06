package cz.cuni.amis.pogamut.ut2004.bot.navigation2;

import cz.cuni.amis.pogamut.ut2004.bot.UT2004BotTest;
import org.junit.Test;

/**
 *
 * @author Peta Michalik
 */
public class UT2004Test019_DMSulphur_Narrow extends UT2004BotTest {

	@Override
	protected String getMapName() {
		return "DM-Sulphur";
	}

	@Override
	protected String getGameType() {
		return "BotDeathMatch";
	}

        @Test
	public void test19_narrow_1_time() {
		startTest(
			// use NavigationTestBot for the test
			Navigation2TestBot.class,
			// timeout: 1 minute
			1,
			// test movement between        start: DM-Sulphur.PathNode51, end: DM-Sulphur.PathNode43 number of repetitions   both ways
			new Navigation2TestBotParameters("DM-Sulphur.PathNode51",      "DM-Sulphur.PathNode43",    1,                        true)
		);
	}

        /**
        * TODO: Test fails
        */
        @Test
	public void test19_narrow_20_time() {
		startTest(
			// use NavigationTestBot for the test
			Navigation2TestBot.class,
			// timeout: 5 minutes
			5,
			// test movement between        start: DM-Sulphur.PathNode51, end: DM-Sulphur.PathNode43 number of repetitions   both ways
			new Navigation2TestBotParameters("DM-Sulphur.PathNode51",      "DM-Sulphur.PathNode43",    20,                        true)
		);
	}

}
