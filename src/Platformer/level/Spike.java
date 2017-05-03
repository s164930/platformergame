/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Platformer.level;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author viktor
 */
public class Spike extends LevelObject {

    Image image;
    
    public Spike(float x, float y) throws SlickException {
        super(x, y);
        
        image = new Image("data/img/objekts/spikes.png");
    }

    @Override
    public void render(float offset_x, float offset_y) {
        image.draw(x-2-offset_x, y-offset_y+2);
    }
    
}
