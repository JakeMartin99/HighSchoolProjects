/*
Jake Martin
10-13-2016
Window Spammer
*/

//Imports the necessary window and delay libraries
import javax.swing.JFrame;
import java.util.Date;
import java.awt.*;
//Imports the necessary sound libraries
import java.io.*;
//import sun.audio.*;
import javax.sound.sampled.*;

public class Windows
{
   
   public static void main(String[] args)
   throws Exception
   {
      //Initializes the Strings message and image
      String message = "";
      String image = "";
      //Loops for 100 windows
      for(int i=0; i<100; i++)
      {
         //Randomizes the position, size, title, and tone played for each window
         int xPos = (int)(Math.random() * 1000);
         int yPos = (int)(Math.random() * 500);
         int xSize = (int)(Math.random() * 400)+200;
         int ySize = (int)(Math.random() * 200)+100;
         int soundRandomizer = (int)(Math.random() * 3);
         int titleRandomizer = (int)(Math.random() * 8);
         
         //Determines the title and icon image
         if(titleRandomizer == 0)
         {
            message = "ERROR: System.VIRUS.exe has been detected!";
            image = "Antivirus.png";
         }else if(titleRandomizer == 1)
         {
            message = "ERROR: Files in C:/System32/cs-CZ/MSBuild/v3.5 including comdlg32.dll.mui have been corrupted!";
            image = "Antivirus.png";
         }else if(titleRandomizer == 2)
         {
            message = "ERROR: Windows.OS.64bit has crashed!";
            image = "Xwarning.png";
         }else if(titleRandomizer == 3)
         {
            message = "ERROR: Filetype XZMOQ.lnv is locked and cannot be executed!";
            image = "Lock.png";
         }else if(titleRandomizer == 4)
         {
            message = "ERROR: Critical hardware threats detected in System32.STUXNET.usgov!";
            image = "Virus.png";
         }else if(titleRandomizer == 5)
         {
            message = "ERROR: File at C:/SWSetup/SynTP/CprocName/IgnoreList/SP65755.cva is damaged!";
            image = "Warning.png";
         }else if(titleRandomizer == 6)
         {
            message = "ERROR: Expected resource missing from C:/Intelchip/CUI/Modules/Resource!";
            image = "Xwarning.png";
         }else if(titleRandomizer == 7)
         {
            message = "ERROR: Unknown user $f@KviH%gfKVh has been granted access!";
            image = "Wifi.png";
         }else
         {
            message = "ERROR: Query for ftconfig.msdia80.dll.ini failed from D:/appPools/tempLogs!";
            image = "Warning.png";
         }
         
         //Creates the window
         JFrame windows = new JFrame(message);
         windows.setBounds(xPos, yPos, xSize, ySize);
         windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         windows.setVisible(true);
         
         //Sets the icon image
         Image icon = Toolkit.getDefaultToolkit().getImage(image);
         windows.setIconImage(icon);
         
         //Plays the sound
         String sound = "Error"+soundRandomizer+".wav";
         File file = new File(sound);
         play(file);
         
         
         //Delays 100ms and checks for an exception error
         try{           
            Thread.sleep(100);
         }catch(InterruptedException e){
            e.printStackTrace();
         }
      }
      
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