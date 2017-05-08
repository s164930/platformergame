
import Platformer.PlatformerGame;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.SlickException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author viktor
 */
public class engineTest implements Runnable {

    @Override
    public void run() {
        try {
            PlatformerGame.main(null);
        } catch (SlickException ex) {
            Logger.getLogger(engineTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
