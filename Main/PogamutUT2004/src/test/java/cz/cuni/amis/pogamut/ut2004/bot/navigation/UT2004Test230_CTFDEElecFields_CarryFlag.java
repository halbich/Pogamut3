package cz.cuni.amis.pogamut.ut2004.bot.navigation;

import cz.cuni.amis.pogamut.ut2004.bot.UT2004BotTest;
import org.junit.Test;

/**
 *
 * @author Peta Michalik
 */
public class UT2004Test230_CTFDEElecFields_CarryFlag extends UT2004BotTest {

	@Override
	protected String getMapName() {
		return "CTF-DE-ElecFields";
	}

	@Override
	protected String getGameType() {
		return "BotDeathMatch";
	}

        @Test
	public void test230_flag_1_time() {
		startTest(
			// use NavigationTestBot for the test
			NavigationTestBot.class,
			// timeout: 1 minute
			1,
			// test movement between        start: CTF-DE-ElecFields.xRedFlagBase0, end: CTF-DE-ElecFields.xBlueFlagBase0 number of repetitions   both ways
			new NavigationTestBotParameters("CTF-DE-ElecFields.xRedFlagBase0",      "CTF-DE-ElecFields.xBlueFlagBase0",    1,                        false)
		);
	}

        /*
        * TODO: Test fails
        */
        @Test
	public void test230_flag_20_time() {


		startTest(
			// use NavigationTestBot for the test
			NavigationTestBot.class,
			// timeout: 20 minutes
			20,
			// test movement between        start: CTF-DE-ElecFields.xRedFlagBase0, end: CTF-DE-ElecFields.xBlueFlagBase0 number of repetitions   both ways
			new NavigationTestBotParameters("CTF-DE-ElecFields.xRedFlagBase0",      "CTF-DE-ElecFields.xBlueFlagBase0",    20,                        false)
		);
	}

}