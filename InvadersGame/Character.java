/*
Jake Martin
*/

//Imports the necessary libraries
import java.awt.*;

//Creates the Character class as a Sprite subclass
public class Character extends Sprite
{
   
   //Creates private variables for x and change
   private int x;
   private int change = 0;
      
   //Constructs the Character object with x and color
   public Character(int x, Color color)
   {
      //Calls the Sprite constructor and sets x
      super(color, new Hitbox(x, 700, x+25, 700+25));
      this.x = x;
   }
   
   //Creates the drawSprite method with Graphics g
   public void drawSprite(Graphics g)
   {
      //Sets g's color
      g.setColor(super.getColor());
      //Draws a filled rectangle
      g.fillRect(this.x, 700, 25, 25);
   }
   
   //Creates the changePos method
   public void changePos()
   {
      //If the canMove method returns true...
      if(canMove())
      {
         //Add change to x
         this.x += this.change;
         //Set the hitbox
         Hitbox box = this.getHitbox();
         this.setHitbox(this.x, box.y1(), this.x+25, box.y2());
      }
   }
   
   //Creates the getX method
   public int getX()
   {
      //Returns x
      return this.x;
   }
   
   //Creates the getY method
   public int getY()
   {
      //Returns 700
      return 700;
   }
   
   //Creates the setChange method with val
   public void setChange(int val)
   {
      //Sets change to val
      this.change = val;
   }
   
   //Creates the private canMove method
   private boolean canMove()
   {
      //If x is > than 0+50 and change is < than 0...
      if(this.x > 0+50 && this.change < 0)
      {
         //Return true
         return true;
      }
      //If x is < than 720-50-25 and change is > than 0...
      if(this.x < 720-50-25 && this.change > 0)
      {
         //Return true
         return true;
      }
      
      //Returns false
      return false;
   }
   
   //Creates the setY method with val
   public void setY(int val){}
   
}