package pkg.islandadventure.gamestate;

import java.awt.Graphics;

public abstract class GameState {

    protected GameStateManager gsm;

    public GameState(GameStateManager gsm) {
        this.gsm = gsm;
    }

    public abstract void init();

    public abstract void update();

    public abstract void draw(Graphics g);
}
