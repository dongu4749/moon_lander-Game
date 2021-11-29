package moon_lander;

import java.awt.Graphics2D;

public interface GameObstacle {
   
   public int getCoordinateX();
   
   public int getCoordinateY();
   
   public void Initialize();
   
   public void LoadContent();
   
//   public void ResetObstacle();
   
   public void Draw(Graphics2D g2d);
}
