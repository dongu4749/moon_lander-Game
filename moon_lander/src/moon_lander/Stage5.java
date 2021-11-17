package moon_lander;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

public class Stage5 extends StageBase {

	@Override
	public void BackgroundImage() {
		// TODO Auto-generated method stub
		
		try
        {
			
            URL backgroundImgUrl = this.getClass().getResource("/resources/images/background5.jpg");
            backgroundImg = ImageIO.read(backgroundImgUrl);
            
            URL redBorderImgUrl = this.getClass().getResource("/resources/images/red_border.png");
            redBorderImg = ImageIO.read(redBorderImgUrl);
        }
        catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }

	}

}
