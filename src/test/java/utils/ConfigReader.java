package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
	
	private static Properties properties = new Properties();
	
	static {
		try {
			FileInputStream fis = new FileInputStream("config/config.properties");
			properties.load(fis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static String get(String key) {
		return properties.getProperty(key);
	}
	
	

}
