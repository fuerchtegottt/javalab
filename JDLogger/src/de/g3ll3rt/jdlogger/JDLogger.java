package de.g3ll3rt.jdlogger;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JDLogger {
	public static void main(String[] args) {
		JDLogger logger = new JDLogger();
		logger.executePollSeries(5, 60);
	}

	public void executeSinglePoll() {
		System.out.println("... FETCHING INVERTER DATA (" + getTimestamp() +")" );
		try {
		String html = getHtmlString();
		System.out.println("... PARSING DATA");
		LogRecord rec = parseHTMLString(html, false);
		System.out.println("    >>> POWER: " + rec.currentPower + " total YIELD: " + rec.totalYield);
		System.out.println("... SENDING DATA TO WEB STORE");
		} catch (java.net.ConnectException e ) {
			System.out.println("ERROR: inverter server timeout");
		}
	}
	
	/**
	 * CAUTION: be gentle to the slow server and dont go below 60 sec. delay
	 * @param iterations
	 * @param delaySeconds
	 */
	public void executePollSeries(int iterations, int delaySeconds) {
		for (int i = 0; i < iterations; i++) {
			int actIteration = i + 1;
			System.out.println("... SENDING REQUEST " + actIteration + " of " + iterations );
			executeSinglePoll();
			if (actIteration < iterations) {  //dont pause after last iteration
				try {
					TimeUnit.SECONDS.sleep(delaySeconds);
				} catch (InterruptedException e) {
					//should not happen
				}				
			}
		}
		System.out.println("... SERIES FINISHED");
	}

	/**
	 * @return html get in a string
	 */
	public String getHtmlString() throws java.net.ConnectException {
		String result = "";
		try {
			// String webPage = "http://192.168.2.66/status.html";
			String webPage = Settings.deyeURL + Settings.deyeStatusPath;
			String name = Settings.deyeUserName;
			String password = Settings.deyePassword;

			String authString = name + ":" + password;
			byte[] authEncBytes = Base64.getEncoder().encode(authString.getBytes());
			String authStringEnc = new String(authEncBytes);

			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
			urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			result = sb.toString();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param html
	 * @param debug console output of html string?
	 * @return LogRecord object
	 * 
	 *         Method searches for defined js variables within html string data has
	 *         following form: var webdata_now_p = "471"; var webdata_today_e =
	 *         "1.80"; var webdata_total_e = "4.4";
	 * 
	 */
	public LogRecord parseHTMLString(String html, boolean debug) {
		LogRecord rec = new LogRecord();
		html = html.replaceAll("\"", ""); // remove all double quotes
		if (debug == true) {
			System.out.println("---HTML STRING START---");
			System.out.println(html);
			System.out.println("---HTML STRING END---");
		}
		String power = regexSearch("(?<=webdata_now_p = )\\d+", html); // e.g. webdata_now_p = 231;
		String yield = regexSearch("(?<=webdata_total_e = )\\d+.\\d+", html); // e.g webdata_total_e = 2.8;

		if (power != null) {
			try {
				rec.currentPower = Integer.valueOf(power);
			} catch (NumberFormatException e) {
				rec.currentPower = 0;
			}
		}

		if (yield != null) {
			try {
				rec.totalYield = Double.valueOf(yield);
			} catch (NumberFormatException e) {
				rec.totalYield = 0;
			}
		}
		return rec;
	}

	/**
	 * test method for regex studies :-)
	 */
	public void testRegEx() {
		String input = "var webdata_now_p = \"231\"; lat: 56.894205 long: 008.528896 webdata_total_e = \"2.8\" speed: 000.0 24/02/13 21:21   bat:F signal:F  imei:12345678901";

		input = input.replaceAll("\"", "");
		String lat = regexSearch("(?<=lat: )\\d+.\\d+", input);
		String lng = regexSearch("(?<=long: )\\d+.\\d+", input);
		String imei = regexSearch("(?<=imei:)\\d+", input);
		String power = regexSearch("(?<=webdata_now_p = )\\d+", input);
		String yield = regexSearch("(?<=webdata_total_e = )\\d+.\\d+", input);

		if (lat != null && lng != null && imei != null) {
			System.out.println(input);
			System.out.println(lat);
			System.out.println(lng);
			System.out.println(power);
			System.out.println(yield);
		}
	}

	private String getTimestamp() {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		return timeStamp;
	}

	public String regexSearch(String regex, String input) {
		Matcher m = Pattern.compile(regex).matcher(input);
		if (m.find())
			return m.group();
		return null;
	}
}