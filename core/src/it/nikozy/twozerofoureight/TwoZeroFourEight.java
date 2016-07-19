package it.nikozy.twozerofoureight;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import it.nikozy.twozerofoureight.screen.GameplayScreen;
import it.nikozy.twozerofoureight.util.Globals;

import static it.nikozy.twozerofoureight.util.GameConfiguration.FONT_FILE;
import static it.nikozy.twozerofoureight.util.GameConfiguration.TIME_SCALE_FACTOR;

public class TwoZeroFourEight extends Game {
	public static final String TAG = TwoZeroFourEight.class.getName();

	@Override
	public void create() {
        Globals.ASSETS = new AssetManager();
        Globals.ASSETS.load(FONT_FILE, BitmapFont.class);
        Globals.generateBackground();
        Globals.ASSETS.finishLoading();
        setScreen(new GameplayScreen());
	}

	@Override
	public void render() {
		if(screen != null) {
			screen.render(Gdx.graphics.getDeltaTime() * TIME_SCALE_FACTOR);
		}
	}

    @Override
    public void dispose() {
        Globals.ASSETS.dispose();
    }

    @Override
    public void resume() {
        Globals.ASSETS.update();
    }
}
