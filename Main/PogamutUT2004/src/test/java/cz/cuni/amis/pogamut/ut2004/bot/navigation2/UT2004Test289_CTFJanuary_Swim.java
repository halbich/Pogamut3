package cz.cuni.amis.pogamut.ut2004.bot.navigation2;

import cz.cuni.amis.pogamut.ut2004.bot.UT2004BotTest;
import org.junit.Test;

/**
 *
 * @author Peta Michalik
 */
public class UT2004Test289_CTFJanuary_Swim extends UT2004BotTest {

	@Override
	protected String getMapName() {
		return "CTF-January";
	}

	@Override
	protected String getGameType() {
		return "BotDeathMatch";
	}

        @Test
	public void test289_swim_1_time() {
		startTest(
			// use NavigationTestBot for the test
			Navigation2TestBot.class,
			// timeout: 1 minute
			1,
			// test movement between        start: CTF-January.PathNode128, end: CTF-January.PathNode154 number of repetitions   both ways
			new Navigation2TestBotParameters("CTF-January.PathNode128",      "CTF-January.PathNode154",    1,                        true)
		);
	}

        /*
         * TODO: Test fails
         */
        @Test
	public void test289_swim_20_time() {


		startTest(
			// use NavigationTestBot for the test
			Navigation2TestBot.class,
			// timeout: 10 minutes
			10,
			// test movement between        start: CTF-January.PathNode128, end: CTF-January.PathNode154 number of repetitions   both ways
			new Navigation2TestBotParameters("CTF-January.PathNode128",      "CTF-January.PathNode154",    20,                        true)
		);
	}

}
