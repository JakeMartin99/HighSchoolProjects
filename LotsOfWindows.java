import java.awt.*;
import javax.swing.*;

public class LotsOfWindows
{
   
   public static void main(String[] args)
   {
      while(true)
      {
         int x = (int)(Math.random() * 1300) + 10;
         int y = (int)(Math.random() * 700) + 10;
         
         JFrame window = new JFrame("WINDOWS");
         window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
         window.setLocation(x, y);
         window.setSize(500, 250);
         window.setVisible(true);
         
         try{
            Thread.sleep(100);
         }catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
         }
         window.dispose();
      }
   }
   
}