/*
Jake Martin
5-23-2017
3d Game
Button
*/

//Imports the necessary libraries
import java.awt.*;

//Creates the Button class
public class Button
{
   
   //Creates instance variables for text, size, and position
   private String text;
   private int size;
   private int x;
   private int y;
   
   //Constructs the Button object with text, size and position
   public Button(String text, int size, int x, int y)
   {
      //Assigns parameters to instance variables
      this.text = text;
      this.size = size;
      this.x = x;
      this.y = y;
   }
   
   //Creates the display method with Graphics and position 2
   public void display(Graphics g, int x2, int y2)
   {
      //Calculates values relevant to drawing the button
      int semiSize = this.size/4;
      double length = this.text.length();
      if(this.text.contains(" "))
      {
         length -= 1.5;
      }
      //Draws the Button
      g.setColor(Color.DARK_GRAY);
      g.fillRoundRect(this.x, this.y, (int)(semiSize*3*length), semiSize*5, this.size, this.size);
      g.setColor(Color.GRAY);
      g.fillRoundRect(this.x+5, this.y+5, (int)(semiSize*3*length)-10, semiSize*5-10, this.size, this.size);
      //Depending on where position 2 is, changes the text's color
      if(this.checkCoord(x2, y2))
      {
         g.setColor(Color.WHITE);
      }
      else
      {
         g.setColor(Color.BLACK);
      }
      //Draws the text
      g.setFont(new Font("Arial", Font.PLAIN, this.size));
      g.drawString(this.text, this.x+semiSize, this.y+this.size);
   }
   
   //Creates the checkCoord method with position 2
   public boolean checkCoord(int x2, int y2)
   {
      //If position 2 is within the bounds of Button, returns true
      if(x2 >= this.x && x2 <= (int)(this.x+(this.size/4)*3*this.text.length()) && y2 >= this.y + (this.size/4) && y2 <= this.y + (3*this.size)/2)
      {
         return true;
      }
      //Returns false
      return false;
   }
   
}