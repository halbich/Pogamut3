package cz.cuni.amis.pogamut.ut2004.bot.navigation2;

import cz.cuni.amis.pogamut.ut2004.bot.UT2004BotTest;
import org.junit.Test;

/**
 *
 * @author Peta Michalik
 */
public class UT2004Test237_CTFDecember_CarryFlag extends UT2004BotTest {

	@Override
	protected String getMapName() {
		return "CTF-December";
	}

	@Override
	protected String getGameType() {
		return "BotDeathMatch";
	}

        @Test
	public void test237_flag_1_time() {
		startTest(
			// use NavigationTestBot for the test
			Navigation2TestBot.class,
			// timeout: 1 minute
			1,
			// test movement between        start: CTF-December.xRedFlagBase1, end: CTF-December.xBlueFlagBase0 number of repetitions   both ways
			new Navigation2TestBotParameters("CTF-December.xRedFlagBase1",      "CTF-December.xBlueFlagBase0",    1,                        false)
		);
	}

        /*
        * TODO: Test fails
        */
        @Test
	public void test237_flag_20_time() {


		startTest(
			// use NavigationTestBot for the test
			Navigation2TestBot.class,
			// timeout: 25 minutes
			25,
			// test movement between        start: CTF-December.xRedFlagBase1, end: CTF-December.xBlueFlagBase0 number of repetitions   both ways
			new Navigation2TestBotParameters("CTF-December.xRedFlagBase1",      "CTF-December.xBlueFlagBase0",    20,                        false)
		);
	}

}
