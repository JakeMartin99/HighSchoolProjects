/*
Jake Martin
5-23-2017
3d Game
Game
*/

//Imports the necessary libraries
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;
import java.io.*;

//Creates the Game class as a JFrame subclass that implements MouseInputListener
public class Game extends JFrame implements MouseInputListener
{
   
   //Creates instance variables for components, running, and Files
   public JPanel panel;
   public Drawer draw;
   public boolean running = true;
   public File lead;
   public File set;
   
   //Constructs the Game object
   public Game()
   {
      //Calls the JFrame constructor
      super("GUNNER 3D");
      //Sets JFrame settings
      this.setLayout(null);
      this.setLocationRelativeTo(null);
      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
      this.setBounds(0, 0, 900, 900);
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
      this.setFocusable(true);
      
      //Initializes components
      panel = new JPanel();
      draw = new Drawer(this, image);
      
      //Sets panel's layout, adds each component to it, and removes the cursor
      BorderLayout layout = new BorderLayout();
      panel.setLayout(layout);
      panel.add(draw);
      Toolkit kit = Toolkit.getDefaultToolkit();
      Cursor cursor = kit.createCustomCursor(new BufferedImage(1, 1, 2), new Point(0, 0), "");
      panel.setCursor(cursor);
      
      //Adds the panel and sets its background
      this.add(panel);
      this.setContentPane(panel);
      Container c = this.getContentPane();
      c.setBackground(Color.WHITE);
      
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
      
      //Creates instance variables for window, mouse, Images, lasers, targets, timeLeft, and Files
      private Game window;
      private int mouseX;
      private int mouseY;
      private boolean isClicked;
      private Image scene = Toolkit.getDefaultToolkit().getImage("Scene.png");
      private Image cursor;
      private Image[] weapons = new Image[7];
      private ArrayList<Laser> lasers = new ArrayList<>();
      private Target[] targets = new Target[35];
      private double timeLeft;
      private int score = 0;
      private String name;
      private int score1;
      private String name1;
      private int score2;
      private String name2;
      private int score3;
      private Color color;
      
      //Constructs the Drawer object with window and cursor
      public Drawer(Game window, Image cursor)
      {
         //Sets instance variables
         this.window = window;
         this.cursor = cursor;
         
         for(int i=0; i<targets.length; i++)
         {
            targets[i] = new Target(100 + 700*Math.random(), 100 + 550*Math.random(), Math.random()*500 + 100, Math.random());
         }
         
         for(int i=0; i<weapons.length; i++)
         {
            weapons[i] = Toolkit.getDefaultToolkit().getImage("Weapon" + i + ".png");
         }
         
         this.timeLeft = 30;
         
         //Reads data from Files
         try{
            Scanner in = new Scanner(this.window.lead);
            String line1 = in.nextLine();
            this.score1 = Integer.parseInt(line1.substring(3, line1.length()-4));
            this.name1 = line1.substring(line1.length()-3);
            String line2 = in.nextLine();
            this.score2 = Integer.parseInt(line2.substring(3, line2.length()-4));
            this.name2 = line2.substring(line2.length()-3);
            String line3 = in.nextLine();
            this.score3 = Integer.parseInt(line3.substring(3, line3.length()-4));
            in.close();
         }catch(IOException e){}
         try{
            Scanner in = new Scanner(this.window.set);
            String val = in.nextLine();
            if(val.equals("RED"))
            {
               this.color = Color.RED;
            }
            else if(val.equals("GREEN"))
            {
               this.color = Color.GREEN;
            }
            else
            {
               this.color = Color.BLUE;
            }
            this.name = in.nextLine();
            if(name.equals("EGG"))
            {
               this.color = new Color(255, 100, 180, 150);
            }
            in.close();
         }catch(IOException e){}
      }
      
      //Creates the paintComponent method with Graphics
      public void paintComponent(Graphics g)
      {
         //Draws the "background"
         g.drawImage(this.scene, 0, 0, null);
         g.setFont(new Font("Arial", Font.PLAIN, 25));
         g.setColor(Color.WHITE);
         g.drawString("Time Left: " + Math.round(this.timeLeft*10000)/10000., 5, 25);
         g.drawString("Score: " + this.score, 5, 50);
         
         //If mouse is in the screen, draws the cursor
         if(this.mouseX > 0 && this.mouseX < 900 && this.mouseY > 0 && this.mouseY < 900)
         {
            g.drawImage(this.cursor, this.mouseX-52, this.mouseY-52, null);
         }
         
         //Sets the weapon based on its angle
         double weaponAngle = Math.toDegrees(Math.atan((this.mouseY-733.)/(this.mouseX-770.)));
         if(this.mouseX > 770)
         {
            weaponAngle += 180;
         }
         int weaponNum;
         if(weaponAngle >= 95)
         {
            weaponNum = 6;
         }
         else if(weaponAngle >= 65)
         {
            weaponNum = 5;
         }
         else if(weaponAngle >= 40)
         {
            weaponNum = 4;
         }
         else if(weaponAngle >= 30)
         {
            weaponNum = 3;
         }
         else if(weaponAngle >= 20)
         {
            weaponNum = 2;
         }
         else if(weaponAngle >= 10)
         {
            weaponNum = 1;
         }
         else
         {
            weaponNum = 0;
         }
         
         //If mouse isClicked, adds a new Laser to lasers
         if(this.isClicked)
         {
            if(this.name.equals("HAX"))
            {
               this.color = new Color((int)(Math.random()*200)+55, (int)(Math.random()*200)+55, (int)(Math.random()*200)+55);
            }
            weaponAngle = Math.toRadians(weaponAngle)*-1;
            double distance = Math.sqrt(Math.pow((this.mouseY-733.), 2) + Math.pow((this.mouseX-770.), 2));
            lasers.add(new Laser(770, 733, 20, (this.mouseX-770.)/50, (this.mouseY-733.)/50, 10, this.color));
            this.isClicked = false;
         }
         
         //On each Target in targets calls move() and if collidedWith() replaces it and increments score
         for(int i=0; i<targets.length; i++)
         {
            targets[i].move(g);
            if(targets[i].collidedWith(lasers))
            {
               targets[i] = new Target(100 + 700*Math.random(), 100 + 550*Math.random(), Math.random()*500 + 100, Math.random());
               this.score += 10;
            }
         }
         
         //Calls move on each laser in lasers, and removes it if it is not in the screen
         for(int i=0; i<lasers.size(); i++)
         {
            lasers.get(i).move(g);
            int[] pos = lasers.get(i).getPos();
            if(pos[0] < 0 || pos[0] > 900 || pos[1] < 0 || pos[1] > 900 || pos[2] > 900)
            {
               lasers.remove(i);
               i--;
            }
         }
         //Draws the weapon
         g.drawImage(this.weapons[weaponNum], 540, 666, null);
         
         //Decrements timeLeft
         this.timeLeft -= 0.01;
         //If timeLeft is < than 0, sets window.running to false and updates Files if necessary
         if(this.timeLeft < 0)
         {
            this.window.running = false;
            if(this.score > this.score3)
            {
               this.window.lead.delete();
               try{
                  this.window.lead = new File("_Leaderboard.store");
                  if(this.window.lead.createNewFile())
                  {
                     PrintWriter pw = new PrintWriter(this.window.lead);
                     int val1;
                     int val2;
                     int val3;
                     if(this.score > this.score1)
                     {
                        pw.println("1. " + this.score + "-" + this.name);
                        pw.println("2. " + this.score1 + "-" + this.name1);
                        pw.println("3. " + this.score2 + "-" + this.name2);
                     }
                     else if(this.score > this.score2)
                     {
                        pw.println("1. " + this.score1 + "-" + this.name1);
                        pw.println("2. " + this.score + "-" + this.name);
                        pw.println("3. " + this.score2 + "-" + this.name2);
                     }
                     else
                     {
                        pw.println("1. " + this.score1 + "-" + this.name1);
                        pw.println("2. " + this.score2 + "-" + this.name2);
                        pw.println("3. " + this.score + "-" + this.name);
                     }
                     pw.close();
                  }
               }catch(IOException e){}
            }
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