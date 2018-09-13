package fastily.jwiki.core;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class ColorLogTests {

    private final static ColorLog colorLog = new ColorLog(true);
    private final Wiki wiki = new Wiki ("test.wikipedia.org");
    static Logger logger = LogManager.getLogger(ColorLogTests.class);

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
    public void testLvlTrace() throws IOException {
        boolean isStringFound;

        Configurator.setLevel(LogManager.getLogger(ColorLog.class).getName(), Level.TRACE);


        colorLog.fyi(wiki,fyi_msg);
        Assert.assertTrue(readFileFindString(filePath, fyi_msg));

        colorLog.debug(wiki,debug_msg);
        Assert.assertTrue(readFileFindString(filePath, debug_msg));

        colorLog.info(wiki,info_msg);
        Assert.assertTrue(readFileFindString(filePath, info_msg));

        colorLog.warn(wiki,warn_msg);
        Assert.assertTrue(readFileFindString(filePath, warn_msg));

        colorLog.error(wiki,error_msg);
        Assert.assertTrue(readFileFindString(filePath, error_msg));
    }

    @Test
    public void testLvlDebug() throws IOException {
        boolean isStringFound;
        Configurator.setLevel(LogManager.getLogger(ColorLog.class).getName(), Level.DEBUG);


        colorLog.fyi(wiki,fyi_msg);
        Assert.assertFalse(readFileFindString(filePath, fyi_msg));

        colorLog.debug(wiki,debug_msg);
        Assert.assertTrue(readFileFindString(filePath, debug_msg));

        colorLog.info(wiki,info_msg);
        Assert.assertTrue(readFileFindString(filePath, info_msg));

        colorLog.warn(wiki,warn_msg);
        Assert.assertTrue(readFileFindString(filePath, warn_msg));

        colorLog.error(wiki,error_msg);
        Assert.assertTrue(readFileFindString(filePath, error_msg));

    }


    /**
     * Method to test which log levels will log in the file .log, with INFO lvl setted.
     *
     */
    @Test
    public void testLvlInfo() throws IOException {
        boolean isStringFound;
        Configurator.setLevel(LogManager.getLogger(ColorLog.class).getName(), Level.INFO);


        colorLog.fyi(wiki,fyi_msg);
        Assert.assertFalse(readFileFindString(filePath, fyi_msg));

        colorLog.debug(wiki,debug_msg);
        Assert.assertFalse(readFileFindString(filePath, debug_msg));

        colorLog.info(wiki,info_msg);
        Assert.assertTrue(readFileFindString(filePath, info_msg));

        colorLog.warn(wiki,warn_msg);
        Assert.assertTrue(readFileFindString(filePath, warn_msg));

        colorLog.error(wiki,error_msg);
        Assert.assertTrue(readFileFindString(filePath, error_msg));

    }

    /**
     * Method to test which log levels will log in the file .log, with WARN lvl setted.
     *
     */
    @Test
    public void testLvlWarn() throws IOException {
        boolean isStringFound;
        Configurator.setLevel(LogManager.getLogger(ColorLog.class).getName(), Level.WARN);


        colorLog.fyi(wiki,fyi_msg);
        Assert.assertFalse(readFileFindString(filePath, fyi_msg));

        colorLog.debug(wiki,debug_msg);
        Assert.assertFalse(readFileFindString(filePath, debug_msg));

        colorLog.info(wiki,info_msg);
        Assert.assertFalse(readFileFindString(filePath, info_msg));

        colorLog.warn(wiki,warn_msg);
        Assert.assertTrue(readFileFindString(filePath, warn_msg));

        colorLog.error(wiki,error_msg);
        Assert.assertTrue(readFileFindString(filePath, error_msg));
    }


    /**
     * Method to test which log levels will log in the file .log, with ERROR lvl setted.
     *
     */
    @Test
    public void testLvlError() throws IOException {
        boolean isStringFound;
        Configurator.setLevel(LogManager.getLogger(ColorLog.class).getName(), Level.ERROR);


        colorLog.fyi(wiki,fyi_msg);
        Assert.assertFalse(readFileFindString(filePath, fyi_msg));

        colorLog.debug(wiki,debug_msg);
        Assert.assertFalse(readFileFindString(filePath, debug_msg));

        colorLog.info(wiki,info_msg);
        Assert.assertFalse(readFileFindString(filePath, info_msg));

        colorLog.warn(wiki,warn_msg);
        Assert.assertFalse(readFileFindString(filePath, warn_msg));

        colorLog.error(wiki,error_msg);
        Assert.assertTrue(readFileFindString(filePath, error_msg));
    }


    /**
     * Method to test which log levels will log in the file .log, with FATAL lvl setted.
     *
     */
    public void testLvlFatal() throws IOException {
        boolean isStringFound;
        Configurator.setLevel(LogManager.getLogger(ColorLog.class).getName(), Level.FATAL);


        colorLog.fyi(wiki,fyi_msg);
        Assert.assertFalse(readFileFindString(filePath, fyi_msg));

        colorLog.debug(wiki,debug_msg);
        Assert.assertFalse(readFileFindString(filePath, debug_msg));

        colorLog.info(wiki,info_msg);
        Assert.assertFalse(readFileFindString(filePath, info_msg));

        colorLog.warn(wiki,warn_msg);
        Assert.assertFalse(readFileFindString(filePath, warn_msg));

        colorLog.error(wiki,error_msg);
        Assert.assertTrue(readFileFindString(filePath, error_msg));
    }


    /**
     * Method to test which log levels will log in the file .log, with ALL lvl setted.
     *
     */
    public void testLvlAll() throws IOException {
        boolean isStringFound;
        Configurator.setLevel(LogManager.getLogger(ColorLog.class).getName(), Level.ALL);


        colorLog.fyi(wiki,"Test trace message");
        Assert.assertTrue(readFileFindString(filePath, fyi_msg));

        colorLog.debug(wiki,"Test debug message");
        Assert.assertTrue(readFileFindString(filePath, debug_msg));

        colorLog.info(wiki,"Test info message");
        Assert.assertTrue(readFileFindString(filePath, info_msg));

        colorLog.warn(wiki,"Test warn message");
        Assert.assertTrue(readFileFindString(filePath, warn_msg));

        colorLog.error(wiki,"Test error message");
        Assert.assertTrue(readFileFindString(filePath, error_msg));
    }






    }