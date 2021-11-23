package moon_lander;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class MovingObstacle extends JFrame implements GameObstacle{
   
      private int x;
      
      private int y;
      
       public BufferedImage movingobstacleImg;
       public BufferedImage movingobstacleImg2;
       public BufferedImage movingobstacleImg3;
       public BufferedImage movingobstacleImg4;
       public BufferedImage movingobstacleImg5;
      
       private Random random;
       
       /**
        * x좌표상에서 로켓은 얼마나 빠르고 어느 방향으로 움직이는가?
        */
       private int speedX;
       /**
        * y좌표상에서 로켓은 얼마나 빠르고 어느 방향으로 움직이는가?
        */
       
       
      
      public static int movingobstacleImgWidth;
      public static int movingobstacleImgHeight;
      
      public int getCoordinateX() {
    	  return x;
      }
      
      public int getCoordinateY() {
    	  return y;
      }
      
      public void setSpeedX(int speedX) {
    	  this.speedX=speedX;
      }
     
      
      public MovingObstacle(){
            Initialize();
           LoadContent();
          
           x = Framework. frameWidth/2-300;
           y = random.nextInt(Framework.frameWidth-300)+200;
      }
      
      public void Initialize()
       {
          
         random = new Random();
         ResetObstacle();
        
         speedX = random.nextInt(10)+5;
          
       }
      
      public void LoadContent()
       {
           try
           {
               URL movingobstacleImgUrl = this.getClass().getResource("/resources/images/movingobstcle.jpg");
               URL movingobstacleImg2Url = this.getClass().getResource("/resources/images/fire_obstacle.png");
               URL movingobstacleImg3Url = this.getClass().getResource("/resources/images/movingobstcle.png");
               URL movingobstacleImg4Url = this.getClass().getResource("/resources/images/Stoneobstacle.png");
               URL movingobstacleImg5Url = this.getClass().getResource("/resources/images/munge.jpg");
               movingobstacleImg = ImageIO.read(movingobstacleImgUrl);
               movingobstacleImg2 = ImageIO.read(movingobstacleImg2Url);
               movingobstacleImg3 = ImageIO.read(movingobstacleImg3Url);
               movingobstacleImg4 = ImageIO.read(movingobstacleImg4Url);
               movingobstacleImg5 = ImageIO.read(movingobstacleImg5Url);
              
               
               movingobstacleImgWidth = movingobstacleImg.getWidth();
               movingobstacleImgHeight = movingobstacleImg.getHeight();
         
                           
           }
           catch (IOException ex) {
               Logger.getLogger(PlayerRocket.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
      
      public void ResetObstacle()
       {
         x = Framework. frameWidth/2-300;
         y = random.nextInt(Framework.frameWidth-movingobstacleImgWidth-300)+200;
       }
      
      public void Draw(Graphics2D g2d)
       {
          
          if(StageBase.stage_count==1)
             g2d.drawImage(movingobstacleImg, x, y, null);
          else if(StageBase.stage_count==2)
        	  g2d.drawImage(movingobstacleImg3, x, y, null);
          else if(StageBase.stage_count==3)
        	  
          g2d.drawImage(movingobstacleImg2, x, y, null);
          else if(StageBase.stage_count==4) 
        	  g2d.drawImage(movingobstacleImg4, x, y, null);
          else
        	  g2d.drawImage(movingobstacleImg5, x, y, null);
          
           
       }
      
      public void Update()
      {
         
         while(x <=800) {
              x += speedX;
              break;
         }
         
           
       }


}