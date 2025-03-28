package com.example.utils;

import java.lang.reflect.Method;
import java.io.File;
import java.io.IOException;
import org.testng.annotations.DataProvider;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class for providing test data from a JSON file.
 */
public class DataProviderUtils {

    private static final Logger logger = LoggerFactory.getLogger(DataProviderUtils.class);
    private static final String TEST_DATA_FILE = "src/test/resources/testdata.json";
    
    @DataProvider(name = "testData")
    public Object[][] getTestData(Method method) {
    	
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(new File(TEST_DATA_FILE));
            return new Object[][] { { rootNode } };
            }
        
        catch (IOException e) {
            logger.error("Failed to load test data from file: {}", TEST_DATA_FILE, e);
            return new Object[0][];
        }
    }
}
