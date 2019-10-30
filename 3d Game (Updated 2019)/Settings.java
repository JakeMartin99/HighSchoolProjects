/*
Jake Martin
5-23-2017
3d Game
Settings
*/

//Imports the necessary libraries
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

//Creates the Settings class as a JFrame subclass that implements MouseInputListener and KeyListener
public class Settings extends JFrame implements MouseInputListener, KeyListener
{
   
   //Creates instance variables for components, running, and Files
   public JPanel panel;
   public Drawer draw;
   public boolean running = true;
   public File set;
   
   //Constructs the Settings object
   public Settings()
   {
      //Calls the JFrame constructor
      super("GUNNER 3D");
      //Sets JFrame settings
      this.setLayout(null);
      this.setLocationRelativeTo(null);
      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
      this.setBounds(0, 0, 285, 500);
      this.setResizable(false);
      Image image = Toolkit.getDefaultToolkit().getImage("Cursor.png");
      this.setIconImage(image);
      
      //Initializes Files, and creates them with default values if nonexistant
      try{
         set = new File("_Settings.store");
         if(set.createNewFile())
         {
            PrintWriter pw = new PrintWriter(set);
            pw.println("RED");
            pw.println("AAA");
            pw.close();
         }
      }catch(IOException e){}
      
      //Adds "Listeners"
      this.addMouseListener(this);
      this.addMouseMotionListener(this);
      this.addKeyListener(this);
      this.setFocusable(true);
      
      //Initializes components
      panel = new JPanel();
      draw = new Drawer(this);
      
      //Sets panel's layout and adds each component to it
      BorderLayout layout = new BorderLayout();
      panel.setLayout(layout);
      panel.add(draw);
      
      //Adds the panel and sets its background
      this.add(panel);
      this.setContentPane(panel);
      Container c = this.getContentPane();
      c.setBackground(Color.BLACK);
      
      //Makes the JFrame visible
      this.setVisible(true);
   }
   
   //Creates the static delay method with time
   public static void delay(int time)
   {
      //Tries to sleep Thread for time ms
      try
      {
         Thread.sleep(time);
      }
      catch(InterruptedException ex)
      {
         Thread.currentThread().interrupt();
      }
   }
   
   //Creates the repaint method
   public void repaint()
   {
      //Repaints the Container
      Container c = this.getContentPane();
      c.repaint();
   }
   
   @Override //From MouseInputListener
   public void mouseClicked(MouseEvent event)
   {
      //Requests focus to the JFrame
      this.requestFocusInWindow();
   }
 
   @Override //From MouseInputListener
   public void mouseDragged(MouseEvent event)
   {
      //Stores the mouse's position and calls setMouse() on draw
      int x = event.getX();
      int y = event.getY();
      draw.setMouse(x, y);
   }
 
   @Override //From MouseInputListener
   public void mousePressed(MouseEvent event)
   {
      //Calls changeClicked() on draw
      draw.changeClicked(true);
   }
 
   @Override //From MouseInputListener
   public void mouseReleased(MouseEvent event)
   {
      //Calls changeClicked() on draw
      draw.changeClicked(false);
   }
 
   @Override //From MouseInputListener
   public void mouseEntered(MouseEvent event)
   {
   }
 
   @Override //From MouseInputListener
   public void mouseExited(MouseEvent event)
   {
   }
 
   @Override //From MouseInputListener
   public void mouseMoved(MouseEvent event)
   {
      //Stores the mouse's position and calls setMouse() on draw
      int x = event.getX();
      int y = event.getY();
      draw.setMouse(x, y);
   }
   
   @Override //From KeyListener
   public void keyTyped(KeyEvent event)
   {
      //If draw.name is < than 3 characters, add the key typed to it.
      if(draw.name.length() < 3)
      {
         char key = event.getKeyChar();
         draw.name += (key + "").toUpperCase();
      }
   }
   
   @Override //From KeyListener
   public void keyPressed(KeyEvent event)
   {
   }
   
   @Override //From KeyListener
   public void keyReleased(KeyEvent event)
   {
   }
   
   //Creates the static Drawer class as a JComponent subclass
   static class Drawer extends JComponent
   {
      
      //Creates instance variables for window, mouse, Files, and exit
      private Settings window;
      private int mouseX;
      private int mouseY;
      private boolean isClicked;
      private String color;
      public String name;
      private Button exit = new Button("SAVE&EXIT ", 40, 10, 400);
      
      //Constructs the Drawer object with window
      public Drawer(Settings window)
      {
         //Sets instance variables
         this.window = window;
         //Reads data from Files
         try{
            Scanner in = new Scanner(this.window.set);
            this.color = in.nextLine();
            this.name = in.nextLine();
            in.close();
         }catch(IOException e){}
      }
      
      //Creates the paintComponent method with Graphics
      public void paintComponent(Graphics g)
      {
         //Draws all of the graphical entities
         g.setFont(new Font("Arial", Font.PLAIN, 25));
         g.setColor(new Color((int)(Math.random()*255), 0, (int)(Math.random()*255)));
         g.drawString("Color:   " + this.color, 50, 50);
         g.drawString("Name:  " + this.name, 50, 100);
         g.drawString("  " + Math.round(Math.random()*1000000000000000000.), 0, 200);
         g.drawString("  " + Math.round(Math.random()*1000000000000000000.), 0, 250);
         g.drawString("  " + Math.round(Math.random()*1000000000000000000.), 0, 300);
         g.drawString("  " + Math.round(Math.random()*1000000000000000000.), 0, 350);
         this.exit.display(g, this.mouseX, this.mouseY);
         //If isClicked, sets window.running to false and/or updates Files depending on position 
         if(this.isClicked && this.exit.checkCoord(this.mouseX, this.mouseY))
         {
            this.window.running = false;
            this.window.set.delete();
            try{
               this.window.set = new File("_Settings.store");
               if(this.window.set.createNewFile())
               {
                  PrintWriter pw = new PrintWriter(this.window.set);
                  pw.println(this.color);
                  pw.println(this.name);
                  pw.close();
               }
            }catch(IOException e){}
         }
         else if(this.isClicked && this.mouseX > 135 && this.mouseX < 200 && this.mouseY > 50 && this.mouseY < 75)
         {
            if(this.color.equals("RED"))
            {
               this.color = "GREEN";
            }
            else if(this.color.equals("GREEN"))
            {
               this.color = "BLUE";
            }
            else
            {
               this.color = "RED";
            }
         }
         else if(this.isClicked && this.mouseX > 135 && this.mouseX < 200 && this.mouseY > 100 && this.mouseY < 125 && this.name.length() >= 3)
         {
            this.name = "";
         }
         this.isClicked = false;
      }
      
      //Creates the setMouse method with position
      public void setMouse(int x, int y)
      {
         //Sets mouse's position
         this.mouseX = x;
         this.mouseY = y;
      }  
      
      //Creates the changeClicked method with state
      public void changeClicked(boolean state)
      {
         //Sets isClicked to state
         this.isClicked = state;
      }  
   }
    
}