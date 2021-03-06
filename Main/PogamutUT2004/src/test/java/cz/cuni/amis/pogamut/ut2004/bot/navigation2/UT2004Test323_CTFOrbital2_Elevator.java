package cz.cuni.amis.pogamut.ut2004.bot.navigation2;

import cz.cuni.amis.pogamut.ut2004.bot.UT2004BotTest;
import org.junit.Test;

/**
 *
 * @author Peta Michalik
 */
public class UT2004Test323_CTFOrbital2_Elevator extends UT2004BotTest {

	@Override
	protected String getMapName() {
		return "CTF-Orbital2";
	}

	@Override
	protected String getGameType() {
		return "BotDeathMatch";
	}

        @Test
	public void test323_elevator_1_time() {
		startTest(
			// use NavigationTestBot for the test
			Navigation2TestBot.class,
			// timeout: 1 minute
			1,
			// test movement between        start: CTF-Orbital2.AssaultPath3, end: CTF-Orbital2.LiftExit16 number of repetitions   both ways
			new Navigation2TestBotParameters("CTF-Orbital2.AssaultPath3",      "CTF-Orbital2.LiftExit16",    1,                        true)
		);
	}

        /*
         * TODO: Test fails
         */
        @Test
	public void test323_elevator_20_time() {


		startTest(
			// use NavigationTestBot for the test
			Navigation2TestBot.class,
			// timeout: 3 minutes
			3,
			// test movement between        start: CTF-Orbital2.AssaultPath3, end: CTF-Orbital2.LiftExit16 number of repetitions   both ways
			new Navigation2TestBotParameters("CTF-Orbital2.AssaultPath3",      "CTF-Orbital2.LiftExit16",    20,                        true)
		);
	}

}
