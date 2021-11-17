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

public class Obstacle2 extends JFrame{
   
   public int x2,y2;
   
   public BufferedImage obstacleImg2;
   
   private Random random;
   
   public int obstacleImgWidth2;
   public int obstacleImgHeight2;
   
   public Obstacle2(){
       Initialize();
        LoadContent();
        
        x2 = random.nextInt(Framework.frameWidth);
       y2 = random.nextInt(Framework.frameWidth);
   }
   
   public void Initialize()
    {
       
      random = new Random();
      ResetObstacle();
      
       
    }
   
   private void LoadContent()
    {
        try
        {
            URL obstacleImg2Url = this.getClass().getResource("/resources/images/fixobject.png");
            obstacleImg2 = ImageIO.read(obstacleImg2Url);
            obstacleImgWidth2 = obstacleImg2.getWidth();
            obstacleImgHeight2 = obstacleImg2.getHeight();
     
                        
        }
        catch (IOException ex) {
            Logger.getLogger(PlayerRocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
   public void ResetObstacle()
    {
      x2 = random.nextInt(Framework.frameWidth-obstacleImgWidth2-160)+80;
      y2 = random.nextInt(Framework.frameWidth-obstacleImgHeight2-500)+250;
    }
   
   public void Draw(Graphics2D g2d)
    {
       g2d.drawImage(obstacleImg2, x2, y2, null);
        
    }
}