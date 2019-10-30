/*
Jake Martin
*/

//Imports the necessary libraries
import java.awt.*;

//Creates the abstract Sprite class
public abstract class Sprite
{
   
   //Creates private variables for color and hitbox
   private Color color;
   private Hitbox hitbox;
   
   //Constructs the Sprite object with color and hitbox
   public Sprite(Color color, Hitbox hitbox)
   {
      //Sets color and hitbox
      this.color = color;
      this.hitbox = hitbox;
   }
   
   //Creates the getColor method
   public Color getColor()
   {
      //Returns color
      return this.color;
   }
   
   //Creates the getHitbox method
   public Hitbox getHitbox()
   {
      //Returns hitbox
      return this.hitbox;
   }
   
   //Creates the setHitbox method with each value
   public void setHitbox(int x1, int y1, int x2, int y2)
   {
      //Sets hitbox to a new Hitbox
      this.hitbox = new Hitbox(x1, y1, x2, y2);
   }
   
   //Creates the hasCollidedWith method with sprite
   public boolean hasCollidedWith(Sprite sprite)
   {
      //Returns the call of hasCollidedWith on hitbox
      return this.hitbox.hasCollidedWith(sprite.getHitbox());
   }
   
   //Creates the abstract drawSprite method with Graphics g
   public abstract void drawSprite(Graphics g);
   
   //Creates the abstract changePos method
   public abstract void changePos();
   
   //Creates the abstract getX method
   public abstract int getX();
   
   //Creates the abstract getY method
   public abstract int getY();
   
   //Creates the abstract setChange method with val
   public abstract void setChange(int val);
   
   //Creates the abstract setY method with val
   public abstract void setY(int val);
   
}