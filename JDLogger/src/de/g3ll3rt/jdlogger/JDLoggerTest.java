package de.g3ll3rt.jdlogger;

public class JDLoggerTest {
	private final String version = "1.3";
	public static void main(String[] args) {
		JDLogger logger = new JDLogger();
		System.out.println("JDLogger V" + logger.getVersion());
		// logger.executePollSeries(300, 60);
		logger.executeTestPoll();
	}	

}
