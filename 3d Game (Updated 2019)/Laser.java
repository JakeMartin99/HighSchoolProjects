/*
Jake Martin
5-23-2017
3d Game
Laser
*/

//Imports the necessary libraries
import java.awt.*;
import java.util.*;
import java.io.*;
//import sun.audio.*;
import javax.sound.sampled.*;

//Creates the Laser class
public class Laser
{
   
   //Creates instance variables for 3d motion and pixels
   private double x_speed;
   private double y_speed;
   private double z_speed;
   private ArrayList<Pixel> pixels = new ArrayList<>();
   
   //Constructs the Laser object with 3d position, 3d motion, and color
   public Laser(double x, double y, double z, double x_speed, double y_speed, double z_speed, Color color)
   {
      //Assigns parameters to instance variables
      this.x_speed = x_speed;
      this.y_speed = y_speed;
      this.z_speed = z_speed;
      pixels.add(new Pixel(x, y, z, color));
      pixels.add(new Pixel(x+x_speed, y+y_speed, z+z_speed, color));
      pixels.add(new Pixel(x+(2*x_speed), y+(2*y_speed), z+(2*z_speed), color));
      pixels.add(new Pixel(x+(3*x_speed), y+(3*y_speed), z+(3*z_speed), color));
      
      //Plays Laser.wav
      try{
         File sound = new File("Laser.wav");
         play(sound);
      }catch(Exception e){}
   }
   
   //Creates the move method with Graphics
   public void move(Graphics g)
   {
      //Calls move() and display() on each Pixel in pixels
      for(Pixel pix : pixels)
      {
         pix.move(x_speed, y_speed, z_speed);
         pix.display(g);
         this.x_speed *= 0.999;
         this.y_speed *= 0.999;
      }
   }
   
   //Creates the getPos method
   public int[] getPos()
   {
      //Returns the position of the first Pixel in pixels
      return(pixels.get(0).getPos());
   }
   
   public static void play(File file)
   {
      try{
         final Clip clip = (Clip)AudioSystem.getLine(new Line.Info(Clip.class));
         
         clip.addLineListener(new LineListener()
         {
            @Override
            public void update(LineEvent event)
            {
               if (event.getType() == LineEvent.Type.STOP)
                  clip.close();
            }
         });
         
         clip.open(AudioSystem.getAudioInputStream(file));
         clip.start();
      }
      catch (Exception exc)
      {
         System.out.println(exc);
      }
   }
   
}