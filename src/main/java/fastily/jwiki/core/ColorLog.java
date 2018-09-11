package fastily.jwiki.core;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Colors a String and logs it to console. Your terminal must support ASCII escapes for this to work, otherwise the text
 * will not be colored.
 * 
 * @author Fastily
 *
 */
class ColorLog
{
	/**
	 * The date formatter prefixing output.
	 */
	private static final DateTimeFormatter df = DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm:ss a");

    /**
     * Creating
     */
    private static Logger logger = LogManager.getLogger(ColorLog.class);

    /**
     * Creating a custom log4j level: FYI
      */
    final Level fyiLogLevel = Level.forName("FYI", 1);


	/**
	 * Flag indicating whether logging with this object is allowed.
	 */
	protected boolean enabled;

	/**
	 * Constructor, creates a new ColorLog.
	 * 
	 * @param enableLogging Set true to allow this ColorLog to print log output, otherwise logging with Log4j
	 */
	protected ColorLog(boolean enableLogging)
	{
		enabled = enableLogging;
	}


	/**
	 * Logs a message for a wiki method.
	 * 
	 * @param wiki The wiki object to use
	 * @param message The String to print
	 * @param logLevel The identifier to log the message at (e.g. "INFO", "WARNING")
	 * @param color The color to print the message with. Output will only be colored if this terminal supports it.
     * @return normal log if enabled==true, otherwise returns logging with Log4j by matched logLevel.
	 */
	private void log(Wiki wiki, String message, String logLevel, CC color)      //TODO
	{
		if (enabled)
			System.err.printf("%s%n%s: \u001B[3%dm%s: %s\u001B[0m%n", LocalDateTime.now().format(df), logLevel, color.v, wiki, message);

		else
        {
            switch(logLevel)
            {
                case "error": logger.error(wiki + " - " + message);
                        break;

                case "warn": logger.warn(wiki + " - " + message);
                        break;

                case "info": logger.info(wiki + " - " + message);
                        break;

                case "debug": logger.debug(wiki + " - " + message);
                        break;

                case "fyi": logger.log(fyiLogLevel, "s");
                        break;

                default: logger.trace("Incorrect log level");

            }
        }
	}

	/**
	 * Output warning message for wiki. Text is yellow.
	 * 
	 * @param wiki The wiki object to use
	 * @param s The String to print.
     * @return normal log if enabled==true, otherwise returns warning log using Log4j
	 */
	protected void warn(Wiki wiki, String s)
	{
	    if(enabled)
		    log(wiki, s, "WARNING", CC.YELLOW);
	    else
            logger.warn(wiki + " - " + s);
	}

	/**
	 * Output info message for wiki. Text is green.
	 * 
	 * @param wiki The wiki object to use
	 * @param s The String to print.
     * @return normal log if enabled==true, otherwise returns info log using Log4j
	 */
	protected void info(Wiki wiki, String s)
	{
	    if(enabled)
		    log(wiki, s, "INFO", CC.GREEN);
	    else
            logger.info(wiki + " - " + s);
	}

	/**
	 * Output error message for wiki. Text is red.
	 * 
	 * @param wiki The wiki object to use
	 * @param s The String to print.
     * @return normal log if enabled==true, otherwise returns error log using Log4j
	 */
	protected void error(Wiki wiki, String s)
	{
	    if(enabled)
		    log(wiki, s, "ERROR", CC.RED);
	    else
	        logger.error(wiki + " - " + s);
	}

	/**
	 * Output debug message for wiki. Text is purple.
	 * 
	 * @param wiki The wiki object to use
	 * @param s The String to print.
     * @return normal log if enabled==true, otherwise returns debug log using Log4j
	 */
	protected void debug(Wiki wiki, String s)
	{
	    if(enabled)
		    log(wiki, s, "DEBUG", CC.PURPLE);
	    else
	        logger.debug(wiki + " - " + s);
	}

	/**
	 * Output miscellaneous message for wiki. Text is blue.
	 * 
	 * @param wiki The wiki object to use
	 * @param s The String to print.
     * @return normal log if enabled==true, otherwise returns trace log using Log4j
	 */
	protected void fyi(Wiki wiki, String s)
	{
	    if(enabled)
		    log(wiki, s, "FYI", CC.CYAN);
	    else {
            logger.log(fyiLogLevel, "s");
        }
	}

	/**
	 * Represents ASCII colors.
	 * 
	 * @author Fastily
	 *
	 */
	private static enum CC
	{
		/**
		 * A font color, black, which can be applied to a String if your terminal supports it.
		 */
		BLACK(0),

		/**
		 * A font color, red, which can be applied to a String if your terminal supports it.
		 */
		RED(1),

		/**
		 * A font color, green, which can be applied to a String if your terminal supports it.
		 */
		GREEN(2),

		/**
		 * A font color, yellow, which can be applied to a String if your terminal supports it.
		 */
		YELLOW(3),

		/**
		 * A font color, blue, which can be applied to a String if your terminal supports it.
		 */
		BLUE(4),

		/**
		 * A font color, purple, which can be applied to a String if your terminal supports it.
		 */
		PURPLE(5),

		/**
		 * A font color, cyan, which can be applied to a String if your terminal supports it.
		 */
		CYAN(6),

		/**
		 * A font color, white, which can be applied to a String if your terminal supports it.
		 */
		WHITE(7);

		/**
		 * The ascii color value.
		 */
		private int v;

		/**
		 * Constructor, creates a new CC.
		 * 
		 * @param v The color code to use.
		 */
		private CC(int v)
		{
			this.v = v;
		}
	}
}