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

public class Laser extends JFrame{
   
    private int x;
    
    private int y;
    
    private int tempSpeedX;
    
    private int tempSpeedY;
   
    private Random random;
    
    public BufferedImage bomb;
    
    public static int bombImgWidth;
    public static int bombImgImgHeight;
    
    private int speedX;
    
    private int speedY;
    
    public int getCoordinateX() {
       return x;
    }
    
    public int getCoordinateY() {
       return y;
    }
    
   
    public Laser() {
        Initialize();
        LoadContent();
        
        x=Framework.game.shootingobstacle.getCoordinateX();
        y=700;
    }
    
    public void Initialize()
    {
       
      random = new Random();
     for(int i=1;i<=6;i++) {
        if(StageBase.stage_count==i) {
           int k,j;
           k = random.nextInt(6)+1;
           j = random.nextInt(2);
           speedY = 8+5*i;
           tempSpeedY = speedY;
           if(j==0) {
           speedX = k*i;
           tempSpeedX = speedX;
           }
           else {
           speedX = -k*i;
           tempSpeedX = speedX;
           }
        }
     }
     
       
    }
    
    public void LoadContent()
    {
        try
        {
           URL bombImgUrl = this.getClass().getResource("/resources/images/bomb1.png");
           bomb = ImageIO.read(bombImgUrl);
      
           bombImgWidth = bomb.getWidth();
           bombImgImgHeight = bomb.getHeight();
        }
        catch (IOException ex) {
           Logger.getLogger(Laser.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    public void Draw(Graphics2D g2d)
    {
       
       
       g2d.drawImage(bomb,x, y, null);
       
        
    }
    
    public void Update()
    {
       if(StageBase.stage_count<4) {
           y-=speedY;
       }
       else {
          x+=speedX;
          y-=speedY;
       }
       if(PauseOption.isPause==true) {
          speedX=0;
          speedY=0;
       }
       else if(Canvas.keyboardKeyState(KeyEvent.VK_O)) {
          speedX=tempSpeedX;
          speedY=tempSpeedY;
           x+=speedX;
              y-=speedY;
       }

    }
    
   
    
}