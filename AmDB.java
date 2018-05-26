
package       csci6836program3;

import        java.util.Random;

public class AmDB 
{
   private double xarr[];
   private double yarr[];
   private final int        nV;
   private static final int 
                         M = 5;
   private double       aM[][];
   private final Random   rand; 
   private static final 
      boolean    DEBUG =  false; 
   
   private double getRD
    ( final double v1, final double v2)
   {
      
      final double r=
          rand.nextDouble();
      final double ans= v1 + r*(v2 - v1);
      return ans;
      
   }   
   
 
   private void loadR
   (
      final double   r1,
      final double   r2 
   )
   {
      for(int i=0; i<nV; ++i)
      {
         xarr[i] = getRD(r1,r2);
         yarr[i] = getRD(r1,r2);
         aM[i][i] = 0.0;
      }
      for(int i=0; i<nV; ++i)
         for(int j=i+1; j<nV; j++)
         {
            double x1 = xarr[i]-xarr[j];
            x1*=x1;
            double y1 = yarr[i]-yarr[j];
            y1*=y1;
            final double s = x1 + y1;
            final double d = Math.sqrt(s);
            aM[i][j] = d;
            aM[j][i] = d;
            
         }
   }
   
   public final double getX(final int x)
                     { return xarr[x]; }
   
   public final double getY(final int y)
                     { return yarr[y]; }  
   
   public void dumpM(AmDB am)
   {
      for (int i=0; i<nV;++i)
         for(int j=0; j<nV;++j)
         {
           System.out.print(i);
           System.out.print
                         (" ");
           System.out.print(j);
           System.out.print
                         (" ");
           final boolean b =
                    am.isConnected(i,j);
           if(b)
              System.out.
                      println(aM[i][j]);
              
           else
              System.out.  println("N");
         }
   }  
   
  
   public double getWt
   (
      final int              i,
      final int               j
   )
   {
      final double ans = aM[i][j];
      return               ans;
   }
           
   public boolean isConnected
   (
      final int              i,
      final int               j
   )
   {
      final double ans =       aM[i][j];
      return               (ans != 0.0);
   }
 
      public AmDB
   (
      final double   r1,
      final double   r2,
      final int      nv,
      final int      s
   ) 
   {
      if(nv<M)
      {
         System.out.println
              ("nv too small");
         System.exit      (nv);
      }
      rand  =  new   Random(s);
      nV    =               nv;
      aM    = new double[nV][nV];
      xarr  = new double[nV];
      yarr  = new double[nV];
      loadR(r1,r2);
      if (DEBUG) dumpM(this);
   }
   
}



