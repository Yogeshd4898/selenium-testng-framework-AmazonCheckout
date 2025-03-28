package com.example.utils;

import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class for reading JSON data from files.
 */
public class JSONUtils {

    private static final Logger logger = LoggerFactory.getLogger(JSONUtils.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Reads JSON from the specified file and maps it to an instance of the provided class type.
     *
     * @param filePath the path to the JSON file
     * @param clazz    the class to which the JSON data should be mapped
     * @param <T>      the type of the returned object
     * @return an instance of type T mapped from the JSON file, or null if an error occurs
     */
    public static <T> T readJSON(String filePath, Class<T> clazz) {
        T data = null;
        try {
            data = mapper.readValue(new File(filePath), clazz);
        } catch (Exception e) {
            logger.error("Error reading JSON from file: {}", filePath, e);
        }
        return data;
    }
}
