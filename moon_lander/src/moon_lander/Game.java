package moon_lander;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * Actual game.
 * 
 * @author www.gametutorial.net
 */

public class Game {
	
	
	   
	    
    /**
     * The space rocket with which player will have to land.
     */
    public static PlayerRocket playerRocket;
	 public static PlayerEnemy playerEnemy;
    
    /**
     * Landing area on which rocket will have to land.
     */
    public static LandingArea landingArea;
    public static Obstacle obstacle;
    public static Obstacle2 obstacle2;
    public static MovingObstacle movingobstacle;
    public static MovingObstacle2 movingobstacle2;
    public Ranking ranking;

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
    	
    	if(Mode==true)
    	{
    	playerEnemy = new PlayerEnemy();
    	}
    	
    	
    	landingArea  = new LandingArea();
        obstacle = new Obstacle();
        obstacle2 = new Obstacle2();
        movingobstacle = new MovingObstacle();
        movingobstacle2 = new MovingObstacle2();
    }
    
    /**
     * Load game files - images, sounds, ...
     */
    private void LoadContent()
    {
	        Stage1 stage1 = new Stage1();
			stage1.BackgroundImage();
			
    }
    
    
    /**
     * Restart game - reset some variables.
     */
    public void RestartGame()
    {
		/* playerEnemy.ResetPlayer(); */
    	
    	
        playerRocket.ResetPlayer();
    	
    	if(Mode==true)
    	{
        playerEnemy.ResetPlayer();
    	}
    	
        obstacle.ResetObstacle();
        obstacle2.ResetObstacle();
        movingobstacle.ResetObstacle();
        movingobstacle2.ResetObstacle2();
    }
    public static boolean Mode = false;
    
    /**
     * Update game logic.
     * 
     * @param gameTime gameTime of the game.
     * @param mousePosition current mouse position.
     */
    public void UpdateGame(long gameTime, Point mousePosition)
    {
        // Move the rocket
    	
    	playerRocket.Update();
    	if(Mode == true)
    	{
    	playerEnemy.Update();
    	}
    	movingobstacle.Update();
        movingobstacle2.Update();
        if(movingobstacle.x == 800 || movingobstacle2.x2 == 800)
        {
        	MovingObstacle.ResetObstacle();
        	MovingObstacle2.ResetObstacle2();
        }
        
        
        // Checks where the player rocket is. Is it still in the space or is it landed or crashed?
        // First we check bottom y coordinate of the rocket if is it near the landing area.
        if(playerRocket.y + playerRocket.rocketImgHeight - 10 > landingArea.y)
        {
            // Here we check if the rocket is over landing area.
        	if(StageBase.stage_count==1) 
        	{
            if((playerRocket.x > landingArea.x) && (playerRocket.x < landingArea.x + landingArea.landingAreaImgWidth - playerRocket.rocketImgWidth))
            
            {
                // Here we check if the rocket speed isn't too high.
                if(playerRocket.speedY <= playerRocket.topLandingSpeed) {
                    playerRocket.landed = true;

                }
                else
                    playerRocket.crashed = true;
            }
            else
                playerRocket.crashed = true;
        	}
        	else if(StageBase.stage_count==2)
        	{
        		if((playerRocket.x > landingArea.x) && (playerRocket.x < landingArea.x + landingArea.landingAreaImgWidth2 - playerRocket.rocketImgWidth))
                    
                {
                    // Here we check if the rocket speed isn't too high.
                    if(playerRocket.speedY <= playerRocket.topLandingSpeed) {
                        playerRocket.landed = true;
     
                    }
                    else
                        playerRocket.crashed = true;
                }
                else
                    playerRocket.crashed = true;
        	}
        	else if(StageBase.stage_count==3)
        	{
        		if((playerRocket.x > landingArea.x) && (playerRocket.x < landingArea.x + landingArea.landingAreaImgWidth3 - playerRocket.rocketImgWidth))
                    
                {
                    // Here we check if the rocket speed isn't too high.
                    if(playerRocket.speedY <= playerRocket.topLandingSpeed) {
                        playerRocket.landed = true;
     
                    }
                    else
                        playerRocket.crashed = true;
                }
                else
                    playerRocket.crashed = true;
        	}
        	else if(StageBase.stage_count==4)
        	{
        		if((playerRocket.x > landingArea.x) && (playerRocket.x < landingArea.x + landingArea.landingAreaImgWidth4 - playerRocket.rocketImgWidth))
                    
                {
                    // Here we check if the rocket speed isn't too high.
                    if(playerRocket.speedY <= playerRocket.topLandingSpeed) {
                        playerRocket.landed = true;

                    }
                    else
                        playerRocket.crashed = true;
                }
                else
                    playerRocket.crashed = true;
        	}
        	else if(StageBase.stage_count==5)
        	{
        		if((playerRocket.x > landingArea.x) && (playerRocket.x < landingArea.x + landingArea.landingAreaImgWidth5 - playerRocket.rocketImgWidth))
                    
                {
                    // Here we check if the rocket speed isn't too high.
                    if(playerRocket.speedY <= playerRocket.topLandingSpeed) {
                        playerRocket.landed = true;
   	
     
                    }
                    else
                        playerRocket.crashed = true;
                }
                else
                    playerRocket.crashed = true;
        	}
        	else if(StageBase.stage_count==99)
        	{
        		if((playerRocket.x > landingArea.x) && (playerRocket.x < landingArea.x + landingArea.landingAreaImgWidth5 - playerRocket.rocketImgWidth))
                    
                {
                    // Here we check if the rocket speed isn't too high.
                    if(playerRocket.speedY <= playerRocket.topLandingSpeed) {
                        playerRocket.landed = true;	
     
                    }
                    else
                        playerRocket.crashed = true;
                }
                else
                    playerRocket.crashed = true;
        	}
            Framework.gameState = Framework.GameState.GAMEOVER;
        }
        Rectangle rocketRectangle = new Rectangle(playerRocket.x,playerRocket.y,playerRocket.rocketImg.getWidth(),playerRocket.rocketImg.getHeight());
        Rectangle obstacleRectangle = new Rectangle(obstacle.x,obstacle.y,obstacle.obstacleImg.getWidth(),obstacle.obstacleImg.getHeight());
        if(rocketRectangle.intersects(obstacleRectangle)) {
           playerRocket.crashed = true;
           Framework.gameState = Framework.GameState.GAMEOVER;
        }
        Rectangle rocketRectangle2 = new Rectangle(playerRocket.x,playerRocket.y,playerRocket.rocketImg.getWidth(),playerRocket.rocketImg.getHeight());
        Rectangle obstacleRectangle2 = new Rectangle(obstacle2.x2,obstacle2.y2,obstacle2.obstacleImg2.getWidth(),obstacle2.obstacleImg2.getHeight());
        if(rocketRectangle2.intersects(obstacleRectangle2)) {
           playerRocket.crashed = true;
           Framework.gameState = Framework.GameState.GAMEOVER;
        }
       
        Rectangle movingobstacleRectangle = new Rectangle(movingobstacle.x,movingobstacle.y,movingobstacle.movingobstacleImg.getWidth(),movingobstacle.movingobstacleImg.getHeight());
        
        if(rocketRectangle.intersects(movingobstacleRectangle)) {
           playerRocket.crashed = true;
           Framework.gameState = Framework.GameState.GAMEOVER;
    }
        
       
        
Rectangle movingobstacleRectangle2 = new Rectangle(movingobstacle2.x2,movingobstacle2.y2,movingobstacle2.movingobstacle2Img.getWidth(),movingobstacle2.movingobstacle2Img.getHeight());
        
        if(rocketRectangle.intersects(movingobstacleRectangle2)) {
           playerRocket.crashed = true;
           Framework.gameState = Framework.GameState.GAMEOVER;
        }
        if(Mode==true)
        {
    Rectangle enemyRectangle = new Rectangle(playerEnemy.x,playerEnemy.y,playerEnemy.enemyImg.getWidth(),playerEnemy.enemyImg.getHeight());
        {
        	 if(enemyRectangle.intersects(rocketRectangle)) {
                 playerRocket.crashed = true;
                 Framework.gameState = Framework.GameState.GAMEOVER;
        }
    }
        }
    }
    }
