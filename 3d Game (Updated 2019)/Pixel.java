/*
Jake Martin
5-23-2017
3d Game
Pixel
*/

//Imports the necessary libraries
import java.awt.*;

//Creates the Pixel class
public class Pixel
{
   
   //Creates instance variables for 3d position and color
   private double x;
   private double y;
   private double z;
   private Color color;
   
   //Constructs the Pixel object with 3d position and color
   public Pixel(double x, double y, double z, Color color)
   {
      //Assigns parameters to instance variables
      this.x = x;
      this.y = y;
      this.z = z;
      this.color = color;
   }
   
   //Creates the getPos method
   public int[] getPos()
   {
      //Returns an int[] containing 3d position
      int[] vals = new int[3];
      vals[0] = (int)this.x;
      vals[1] = (int)this.y;
      vals[2] = (int)this.z;
      return vals;
   }
   
   //Creates the display method with Graphics
   public void display(Graphics g)
   {
      //Draws the Pixel based on its 3d position
      g.setColor(this.color);
      int[] pos = this.getPos();
      int size = (int)(5000/this.z);
      g.fillRect(pos[0]-size/2, pos[1]-size/2, size, size);
   }
   
   //Creates the move method with 3d motion
   public void move(double x, double y, double z)
   {
      //Increments 3d position by 3d motion
      this.x += x;
      this.y += y;
      this.z += z;
   }
   
   //Creates the rotate method with plane, coordinate, and angle
   public void rotate(String plane, double coord1, double coord2, double angle)
   {
      //Based on plane, uses trig to rotate the Pixel around coordinate by angle degrees
      if(plane.equals("XY"))
      {
         double radius = Math.sqrt(Math.pow(coord1-this.x, 2) + Math.pow(coord2-this.y, 2));
         double currentAng = angle + Math.toDegrees(Math.atan((coord2-this.y)/(coord1-this.x)));
         if(this.x < coord1)
            currentAng += 180;
         this.x = radius * Math.cos(Math.toRadians(currentAng)) + coord1;
         this.y = radius * Math.sin(Math.toRadians(currentAng)) + coord2;
      }
      else if(plane.equals("XZ"))
      {
         double radius = Math.sqrt(Math.pow(coord1-this.x, 2) + Math.pow(coord2-this.z, 2));
         double currentAng = angle + Math.toDegrees(Math.atan((coord2-this.z)/(coord1-this.x)));
         if(this.x < coord1)
            currentAng += 180;
         this.x = radius * Math.cos(Math.toRadians(currentAng)) + coord1;
         this.z = radius * Math.sin(Math.toRadians(currentAng)) + coord2;
      }
      else if(plane.equals("YZ"))
      {
         double radius = Math.sqrt(Math.pow(coord1-this.y, 2) + Math.pow(coord2-this.z, 2));
         double currentAng = angle + Math.toDegrees(Math.atan((coord2-this.z)/(coord1-this.y)));
         if(this.y < coord1)
            currentAng += 180;
         this.y = radius * Math.cos(Math.toRadians(currentAng)) + coord1;
         this.z = radius * Math.sin(Math.toRadians(currentAng)) + coord2;
      }
   }
   
}