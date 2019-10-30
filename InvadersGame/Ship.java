/*
Jake Martin
*/

//Imports the necessary libraries
import java.awt.*;

//Creates the Ship class as a Sprite subclass
public class Ship extends Sprite
{
   
   //Creates private variables for x, y, and change
   private int x;
   private int y;
   private int change = 0;
      
   //Constructs the Ship object
   public Ship(int x, int y, Color color)
   {
      //Calls the Sprite constructor, sets x, and sets y
      super(color, new Hitbox(x, y, x+25, y+25));
      this.x = x;
      this.y = y;
   }
   
   //Creates the drawSprite method with Graphics g
   public void drawSprite(Graphics g)
   {
      //Sets g's color
      g.setColor(super.getColor());
      //Draws a filled rectangle
      g.fillRect(this.x, this.y, 25, 25);
   }
   
   //Creates the changePos method
   public void changePos()
   {
      //Adds change to x
      this.x += this.change;
      //Sets the hitbox
      Hitbox box = this.getHitbox();
      this.setHitbox(this.x, box.y1(), this.x+25, box.y2());
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
      //Returns y
      return this.y;
   }
   
   //Creates the setChange method with val
   public void setChange(int val)
   {
      //Sets change to val
      this.change = val;
   }
   
   //Creates the setY method with val
   public void setY(int val)
   {
      //Sets y to val;
      this.y = val;
      //Sets the hitbox
      Hitbox box = this.getHitbox();
      this.setHitbox(box.x1(), this.y, box.x2(), this.y+25);
   }
   
}