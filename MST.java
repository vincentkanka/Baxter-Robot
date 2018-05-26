
package csci6836program3;

import java.util.ArrayList;

/**
 *
 * @author billp
 */
public class MST
{
   private final 
      ArrayList<Edge>              mLst;
   
   private  GrDBU grdb =           null;
   
   public void addTo(Edge e)
                        { mLst.add(e); }
    
   public Edge getAt(final int i)
   {
     final Edge e =         mLst.get(i);
     return                           e;
   }
   public int getNE()
                 { return mLst.size(); }
   
   public GrDBU getGr()  {return grdb; }
   
   public void dump()
   {
      System.out.println             ();
      System.out.print     ("Dump of ");
      System.out.println      (" MST ");
      double                    t = 0.0;
      for ( Edge e : mLst)
      {
         final int v1 =     e.getFrom();
         final int v2 =     e.getFrom();
         final double w  =   e.getWeight();
         t +=                         w;
         System.out.print
                   ("Edge connecting ");
         System.out.print          (v1);
         System.out.print     (" and ");
         System.out.print          (v2);         
         System.out.print(". weight= ");
         System.out.println         (w);          
      }
      System.out.print("Total Weight=");
      System.out.println            (t); 
   }
 //
   // This function prints 
   // out the graph in the
   // same format as format 
   // the dump of the original
   // graph.  It can be called
   // externally or 
   // called if a DEBUG flag
   // is set (see GrDBU).
   //
   public void dumpTree() {grdb.dump();}
   
   //
   // This function
   // builds the graph
   // of the minimum 
   // spanning tree --
   // an adjacency list
   // of vertex objects
   // in an arraylist
   // as in GrDBU.
   //
   public void buildGraph()           
   {
      grdb = new            GrDBU(this);
   }
    
   public Vertex getVertAt(final int i)
               { return grdb.getAt(i); }
  
   
   public MST()
   {
      mLst = new          ArrayList<>();
   }
}
