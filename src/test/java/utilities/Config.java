package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
	private static Properties properties;
	
	static {
		try {
			String pPath = ".//src/test/resources/testData/configuration.properties";
			FileInputStream input = new FileInputStream(pPath);
			properties = new Properties();
			properties.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
}
