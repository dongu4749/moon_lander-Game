package moon_lander;

import java.awt.Color;
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

/**
 * The space rocket with which player will have to land.
 * 
 * @author www.gametutorial.net
 */

public class PlayerRocket extends JFrame{
    
    /**
     * We use this to generate a random number for starting x coordinate of the rocket.
     */
    private Random random;
    public int hp = 100;
    /**
     * X coordinate of the rocket.
     */
    public int x;
    /**
     * Y coordinate of the rocket.
     */
    public int y;
    
    /**
     * Is rocket landed?
     */
    public static boolean landed;
    
    private StageBase stageBase;
    /**
     * Has rocket crashed?
     */
    public boolean crashed;
        
    /**
     * 로켓의 가속 속도.
     */
    public static int speedAccelerating;
    /**
     * 로켓의 정지/하강 속도. 속도가 떨어지는 이유는 중력이 로켓을 달로 끌어내리기 때문이다.
     */
    public static double speedStopping;
    
    /**
     * 착륙 시 충돌 없이 로켓이 가질 수 있는 최대 속도입니다.
     */
    public int topLandingSpeed;
    
    /**
     * x좌표상에서 로켓은 얼마나 빠르고 어느 방향으로 움직이는가?
     */
    public static int speedX;
    /**
     * y좌표상에서 로켓은 얼마나 빠르고 어느 방향으로 움직이는가?
     */
    public static int speedY;
            
    /**
     * Image of the rocket in air.
     */
    public BufferedImage rocketImg;
    /**
     * Image of the rocket when landed.
     */
    private BufferedImage rocketLandedImg;
    /**
     * Image of the rocket when crashed.
     */
    private BufferedImage rocketCrashedImg;
    /**
     * Image of the rocket fire.
     */
    private BufferedImage rocketFireImg;
    
    
    /**
     * Width of rocket.
     */
    public int rocketImgWidth;
    /**
     * Height of rocket.
     */
    public int rocketImgHeight;
    
    
    public PlayerRocket()
    {
        Initialize();
        LoadContent();
        
        //이제 rocketImgWidth가 있으므로 시작 x 좌표를 설정합니다.
        x = random.nextInt(Framework.frameWidth - rocketImgWidth);
    }
    
    
    public void Initialize()
    {
        random = new Random();
        
        ResetPlayer();
       
        speedStopping = 1;
       
        speedAccelerating = 2;
        
        topLandingSpeed = 5;
    }
    
    private void LoadContent()
    {
        try
        {
            URL rocketImgUrl = this.getClass().getResource("/resources/images/rocket.png");
            rocketImg = ImageIO.read(rocketImgUrl);
            rocketImgWidth = rocketImg.getWidth();
            rocketImgHeight = rocketImg.getHeight();
            
            URL rocketLandedImgUrl = this.getClass().getResource("/resources/images/rocket_landed.png");
            rocketLandedImg = ImageIO.read(rocketLandedImgUrl);
            
            URL rocketCrashedImgUrl = this.getClass().getResource("/resources/images/rocket_crashed.png");
            rocketCrashedImg = ImageIO.read(rocketCrashedImgUrl);
            
            URL rocketFireImgUrl = this.getClass().getResource("/resources/images/rocket_fire.png");
            rocketFireImg = ImageIO.read(rocketFireImgUrl);
           
            
        }
        catch (IOException ex) {
            Logger.getLogger(PlayerRocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Here we set up the rocket when we starting a new game.
     */
    public void ResetPlayer()
    {
    	hp=100;
        landed = false;
        crashed = false;
        
        x = random.nextInt(Framework.frameWidth - rocketImgWidth);
        y = 10;
        
        speedX = 0;
        speedY = 0;
        if(StageBase.stage_count==2)
        	speedStopping= 1.2;
        if(StageBase.stage_count==3)
        	speedStopping= 1.4;
        if(StageBase.stage_count==4)
        	speedStopping= 1.6;
        if(StageBase.stage_count==5)
        	speedStopping= 1.8;
        if(StageBase.stage_count==99)
        	speedStopping= 7;
        
        if(Canvas.keyboardKeyState(KeyEvent.VK_P))
        {
        	pause =true;
        	Gamepause();
        	
        }
        if(Canvas.keyboardKeyState(KeyEvent.VK_O))
        {
        	
        	pause =false;
        	Gamerestart();
        }
    }
    
    
    /**
     * Here we move the rocket.
     */
    public void Update()
    {
    	if(pause != true)
    	{
        // Calculating speed for moving up or down.
        if(Canvas.keyboardKeyState(KeyEvent.VK_W))
            speedY -= speedAccelerating;
        else
            speedY += speedStopping;
        
        // Calculating speed for moving or stopping to the left.
        if(Canvas.keyboardKeyState(KeyEvent.VK_A))
            speedX -= speedAccelerating;
        else if(speedX < 0)
            speedX += speedStopping;
        
        // Calculating speed for moving or stopping to the right.
        if(Canvas.keyboardKeyState(KeyEvent.VK_D))
            speedX += speedAccelerating;
        else if(speedX > 0)
            speedX -= speedStopping;
        if(Canvas.keyboardKeyState(KeyEvent.VK_P))
        {
        	pause =true;
        	Gamepause();
        	
        }
    	}
        if(Canvas.keyboardKeyState(KeyEvent.VK_O))
        {
        	pause =false;
        	Gamerestart();
        }
       
        // Moves the rocket.
        x += speedX;
        y += speedY;
        
        
    }
    int p1speedX=1,p1speedY=1;
    int p2speedX=1, p2speedY=1;
    public static boolean pause = false;
    public void Pausestore()
    {
    	
      	if(pause == true )
      	{
      		p1speedX=PlayerRocket.speedX;
      		p1speedY=PlayerRocket.speedY;
            
      		p2speedX=PlayerEnemy.speedX;
      		p2speedY=PlayerEnemy.speedY;
            
            
      	}
    }
    public void Gamepause()
    {
    	
    	PlayerRocket.speedX=0;
        PlayerRocket.speedY=0;
        PlayerEnemy.speedX=0;
        PlayerEnemy.speedY=0;
        MovingObstacle.speedX3=0;
        MovingObstacle.speedX4=0;
        MovingObstacle2.speedX1=0;
        MovingObstacle2.speedX2=0; 
        PlayerRocket.speedStopping=0;
        PlayerEnemy.speedStopping=0;
    }	
    
    public void Gamerestart()
    {
    	MovingObstacle.speedX3=7;
        MovingObstacle.speedX4=6;
    	MovingObstacle2.speedX1=7;
        MovingObstacle2.speedX2=6;
        PlayerRocket.speedX=p1speedX;
        PlayerRocket.speedY=p1speedY;
        PlayerRocket.speedStopping=1;
        PlayerEnemy.speedX=p2speedX;
        PlayerEnemy.speedY=p2speedY;
        PlayerEnemy.speedStopping=1;
    }
    public void Draw(Graphics2D g2d)
    {
        g2d.setColor(Color.white);
        g2d.drawString("Rocket coordinates: " + x + " : " + y, 5, 15);
        g2d.drawString("1P-Rocket", x+2, y-7);
        
       
        
        if(stageBase.stage_count==1)
        g2d.drawString("스테이지:1",700, 20);
        else if(stageBase.stage_count==2)
        	g2d.drawString("스테이지:2",700, 20);
        else if(stageBase.stage_count==3)
        	g2d.drawString("스테이지:3",700, 20);
        else if(stageBase.stage_count==4)
        	g2d.drawString("스테이지:4",700, 20);
        else if(stageBase.stage_count==5)
        	g2d.drawString("스테이지:5",700, 20);
        else if(stageBase.stage_count==99)
        	g2d.drawString("스테이지:99",700, 20);
        g2d.setColor(Color.green);
        g2d.fillRect(this.x-1,this.y-40,hp,20);
        // If the rocket is landed.
        if(landed)
        {
            g2d.drawImage(rocketLandedImg, x, y, null);
        }
        // If the rocket is crashed.
        else if(crashed)
        {
            g2d.drawImage(rocketCrashedImg, x, y + rocketImgHeight - rocketCrashedImg.getHeight(), null);
        }
        // If the rocket is still in the space.
        else
        {
            // If player hold down a W key we draw rocket fire.
        	if(pause != true)
        	{
            if(Canvas.keyboardKeyState(KeyEvent.VK_W))
                g2d.drawImage(rocketFireImg, x + 12, y + 66, null);
        	}
            g2d.drawImage(rocketImg, x, y, null);
        	
        }
    }
    
}
