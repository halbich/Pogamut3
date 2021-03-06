package cz.cuni.amis.pogamut.ut2004.bot.navigation2;

import cz.cuni.amis.pogamut.ut2004.bot.UT2004BotTest;
import org.junit.Test;

/**
 *
 * @author Peta Michalik
 */
public class UT2004Test198_CTFAbsoluteZero_Jump extends UT2004BotTest {

	@Override
	protected String getMapName() {
		return "CTF-AbsoluteZero";
	}

	@Override
	protected String getGameType() {
		return "BotDeathMatch";
	}

        @Test
	public void test198_jump_1_time() {
		startTest(
			// use NavigationTestBot for the test
			Navigation2TestBot.class,
			// timeout: 1 minute
			1,
			// test movement between        start: CTF-AbsoluteZero.JumpSpot13, end: CTF-AbsoluteZero.JumpSpot14 number of repetitions   both ways
			new Navigation2TestBotParameters("CTF-AbsoluteZero.JumpSpot13",      "CTF-AbsoluteZero.JumpSpot14",    1,                        false)
		);
	}

        /*
        * TODO: Test fails
        */
        @Test
	public void test198_jump_20_time() {


		startTest(
			// use NavigationTestBot for the test
			Navigation2TestBot.class,
			// timeout: 5 minutes
			5,
			// test movement between        start: CTF-AbsoluteZero.JumpSpot13, end: CTF-AbsoluteZero.JumpSpot14 number of repetitions   both ways
			new Navigation2TestBotParameters("CTF-AbsoluteZero.JumpSpot13",      "CTF-AbsoluteZero.JumpSpot14",    20,                        false)
		);
	}

}
