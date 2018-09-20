package fastily.jwiki.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class ColorLogTests {

    private final static ColorLog colorLog = new ColorLog(true);
    private final Wiki wiki = new Wiki ("test.wikipedia.org");
    private static final Log logger = LogFactory.getLog(ColorLogTests.class);

    /**
     * File where to log logging messages
     */
    String filePath = "ColorLogTest.log";

    /**
     *Read a file line by line and looks for stringToFind. If not found, return false and exit
     * @param stringToFind String we want to search
     */
    public static boolean readFileFindString(String filePath, String stringToFind) throws IOException {
         String currentLine;

         try
         {
             BufferedReader bReader = new BufferedReader(new FileReader(filePath));

             while((currentLine = bReader.readLine()) != null) {
                 if (currentLine.contains(stringToFind)) {
                     return true;
                 }
             }
         }
         catch (IOException e)
         {
             logger.error("File not found");
         }

         return false;
    }

    /**
     * Message for each level
     */
    String fyi_msg;
    String debug_msg;
    String info_msg;
    String warn_msg;
    String error_msg;


    @Rule
    public final TestName testName = new TestName();

    @Before
    public void setLogMsg () {
        String simpleTestName = testName.getMethodName();

        fyi_msg = simpleTestName + "_" + "fyi";
        debug_msg =  simpleTestName+ "_" + "debug";
        info_msg = simpleTestName + "_" + "info";
        warn_msg = simpleTestName + "_" + "warn";
        error_msg = simpleTestName + "_" + "error";


    }


    /**
     * Method to test which log levels will log in the file .log, with TRACE lvl setted.
     *
     */
    @Test
    public void testLogging() throws IOException {

        assertTrue("You must set the logging level to INFO for this test, in log42j.xml, Logger=ColorLog", logger.isInfoEnabled());

        colorLog.fyi(wiki,fyi_msg);
        colorLog.debug(wiki,debug_msg);
        colorLog.info(wiki,info_msg);
        colorLog.warn(wiki,warn_msg);
        colorLog.error(wiki,error_msg);


        assertFalse(readFileFindString(filePath, fyi_msg));

        assertFalse(readFileFindString(filePath, debug_msg));

        assertTrue(readFileFindString(filePath, info_msg));

        assertTrue(readFileFindString(filePath, warn_msg));

        assertTrue(readFileFindString(filePath, error_msg));
    }

}