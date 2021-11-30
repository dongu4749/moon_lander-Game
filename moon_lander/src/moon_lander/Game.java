package moon_lander;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

import player.PlayerEnemy;
import player.PlayerRocket;
import ranking.RankingWriter;

import java.util.ArrayList;

/**
 * Actual game.
 * 
 * @author www.gametutorial.net
 */

public class Game {
	
	
	   
	    
    /**
     * The space rocket with which player will have to land.
     */
    public PlayerRocket playerRocket;
	public PlayerEnemy playerEnemy;
	public static boolean isMode = false;
    /**
     * Landing area on which rocket will have to land.
     */
    public LandingArea landingArea;
    public Obstacle obstacle;
    public Obstacle obstacle1;
    public MovingObstacle movingobstacle;
    public MovingObstacle movingobstacle1;
    public RankingWriter ranking;
    public PauseOption option;
    public ShootingObstacle shootingobstacle;
    public ArrayList<Laser> laserList;

    public Game()
    {
        Framework.gameState = Framework.GameState.GAME_CONTENT_LOADING;
        
        Thread threadForInitGame = new Thread() {
            @Override
            public void run(){
                // Sets variables and objects for the game.
                Initialize();
                // Load game files (images, sounds, ...)
                LoadContent();
                
                Framework.gameState = Framework.GameState.PLAYING;
                
            }
        };
        threadForInitGame.start();
    }
   

   /**
     * Set variables and objects for the game.
     */
    private void Initialize()
    {
    	
    	playerRocket = new PlayerRocket();
    	option = new PauseOption();
    	if(isMode==true)
    	{
    	playerEnemy = new PlayerEnemy();
    	playerEnemyUpdate();
    	}
    	
    	
    	landingArea  = new LandingArea();
        obstacle = new Obstacle();
        obstacle1 = new Obstacle();
        movingobstacle = new MovingObstacle();
        movingobstacle1 = new MovingObstacle();
        shootingobstacle = new ShootingObstacle();
        laserList = new ArrayList<Laser>();
        
    }
    
    /**
     * Load game files - images, sounds, ...
     */
    private void LoadContent()
    {
	        StageBase stagebase = new StageBase();
			stagebase.call_BackgroungImage();	
    }
    
    /**
     * Restart game - reset some variables.
     */
    public void Draw(Graphics2D g2d)
    {
    	if(playerRocket.isLanded)
        {
            g2d.drawImage(playerRocket.rocketLandedImg, playerRocket.getCoordinateX(), playerRocket.getCoordinateY(), null);
        }
        // If the rocket is crashed.
        else if(playerRocket.isCrashed)
        {
            g2d.drawImage(playerRocket.rocketCrashedImg, playerRocket.getCoordinateX(), playerRocket.getCoordinateY() + playerRocket.rocketImgHeight - playerRocket.rocketCrashedImg.getHeight(), null);
        }
        // If the rocket is still in the space.
        else
        {	
            // If player hold down a W key we draw rocket fire.   	
            g2d.drawImage(playerRocket.rocketImg, playerRocket.getCoordinateX(), playerRocket.getCoordinateY(), null);
        	
        }
    	  if(PauseOption.isPause != true)
      	{
          if(Canvas.keyboardKeyState(KeyEvent.VK_W))
          {
          if(PlayerRocket.character_num == 1)
              g2d.drawImage(playerRocket.rocketFireImg,  playerRocket.getCoordinateX()+ 12, playerRocket.getCoordinateY() + 66, null);
          if(PlayerRocket.character_num == 2)
          	g2d.drawImage(playerRocket.rocketFireImg, playerRocket.getCoordinateX()+ 12,  playerRocket.getCoordinateY()+ 66, null);
          if(PlayerRocket.character_num == 3)
          	g2d.drawImage(playerRocket.rocketFireImg, playerRocket.getCoordinateX(), playerRocket.getCoordinateY() +66, null);
          
      	 }
         if(isMode==true)
         {
        	 if(Canvas.keyboardKeyState(KeyEvent.VK_I))
                 g2d.drawImage(playerEnemy.rainbowImg, playerEnemy.getCoordinateX() + 16, playerEnemy.getCoordinateY() +40, null);
             g2d.drawImage(playerEnemy.enemyImg, playerEnemy.getCoordinateX(), playerEnemy.getCoordinateY(), null);
         }
      	}
      
    }
    public void playerRocketUpdate()
    {
    	if(PauseOption.isPause != true)
    	{
        // Calculating speed for moving up or down.
        if(Canvas.keyboardKeyState(KeyEvent.VK_W))
       	 playerRocket.playerRocketKeyEventWPress();
        else
       	 playerRocket.playerRocketKeyEventWNotPress();
        
        // Calculating speed for moving or stopping to the left.
        if(Canvas.keyboardKeyState(KeyEvent.VK_A))
       	 playerRocket.playerRocketKeyEventLeftPress();
        else if(playerRocket.getSpeedX() < 0)
       	 playerRocket.playerRocketKeyEventLeftNotPress();
        
        // Calculating speed for moving or stopping to the right.
        if(Canvas.keyboardKeyState(KeyEvent.VK_D))
       	 playerRocket.playerRocketKeyEventRightPress();
        else if(playerRocket.getSpeedX() > 0)
       	 playerRocket.playerRocketKeyEventRightNotPress();
        if(Canvas.keyboardKeyState(KeyEvent.VK_P))
        {
        	PauseOption.isPause =true;
        	option.gamePause();
        	
        }
    	}
        if(Canvas.keyboardKeyState(KeyEvent.VK_O))
        {
        	PauseOption.isPause =false;
        	option.gameRestart();
        }       
        // Moves the rocket.
        playerRocket.moveRocket();        
    }
    public void playerEnemyUpdate()
    {
    	if(Canvas.keyboardKeyState(KeyEvent.VK_I))
            playerEnemy.playerEnemyKeyEventIPress();
        else
            playerEnemy.playerEnemyKeyEventINotPress();
        
        // Calculating speed for moving or stopping to the left.
        if(Canvas.keyboardKeyState(KeyEvent.VK_J))
            playerEnemy.playerEnemyKeyEventJPress();
        else if(playerEnemy.getSpeedX() < 0)
            playerEnemy.playerEnemyKeyEventJNotPress();
        
        // Calculating speed for moving or stopping to the right.
        if(Canvas.keyboardKeyState(KeyEvent.VK_L))
            playerEnemy.playerEnemytKeyEventLPress();
        else if(playerEnemy.getSpeedX() > 0)
            playerEnemy.playerEnemyKeyEventLNotPress();
        
        // Moves the rocket.
        playerEnemy.moveEnemy();
        
        
    }
    public void RestartGame()
    {
    	playerRocketUpdate();
		/* playerEnemy.ResetPlayer(); */
    	for(int i=2;i<=5;i++)
    	{  		
    			if(StageBase.stage_count==i)
    			{				
    				double speed=1.2;
    		        playerRocket.setSpeedStopping(speed);
    		        speed += 0.2;	
    			}
    			if(StageBase.stage_count==6)
    				playerRocket.setSpeedStopping(3);
    			
    	}
    	 if(Canvas.keyboardKeyState(KeyEvent.VK_P))
         {
         	PauseOption.isPause =true;
         	option.gamePause();
         	
         }
         if(Canvas.keyboardKeyState(KeyEvent.VK_O))
         {
         	
         	PauseOption.isPause =false;
         	option.gameRestart();
         }
        playerRocket.ResetPlayer();
    	
    	if(isMode==true)
    	{
    		playerRocket.hp=100;
    		playerEnemyUpdate();
    		playerEnemy.ResetPlayer();
    		
    	}
    	
        obstacle.resetObstacle();
        obstacle1.resetObstacle();
        laserList.clear();
        
    }
    
    public void landingAreaValue()
    {
    	int landingAreaArrayList[]= {landingArea.landingAreaImgWidth,landingArea.landingAreaImgWidth2,landingArea.landingAreaImgWidth3,landingArea.landingAreaImgWidth4,landingArea.landingAreaImgWidth5};
        for(int i=0; i<5;i++)
        {
        	int AreaValue = landingAreaArrayList[i];
        	for(int j=1;j<=6;j++)
        	if(StageBase.stage_count==j) 
        	{
            if((playerRocket.getCoordinateX() > landingArea.x) && (playerRocket.getCoordinateX() < landingArea.x + landingAreaArrayList[i] - playerRocket.rocketImgWidth))
            
            {
                // Here we check if the rocket speed isn't too high.
                if(playerRocket.getSpeedY() <= playerRocket.getTopLandingSpeed()) {
                    playerRocket.isLanded = true;       

                }
                else
                	
                    playerRocket.isCrashed = true;
            }
            else
            	
                playerRocket.isCrashed = true;
        	}
        	
        }
    }
    public void rectangleCrashed()
    {
    	playerRocket.hp -= 10;
     	if(playerRocket.hp ==0)
     	{
        playerRocket.isCrashed = true;
        Framework.gameState = Framework.GameState.GAMEOVER;
     	}
    }
    public void rectangleOver()
    {
    	 Rectangle rocketRectangle = new Rectangle(playerRocket.getCoordinateX(),playerRocket.getCoordinateY(),playerRocket.rocketImg.getWidth(),playerRocket.rocketImg.getHeight());
         Rectangle obstacleRectangle = new Rectangle(obstacle.getCoordinateX(),obstacle.getCoordinateY(),obstacle.obstacleImg.getWidth(),obstacle.obstacleImg.getHeight());
         Rectangle rocketRectangle2 = new Rectangle(playerRocket.getCoordinateX(),playerRocket.getCoordinateY(),playerRocket.rocketImg.getWidth(),playerRocket.rocketImg.getHeight());
         Rectangle obstacleRectangle2 = new Rectangle(obstacle1.getCoordinateX(),obstacle1.getCoordinateY(),obstacle1.obstacleImg.getWidth(),obstacle1.obstacleImg.getHeight());
         Rectangle movingobstacleRectangle = new Rectangle(movingobstacle.getCoordinateX(),movingobstacle.getCoordinateY(),movingobstacle.movingobstacleImg.getWidth(),movingobstacle.movingobstacleImg.getHeight());
         Rectangle movingobstacleRectangle2 = new Rectangle(movingobstacle1.getCoordinateX(),movingobstacle1.getCoordinateY(),movingobstacle1.movingobstacleImg.getWidth(),movingobstacle.movingobstacleImg.getHeight());
         Rectangle shootingobstacleRectangle = new Rectangle(shootingobstacle.getCoordinateX(),shootingobstacle.getCoordinateY(),shootingobstacle.movingobstacleImgWidth,shootingobstacle.movingobstacleImgHeight);
         if(rocketRectangle.intersects(obstacleRectangle)||rocketRectangle2.intersects(obstacleRectangle2)||rocketRectangle.intersects(movingobstacleRectangle)) 
         {
        	 rectangleCrashed();
         }         
        if(StageBase.stage_count>=2) {
         if(rocketRectangle.intersects(movingobstacleRectangle2)) 
         {
        	 rectangleCrashed();
         }
        }  
        if(StageBase.stage_count>=3) {
        if(shootingobstacleRectangle.intersects(rocketRectangle)) {
           rectangleCrashed();
        }
        }
         if(isMode==true)
         {
     Rectangle enemyRectangle = new Rectangle(playerEnemy.getCoordinateX(),playerEnemy.getCoordinateY(),playerEnemy.enemyImg.getWidth(),playerEnemy.enemyImg.getHeight());
         {
         	 if(enemyRectangle.intersects(rocketRectangle)) 
         	 {
         		playerRocket.hp -= 20;
         	 }
         		if(playerRocket.hp ==0)
             	{
                playerRocket.isCrashed = true;
                Framework.gameState = Framework.GameState.GAMEOVER;
             }
         }
         }
    }
    public void UpdateGame(long gameTime, Point mousePosition)
    {
        // Move the rocket
    	
    	playerRocketUpdate();
    	if(isMode == true)
    	{
    	playerEnemyUpdate();
    	}
    	movingobstacle.Update();
        movingobstacle1.Update();
        
        shootingobstacle.Update();
        for(int i=0;i<laserList.size();i++) {
            Laser laser = laserList.get(i);
            
            laser.Update();
            Rectangle rocketRectangle = new Rectangle(playerRocket.getCoordinateX(),playerRocket.getCoordinateY(),playerRocket.rocketImg.getWidth(),playerRocket.rocketImg.getHeight());
            if(StageBase.stage_count>=3) {
            Rectangle laserRectangle = new Rectangle(laser.getCoordinateX(),laser.getCoordinateY(),laser.bombImgWidth,laser.bombImgImgHeight);
            if(laserRectangle.intersects(rocketRectangle)) {
               rectangleCrashed();
            }
            }
         }
         shootingobstacle.shooting(this, gameTime,mousePosition);
        
        
        // Checks where the player rocket is. Is it still in the space or is it landed or crashed?
        // First we check bottom y coordinate of the rocket if is it near the landing area.
        if(playerRocket.getCoordinateY() + playerRocket.rocketImgHeight - 10 > landingArea.y)
        {
            // Here we check if the rocket is over landing area.
        	landingAreaValue();
            Framework.gameState = Framework.GameState.GAMEOVER;
        }
        rectangleOver();
    }
    }
