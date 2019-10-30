/*
Jake Martin
5-23-2017
3d Game
Leaderboard
*/

//Imports the necessary libraries
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

//Creates the Leaderboard class as a JFrame subclass that implements MouseInputListener
public class Leaderboard extends JFrame implements MouseInputListener
{
   
   //Creates instance variables for components, running, and Files
   public JPanel panel;
   public Drawer draw;
   public boolean running = true;
   public File lead;
   
   //Constructs the Leaderboard object
   public Leaderboard()
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
         lead = new File("_Leaderboard.store");
         if(lead.createNewFile())
         {
            PrintWriter pw = new PrintWriter(lead);
            pw.println("1. 3000-DEV");
            pw.println("2. 2000-DEV");
            pw.println("3. 1000-DEV");
            pw.close();
         }
      }catch(IOException e){}
      
      //Adds "Listeners"
      this.addMouseListener(this);
      this.addMouseMotionListener(this);
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
   
   //Creates the static Drawer class as a JComponent subclass
   static class Drawer extends JComponent
   {
      
      //Creates instance variables for window, mouse, Files, and exit
      private Leaderboard window;
      private int mouseX;
      private int mouseY;
      private boolean isClicked;
      private String line1;
      private String line2;
      private String line3;
      private Button exit = new Button("MAIN MENU ", 40, 10, 400);
      
      //Constructs the Drawer object with window
      public Drawer(Leaderboard window)
      {
         //Sets instance variables
         this.window = window;
         //Reads data from Files
         try{
            Scanner in = new Scanner(this.window.lead);
            this.line1 = in.nextLine();
            this.line2 = in.nextLine();
            this.line3 = in.nextLine();
            in.close();
         }catch(IOException e){}
      }
      
      //Creates the paintComponent method with Graphics
      public void paintComponent(Graphics g)
      {
         //Draws all of the graphical entities
         g.setFont(new Font("Arial", Font.PLAIN, 25));
         g.setColor(new Color(0, (int)(Math.random()*255), (int)(Math.random()*255)));
         g.drawString(this.line1, 50, 50);
         g.drawString(this.line2, 50, 100);
         g.drawString(this.line3, 50, 150);
         g.drawString("  " + Math.round(Math.random()*1000000000000000000.), 0, 200);
         g.drawString("  " + Math.round(Math.random()*1000000000000000000.), 0, 250);
         g.drawString("  " + Math.round(Math.random()*1000000000000000000.), 0, 300);
         g.drawString("  " + Math.round(Math.random()*1000000000000000000.), 0, 350);
         this.exit.display(g, this.mouseX, this.mouseY);
         //If exit isClicked, sets window.running to false
         if(this.isClicked && this.exit.checkCoord(this.mouseX, this.mouseY))
         {
            this.window.running = false;
         }
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