
package       csci6836program3;
import java.io.*;
import java.text.*;
import java.util.*;
/**
 * <p>Title: Simple Log</p>
 *
 * <p>Description:
 *  Simple logger found at http://www.rgagnon.com/javadetails/java-0063.html
 * </p>
 *
 * <p>Copyright: none</p>
 *
 * <p>Company: Real's Java How-to...</p>
 *
 * @author R Gagnon
 * @version 1.0
 */

/**
 * Utilities log
 */
public class SimpleLog
{

    synchronized public static void write
     ( final String file, 
       final double x,
       final double y)
    {
        try
        {
            String msg = String.valueOf(x) + " " + String.valueOf(y);
            FileWriter aWriter = new FileWriter(file, true);
            aWriter.write(msg
                    + System.getProperty("line.separator"));
            aWriter.flush();
            aWriter.close();
        }
        catch (Exception e)
        {
            System.out.println("Could not write to log file");
            System.exit(-1);
        }
    }

}
