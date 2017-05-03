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
public class HighScore extends BasicGameState {

    private Image background;
    private static BufferedReader br;
    private static FileReader fr;
    private static FileWriter fw;
    private static BufferedWriter bw;
    public static ArrayList<Double> scores;
    private Font font;
    private Font bigFont;
    private TrueTypeFont highScoresTTF;
    private TrueTypeFont bigFontTTF;
    private int newest;
    private static String currentLevel;

    @Override
    public int getID() {
        return 2;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        scores = new ArrayList<Double>();
        scores.clear();
        checkCurrentLevel();
        loadScores();
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
        bigFontTTF.drawString(PlatformerGame.WINDOW_WIDTH / 2 - 140, 60, "Highscores");
        renderHighScores();
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
            sbg.getState(PlatformerGame.MAINMENU).init(gc, sbg);
            sbg.enterState(PlatformerGame.MAINMENU, new FadeOutTransition(), new FadeInTransition());
        }
    }

    private void renderHighScores() {
        int i = 1;
        for (Double d : scores) {
            if (i <= 10) {
                highScoresTTF.drawString(PlatformerGame.WINDOW_WIDTH / 2 - 70, i * 30 + 100, i + ": " + d);
            }
            i++;

        }
    }

    private static void checkCurrentLevel() {
        switch (PlatformerGame.currentLevel) {
            case 1:
                currentLevel = "level_1";
                break;
            case 2:
                currentLevel = "level_2";
                break;
            case 3:
                currentLevel = "level_3";
                break;
            default:
                break;
        }
    }

    public static void saveScores(Double d) {
        checkCurrentLevel();
        scores.clear();
        loadScores();
        scores.add(d);
        Collections.sort(scores);
        try {
            fw = new FileWriter("data/score/highscores_" + currentLevel);
            bw = new BufferedWriter(fw);
            int i = 1;
            for (Double score : HighScore.scores) {
                if (i <= 10) {
                    System.out.println(score.toString());
                    bw.write(score.toString());
                    bw.newLine();
                }
                i++;

            }

            bw.close();
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private static void loadScores() {
        checkCurrentLevel();
        try {
            fr = new FileReader("data/score/highscores_" + currentLevel);
            br = new BufferedReader(fr);

            String currentScore = br.readLine();

            while (currentScore != null) {
                double temp = Double.parseDouble(currentScore);
                scores.add(temp);
                currentScore = br.readLine();
            }
            fr.close();
            br.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
