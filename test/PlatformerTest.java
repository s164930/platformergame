/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import Platformer.*;
import Platformer.character.Player;
import org.newdawn.slick.SlickException;

/**
 *
 * @author viktor
 */
public class PlatformerTest {
    Player character;
    
    PlatformerTest() throws SlickException{
        character = new Player(0,0);
    }
    
    public void isMovingTest(){
        character.setMoving(true);
        assertTrue(character.isMoving());
    }
    
    
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
