
package       csci6836program3;
import     java.util.ArrayList;


/**
 *
 */
public class Vertex 
{
   private final 
       ArrayList<Integer> eLst; 
   private final 
       ArrayList<Double> wLst;
   private final 
       ArrayList<Boolean> xLst;
   private final int        nE;
   private int        minIndex;
   private int        maxIndex;
   
   private void tE(final int i)
   {
      if ((i<0)||(i>=nE))
      {
         System.out.println
                  ("tE error");
         System.exit       (i);
      }
   }
   
   public final int getNE()
                 { return nE; }

   public final int findEI
                  (final int e)
   {
      for (int k=0; k<nE; ++k)
      {
        final int t = 
                   eLst.get(k);
        if(t==e)      return k;
      }
      return                -1;
   }   
   
   public final int getEdge
                  (final int i)
   {
      tE                   (i);
      final int ans = 
                   eLst.get(i);
      return               ans;
   }
   
   public final double getWt
                  (final int i)
   {
      tE                   (i);
      final double ans = 
                   wLst.get(i);
      return               ans;
   }  
                  
                 
   public Vertex
   (
      ArrayList<Integer>  edge,
      ArrayList<Double>    wt
   )
   {
      nE       =   edge.size();
      eLst     =          edge;  
      wLst     =            wt;
      xLst = new ArrayList<>();
      incAll                ();
   }

   private void findMinMaxI()
   {
            
      double tMin =  
             Double.MAX_VALUE;
      int tMinI  =          -1;
      double tMax =  
             Double.MIN_VALUE;
      int tMaxI  =          -1;
      final int n = 
                   wLst.size();
  
      //
      // Find the indices of
      // the max and min
      // weights.
      //   
      for(int i=0; i<n; i++)
      {
         final double m1 = 
                   wLst.get(i);
         final boolean inc =
                !(xLst.get(i));
         if (inc && (m1<tMin))
         {
            tMin  =         m1;
            tMinI =          i;
         }
         if (inc && (m1>tMax))
         {
            tMax  =         m1;
            tMaxI =          i;
         }
      }
      minIndex    =      tMinI;
      maxIndex    =      tMaxI;
   }
 
   public final void incAll()
   {
      xLst.            clear();
      for (int i=0; i<nE; i++)
             {xLst.add(false);}
      findMinMaxI           ();
   }
      
   //
   // Exclude this 
   // edge in min/max
   // computations.
   // Also this can 
   // be used to mark 
   // an edge.
   //
   public final void exclude
                  (final int x)
   {
      xLst.set      (x, true);
      findMinMaxI          ();
   }
                  
   public final boolean isEx
                  (final int x)
        { return xLst.get(x); }
                         
   public final int getMinI()
             {return minIndex;}
   
   public final int getMaxI()
             {return maxIndex;} 
   
}
