package org.nachc;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.yaorma.util.time.Timer;

import com.googlecode.junittoolbox.SuiteClasses;
import com.googlecode.junittoolbox.WildcardPatternSuite;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(WildcardPatternSuite.class)

@SuiteClasses({ "**/*IntegrationTest.class" })
public class RunAllIntegrationTests {

	private static Timer TIMER = new Timer();

	@BeforeClass
	public static void setup() {
		TIMER.start();
	}

	@AfterClass
	public static void cleanup() {
		try {
			TIMER.stop();
			log.info("Start:   " + TIMER.getStartAsString());
			log.info("Stop:    " + TIMER.getStopAsString());
			log.info("Elapsed: " + TIMER.getElapsedString());
			log.info("");
		} finally {
			log.info("Doing finally part...");
		}
		log.info("Done.");
	}

	public static void exec() {
		JUnitCore junit = new JUnitCore();
		Result result = junit.run(RunAllIntegrationTests.class);
		System.out.println("Finished. Result: Failures: " +
				result.getFailureCount() + ". Ignored: " +
				result.getIgnoreCount() + ". Tests run: " +
				result.getRunCount() + ". Time: " +
				result.getRunTime() + "ms.");
	}

}
