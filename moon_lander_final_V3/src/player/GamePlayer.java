package player;

import java.awt.Graphics2D;

public interface GamePlayer{
   
   
    public int getCoordinateX();
       
    public int getCoordinateY();
    
    public void setSpeedStopping(double speedStopping);
    
    public int getSpeedX();
    
    public int getSpeedY();
    
    public void Initialize();
    
    public void LoadContent();
    
    public void ResetPlayer();
    
    public void Draw(Graphics2D g2d);
}
