/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Platformer.state;

import Platformer.state.HighScore;

import Platformer.PlatformerGame;
import Platformer.character.Player;
import Platformer.controller.MouseAndKeyBoardPlayerController;
import Platformer.controller.PlayerController;
import Platformer.level.Level;
import Platformer.level.Objective;
import Platformer.physics.Physics;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import org.newdawn.slick.Music;
/**
 *
 * @author vikto
 */
public class LevelState extends BasicGameState{
    
    Level level;
    String startingLevel;
    int ID = 1;
    
    public boolean playMusic = false;
    private Music levelMusic;
    
    long startTime;
    
    private Player player;
    private PlayerController playerController;
    
    private Physics physics;
    
    Input input;
    
    public LevelState(String startingLevel){
        this.startingLevel = startingLevel;
    }
    
    public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
        levelMusic = new Music("data/music/level.ogg");
        // når levelstate bliver addet til statelisten, skal musikken fra lvl'et ikke start
        if (playMusic) levelMusic.loop();
        playMusic = true;
        
        startTime = System.nanoTime();
        
        player = new Player(128, 415);
        level = new Level(startingLevel, player);

        playerController = new MouseAndKeyBoardPlayerController(player);
        physics = new Physics();
    }
    
    public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException{
        playerController.handleInput(container.getInput(), delta);
        physics.handlePhysics(level, delta);
        if(container.getInput().isKeyPressed(Input.KEY_ESCAPE)){
            levelMusic.stop();
            sbg.getState(PlatformerGame.MAINMENU).init(container, sbg);
            sbg.enterState(PlatformerGame.MAINMENU, new FadeOutTransition(), new FadeInTransition());
        }
        if(physics.getCollected() >= level.getNumberOfObjects()){
            levelMusic.stop();
            long endTime = System.nanoTime() - startTime;
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            System.out.println(df.format(endTime / 1000000000.0).replace(",","."));
            HighScore.scores.add(Double.parseDouble(df.format((double)endTime/1000000000.0).replace(",",".")));
            sbg.getState(PlatformerGame.MAINMENU).init(container, sbg);
            sbg.enterState(PlatformerGame.MAINMENU, new FadeOutTransition(), new FadeInTransition());
        }
        

    }
    
    public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException{
        g.scale(PlatformerGame.SCALE, PlatformerGame.SCALE);
        level.render();
    }
    
    public int getID(){
        return ID;
    }
    
}
