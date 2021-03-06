package pkg.islandadventure.gamestate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import pkg.islandadventure.input.Keyboard;

public class IntroState extends GameState {

    private BufferedImage logo;

    private int alpha;
    private int ticks;

    private final int FADE_IN = 80;
    private final int LENGTH = 60;
    private final int FADE_OUT = 60;

    public IntroState(GameStateManager gsm, Keyboard keyboard) {
        super(gsm, keyboard);
    }

    @Override
    public void init() {
        ticks = 0;
        try {
            logo = ImageIO.read(getClass().getResourceAsStream("/bell_labs.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        keyboard.update();
        handleInput();
        ticks++;
        if (ticks < FADE_IN) {
            alpha = (int) (255 - 255 * (1.0 * ticks / FADE_IN));
            if (alpha < 0) {
                alpha = 0;
            }
        }
        if (ticks > FADE_IN + LENGTH) {
            alpha = (int) (255 * (1.0 * ticks - FADE_IN - LENGTH) / FADE_OUT);
            if (alpha > 255) {
                alpha = 255;
            }
        }
        if (ticks > FADE_IN + LENGTH + FADE_OUT) {
            gsm.setState(GameStateManager.PLAY);
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 256, 256);
        g.drawImage(logo, 0, 0, SCREEN_WIDTH * SCALE, SCREEN_HEIGHT * SCALE, null);
        g.setColor(new Color(0, 0, 0, alpha));
        g.fillRect(0, 0, SCREEN_WIDTH * SCALE, SCREEN_HEIGHT * SCALE);
    }

    public void handleInput() {
        if (keyboard.enter()) {
            gsm.setState(GameStateManager.PLAY);
        }

    }

}
