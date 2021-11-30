package moon_lander;

public class PauseOption {
    private int p1speedX=1;
    private int p1speedY=1;
    private int p2speedX=1; 
    private int p2speedY=1;
    
    
    public static boolean isPause = false;
    
    public void pauseStore()
    {
       
         if(isPause == true )
         {
            p1speedX=Framework.game.playerRocket.getSpeedX();
            p1speedY=Framework.game.playerRocket.getSpeedY();
            
            p2speedX=Framework.game.playerEnemy.getSpeedX();
            p2speedY=Framework.game.playerEnemy.getSpeedY();
           
            
            
         }
    }
    
    public void gamePause()
    {
       
        Framework.game.playerRocket.setSpeedX(0);
        Framework.game.playerRocket.setSpeedY(0);
        Framework.game.movingobstacle.setSpeedX(0);
        Framework.game.movingobstacle1.setSpeedX(0);
        Framework.game.playerRocket.setSpeedStopping(0);
        Framework.game.shootingobstacle.setSpeedX(0);
        
        if(Game.isMode==true) {
        Framework.game.playerEnemy.setSpeedStopping(0);
        Framework.game.playerEnemy.setSpeedX(0);
        Framework.game.playerEnemy.setSpeedY(0);
        }
    }   
    
    public void gameRestart()
    {
       Framework.game.movingobstacle.setSpeedX(7);
       Framework.game.movingobstacle1.setSpeedX(7);
       Framework.game.shootingobstacle.setSpeedX(7);
       Framework.game.playerRocket.setSpeedX(p1speedX);
       Framework.game.playerRocket.setSpeedX(p1speedY);
       Framework.game.playerRocket.setSpeedStopping(1);

       if(Game.isMode==true) {
        Framework.game.playerEnemy.setSpeedX(p2speedX);
        Framework.game.playerEnemy.setSpeedY(p2speedY);
        Framework.game.playerEnemy.setSpeedStopping(1);
       }
    }
}