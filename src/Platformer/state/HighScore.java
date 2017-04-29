/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Platformer.state;

import Platformer.PlatformerGame;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import java.awt.Font;
import java.io.BufferedWriter;
import java.io.FileWriter;

import org.newdawn.slick.*;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
/**
 *
 * @author viktor
 */
public class HighScore extends BasicGameState{
    private Image background;
    private BufferedReader br;
    private FileReader fr;
    private FileWriter fw;
    private BufferedWriter bw;
    public static ArrayList<Double> scores;
    private Font font;
    private Font bigFont;
    private TrueTypeFont highScoresTTF;
    private TrueTypeFont bigFontTTF;
    int newest;
    
    public HighScore() throws SlickException{ 
        scores = new ArrayList<Double>();
        
        try {
            fr = new FileReader("data/score/highscores_level_0");
            br = new BufferedReader(fr);
            
            String currentScore = br.readLine();
            
            while(currentScore != null){
                double temp = Double.parseDouble(currentScore);
                scores.add(temp);
                currentScore =  br.readLine();
            }
            

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }


    @Override
    public int getID() {
        return 2;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        font = new Font("Verdana", Font.BOLD, 20);
        bigFont = new Font("Verdana", Font.BOLD, 40);
        bigFontTTF = new TrueTypeFont(bigFont, true);
        highScoresTTF = new TrueTypeFont(font, true);
        background = new Image("data/img/backgrounds/mainmenuBackground.png");
        Collections.sort(scores);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        background.draw();
        bigFontTTF.drawString(PlatformerGame.WINDOW_WIDTH/2 - 140, 60, "Highscores");
        renderHighScores();
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if(gc.getInput().isKeyPressed(Input.KEY_ESCAPE)){
            sbg.enterState(PlatformerGame.MAINMENU, new FadeOutTransition(), new FadeInTransition());
        }
    }

    private void renderHighScores() {
        int i = 1;
        for (Double d : scores){
            if(i<=10){
                highScoresTTF.drawString(PlatformerGame.WINDOW_WIDTH/2 - 70, i * 30 + 100, i + ": " + d);
            }
            i++;
            
        }
    }

    
}
