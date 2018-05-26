
package                csci6836program3;

/**
 *
 * @author billp
 */
public class Edge
{
   final private int               from;
   final private int                 to;
   final private double          weight;
         
   public Edge
   (
      final int                       f, 
      final int                       t, 
      final double                    w
   )
   {
      from   =                        f;
      to     =                        t;
      weight =                        w;
   }
   
   public int getFrom() { return from; }
   public int   getTo() { return   to; }
   public double getWeight()
                      { return weight; }

   
}
