/*
Jake Martin
*/

//Imports the necessary libraries
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

//Creates the InvaderWindow class as a JFrame subclass that implements several "Listener" interfaces
public class InvaderWindow extends JFrame implements KeyListener, MouseInputListener
{
   
   //Declares private variables for frame, storedFrame, score, and JFrame components
   private int frame = 16;
   private int storedFrame = 0;
   private static int score = 0;
   private JPanel panel;
   private Displayer display;
   private Sprite character;
   private Sprite shipArr;
   private JLabel label;
   
   //Creates the main method
   public static void main(String[] args)
   {
      //Creates a new InvaderWindow object
      InvaderWindow game = new InvaderWindow();
      
      //While true...
      while(true)
      {
         //Call the repaint method on game
         game.repaint();
         //Call the delay method with 20
         InvaderWindow.delay(20);
      }
   }
   
   //Constructs the InvaderWindow object
   public InvaderWindow()
   {
      //Calls the JFrame constructor
      super("EARTH INVADERS!");
      //Sets JFrame settings
      this.setLayout(null);
      this.setLocationRelativeTo(null);
      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
      this.setBounds(0, 0, 720, 860);
      this.setResizable(false);
      
      //Adds "Listeners"
      this.addMouseListener(this);
      this.addMouseMotionListener(this);
      this.addKeyListener(this);
      this.setFocusable(true);
      
      //Initializes the public variables
      panel = new JPanel();
      display = new Displayer();
      character = new Character(50, Color.GREEN);
      display.add(character);
      shipArr = new ShipArr(5, 11, Color.GRAY);
      display.add(shipArr);
      label = new JLabel("SCORE: " + this.score);
      label.setForeground(Color.WHITE);
      
      //Sets panel's layout and adds each component to it
      BorderLayout layout = new BorderLayout();
      panel.setLayout(layout);
      panel.add(display, BorderLayout.CENTER);
      panel.add(label, BorderLayout.NORTH);
      
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
      //Repaints the JFrame
      Container c = this.getContentPane();
      c.repaint();
      
      //Calls the moveSprites method on display
      display.moveSprites();
      
      //Increments frame
      this.frame++;
   }
   
   @Override //From KeyListener
   public void keyTyped(KeyEvent event){}
   
   @Override //From KeyListener
   public void keyPressed(KeyEvent event)
   {
      //Stores the KeyEvent's code
      int key = event.getKeyCode();
      
      //If key is space and display's laserCanFire and frame is > than storedFrame + 15...
      if(key == KeyEvent.VK_SPACE && (display.getLaserCanFire() && this.frame > this.storedFrame + 15))
      {
         //Set laser as a new Laser at character's location and set its change
         Sprite laser = new Laser(character.getX()+7, character.getY()-6, Color.RED);
         laser.setChange(-15);
         //Add laser to display and setLaserCanFire to false
         display.add(laser);
         display.setLaserCanFire(false);
         //Set storedFrame to frame
         this.storedFrame = this.frame;
         //Set label's text to "Score: " + score
         label.setText("Score: " + this.score);
      }
      
      //If key is left arrow...
      if(key == KeyEvent.VK_LEFT)
      {
         //Set character's change to -5
         character.setChange(-5);
      }
      //Else if key is right arrow...
      else if(key == KeyEvent.VK_RIGHT)
      {
         //Set character's change to 5
         character.setChange(5);
      }
   }
   
   @Override //From KeyListener
   public void keyReleased(KeyEvent event)
   {
      //Stores the KeyEvent's code
      int key = event.getKeyCode();
      
      //If key is space...
      if(key == KeyEvent.VK_SPACE)
      {
         //setLaserCanFire to true
         display.setLaserCanFire(true);
      }
      
      //If key is left arrow or right arrow...
      if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT)
      {
         //Set character's change to 0
         character.setChange(0);
      }
   }
   
   @Override //From MouseInputListener
   public void mouseClicked(MouseEvent event)
   {
      //Requests focus to the JFrame
      this.requestFocusInWindow();
   }
   
   @Override //From MouseInputListener
   public void mouseDragged(MouseEvent event){}
   
   @Override //From MouseInputListener
   public void mousePressed(MouseEvent event){}
   
   @Override //From MouseInputListener
   public void mouseReleased(MouseEvent event){}
   
   @Override //From MouseInputListener
   public void mouseEntered(MouseEvent event){}
   
   @Override //From MouseInputListener
   public void mouseExited(MouseEvent event){}
   
   @Override //From MouseInputListener
   public void mouseMoved(MouseEvent event){}
   
   //Creates the static Displayer class as a JComponent subclass
   static class Displayer extends JComponent
   {
      
      //Creates private variables for sprites and laserCanFire
      private ArrayList<Sprite> sprites = new ArrayList<>();
      private boolean laserCanFire = true;
      
      //Creates the necessary paintComponent method with Graphics g
      public void paintComponent(Graphics g)
      {
         //Sets g's color
         g.setColor(new Color(255, 75, 0));
         //Draws a filled rectangle
         g.fillRect(0, 725, 720, 135);
         
         //For each Sprite in sprites...
         for(int i=0; i<sprites.size(); i++)
         {  
            //Draw the sprite
            sprites.get(i).drawSprite(g);
         }
      }
      
      //Creates the add method with sprite
      public void add(Sprite sprite)
      {
         //Adds sprite to the beginning of sprites
         sprites.add(0, sprite);
      }
      
      //Creates the remove method with index
      public void remove(int index)
      {
         //Removes sprites' element at index
         sprites.remove(index);
      }
      
      //Creates the moveSprites method
      public void moveSprites()
      {
         //Set shipArrInd to 0
         int shipArrInd = 0;
         
         //For each Sprite in sprites...
         for(int i=0; i<sprites.size(); i++)
         {
            //Set element to sprites' element
            Sprite element = sprites.get(i);
            //Call the changePos method on element
            element.changePos();
            
            //If element is an instanceof Laser...
            if(element instanceof Laser)
            {
               //For each Sprite in sprites...
               for(int j=0; j<sprites.size(); j++)
               {
                  //If it isn't the same as element and a call of hasCollidedWith returns true...
                  if(i != j && sprites.get(j).hasCollidedWith(element))
                  {
                     //If sprites.get(j) is an instanceof ShipArr...
                     if(sprites.get(j) instanceof ShipArr)
                     {
                        //Remove element from sprites and increment score
                        this.remove(i);
                        score += 10;
                     }
                     //Else...
                     else
                     {
                        //If sprites.get(j) is an instanceof Character...
                        if(sprites.get(j) instanceof Character)
                        {
                           //Call the delay method with 1500 and exit the program
                           InvaderWindow.delay(1500);
                           System.exit(0);
                        }
                        //Remove both from sprites
                        if(i > j)
                        {
                           this.remove(i);
                           this.remove(j);
                        }
                        else
                        {
                           this.remove(j);
                           this.remove(i);
                        }
                     }
                     //Break from the inner loop
                     break;
                  }
               }
               //If its y is outside of the screen...
               if(element.getY() < -20 || element.getY() > 725)
               {
                  //Remove it from sprites
                  this.remove(i);
               }
            }
            //Else if element is an instanceof ShipArr...
            else if(element instanceof ShipArr)
            {
               //Set shipArrInd to i
               shipArrInd = i;
            } 
         }
         
         //1% chance to...
         if(Math.random() > 0.99)
         {
            //Set fireShip to the Sprite and shipArrInd
            Sprite fireShip = sprites.get(shipArrInd);
            //Set laser as a new Laser at fireShip's position and set its change
            Sprite laser = new Laser(fireShip.getX()+7, fireShip.getY()+6+25, Color.CYAN);
            laser.setChange(15);
            //Add laser
            this.add(laser);
         }
      }
      
      //Creates the getLaserCanFire method
      public boolean getLaserCanFire()
      {
         //Returns laserCanFire
         return this.laserCanFire;
      }
      
      //Creates the setLaserCanFire method with canFire
      public void setLaserCanFire(boolean canFire)
      {
         //Sets laserCanFire to canFire
         this.laserCanFire = canFire;
      }
                 
   }
   
}