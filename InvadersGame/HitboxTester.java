public class HitboxTester
{
   
   public static void main(String[] args)
   {
      Hitbox a = new Hitbox(10, -10, 16, -1);
      Hitbox b = new Hitbox(0, 0, 5, 5);
      
      System.out.println(a.hasCollidedWith(b) + " " + b.hasCollidedWith(a));
   }
   
}