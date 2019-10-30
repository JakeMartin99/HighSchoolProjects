/*
Jake Martin
5-23-2017
3d Game
MainMenu
*/

//Imports the necessary libraries
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
//import sun.audio.*;
import javax.sound.sampled.*;

//Creates the MainMenu class as a JFrame subclass that implements MouseInputListener
public class MainMenu extends JFrame implements MouseInputListener
{
   
   //Creates instance variables for components and modes
   public JPanel panel;
   public Drawer draw;
   public boolean runGame = false;
   public boolean runLead = false;
   public boolean runSet = false;
   
   public static Clip play(File file)
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
         return clip;
      }
      catch (Exception exc)
      {
         System.out.println(exc);
      }
      return null;
   }
   
   public static void stop(Clip clip)
   {
      try{
         clip.flush();
         clip.stop();
         System.out.println("test");
      }
      catch (Exception exc)
      {
         System.out.println(exc);
      }
   }
   
   //Creates the main method
   public static void main(String[] args) throws Exception
   {
      //Creates a new MainMenu and starts audio
      MainMenu window = new MainMenu();
      File audioMain = new File("BackgroundMain.wav");
      File audioGame = new File("BackgroundGame.wav");
      File audioSub = new File("BackgroundSub.wav");
      Clip playing = play(audioMain);
      
      //Indefinitely loops...
      while(true)
      {
         //If runGame...
         if(window.runGame)
         {
            //Switches audio
            stop(playing);
            playing = play(audioGame);
            
            //Creates a new Game
            Game game = new Game();
            delay(500);
            
            //While game.running, repaints at 60fps
            while(game.running)
            {
               delay(1000/60);
               game.repaint();
            }
            
            //Disposes game and returns to MainMenu mode
            game.dispose();
            window.setVisible(true);
            window.runGame = false;
            delay(500);
            stop(playing);
            playing = play(audioMain);
         }
         //Else if runLead...
         else if(window.runLead)
         {
            //Switches audio
            stop(playing);
            playing = play(audioSub);
            
            //Creates a new Leaderboard
            Leaderboard lead = new Leaderboard();
            delay(500);
            
            //While lead.running, repaints at 60fps
            while(lead.running)
            {
               delay(1000/60);
               lead.repaint();
            }
            
            //Disposes lead and returns to MainMenu mode
            lead.dispose();
            window.setVisible(true);
            window.runLead = false;
            delay(500);
            stop(playing);
            playing = play(audioMain);
         }
         //Else if runSet...
         else if(window.runSet)
         {
            //Switches audio
            stop(playing);
            playing = play(audioSub);
            
            //Creates a new Settings
            Settings set = new Settings();
            delay(500);
            
            //While set.running, repaints at 60fps
            while(set.running)
            {
               delay(1000/60);
               set.repaint();
            }
            
            //Disposes set and returns to MainMenu mode
            set.dispose();
            window.setVisible(true);
            window.runSet = false;
            delay(500);
            stop(playing);
            playing = play(audioMain);
         }
         //Else, repaints window at 60fps
         else
         {
            delay(1000/60);
            window.repaint();
         }
      }
   }
   
   //Constructs the MainMenu object
   public MainMenu()
   {
      //Calls the JFrame constructor
      super("GUNNER 3D");
      //Sets JFrame settings
      this.setLayout(null);
      this.setLocationRelativeTo(null);
      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
      this.setBounds(100, 100, 600, 600);
      this.setResizable(false);
      Image image = Toolkit.getDefaultToolkit().getImage("Cursor.png");
      this.setIconImage(image);
      
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
      panel.add(draw, BorderLayout.CENTER);
      
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
      
      //Creates instance variables for window, mouse, Images, and Buttons
      private MainMenu window;
      private int mouseX;
      private int mouseY;
      private boolean isClicked = false;
      private Image[] images = new Image[90];
      private int count = 0;
      private Button play = new Button("PLAY", 100, 150, 50);
      private Button lead = new Button("LEADERBOARD", 50, 100, 300);
      private Button set = new Button("SETTINGS ", 50, 165, 400);
      private Button quit = new Button("QUIT", 50, 235, 500);
      
      //Constructs the Drawer object with window
      public Drawer(MainMenu window)
      {
         //Sets instance variables
         this.window = window;
         for(int i=1; i<=images.length; i++)
         {
            images[i-1] = Toolkit.getDefaultToolkit().getImage("Background (" + i + ").png");
         }
      }
      
      //Creates the paintComponent method with Graphics
      public void paintComponent(Graphics g)
      {
         //Draws and then increments the "background" gif
         g.drawImage(this.images[this.count], 0, 0, null);
         this.count++;
         if(this.count >= this.images.length)
         {
            this.count = 0;
         }
         
         //Displays the Buttons
         this.play.display(g, this.mouseX, this.mouseY);
         this.lead.display(g, this.mouseX, this.mouseY);
         this.set.display(g, this.mouseX, this.mouseY);
         this.quit.display(g, this.mouseX, this.mouseY);
         
         //If isClicked on a Button, change the mode or exit the program
         if(this.isClicked)
         {
            if(this.play.checkCoord(this.mouseX, this.mouseY))
            {
               window.setVisible(false);
               window.runGame = true;
               this.isClicked = false;
            }
            else if(this.lead.checkCoord(this.mouseX, this.mouseY))
            {
               window.setVisible(false);
               window.runLead = true;
               this.isClicked = false;
            }
            else if(this.set.checkCoord(this.mouseX, this.mouseY))
            {
               window.setVisible(false);
               window.runSet = true;
               this.isClicked = false;
            }
            else if(this.quit.checkCoord(this.mouseX, this.mouseY))
            {
               System.exit(0);
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