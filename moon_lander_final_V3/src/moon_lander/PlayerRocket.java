package moon_lander;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
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

public class PlayerRocket extends JFrame implements GamePlayer{
	
	public static int character_num;
    
    /**
     * We use this to generate a random number for starting x coordinate of the rocket.
     */
    private Random random;
    public static int hp = 100;
    /**
     * X coordinate of the rocket.
     */
    private int x;
    /**
     * Y coordinate of the rocket.
     */
    private int y;
    
    /**
     * Is rocket landed?
     */
    public boolean landed;
    
    private StageBase stageBase;
    /**
     * Has rocket crashed?
     */
    public boolean crashed;
        
    /**
     * ������ ���� �ӵ�.
     */
    private int speedAccelerating;
    /**
     * ������ ����/�ϰ� �ӵ�. �ӵ��� �������� ������ �߷��� ������ �޷� ������� �����̴�.
     */
    private double speedStopping;
    
    /**
     * ���� �� �浹 ���� ������ ���� �� �ִ� �ִ� �ӵ��Դϴ�.
     */
    private int topLandingSpeed;
    
    /**
     * x��ǥ�󿡼� ������ �󸶳� ������ ��� �������� �����̴°�?
     */
    private int speedX;
    /**
     * y��ǥ�󿡼� ������ �󸶳� ������ ��� �������� �����̴°�?
     */
    private int speedY;
            
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

    public int getCoordinateX() {
    	return x;
    }
    
    public int getCoordinateY() {
    	return y;
    }
    
    public void setSpeedStopping(double speedStopping) {
    	this.speedStopping=speedStopping;
    }
    
    public int getSpeedX() {
    	return speedX;
    }
    
    public int getSpeedY() {
    	return speedY;
    }
    
    public void setSpeedX(int speedX) {
    	this.speedX=speedX;
    }
    
    public void setSpeedY(int speedY) {
    	this.speedY=speedY;
    }
    
    public int getTopLandingSpeed() {
    	return topLandingSpeed;
    }
    public PlayerRocket()
    {
        Initialize();
        LoadContent();
        
        //���� rocketImgWidth�� �����Ƿ� ���� x ��ǥ�� �����մϴ�.
        x = random.nextInt(Framework.frameWidth - rocketImgWidth);
        
        if(character_num==2) {
        	topLandingSpeed=9;
            hp=80;
         }
         else if(character_num==3) {
            hp=150;
            topLandingSpeed=6;
         }
    }
    
    
    public void Initialize()
    {
        random = new Random();
        
        ResetPlayer();
       
        speedStopping = 1;
       
        speedAccelerating = 2;
        
        topLandingSpeed = 7;
    }
    
    public void LoadContent()
    {
        try
        {
        	URL rocketImgUrl = null;
        	URL rocketLandedImgUrl = null;
        	URL rocketCrashedImgUrl = null;
        	URL rocketFireImgUrl = null;
        	
        	if(character_num == 1) {
            	rocketImgUrl = this.getClass().getResource("/resources/images/rocket.png");
            	rocketLandedImgUrl = this.getClass().getResource("/resources/images/rocket_landed.png");
            	rocketCrashedImgUrl = this.getClass().getResource("/resources/images/rocket_crashed.png");
            	rocketFireImgUrl = this.getClass().getResource("/resources/images/rocket_fire.png");
            }
            else if(character_num == 2) {
            	rocketImgUrl = this.getClass().getResource("/resources/images/right.png");
            	rocketLandedImgUrl = this.getClass().getResource("/resources/images/right_landed.png");
            	rocketCrashedImgUrl = this.getClass().getResource("/resources/images/right.crashed.png");
            	rocketFireImgUrl = this.getClass().getResource("/resources/images/rainbow.png");
            }
            else if(character_num == 3) {
            	rocketImgUrl = this.getClass().getResource("/resources/images/rocket2.png");
            	rocketLandedImgUrl = this.getClass().getResource("/resources/images/rocket2.landed.png");
            	rocketCrashedImgUrl = this.getClass().getResource("/resources/images/rocket2.crahsed.png");
            	rocketFireImgUrl = this.getClass().getResource("/resources/images/rocket2.fire.png");
            }
            else {
                rocketImgUrl = this.getClass().getResource("/resources/images/rocket.png");
                rocketLandedImgUrl = this.getClass().getResource("/resources/images/rocket_landed.png");
                rocketCrashedImgUrl = this.getClass().getResource("/resources/images/rocket_crashed.png");
                rocketFireImgUrl = this.getClass().getResource("/resources/images/rocket_fire.png");
             }
            
			rocketImg = ImageIO.read(rocketImgUrl);
            rocketImgWidth = rocketImg.getWidth();
            rocketImgHeight = rocketImg.getHeight();

            rocketLandedImg = ImageIO.read(rocketLandedImgUrl);

            rocketCrashedImg = ImageIO.read(rocketCrashedImgUrl);
            
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
    	
    	if(character_num == 1)
    	{
    		hp=100;
    		
    	}
    	else if(character_num == 2)
    	{
    		hp=150;
    		topLandingSpeed=6;
    	}
    	else if(character_num == 3)
    	{
    		hp=80;
    		topLandingSpeed=9;
    	}
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
        if(StageBase.stage_count==6)
        	speedStopping= 3;
        
        if(Canvas.keyboardKeyState(KeyEvent.VK_P))
        {
        	Option.pause =true;
        	Framework.game.option.Gamepause();
        	
        }
        if(Canvas.keyboardKeyState(KeyEvent.VK_O))
        {
        	
        	Option.pause =false;
        	Framework.game.option.Gamerestart();
        }
    }
    
    
    /**
     * Here we move the rocket.
     */
    public void Update()
    {
    	if(Option.pause != true)
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
        	Option.pause =true;
        	Framework.game.option.Gamepause();
        	
        }
    	}
        if(Canvas.keyboardKeyState(KeyEvent.VK_O))
        {
        	Option.pause =false;
        	Framework.game.option.Gamerestart();
        }
       
        // Moves the rocket.
        x += speedX;
        y += speedY;
        
        
    }
  
    public void Draw(Graphics2D g2d)
    {
        g2d.setColor(Color.white);
        g2d.drawString("Rocket coordinates: " + x + " : " + y, 5, 15);
        g2d.drawString("1P-Rocket", x+2, y-7);
        g2d.drawString("HP:", 5, 40);
       
        
        if(stageBase.stage_count==1)
        g2d.drawString("��������:1",700, 20);
        else if(stageBase.stage_count==2)
        	g2d.drawString("��������:2",700, 20);
        else if(stageBase.stage_count==3)
        	g2d.drawString("��������:3",700, 20);
        else if(stageBase.stage_count==4)
        	g2d.drawString("��������:4",700, 20);
        else if(stageBase.stage_count==5)
        	g2d.drawString("��������:5",700, 20);
        else if(stageBase.stage_count==6)
        	g2d.drawString("��������:Hidden",700, 20);
        g2d.setColor(Color.green);
        g2d.fillRect(30,30,hp,10);
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
        	if(Option.pause != true)
        	{
            if(Canvas.keyboardKeyState(KeyEvent.VK_W))
            {
            if(character_num == 1)
                g2d.drawImage(rocketFireImg, x + 12, y + 66, null);
            if(character_num == 2)
            	g2d.drawImage(rocketFireImg, x, y + 66, null);
            if(character_num == 3)
            	g2d.drawImage(rocketFireImg, x + 15, y+33, null);
        	}
        	}
        	
            g2d.drawImage(rocketImg, x, y, null);
        	
        }
    }
    
}
