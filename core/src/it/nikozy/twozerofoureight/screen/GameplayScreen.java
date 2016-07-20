package it.nikozy.twozerofoureight.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import it.nikozy.twozerofoureight.ui.UITile;
import it.nikozy.twozerofoureight.util.Grid;

import static it.nikozy.twozerofoureight.util.GameConfiguration.*;

public class GameplayScreen implements Screen {
    public final String TAG = GameplayScreen.class.getName();

    private Cell[] mCells;
    private Grid mGameLogic;
    private Stage mStage;

    @Override
    public void show() {
        Gdx.app.log(TAG, "show()");
        mStage = new Stage(new StretchViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT));
        mStage.setDebugAll(true);
        mCells = new Cell[mGameLogic.SIZE * mGameLogic.SIZE];

        Table table = new Table();
        table.setFillParent(true);
        table.setTransform(true);
        mStage.addActor(table);

        for(int i = 0; i < mGameLogic.SIZE * mGameLogic.SIZE; i++) {
            if(i % mGameLogic.SIZE == 0) table.row();
            mCells[i] = table.add().size(128.0f);
        }

        mGameLogic = new Grid(mCells);
        Gdx.input.setInputProcessor(new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                switch (keycode) {
                    case Input.Keys.UP:
                        mGameLogic.up();
                        Gdx.app.log(TAG, "\n" + mGameLogic.toString());
                        break;
                    case Input.Keys.DOWN:
                        mGameLogic.down();
                        Gdx.app.log(TAG, "\n" + mGameLogic.toString());
                        break;
                    case Input.Keys.LEFT:
                        mGameLogic.left();
                        Gdx.app.log(TAG, "\n" + mGameLogic.toString());
                        break;
                    case Input.Keys.RIGHT:
                        mGameLogic.right();
                        Gdx.app.log(TAG, "\n" + mGameLogic.toString());
                        break;
                    default:
                        Gdx.app.log(TAG, "\n" + mGameLogic.toString());
                        break;
                    case Input.Keys.R:
                        mGameLogic.init();
                        Gdx.app.log(TAG, "\n" + mGameLogic.toString());
                        break;
                    case Input.Keys.A:
                        mGameLogic.addTile();
                        Gdx.app.log(TAG, "\n" + mGameLogic.toString());
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
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1.f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        mStage.act(delta);
        mStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        mStage.getViewport().update(width, height);
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

    public Cell getTile(int x, int y) {
        return mCells[y * mGameLogic.SIZE + x];
    }
}