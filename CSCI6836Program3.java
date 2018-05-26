
package                csci6836program3;


public class CSCI6836Program3
{
   private final static int    S =  334;
   private final static int      N = 1000;
   
   public static void 
                     main(String[] args) 
   {
      GrDBU gd = 
               new GrDBU(-5.0, 5.0,N,S);       

      Prim p        =      new Prim(gd);
      p.dump                         ();
      
      Tremaux t = new 
                  Tremaux(p.getGr(),gd);
      t.searchD                     (0);

   }
                     
                     
   
}
