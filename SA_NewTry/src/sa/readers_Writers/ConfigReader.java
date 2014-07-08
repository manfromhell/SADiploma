package sa.readers_Writers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;


public class ConfigReader implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ConfigReader configReader = null;
	private final String INIT_TEMP = "init_temp";
	private final String FINAL_TEMP = "final_temp";
	private final String ALPHA = "alpha";
	private final String TEMP_ITER = "temp_iter";
	private final String GAPS_KOEF = "gaps_koef";
	private final String REP_KOEF = "rep_koef";
	private final String NUMBER = "number";
	private final String TYPE = "type";
	
	private double initTemp;
	private double finalTemp;
	private double alpha;
	private int iterationsPerTemperature;
	private double gapsKoef;
	private double repKOEF;
	private int type;
	private int number;

	/**
	 * 
	 * @param fileName
	 * @param args
	 */
	private ConfigReader(String fileName, String[] args) {

		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(this.getClass()
					.getClassLoader().getResource(fileName).getFile()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			CommandLine commandLine = readOptions(args);
			if (commandLine.hasOption(INIT_TEMP)) {
				initTemp = Double.parseDouble(commandLine.getOptionValue(INIT_TEMP));
			} else {
				initTemp = Double.parseDouble(properties.getProperty(INIT_TEMP));
			}
			if (commandLine.hasOption(FINAL_TEMP)) {
				finalTemp = Double.parseDouble(commandLine.getOptionValue(FINAL_TEMP));
			} else {
				finalTemp = Double.parseDouble(properties.getProperty(FINAL_TEMP));
			}
			if (commandLine.hasOption(ALPHA)) {
				alpha = Double.parseDouble(commandLine.getOptionValue(ALPHA));
			} else {
				alpha = Double.parseDouble(properties.getProperty(ALPHA));
			}
			if (commandLine.hasOption(TEMP_ITER)) {
				iterationsPerTemperature = Integer.parseInt(commandLine.getOptionValue(TEMP_ITER));
			} else {
				iterationsPerTemperature = Integer.parseInt(properties.getProperty(TEMP_ITER));
			}
			if (commandLine.hasOption(GAPS_KOEF)) {
				gapsKoef = Double.parseDouble(commandLine.getOptionValue(GAPS_KOEF));
			} else {
				gapsKoef = Double.parseDouble(properties.getProperty(GAPS_KOEF));
			}
			if (commandLine.hasOption(REP_KOEF)) {
				repKOEF = Double.parseDouble(commandLine.getOptionValue(REP_KOEF));
			} else {
				repKOEF = Double.parseDouble(properties.getProperty(REP_KOEF));
			}
			if (commandLine.hasOption(TYPE)) {
				type = Integer.parseInt(commandLine.getOptionValue(TYPE));
			} else {
				type = Integer.parseInt(properties.getProperty(TYPE));
			}
			if (commandLine.hasOption(NUMBER)) {
				number = Integer.parseInt(commandLine.getOptionValue(NUMBER));
			} else {
				number = Integer.parseInt(properties.getProperty(NUMBER));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param args
	 * @return
	 */
	public static ConfigReader getInstance(String fileName, String[] args) {
		if (configReader == null) {
			configReader = new ConfigReader(fileName, args);
		} 
			return configReader;
	}

	/**
	 * 
	 * @param args
	 * @return
	 * @throws ParseException
	 */
	public CommandLine readOptions(String[] args) throws ParseException {
		Options o = new Options();
		o.addOption(INIT_TEMP, true, "Init temperature");
		o.addOption(FINAL_TEMP, true, "final temperature");
		o.addOption(ALPHA, true, "alpha");
		o.addOption(TEMP_ITER, true, "iterations per temperature");
		o.addOption(GAPS_KOEF, true, "weight of gap");
		o.addOption(REP_KOEF, true, "weight of repeat");
		o.addOption(NUMBER, true, "Number for check");
		o.addOption(TYPE, true, "Type of bundle");
		CommandLineParser parser = new PosixParser();
		CommandLine cmd = parser.parse(o, args);
		return cmd;
	}

	/**
	 * @return the initTemp
	 */
	public double getInitTemp() {
		return initTemp;
	}


	/**
	 * @return the finalTemp
	 */
	public double getFinalTemp() {
		return finalTemp;
	}

	/**
	 * @return the alpha
	 */
	public double getAlpha() {
		return alpha;
	}

	/**
	 * @return the iterationsPerTemperature
	 */
	public int getIterationsPerTemperature() {
		return iterationsPerTemperature;
	}

	/**
	 * @return the gapsKoef
	 */
	public double getGapsKoef() {
		return gapsKoef;
	}

	/**
	 * @return the repKOEF
	 */
	public double getRepKOEF() {
		return repKOEF;
	}

	/**
	 * @return type of bundle
	 */
	public int getType() {
		return type;
	}

	/**
	 * @return the places
	 */
	public int getNumber() {
		return number;
	}
}
