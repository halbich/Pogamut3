package cz.cuni.amis.pogamut.ut2004.bot.navigation;

import cz.cuni.amis.pogamut.ut2004.bot.UT2004BotTest;
import org.junit.Test;

/**
 *
 * @author Peta Michalik
 */
public class UT2004Test235_CTFDEElecFields_NarrowRamp extends UT2004BotTest {

	@Override
	protected String getMapName() {
		return "CTF-DE-ElecFields";
	}

	@Override
	protected String getGameType() {
		return "BotDeathMatch";
	}

        @Test
	public void test235_narrow_1_time() {
		startTest(
			// use NavigationTestBot for the test
			NavigationTestBot.class,
			// timeout: 1 minute
			1,
			// test movement between        start: CTF-DE-ElecFields.PathNode53, end: CTF-DE-ElecFields.PathNode169 number of repetitions   both ways
			new NavigationTestBotParameters("CTF-DE-ElecFields.PathNode53",      "CTF-DE-ElecFields.PathNode169",    1,                        true)
		);
	}

        /*
        * TODO: Test fails
        */
        @Test
	public void test235_narrow_20_time() {


		startTest(
			// use NavigationTestBot for the test
			NavigationTestBot.class,
			// timeout: 5 minutes
			5,
			// test movement between        start: CTF-DE-ElecFields.PathNode53, end: CTF-DE-ElecFields.PathNode169 number of repetitions   both ways
			new NavigationTestBotParameters("CTF-DE-ElecFields.PathNode53",      "CTF-DE-ElecFields.PathNode169",    20,                        true)
		);
	}

}