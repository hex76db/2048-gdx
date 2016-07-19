package it.nikozy.twozerofoureight;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import static it.nikozy.twozerofoureight.util.GameConfiguration.TIME_SCALE_FACTOR;

public class TwoZeroFourEight extends Game {
	public static final String TAG = TwoZeroFourEight.class.getName();

	@Override
	public void create() {
		setScreen(new GameplayScreen());
	}

	@Override
	public void render() {
		if(screen != null) {
			screen.render(Gdx.graphics.getDeltaTime() * TIME_SCALE_FACTOR);
		}
	}
}
