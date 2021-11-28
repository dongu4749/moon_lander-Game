package moon_lander;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class ShootingObstacle extends JFrame implements GameObstacle{
   
     private int x;
     
     private int y;
     
     private boolean diraction;
     
     public BufferedImage movingobstacleImg;
     public BufferedImage movingobstacleImg2;
     private PlayerRocket playerRocket;
     
     
     
     public static int movingobstacleImgWidth;
     public static int movingobstacleImgHeight;
     
     private Random random;
     
     private int speedX;
     
     
     public int getCoordinateX() {
        return x;
     }
     
     public int getCoordinateY() {
        return y;
     }
     
     public void setSpeedX(int speedX) {
        this.speedX=speedX;
     }
     
     public ShootingObstacle() {
        Initialize();
         LoadContent();
         
         x = Framework. frameWidth/2-400;
         y = 700;
         
         diraction = true;
     }
     
     public void Initialize()
     {
        
       random = new Random();
//       ResetObstacle();
      
       speedX = 7;
        
     }
     
     public void LoadContent()
     {
         try
         {
             URL movingobstacleImgUrl = this.getClass().getResource("/resources/images/ShottingObstacle.png");
             movingobstacleImg = ImageIO.read(movingobstacleImgUrl);
             
             URL movingobstacleImg2Url = this.getClass().getResource("/resources/images/ShootingObstacle.crashed.png");
             movingobstacleImg2 = ImageIO.read(movingobstacleImg2Url);
            
            
             
             movingobstacleImgWidth = movingobstacleImg.getWidth();
             movingobstacleImgHeight = movingobstacleImg.getHeight();
       
                         
         }
         catch (IOException ex) {
             Logger.getLogger(PlayerRocket.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
     
//     public void ResetObstacle()
//     {
//       x = Framework. frameWidth/2-300;
//       y = random.nextInt(Framework.frameWidth-movingobstacleImgWidth-300)+200;
//     }
     
     public void Draw(Graphics2D g2d)
     {      
        g2d.drawImage(movingobstacleImg, x, y-40, null);
        if(StageBase.stage_count>=3) {
            if(Framework.game.playerRocket.crashed) {
               if(StageBase.stage_count >=3)
                     g2d.drawImage(Framework.game.shootingobstacle.movingobstacleImg2, x, y-40, null);
            }
         }
     }
     
     
     public void Update()
     {
        
        if(diraction) {
           x+=speedX;
           if(x>=800) diraction = false;
        }
        if(!diraction) {
           x-=speedX;
           if(x<=0) diraction = true;
        }
     }
}
