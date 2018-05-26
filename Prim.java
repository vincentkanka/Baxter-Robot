
package csci6836program3;

import java.util.ArrayList;

/**
 *
 * @author billp
 */
public class Prim
{
   private final 
      ArrayList<Integer>           vLst;
   private final 
      ArrayList<Boolean>           iLst;
 
   final private int                 nV;
   final private GrDBU              gDB;
   final private MST                mst;
   
   public GrDBU getGr()
                 { return mst.getGr(); }
   
   public void dump()
   {
      final int n =         mst.getNE();
      double t    =                   0;
      System.out.println             ();
      System.out.println
                ("Dump of MST -- Prim");
      for (int i=0; i<n; i++)
      {
         Edge e =          mst.getAt(i);
         final int v1 =     e.getFrom();
         final int v2 =       e.getTo();
         final double w = e.getWeight();

         t           +=               w;
         System.out.print         (i+1);
         System.out.print        (") ");
         System.out.print
                   ("Edge connecting ");
         System.out.print          (v1);
         System.out.print         (" ");
         System.out.print          (v2);
         System.out.print(" Weight = ");
         System.out.println         (w);

      }
      System.out.print
                ("Total MST Weight = ");
      System.out.print              (t);
      System.out.print
         (" Number of Edges in MST = ");
      System.out.println            (n);
      System.out.println             ();
      System.out.print("Graph of MST:");

      mst.dumpTree();
   }
   
   public void addTo(final int e)
                        { vLst.add(e); }
    
   public int getAt(final int i)
   {
     final int e =          vLst.get(i);
     return                           e;
   }
   public int getNE()
                 { return vLst.size(); }
   
   private int findMinV()
   {
      double min =       Integer.MAX_VALUE;
      int mV  =                      -1;
      for(int v : vLst)
      {
         final Vertex vt = gDB.getAt(v);
         final int minI  = vt.getMinI();
         if(minI != -1)
         {
            final double nm    = 
                         vt.getWt(minI);
            if (nm < min)
            {
               min    =              nm;
               mV     =               v;
            }
         }
      }   
      return                         mV;
   }
   
   private void addV(final int x)
   {

      final Vertex vt =    gDB.getAt(x);
      final int nE    =      vt.getNE();
      for(int k=0; k<nE; ++k)
      {
         final int ei =   vt.getEdge(k);
         final boolean b = iLst.get(ei);
         
         //
         // If this 
         // edge connects
         // this vertex 
         // to a vertex
         // already in the
         // list, exclude it.
         //
         if(b) 
         {
            
            //
            // Exclude this
            // edge (indexed 
            // by k) and the
            // edge it is
            // indexing to.
            //
            vt.exclude              (k);
            final Vertex vx 
                        = gDB.getAt(ei);
            final int ed = vx.findEI(x);
            vx.exclude             (ed);
         }
      }
 
      vLst.add                      (x);
      iLst.set                 (x,true);
   }
     
   private void pAlg(final int sI)
   {
      //
      // Beginning
      // vertex.
      //
      final int initV  =             sI;
      addV                      (initV);
      final int nv     =    gDB.getNV();
      
      //
      // Process the
      // rest of the 
      // vertices 
      // after the first.
      // (start k at 1)
      //
      for (int k=1; k<nv; k++)
      {
         final int mVI    =  findMinV();
         final Vertex mV  = 
                         gDB.getAt(mVI);

         final int mFI   = mV.getMinI();
         final int mE    = 
                        mV.getEdge(mFI);
         final double wt    =   
                          mV.getWt(mFI);
      
         final Edge e    = 
                    new Edge(mVI,mE,wt);
         
         addV                      (mE);
      
         mst.addTo                  (e);

      }
   }
   
   
   public Prim(GrDBU g)
   {
      vLst = new          ArrayList<>();
      iLst = new          ArrayList<>();
      mst  = new                  MST();
      nV   =                  g.getNV();
      for (int j=0; j<nV; j++)
                     { iLst.add(false);}
      final int sI =               nV/2;
      gDB          =                  g;
      pAlg                         (sI);
      mst.                 buildGraph();
   }
}
