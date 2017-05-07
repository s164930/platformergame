/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Platformer.character;

import Platformer.physics.ABoundingRect;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author vikto
 */
public class Player extends Character {

    public Player(float x, float y) throws SlickException {
        super(x, y);
        setSprite(new Image("data/img/sprite_platformer/idle.png"));

        setMovingAnimation(new Image[]{new Image("data/img/sprite_platformer/running.png"),
            new Image("data/img/sprite_platformer/running2.png"), new Image("data/img/sprite_platformer/running3.png"),
            new Image("data/img/sprite_platformer/running4.png"), new Image("data/img/sprite_platformer/running5.png"),
            new Image("data/img/sprite_platformer/running6.png"), new Image("data/img/sprite_platformer/running7.png")}, 80);

        boundingShape = new ABoundingRect(x, y, 26, 32);

        accelerationSpeed = 0.001f;
        maximumSpeed = 0.15f;
        maximumFallSpeed = 0.3f;
        decelerationSpeed = 0.001f;
    }

    public void updateBoundingShape() {
        boundingShape.updatePosition(x, y);
    }
}
