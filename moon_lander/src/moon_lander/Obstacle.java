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

public class Obstacle extends JFrame implements GameObstacle{
   
   private int x;
   
   private int y;
   
   public BufferedImage obstacleImg;
   
   private Random random;
   
   public int obstacleImgWidth;
   public int obstacleImgHeight;
   
   public int getCoordinateX() {
	   return x;
   }
   
   public int getCoordinateY() {
	   return y;
   }
   
   public Obstacle(){
       Initialize();
        LoadContent();
        
        x = random.nextInt(Framework.frameWidth);
       y = random.nextInt(Framework.frameWidth);
   }
   
   public void Initialize()
    {
       
      random = new Random();
      ResetObstacle();
      
       
    }
   
   public void LoadContent()
    {
        try
        {
            URL obstacleImgUrl = this.getClass().getResource("/resources/images/fixobject.png");
            obstacleImg = ImageIO.read(obstacleImgUrl);
            obstacleImgWidth = obstacleImg.getWidth();
            obstacleImgHeight = obstacleImg.getHeight();
     
                        
        }
        catch (IOException ex) {
            Logger.getLogger(PlayerRocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
   public void ResetObstacle()
    {
      x = random.nextInt(Framework.frameWidth-obstacleImgWidth-160)+80;
      y = random.nextInt(Framework.frameWidth-obstacleImgHeight-500)+250;
    }
   
   public void Draw(Graphics2D g2d)
    {
       g2d.drawImage(obstacleImg, x, y, null);
        
    }
}