package moon_lander;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JRootPane;
import javax.swing.RepaintManager;
import javax.swing.SwingUtilities;

import moon_lander.Framework.GameState;
import player.PlayerRocket;
import ranking.RankingCalculator;
import ranking.RankingViewer;
import ranking.RankingWriter;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
/**
 * Framework that controls the game (Game.java) that created it, update it and draw it on the screen.
 * 
 * @author www.gametutorial.net
 */

public class Framework extends Canvas {
    
	
	private ImageCreate image = new ImageCreate();
    /**
     * Width of the frame.
     */
    public static int frameWidth;
    /**
     * Height of the frame.
     */
    public static int frameHeight;
    Audio backgroundMusic;
    public static final long secInNanosec = 1000000000L;
    
    /**
     * Time of one millisecond in nanoseconds.
     * 1 millisecond = 1 000 000 nanoseconds
     */
    public static final long milisecInNanosec = 1000000L;
    
    /**
     * FPS - Frames per second
     * How many times per second the game should update?
     */
    private final int GAME_FPS = 16;
    /**
     * Pause between updates. It is in nanoseconds.
     */
    private final long GAME_UPDATE_PERIOD = secInNanosec / GAME_FPS;
    
    /**
     * Possible states of the game	
     */
    public static enum GameState{STARTING,GAME_START,VISUALIZING, GAME_CONTENT_LOADING, MAIN_MENU, OPTIONS, PLAYING, GAMEOVER,PLAYER_SELECT,STATISTICS,PRODUCER_PAGE,HELP,CHARACTER_SELECT}
    /**
     * Current state of the game
     */
    public static GameState gameState;
    /**
     * Elapsed game time in nanoseconds.
     */
    public static long gameTime;
    public static long pauseTime;
    // It is used for calculating elapsed time.
    public static long lastTime;
    
    // The actual game
    public static Game game;
    private void addButton()
    {
    	add(image.main_Start_Button);
    	add(image.game_Start_Button);
    	add(image.statistics_Button);
    	add(image.producer_Button);
    	add(image.help_Button);
    	add(image.hidden_Button);
    	add(image.goToMain_Button);
    	add(image.goToNextStage_Button);
    	add(image.restart_Button);
    	add(image.music_Start_Button);
    	add(image.music_Stop_Button);
    	add(image.one_Button);
    	add(image.two_Button);
    	add(image.rocket1Select_Button);
    	add(image.rocket2Select_Button);
    	add(image.rocket3Select_Button);
    }
	private void Initialize()
    {
    	addButton();
    	image.rocketButton(this);
    	image.menuButton(this);
    	image.ingameButton(this);
    }
	/**
     * Image for menu.
     */
    public Framework ()
    {
        super(); // Canvas() : 기본생성자 호출!
        
        gameState = GameState.VISUALIZING;
        
        //We start game in new thread.
        Thread gameThread = new Thread() {
            @Override
            public void run(){
                GameLoop();
            }
        };
        
        gameThread.start();
    }
    
    
   /**
     * Set variables and objects.
     * This method is intended to set the variables and objects for this class, variables and objects for the actual game can be set in Game.java.
     */
 
    
    /**
     * Load files - images, sounds, ...
     * This method is intended to load files for this class, files for the actual game can be loaded in Game.java.
     */
    private void LoadContent()
    {
        image.ImageLoadContent();
		backgroundMusic = new Audio("src/audio/bgm.wav",true);
		if(backgroundMusic.audioPlayingTrue())
		backgroundMusic.start();
    }
    
    /**
     * In specific intervals of time (GAME_UPDATE_PERIOD) the game/logic is updated and then the game is drawn on the screen.
     */
    private void GameLoop()
    {
    	
        // This two variables are used in VISUALIZING state of the game. We used them to wait some time so that we get correct frame/window resolution.
        long visualizingTime = 0, lastVisualizingTime = System.nanoTime();
        
        // This variables are used for calculating the time that defines for how long we should put threat to sleep to meet the GAME_FPS.
        long beginTime, timeTaken, timeLeft;
        boolean first_in = false;
        
        while(true)
        {
            beginTime = System.nanoTime();
            
            switch (gameState)
            {
                case PLAYING:
                    
                    if(PauseOption.isPause == false)
                    {
                     gameTime += System.nanoTime() - lastTime;
                     game.UpdateGame(gameTime, mousePosition());
                    
                     lastTime = System.nanoTime();
                    }
                    else if(PauseOption.isPause == true)
                    {
                    pauseTime += System.nanoTime() - lastTime;
                    game.UpdateGame(gameTime, mousePosition());
                    
                    lastTime = System.nanoTime();
                    }
                    image.buttonHidden();
                    
                    first_in = true;
                break;
                case GAMEOVER:
                	
                	if(game.playerRocket.isLanded==false)
                	{
                		image.restart_Button.setVisible(true);
                		image.goToNextStage_Button.setVisible(false);
                	}
                	 if(StageBase.stage_count==6)
                	 {
                		 if(game.playerRocket.isLanded==true)
                		 {
                			 image.restart_Button.setVisible(false);
                		 }
                	 }
                 	if(first_in == true && game.playerRocket.isLanded == true) {
                 		RankingCalculator.save_Score(StageBase.stage_count, StageBase.time_Score,PlayerRocket.hp); 
                 		// 5레벨에서 깨도 한번 ++되어서 ==6으로 바꿈.
                 		if(StageBase.stage_count == 5) {
                 			RankingCalculator.add_Score();
                 			RankingCalculator.calculation_Ranking();
                 			RankingWriter.update_Ranking();
                 		}
                 	first_in = false;
                 	}
                break;
                case STATISTICS:
                	image.buttonHidden();
                	new RankingViewer();
                    gameState = GameState.MAIN_MENU;
                break;
                case STARTING:
                    // Sets variables and objects.
                    Initialize();
                    // Load files - images, sounds, ...
                    LoadContent();
                    
                    // When all things that are called above finished, we change game status to main menu.
                    gameState = GameState.MAIN_MENU;
                break;
                case PRODUCER_PAGE:
                	image.goToMain_Button.setVisible(true);
                	break;
                	
                case HELP:
                	image.goToMain_Button.setVisible(true);
                	break;
                case GAME_START:
                	
                	break;
                case VISUALIZING:
                    // On Ubuntu OS (when I tested on my old computer) this.getWidth() method doesn't return the correct value immediately (eg. for frame that should be 800px width, returns 0 than 790 and at last 798px). 
                    // So we wait one second for the window/frame to be set to its correct size. Just in case we
                    // also insert 'this.getWidth() > 1' condition in case when the window/frame size wasn't set in time,
                    // so that we although get approximately size.
                    if(this.getWidth() > 1 && visualizingTime > secInNanosec)
                    {
                        frameWidth = this.getWidth();
                        frameHeight = this.getHeight();

                        // When we get size of frame we change status.
                        gameState = GameState.STARTING;
                    }
                    else
                    {
                        visualizingTime += System.nanoTime() - lastVisualizingTime;
                        lastVisualizingTime = System.nanoTime();
                    }
                break;
            }
            
            // Repaint the screen.
            repaint();
            
            // Here we calculate the time that defines for how long we should put threat to sleep to meet the GAME_FPS.
            timeTaken = System.nanoTime() - beginTime;
            timeLeft = (GAME_UPDATE_PERIOD - timeTaken) / milisecInNanosec; // In milliseconds
            
            // If the time is less than 10 milliseconds, then we will put thread to sleep for 10 millisecond so that some other thread can do some work.
            if (timeLeft < 10) 
                timeLeft = 10; //set a minimum
            
            try {
                 //Provides the necessary delay and also yields control so that other thread can do work.
                 Thread.sleep(timeLeft);
            } catch (InterruptedException ex) { }
        }
    }
   
   
    /**
     * Draw the game to the screen. It is called through repaint() method in GameLoop() method.
     */
    @Override
    public void Draw(Graphics2D g2d)
    {
        switch (gameState)
        {
            case PLAYING:
            	StageBase stagebase = new StageBase();
    			stagebase.call_BackgroungImage();
    			int stage_level=1;
    			g2d.drawString("스테이지:"+stage_level,700, 20);
    			
            	for(int i=2;i<=5;i++)
            	{
            		for(int j=2;j<=5;j++)
            			if(StageBase.stage_count==j)
            			{
            				stage_level++;
            				g2d.drawString("스테이지:"+stage_level,700, 20);
            				double speed=1.2;
            		        game.playerRocket.setSpeedStopping(speed);
            		        speed += 0.2;	
            			}
            	}
            	            
            	if(StageBase.stage_count==6)
    				game.playerRocket.setSpeedStopping(3);
                StageBase.Draw(g2d, mousePosition());
               
            break;
            case PRODUCER_PAGE:
            	image.buttonHidden();
            	image.goToMain_Button.setVisible(true);
            	g2d.drawImage(image.produerPageImg, 0, 0, frameWidth, frameHeight, null);
            	
            break;
            case GAME_START:
            	image.buttonHidden();
            	image.setGameStartButton();
            	image.goToMain_Button.setVisible(true);
            	image.game_Start_Button.setVisible(true);
            	g2d.drawImage(image.startPageImg, 0, 0, frameWidth, frameHeight, null);
            	break;
            case HELP:
            	image.buttonHidden();
            	image.goToMain_Button.setVisible(true);
            	g2d.drawImage(image.helpPageImg, 0, 0, frameWidth, frameHeight, null);
            	
            	break;
            case PLAYER_SELECT:
            	image.buttonHidden();
            	image.goToMain_Button.setVisible(true);
            	image.one_Button.setVisible(true);
            	image.two_Button.setVisible(true);
            	image.music_Start_Button.setVisible(true);
            	image.music_Stop_Button.setVisible(true);
            	image.goToMain_Button.setVisible(true);
            	image.setMusicButton();
            	image.setPlayerButton();
            	g2d.drawImage(image.playerSelectPageImg, 0, 0, frameWidth, frameHeight, null);
   
            	break;
            case CHARACTER_SELECT:
            	image.buttonHidden();
            	image.rocket1Select_Button.setVisible(true);
            	image.rocket2Select_Button.setVisible(true);
            	image.rocket3Select_Button.setVisible(true);
            	image.goToMain_Button.setVisible(true);
            	image.setRocketButton();
            	g2d.drawImage(image.characterSelectPageImg, 0, 0, frameWidth, frameHeight, null);
            	
            break;	
            case GAMEOVER:
            	image.goToMain_Button.setVisible(true);
            	if(game.playerRocket.isLanded == true)
            	image.goToNextStage_Button.setVisible(true);
            	image.music_Start_Button.setVisible(true);
            	image.music_Stop_Button.setVisible(true);
            	image.setGameOverButton();
            	image.setMusicButton();
            	StageBase.DrawGameOver(g2d, mousePosition(), gameTime,pauseTime);
            	
            break;
            case MAIN_MENU:
            	image.buttonHidden();
            	image.mainButtonVisible();
            	image.setMainButton();
            	image.setMusicButton();
                g2d.drawImage(image.moonLanderMenuImg, 0, 0, frameWidth, frameHeight, null);
                g2d.setColor(Color.green);
                g2d.drawString("WWW.GAMETUTORIAL.NET", 7, frameHeight-120);
                g2d.drawString("Best Score:"+" "+ RankingCalculator.final_Score[0][1], 7, frameHeight - 140);
               
            break;

            case GAME_CONTENT_LOADING:
            	image.buttonHidden();
                g2d.setColor(Color.white);
                g2d.drawString("GAME is LOADING", frameWidth / 2 - 50, frameHeight / 2);
            break;
        }
    }
   
    
    /**
     * Starts new game.
     */
    public static void newGame()  {
        // We set gameTime to zero and lastTime to current time for later calculations.
        gameTime = 0;
        pauseTime = 0;
        lastTime = System.nanoTime();
        
        game = new Game();
    }
    
    /**
     *  Restart game - reset game time and call RestartGame() method of game object so that reset some variables.
     */
    void restartGame()
    {
        // We set gameTime to zero and lastTime to current time for later calculations.
        gameTime = 0;
        lastTime = System.nanoTime();
        pauseTime=0;
        game.RestartGame();
        
        // We change game status so that the game can start.
        gameState = GameState.PLAYING;
        
    }
    
    /**
     * Returns the position of the mouse pointer in game frame/window.
     * If mouse position is null than this method return 0,0 coordinate.
     * 
     * @return Point of mouse coordinates.
     */
    private Point mousePosition()
    {
        try
        {
            Point mp = this.getMousePosition();
            
            if(mp != null)
                return this.getMousePosition();
            else
                return new Point(0, 0);
        }
        catch (Exception e)
        {
            return new Point(0, 0);
        }
    }
    
    /**
     * This method is called when keyboard key is released.
     * 
     * @param e KeyEvent
     */
        
    /**
     * This method is called when mouse button is clicked.
     * 
     * @param e MouseEvent
     */
    @Override
    public void mouseClicked(MouseEvent e)
    {
        
    }
	@Override
	public void keyReleasedFramework(KeyEvent e) {
		// TODO 자동 생성된 메소드 스텁
		
	}
}
