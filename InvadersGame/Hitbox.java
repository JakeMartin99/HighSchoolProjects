/*
Jake Martin
*/

//Creates the Hitbox class
public class Hitbox
{
   
   //Creates private variables for each value
   private int x1;
   private int y1;
   private int x2;
   private int y2;
   
   //Constructs the Hitbox object with each value
   public Hitbox(int x1, int y1, int x2, int y2)
   {
      //Sets each value
      this.x1 = x1;
      this.y1 = y1;
      this.x2 = x2;
      this.y2 = y2;
   }
   
   //Creates the hasCollidedWith method with box2
   public boolean hasCollidedWith(Hitbox box2)
   {
      //If this point 1 overlaps...
      if(this.x1() >= box2.x1() && this.y1 >= box2.y1() && this.x1() <= box2.x2() && this.y1 <= box2.y2())
      {
         //Return true
         return true;
      }
      //Else if this point 2 overlaps...
      else if(this.x2() >= box2.x1() && this.y2 >= box2.y1() && this.x2() <= box2.x2() && this.y2 <= box2.y2())
      {
         //Return true
         return true;
      }
      //Else if box2 point 1 overlaps...
      if(box2.x1() >= this.x1() && box2.y1 >= this.y1() && box2.x1() <= this.x2() && box2.y1 <= this.y2())
      {
         //Return true
         return true;
      }
      //Else if box2 point 2 overlaps...
      else if(box2.x2() >= this.x1() && box2.y2 >= this.y1() && box2.x2() <= this.x2() && box2.y2 <= this.y2())
      {
         //Return true
         return true;
      }
      //Returns false
      return false;
   }
   
   //Creates the x1 method
   public int x1()
   {
      //Returns x1
      return this.x1;
   }
   
   //Creates the y1 method
   public int y1()
   {
      //Returns y1
      return this.y1;
   }
   
   //Creates the x2 method
   public int x2()
   {
      //Returns x2
      return this.x2;
   }
   
   //Creates the y2 method
   public int y2()
   {
      //Returns y2
      return this.y2;
   }
      
}