package fastily.jwiki.test;

import fastily.jwiki.core.ColorLog;

public class ColorLogTests {

    //static Logger logger = LogManager.getLogger(ColorLogTests.class);


    public static void main(String [] args)
    {
        /*Layout layout = new PatternLayout();
        StringWriter stringWriter = new StringWriter();
        WriterAppender writerAppender = new WriterAppender(layout, stringWriter);
        logger.addAppender(writerAppender);
        TextArea textArea = new TextArea();
*/
        //logger.log(Level.INFO, "PROVA");

        //logger.error("test");
        ColorLog logger=new ColorLog(false);
        logger.info(null,"CIAO CIAO");
    }











}
