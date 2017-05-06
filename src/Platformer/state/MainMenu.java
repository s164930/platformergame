/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Platformer.state;

import Platformer.PlatformerGame;
import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.Music;
import org.newdawn.slick.opengl.TextureImpl;

/**
 *
 * @author Viktor Vase Frandsen
 */
public class MainMenu extends BasicGameState {

    public static final int ID = 0;
    private int playerChoice = 0;
    private static final int NOCHOICES = 3;
    private static final int START = 0;
    private static final int HIGHSCORE = 1;
    private static final int QUIT = 2;
    private String[] playersOptions = new String[NOCHOICES];
    private String[] levelOptions = new String[NOCHOICES];
    private boolean exit = false;
    private Font playerOptionsFont, titleFont;
    private TrueTypeFont playersOptionsTTF, titleTTF;
    private Color chosen = new Color(153, 204, 255);
    private static Music mainMenuMusic;
    private Image background;
    private boolean levelSelect = false;
    private boolean highScoreSelect = false;
    private String title = "A Game of Debt";

    public MainMenu() throws SlickException {
        mainMenuMusic = new Music("/data/music/mainMenu.ogg");
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

        if (!mainMenuMusic.playing()) {
            mainMenuMusic.loop();
        }
        playerOptionsFont = new Font("Verdana", Font.BOLD, 40);
        playersOptionsTTF = new TrueTypeFont(playerOptionsFont, true);
        titleFont = new Font("Verdana", Font.BOLD, 40);
        titleTTF = new TrueTypeFont(titleFont, true);

        background = new Image("data/img/backgrounds/mainmenuBackground.png");
        playersOptions[0] = "Start";
        playersOptions[1] = "Highscores";
        playersOptions[2] = "Quit";
        levelOptions[0] = "Level 1";
        levelOptions[1] = "Level 2";
        levelOptions[2] = "Level 3";
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        background.draw();
        titleTTF.drawString(PlatformerGame.WINDOW_WIDTH / 2 - 160, 120, title, Color.orange);

        renderPlayerOptions();
        if (exit) {
            gc.exit();
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();
        if (levelSelect || highScoreSelect) {
            if (input.isKeyPressed(Input.KEY_ESCAPE)) {
                levelSelect = false;
                highScoreSelect = false;
            }
        }
        if (input.isKeyPressed(Input.KEY_DOWN)) {
            if (playerChoice == (NOCHOICES - 1)) {
                playerChoice = 0;
            } else {
                playerChoice++;
            }
        }
        if (input.isKeyPressed(Input.KEY_UP)) {
            if (playerChoice == 0) {
                playerChoice = NOCHOICES - 1;
            } else {
                playerChoice--;
            }
        }
        if (input.isKeyPressed(Input.KEY_ENTER)) {
            if (levelSelect) {
                switch (playerChoice) {
                    case 0:
                        PlatformerGame.currentLevel = 1;
                        break;
                    case 1:
                        PlatformerGame.currentLevel = 2;
                        break;
                    case 2:
                        PlatformerGame.currentLevel = 3;
                        break;
                    default:
                        break;
                }
                if (highScoreSelect) {
                    sbg.getState(PlatformerGame.HIGHSCORE).init(gc, sbg);
                    sbg.enterState(PlatformerGame.HIGHSCORE, new FadeOutTransition(), new FadeInTransition());
                } else {
                    sbg.getState(PlatformerGame.LEVEL).init(gc, sbg);
                    sbg.enterState(PlatformerGame.LEVEL, new FadeOutTransition(), new FadeInTransition());
                    mainMenuMusic.stop();
                }

            } else {
                switch (playerChoice) {
                    case QUIT:
                        exit = true;
                        break;
                    case START:
                        levelSelect = true;
                        break;
                    case HIGHSCORE:
                        highScoreSelect = true;
                        levelSelect = true;
                        break;
                }
            }

        }
    }

    /*
    Tegner de forskellige strings, med farve på spillerens valgte skærm-element.
     */
    private void renderPlayerOptions() {
        if (levelSelect) {
            for (int i = 0; i < NOCHOICES; i++) {
                if (playerChoice == i) {
                    playersOptionsTTF.drawString(PlatformerGame.WINDOW_WIDTH / 2 - 70, i * 50 + 200, levelOptions[i], chosen);
                } else {
                    playersOptionsTTF.drawString(PlatformerGame.WINDOW_WIDTH / 2 - 70, i * 50 + 200, levelOptions[i]);
                }
            }
        } else {
            for (int i = 0; i < NOCHOICES; i++) {
                if (playerChoice == i) {
                    playersOptionsTTF.drawString(PlatformerGame.WINDOW_WIDTH / 2 - 70, i * 50 + 200, playersOptions[i], chosen);

                } else {
                    playersOptionsTTF.drawString(PlatformerGame.WINDOW_WIDTH / 2 - 70, i * 50 + 200, playersOptions[i]);
                }
            }
        }

    }

}
