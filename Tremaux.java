package csci6836program3;


import java.util.ArrayList;


public class Tremaux 
{

   private final GrDBU      gd;
   private final GrDBU      go;
   private final int        nV;
   private ArrayList<Integer>
                          mA[];
   
   private void clrMA()
   {
      for (ArrayList<Integer> a 
                          : mA)
      {
         final int n =a.size();
         for (int i=0;i<n;++i)
            a.set        (i,0);
      }
   }
   
   private void loadMA()
   {
      mA = new   ArrayList[nV];
      for (int i=0; i<nV; i++)
      {
         final Vertex v = 
                   gd.getAt(i);
         final int s = 
                     v.getNE();
         mA[i]= 
             new ArrayList<>();
         for(int j=0; j<s; j++)
            mA[i].add      (0);
      }
   }

   private int findU
   ( 
      final int v
   )
   {
      final int n = 
                  mA[v].size();
      for(int k=0; k<n; ++k)
         if(mA[v].get(k)==0) 
                      return k;
      return                -1;
   }   

    
   private int findF
   ( 
      final int v
   )
   {
      final int n = 
                  mA[v].size();
      for(int k=0; k<n; ++k)
         if(mA[v].get(k)==2)
                      return k;
      return                -1;
   } 
   

   private void markF
   ( 
      final int v,
      final int i
   )
   {
      mA[v].set(i, 2);
   } 
   
   
   private void markE
   ( 
      final int v,
      final int i
   )
   {
      mA[v].set(i, 1);
   }
   
   private boolean anyM
                  (final int v)
   {
      for(int i : mA[v])
         if (i!=0) return true;
      return             false;
   }

                  
   public void searchD
                  (final int s)
   {
      
      final double xs = go.getX(s);
      final double ys = go.getY(s); 
      
      SimpleLog.write("myFile.dat",xs,ys);
      
      //
      // Step 1.
      //
      int v      =           s;
      boolean bF =        true;
      Vertex                vt;
      clrMA                 ();
           
      //
      // Step 2.
      //
      while(bF) 
         if (findU(v) != -1)
      {
         
         //
         // Step 3.
         //
         vt =      gd.getAt(v);
         final int uP = 
                      findU(v);
         final int e  =
                vt.getEdge(uP);
         
         markE          (v,uP);
         final Vertex u = 
                   gd.getAt(e);
         
         //
         // Get the
         // index of 
         // the edge
         // so it 
         // can be marked.
         //
         final int ix =
                   u.findEI(v);
         if (anyM(e))
         {
            markE       (e,ix);
         }
         else
         {
            final double x = go.getX(e);
            final double y = go.getY(e);
            SimpleLog.write("myFile.dat",x,y);
            markF       (e,ix);
            v     =          e;
         }
      }
      else
      {
         
         //
         // Step 4.
         //
         final int f =findF(v);

         if (f==-1)   
         {
            bF =         false;
            System.out.
                    println(v);
         }
         else
         {
            
           //
           //  Step 5.
           //
           System.out.
                    println(v);
           vt =    gd.getAt(v); 
           final int e = 
                 vt.getEdge(f);
           v =               e; 

         }
      }
      SimpleLog.write("myFile.dat",xs,ys);
   }
   
   
   Tremaux
   (
      final GrDBU            g,
      final GrDBU           gO
   )
   {
      gd =                   g;
      go =                  gO;
      nV =           g.getNV();
      loadMA                ();
   }
}
