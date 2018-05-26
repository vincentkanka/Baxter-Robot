
package       csci6836program3;

import        java.util.Random;
import     java.util.ArrayList;

public class GrDBU 
{

   private static final 
      boolean    DEBUG =  false;
   private final 
       ArrayList<Vertex>  vLst;    
   
   private final int        nV;

   private static final int 
                        M1 = 5;
   private static final int 
                     M2 = 1000;
   private final Random   rand; 
   private final AmDB       am;
   public final Vertex getAt
                  (final int i)
   {
      if((i<0)||(i>=nV))
      {
         System.out.println
           ("Bad Vert. index");
         System.exit       (i);
      }
      Vertex ans = vLst.get(i);
      return               ans;
   }
   
   private int getR
    ( final int b, final int r)
   {
      final int v1 =     r - 1;
      final int v2 = 
          rand.nextInt(v1) + b;
      return                v2;
   }
    
   public final double getX(final int x)
                  { return am.getX(x); }
   
   public final double getY(final int y)
                  { return am.getY(y); } 
      
   public int getNV()
                 { return nV; }
   
   private int loadEW
   (
      final int              i,
      ArrayList<Integer>    eL,
      ArrayList<Double>     wL      
   )
   {
      for(int j=0; j<nV; ++j)
      {
         final boolean c =
           am.isConnected(i,j);
         final double wt =
                 am.getWt(i,j);
         if (c)
         {
            eL.add         (j);
            wL.add        (wt);
         }
      }   
      final int s =  eL.size();
      return                 s;
   }
   
   private void loadGMST
               ( final MST m )
   {
      //
      // Convert the
      // edges in MST
      // into arraylists
      // of edges and weights
      // to construct
      // each vertex.
      //
      final int nE = m.getNE();
      ArrayList<ArrayList<Integer>> aE = new ArrayList<>();
      ArrayList<ArrayList<Double>> aW = new ArrayList<>();
      for(int i=0; i<nV; ++i)
      {
          ArrayList<Integer> e = new ArrayList<>();
          aE.add(e);
          ArrayList<Double> f = new ArrayList<>();
          aW.add(f);
      }
      for (int i = 0; i<nE; i++)
      {
         final Edge e =  m.getAt(i);
         final int v1 = e.getFrom();
         final int v2 =   e.getTo();
         final double wt = e.getWeight();
         ArrayList<Integer> ae1 = aE.get(v1);
         ArrayList<Integer> ae2 = aE.get(v2);
         ArrayList<Double> aw1 = aW.get(v1);
         ArrayList<Double> aw2 = aW.get(v2);
         ae1.add(v2);
         aw1.add(wt);
         ae2.add(v1);
         aw2.add(wt);
      }
      
      //
      // Create and add each
      // vertex to the graph.
      //
      for(int i=0; i<nV; ++i)
      {
         ArrayList<Integer> eI = aE.get(i);
         ArrayList<Double> wI = aW.get(i);
         Vertex v = 
             new Vertex(eI,wI);
         vLst.add          (v);
      }
      
         
//         final int nE = m.getNE();
//         
//         for (int )
//         Edge e =m.getAt(i);
//
//         final int s =
//               loadEW(i,eI,wI);
//         Vertex v = 
//             new Vertex(eI,wI);
//         vLst.add          (v);
 //     }
   }
   
   private void loadG()
   {
      for(int i=0; i<nV; ++i)
      {
         ArrayList<Integer>eI=
             new ArrayList<>();
         ArrayList<Double>wI=    
             new ArrayList<>();
         final int s =
               loadEW(i,eI,wI);
         Vertex v = 
             new Vertex(eI,wI);
         vLst.add          (v);
      }
   }
   
   public final void dump()
   {
      System.out.println    ();
      System.out.println    
             ("Dump of graph");
      
      for(int i=0; i<nV; ++i)
      {
         System.out.print  (i);
         System.out.print
                       ("   ");         
         Vertex v     =
                      getAt(i);
         final int nE =
                     v.getNE();
         for (int j=0;j<nE;++j)
         {
            final int e = 
                  v.getEdge(j);
            System.out.print
                           (e);
            System.out.print
                         (" ");
            if(i==e)
            {
               System.out.print
                  ("NG ERROR");
               System.exit (e);
            }
         }
         System.out.println ();
         System.out.print
                      ("    ");
         for (int j=0;j<nE;++j)
         {
            final double x = v.getWt(j);
            System.out.print        (x);
            System.out.print
                         (" ");
         }
         System.out.println ();
         final int mini = 
                   v.getMinI();
         final int maxi = 
                   v.getMaxI();
         
         System.out.print(" ");
         System.out.print
               ("MinIndex = ");
         System.out.print
                        (mini);
         System.out.print(" ");

         System.out.print
               ("MaxIndex = ");
         System.out.println
                        (maxi);         
         
         System.out.println ();
      }     
   }        

   public final AmDB getAM()
                 { return am; }
   
   public GrDBU(final MST m)
   {
      nV =       m.getNE()+1;
      rand = null;
      am= null;

      vLst = new ArrayList<>(); 

      loadGMST(m);
      if (DEBUG)        dump();
   }
   
   public GrDBU
   (
      final double   r1,
      final double   r2,
      final int      nv,
      final int      s
   ) 
   {
      if(nv<M1)
      {
         System.out.println
              ("nv too small");
         System.exit      (nv);
      }
      else if(nv>M2)
      {
         System.out.println
              ("nv too large");
         System.exit      (nv);
      }
      rand  =  new   Random(s); 
      am    =   new AmDB(r1,r2,nv,s);
      vLst = new ArrayList<>();
      nV    =               nv;

      loadG                 ();
      if (DEBUG)        dump();
   }

}


