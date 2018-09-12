package fastily.jwiki.core;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class ColorLogTests {
    private final ColorLog colorLog = new ColorLog(true,true);
    private final Wiki wiki = new Wiki ("test.wikipedia.org");
    static Logger logger = LogManager.getLogger(ColorLog.class);

    /**
     *
     */
    public static boolean readFileFindString(String filePath, String stringToFind) throws IOException {
         BufferedReader bReader = null;
         FileReader fReader = null;
         String currentLine;
         boolean stringFound=false;

         try
         {
             BufferedReader br = new BufferedReader(new FileReader(filePath));

             while((currentLine = bReader.readLine()) != null) {
                 if(currentLine.contains(stringToFind.toLowerCase())) {
                    stringFound=true;
                 }
                 else
                     logger.warn("String not found in" + filePath);
             }
         }
         catch (IOException e)
         {
             logger.error("File not found");
         }

         return stringFound;
    }

    public static boolean findString(String stringToFind)
    {
        boolean isStringFound;
        try
        {
            isStringFound = readFileFindString("C:\\Users\\ADMIN\\Documents\\Primo lancio SEMI\\jwiki\\ColorLogTest.log", stringToFind);
        }
        catch(IOException exc)
        {
            isStringFound=false;
        }
        return isStringFound;
    }

    @Test
    public void TestProva () {
        colorLog.info(wiki,"test info message");
    }

    @Test
    public void TestDebugWithInfo(){
        Configurator.setLevel(logger.getName(), Level.INFO);
        colorLog.debug(wiki,"Test debug message");

        boolean isStringFound = findString("debug");

        //so che è false
        Assert.assertTrue(!isStringFound);
    }

    @Test
    public void TestWarnWithInfo()
    {
        Configurator.setLevel(logger.getName(), Level.WARN);
        colorLog.info(wiki, "Test info message");

        boolean isStringFound = findString("warn");

        Assert.assertTrue(!isStringFound);

    }

    @Test
    public void TestErrorWithWarn()
    {
        Configurator.setLevel(logger.getName(), Level.ERROR);
        colorLog.warn(wiki, "Test warn message");

        boolean isStringFound = findString("error");

        Assert.assertTrue(!isStringFound);
    }

    //deve leggere da file e guarda se ci sono


/*

    public static class TestAppender extends {
        public List<LoggingEvent> events = new ArrayList<LoggingEvent>();
        public void close() {}
        public boolean requiresLayout() {return false;}
        @Override
        protected void append(LoggingEvent event) {
            events.add(event);
        }
    }
    */

}