package fastily.jwiki.core;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.WriterAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.StringWriter;


public class ColorLogTests_bkp {


    public static Logger logger = LogManager.getContext().getLogger("ColorLog");
    final static LoggerContext ctx = (LoggerContext) LogManager.getContext();
    final static Configuration config = ctx.getConfiguration();
    final static StringWriter stringWriter = new StringWriter();

    @BeforeClass
    public static void setupEnvironment() {

        PatternLayout patternLayout = PatternLayout.newBuilder().withPattern("%message").build();
        Appender appender = WriterAppender.newBuilder().setName("writeAppender").setTarget(stringWriter).setLayout(patternLayout).build();

        appender.start();
        config.addAppender(appender);

        Logger logger = ctx.getLogger("ColorLog");
        ((org.apache.logging.log4j.core.Logger) logger).addAppender(appender);

        ctx.updateLoggers();
    }

    private void setLoggerLevel () {
        LoggerConfig loggerConfig = config.getLoggerConfig("ColorLog");
        loggerConfig.setLevel(Level.INFO);
        ctx.updateLoggers();
    }

    @Test
    public void TestProva () {
        setLoggerLevel();

        ColorLog colorLog = new ColorLog(true,true);

        Wiki wiki = new Wiki ("test.wikipedia.org");
        colorLog.info(wiki,"info log message");
        String result = stringWriter.toString();
    }

    @After
    public void cleanStringWriter() {
        stringWriter.flush();
    }


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
