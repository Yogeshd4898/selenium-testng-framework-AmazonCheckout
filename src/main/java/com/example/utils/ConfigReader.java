package com.example.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class to read configuration properties from a file.
 */
public class ConfigReader {

	private static final Logger logger = LoggerFactory.getLogger(ConfigReader.class);
	private final Properties properties;

    // hello
	/**
	 * Constructs a ConfigReader and loads properties from the specified file path.
	 *
	 * @param filePath the path to the configuration properties file
	 */
	public ConfigReader(String filePath) {
		properties = new Properties();
		try (InputStream input = new FileInputStream(filePath)) {
			properties.load(input);
		} catch (IOException e) {
			logger.error("Failed to load configuration from file: {}", filePath, e);
		}
	}

	/**
	 * Retrieves the property value for the given key.
	 *
	 * @param key the property key
	 * @return the property value, or null if the key is not found
	 */
	public String getProperty(String key) {
		return properties.getProperty(key);
	}
}
