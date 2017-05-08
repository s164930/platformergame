/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Platformer.PlatformerGame;
import Platformer.character.Player;
import Platformer.level.Level;
import Platformer.level.LevelObject;
import Platformer.level.Spike;
import Platformer.level.tile.AirTile;
import Platformer.level.tile.Tile;
import Platformer.physics.ABoundingRect;
import Platformer.physics.Physics;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.newdawn.slick.SlickException;

/**
 *
 * @author viktor
 */
public class PlatformerGameTest {
    private static Thread t;
    
    public PlatformerGameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws InterruptedException {
        t = new Thread(new engineTest());
        t.start();
        
        Thread.sleep(6000);
        
    }
    
    @AfterClass
    public static void tearDownClass() throws InterruptedException {
        PlatformerGame.doneTesting = true;
        t.interrupt();
        
        Thread.sleep(1000);
    }
    
    @Test
    public void testLevelTiles() throws SlickException{
        Player player = new Player(0,0);
        Level level = new Level("level_1", player);
        Tile[][] map = level.getTiles();
        boolean isAirTile = false;
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                if(map[i][j].getBoundingShape() == null){
                    isAirTile = true;
                }
            }
        }
        assertTrue(isAirTile);
    }
    
    @Test
    public void testCollision() {
        ABoundingRect first = new ABoundingRect(12,12,32,32);
        ABoundingRect second = new ABoundingRect(30,12,32,32);
        assertTrue(first.checkCollision(second));
    }
    
    @Test
    public void testDeath() throws SlickException {
        Player player = new Player(0,0);
        Level level = new Level("level_1", player);
        
        Physics physics = new Physics();
        level.player.setX(100);
        level.player.setY(100);
        LevelObject spike = new Spike(100,100);
        level.addLevelObject(spike);
        physics.handlePhysics(level, 60);
        assertTrue(physics.isDead());
        
    }
    
    @Test
    public void testGravity() throws SlickException{
        Player player = new Player(0,0);
        Level level = new Level("level_1", player);
        
        Physics physics = new Physics();
        player.jump();
        physics.handlePhysics(level, 60);
        assertTrue(player.getYVelocity() < 0);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
