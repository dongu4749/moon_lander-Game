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

public class MovingObstacle2 extends JFrame {
   
      public static int x2,y2;
      
       public BufferedImage movingobstacle2Img;
       public BufferedImage movingobstacle2Img2;
       public BufferedImage movingobstacle2Img3;
       public BufferedImage movingobstacle2Img4;
       public BufferedImage movingobstacle2Img5;
       public static Random random;
       
       /**
        * x좌표상에서 로켓은 얼마나 빠르고 어느 방향으로 움직이는가?
        */
       private int speedX;
       /**
        * y좌표상에서 로켓은 얼마나 빠르고 어느 방향으로 움직이는가?
        */
       
       public static int speedX1;
       public static int speedX2;
      
      public static int movingobstacleImgWidth2;
      public int movingobstacleImgHeight2;
      
      public MovingObstacle2(){
            Initialize();
           LoadContent();
           
           x2 = Framework. frameWidth/2;
           y2 = random.nextInt(Framework.frameWidth-300)+200;
      }
      
      public void Initialize()
       {
          
         random = new Random();
         ResetObstacle2();
         
         speedX1 = 7;
         speedX2 = 6;
          
       }
      
      private void LoadContent()
       {
           try
           {
               URL movingobstacle2ImgUrl = this.getClass().getResource("/resources/images/movingobstcle.jpg");
               URL movingobstacle2Img2Url = this.getClass().getResource("/resources/images/fire_obstacle.png");
               URL movingobstacle2Img3Url = this.getClass().getResource("/resources/images/movingobstcle.png");
               URL movingobstacle2Img4Url = this.getClass().getResource("/resources/images/Stoneobstacle.png");
               URL movingobstacle2Img5Url = this.getClass().getResource("/resources/images/munge.jpg");
               movingobstacle2Img = ImageIO.read(movingobstacle2ImgUrl);
               movingobstacle2Img2= ImageIO.read(movingobstacle2Img2Url);
               movingobstacle2Img3 = ImageIO.read(movingobstacle2Img3Url);
               movingobstacle2Img4 = ImageIO.read(movingobstacle2Img4Url);
               movingobstacle2Img5 = ImageIO.read(movingobstacle2Img5Url);
               movingobstacleImgWidth2 = movingobstacle2Img.getWidth();
               movingobstacleImgHeight2 = movingobstacle2Img.getHeight();
                           
           }
           catch (IOException ex) {
               Logger.getLogger(PlayerRocket.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
      
      public static void ResetObstacle2()
       {
         x2 = Framework. frameWidth/2;
         y2 = random.nextInt(Framework.frameWidth-movingobstacleImgWidth2-160)+200;
       }
      
      public void Draw(Graphics2D g2d)
       {
    	  if(StageBase.stage_count==1)
              g2d.drawImage(movingobstacle2Img, x2, y2, null);
           else if(StageBase.stage_count==2)
        	   g2d.drawImage(movingobstacle2Img3, x2, y2, null);
           else if(StageBase.stage_count==3)
         	  g2d.drawImage(movingobstacle2Img2, x2, y2, null);
           else if(StageBase.stage_count==4) 
         	  g2d.drawImage(movingobstacle2Img4, x2, y2, null);
           else
         	  g2d.drawImage(movingobstacle2Img5, x2, y2, null);
        
           
       }
      
      public void Update()
      {
         
         while(x2 <=800) {
              x2 += speedX1;
              break;
         }
         
           
       }


}