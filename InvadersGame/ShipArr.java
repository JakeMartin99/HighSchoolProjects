/*
Jake Martin
*/

//Imports the necessary libraries
import java.awt.*;
import java.util.*;

//Creates the ShipArr class as a Sprite subclass
public class ShipArr extends Sprite
{
   
   //Creates private variables for ships, x, y, change, the bounds, the boundCheckers, and fireShipInd
   private ArrayList<Sprite> ships = new ArrayList<>();
   private int x = 25;
   private int y = 50;
   private int change = 1;
   private int leftBound = 25;
   private int rightBound = 720-25-25;
   private int leftBoundChecker = 0;
   private int rightBoundChecker = 500;
   private int fireShipInd = 0;
      
   //Constructs the Ship object
   public ShipArr(int rows, int cols, Color color)
   {
      //Calls the Sprite constructor
      super(color, new Hitbox(0, 0, 1, 1));
      //Fills ships with Ships
      for(int col=0; col<cols; col++)
      {
         for(int row=0; row<rows; row++)
         {
            Sprite ship = new Ship(50*col+25, 50*row+20, color);
            ship.setChange(this.change);
            ships.add(ship);
         }
      }
   }
   
   @Override //From Sprite
   public boolean hasCollidedWith(Sprite sprite)
   {
      //For each Ship in ships...
      for(int i=0; i<ships.size(); i++)
      {
         //If the Ship hasCollidedWith sprite...
         if(ships.get(i).getHitbox().hasCollidedWith(sprite.getHitbox()))
         {
            //Remove the Ship and return true
            ships.remove(i);
            return true;
         }
      }
      //Returns false
      return false;
   }
   
   //Creates the drawSprite method with Graphics g
   public void drawSprite(Graphics g)
   {
      //For each Ship in ships...
      for(int i=0; i<ships.size(); i++)
      {
         //Draw the Ship
         ships.get(i).drawSprite(g);
      }
   }
   
   //Creates the changePos method
   public void changePos()
   {
      //Creates variables for the boundsHaveShips, numCols, and prevNumCol
      boolean leftBoundHasShip = false;
      boolean rightBoundHasShip = false;
      int numCols = 0;
      int prevColNum = 0;
      
      //If x is at the screen's edge...
      if(this.x < this.leftBound || this.x > this.rightBound-(50*11-50))
      {
         //Set change to reverse
         this.change *= -1;
      }
      
      //Adds change to x
      this.x += this.change;
      
      //For each Ship in ships...
      for(int i=0; i<ships.size(); i++)
      {
         //Set ship as the Ship at i and call setChange on it
         Sprite ship = ships.get(i);
         ship.setChange(this.change);
         
         //If x is at the screen's edge...
         if(this.x < this.leftBound || this.x > this.rightBound-(50*11-50))
         {
            //Call setY on ship
            ship.setY(ship.getY() + this.y);
         }
         
         //Calls changePos on ship
         ship.changePos();
         
         //If ship's x isn't = to prevColNum...
         if(ship.getX() != prevColNum)
         {
            //Set prevColNum to ship's x
            prevColNum = ship.getX();
            //Increment numCols
            numCols++;
         }
         
         //If ship's x is = to x + leftBoundChecker...
         if(ship.getX() == this.x + leftBoundChecker)
         {
            //Set leftBoundHasShip to true
            leftBoundHasShip = true;
         }
         //Else if ship's x is = to x + rightBoundChecker...
         else if(ship.getX() == this.x + rightBoundChecker)
         {
            //Set rightBoundHasShip to true
            rightBoundHasShip = true;
         }
      }
      
      //If leftBoundHasShip is false and numCols > 1...
      if(!leftBoundHasShip && numCols > 1)
      {
         //Decrement leftBound
         this.leftBound -= 50;
         //Increment leftBoundChecker
         this.leftBoundChecker += 50;
      }
      //If rightBoundHasShip is false and numCols > 1...
      if(!rightBoundHasShip && numCols > 1)
      {
         //Increment rightBound
         this.rightBound += 50;
         //Decrement rightBoundChecker
         this.rightBoundChecker -= 50;
      }
      //If numCols is = to 1...
      if (numCols == 1)
      {
         //Set leftBound to 25 - 500
         this.leftBound = 25-500;
         //Set rightBound to 720-25-25
         this.rightBound = 720-25-25;
      }
   }
   
   //Creates the getX method
   public int getX()
   {
      //Sets fireShipInd to a random index of ships
      this.fireShipInd = (int)(Math.random() * ships.size());
      //Returns the x of the ship at fireShipInd
      return ships.get(this.fireShipInd).getX();
   }
   
   //Creates the getY method
   public int getY()
   {
      //For each Ship in ships...
      for(int i=0; i<ships.size(); i++)
      {
         //Set fireShip to the Ship at fireShipInd
         Sprite fireShip = ships.get(this.fireShipInd);
         //Set newShip to the Ship at i
         Sprite newShip = ships.get(i);
         //If newShip's x and y are = to fireShip's...
         if(newShip.getX() == fireShip.getX() && newShip.getY() > fireShip.getY())
         {
            //Set fireShipInd to i
            this.fireShipInd = i;
         }
      }
      //Returns the Ship and fireShipInd's y
      return ships.get(this.fireShipInd).getY();
   }
   
   //Creates the setChange method with val
   public void setChange(int val){}
   
   //Creates the setY method with val
   public void setY(int val){}
   
}