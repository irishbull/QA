package ta.dataproviders;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;


import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JSONDataProvider {

    private static final Logger logger = LoggerFactory.getLogger(JSONDataProvider.class);

    /**
     * fetchData method to retrieve test data for specified method
     *
     * @return Object[][]
     */
    @DataProvider(name = "fetchJSONData")
    public Object[][] fetchData(ITestContext ctx, Method method) throws IOException, ParseException {
        Object rowID;
        Object description;
        Object[][] result;
        List<JSONObject> testDataList = new ArrayList<>();


        String dataFile = ctx.getCurrentXmlTest().getAllParameters().get("json-path");
        logger.info(dataFile);

        JSONArray testData = (JSONArray) extractJSONData(dataFile).get(method.getName());

        for (int i = 0; i < testData.size(); i++) {
            testDataList.add((JSONObject) testData.get(i));
        }

        // include Filter
        if (System.getProperty("includePattern") != null) {
            String include = System.getProperty("includePattern");
            List<JSONObject> newList = new ArrayList<>();
            List<String> tests = Arrays.asList(include.split(",", -1));

            for (String getTest : tests) {
                for (int i = 0; i < testDataList.size(); i++) {
                    if (testDataList.get(i).toString().contains(getTest)) {
                        newList.add(testDataList.get(i));
                    }
                }
            }

            // reassign testRows after filtering tests
            testDataList = newList;
        }

        // exclude Filter
        if (System.getProperty("excludePattern") != null) {
            String exclude = System.getProperty("excludePattern");
            List<String> tests = Arrays.asList(exclude.split(",", -1));

            for (String getTest : tests) {
                // start at end of list and work backwards so index stays in sync
                for (int i = testDataList.size() - 1; i >= 0; i--) {
                    if (testDataList.get(i).toString().contains(getTest)) {
                        testDataList.remove(testDataList.get(i));
                    }
                }
            }
        }

        // create object for dataprovider to return
        try {
            result = new Object[testDataList.size()][testDataList.get(0).size()];

            for (int i = 0; i < testDataList.size(); i++) {
                rowID = testDataList.get(i).get("rowID");
                description = testDataList.get(i).get("description");
                result[i] = new Object[]{rowID, description, testDataList.get(i)};
            }
        } catch (IndexOutOfBoundsException ie) {
            result = new Object[0][0];
        }

        return result;
    }

    /**
     * extractJSONData method to get JSON data from file
     *
     * @return JSONObject
     */
    public static JSONObject extractJSONData(String file) throws IOException, ParseException {
        FileReader reader = new FileReader(file);
        JSONParser jsonParser = new JSONParser();

        return (JSONObject) jsonParser.parse(reader);
    }

}
