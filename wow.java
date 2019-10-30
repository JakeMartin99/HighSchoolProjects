public class wow
{
   
   public static void main(String[] args)
   {
      int count = 0;
      
      while(true)
      {
         char Char = (char)(int)(Math.random()*250+30);
         System.out.print(Char);
         try
         {
            Thread.sleep(20);
         }
         catch(Exception e)
         {
         }
         count++;
         if(count > 50)
         {
            System.out.println();
            count = 0;
         }
      }
   }
   
}