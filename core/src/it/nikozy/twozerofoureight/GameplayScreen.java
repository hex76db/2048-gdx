package it.nikozy.twozerofoureight;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import it.nikozy.twozerofoureight.util.Grid;

public class GameplayScreen implements Screen {
    public final String TAG = GameplayScreen.class.getName();

    @Override
    public void show() {
        Gdx.app.log(TAG, "show()");

        final Grid grid = new Grid();
        Gdx.app.log(TAG, "\n" + grid.toString());
        Gdx.input.setInputProcessor(new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                switch (keycode) {
                    case Input.Keys.UP:
                        grid.up();
                        Gdx.app.log(TAG, "\n" + grid.toString());
                        break;
                    case Input.Keys.DOWN:
                        grid.down();
                        Gdx.app.log(TAG, "\n" + grid.toString());
                        break;
                    case Input.Keys.LEFT:
                        grid.left();
                        Gdx.app.log(TAG, "\n" + grid.toString());
                        break;
                    case Input.Keys.RIGHT:
                        grid.right();
                        Gdx.app.log(TAG, "\n" + grid.toString());
                        break;
                }
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(int amount) {
                return false;
            }
        });
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
