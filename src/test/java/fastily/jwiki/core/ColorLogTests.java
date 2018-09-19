package fastily.jwiki.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/*import org.apache.commons.logging.impl.Log4JLogger;*/
import org.apache.commons.logging.impl.Log4JLogger;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/*import java.util.logging.Level;*/
/*import java.util.logging.LogManager;*/

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;




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
     * Log messages with every logging lvl
     */
    private void logMsg()
    {
        colorLog.fyi(wiki,fyi_msg);
        colorLog.debug(wiki,debug_msg);
        colorLog.info(wiki,info_msg);
        colorLog.warn(wiki,warn_msg);
        colorLog.error(wiki,error_msg);
    }

   /* public static void process(org.apache., String level) {
        if (level != null) {
            log.setLevel(org.apache.log4j.Level.toLevel(level));
        }
    }*/

    /**
     * Method to test which log levels will log in the file .log, with TRACE lvl setted.
     *
     */


    /*@Test
    public void testLvlTrace() throws IOException {
        //Configurator.setLevel(LogManager.getLogger(ColorLog.class).getName(), Level.TRACE);
        logger.getClass()
        ((Log4JLogger) logger).getLogger()(org.apache.log4j.Level.DEBUG);


        logMsg();
        assertTrue(readFileFindString(filePath, fyi_msg));

        logMsg();
        assertTrue(readFileFindString(filePath, debug_msg));

        logMsg();
        assertTrue(readFileFindString(filePath, info_msg));

        logMsg();
        assertTrue(readFileFindString(filePath, warn_msg));

        logMsg();
        assertTrue(readFileFindString(filePath, error_msg));
    }*/

    /*@Test
    public void testLvlDebug() throws IOException {
        Configurator.setLevel(LogManager.getLogger(ColorLog.class).getName(), Level.DEBUG);


        logMsg();
        assertFalse(readFileFindString(filePath, fyi_msg));

        logMsg();
        assertTrue(readFileFindString(filePath, debug_msg));

        logMsg();
        assertTrue(readFileFindString(filePath, info_msg));

        logMsg();
        assertTrue(readFileFindString(filePath, warn_msg));

        logMsg();
        assertTrue(readFileFindString(filePath, error_msg));

    }*/


    /**
     * Method to test which log levels will log in the file .log, wit INFO lvl setted.
     *      *h
     */

    @Test
    public void testLvlInfo() throws IOException { //perchè nella configurazione abbiamo INFO


        logMsg();
        assertFalse(readFileFindString(filePath, fyi_msg));

        logMsg();
        assertFalse(readFileFindString(filePath, debug_msg));

        logMsg();
        assertTrue(readFileFindString(filePath, info_msg));

        logMsg();
        assertTrue(readFileFindString(filePath, warn_msg));

        logMsg();
        assertTrue(readFileFindString(filePath, error_msg));

    }

    /**
     * Method to test which log levels will log in the file .log, with WARN lvl setted.
     *
     */
    /*@Test
    public void testLvlWarn() throws IOException {
        Configurator.setLevel(LogManager.getLogger(ColorLog.class).getName(), Level.WARN);
        LogFactory.


        logMsg();
        assertFalse(readFileFindString(filePath, fyi_msg));

        logMsg();
        assertFalse(readFileFindString(filePath, debug_msg));

        logMsg();
        assertFalse(readFileFindString(filePath, info_msg));

        logMsg();
        assertTrue(readFileFindString(filePath, warn_msg));

        logMsg();
        assertTrue(readFileFindString(filePath, error_msg));
    }*/


    /**
     * Method to test which log levels will log in the file .log, with ERROR lvl setted.
     *
     */
   /* @Test
    public void testLvlError() throws IOException {
        Configurator.setLevel(LogManager.getLogger(ColorLog.class).getName(), Level.ERROR);


        logMsg();
        assertFalse(readFileFindString(filePath, fyi_msg));

        logMsg();
        assertFalse(readFileFindString(filePath, debug_msg));

        logMsg();
        assertFalse(readFileFindString(filePath, info_msg));

        logMsg();
        assertFalse(readFileFindString(filePath, warn_msg));

        logMsg();
        assertTrue(readFileFindString(filePath, error_msg));
    }*/


    /**
     * Method to test which log levels will log in the file .log, with FATAL lvl setted.
     *
     */
    /*public void testLvlFatal() throws IOException {
        Configurator.setLevel(LogManager.getLogger(ColorLog.class).getName(), Level.FATAL);


        logMsg();
        assertFalse(readFileFindString(filePath, fyi_msg));

        logMsg();
        assertFalse(readFileFindString(filePath, debug_msg));

        logMsg();
        assertFalse(readFileFindString(filePath, info_msg));

        logMsg();
        assertFalse(readFileFindString(filePath, warn_msg));

        logMsg();
        as sertTrue(readFileFindString(filePath, error_msg));
    }*/


    /**
     * Method to test which log levels will log in the file .log, with ALL lvl setted.
     *
     */
    /*public void testLvlAll() throws IOException {
        Configurator.setLevel(LogManager.getLogger(ColorLog.class).getName(), Level.ALL);


        logMsg();
        assertTrue(readFileFindString(filePath, fyi_msg));

        logMsg();
        assertTrue(readFileFindString(filePath, debug_msg));

        logMsg();
        assertTrue(readFileFindString(filePath, info_msg));

        logMsg();
        assertTrue(readFileFindString(filePath, warn_msg));

        logMsg();
        assertTrue(readFileFindString(filePath, error_msg));
    }*/
}