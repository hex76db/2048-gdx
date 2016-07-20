package it.nikozy.twozerofoureight.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import it.nikozy.twozerofoureight.util.Grid;
import it.nikozy.twozerofoureight.util.Utilities;

import static it.nikozy.twozerofoureight.util.GameConfiguration.VIEWPORT_HEIGHT;
import static it.nikozy.twozerofoureight.util.GameConfiguration.VIEWPORT_WIDTH;

public class GameplayScreen implements Screen {
    public final String TAG = GameplayScreen.class.getName();

    private Cell[] mCells;
    private Grid mGameLogic;
    private Stage mStage;

    @Override
    public void show() {
        Gdx.app.log(TAG, "show()");
        mStage = new Stage(new StretchViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT));
        mCells = new Cell[mGameLogic.SIZE * mGameLogic.SIZE];

        Label highscore = new Label("MOVE ARROWS TO PLAY", new Skin(Gdx.files.internal("uiskin.json")), "default");
        highscore.setPosition(VIEWPORT_WIDTH / 6, VIEWPORT_HEIGHT - 100);
        mStage.addActor(highscore);
        Table table = new Table();
        table.setFillParent(true);
        table.setTransform(true);
        table.setDebug(true);

        for(int i = 0; i < mGameLogic.SIZE * mGameLogic.SIZE; i++) {
            if(i % mGameLogic.SIZE == 0) table.row();
            mCells[i] = table.add().size(128.0f);
        }
        table.pack();

        Gdx.app.log(TAG, String.format("x %f, y %f", table.getX(), table.getY()));
        Image back = new Image(Utilities.GRAY_TEXTURE);
        back.setBounds(0.0f + 44.f, 0.0f + 144, 128 * Grid.SIZE, 128 * Grid.SIZE);
        back.setDebug(true);
        mStage.addActor(back);
        mStage.addActor(table);

        mGameLogic = new Grid(mCells, highscore);
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
                    /*case Input.Keys.A:
                        mGameLogic.addTile();
                        Gdx.app.log(TAG, "\n" + mGameLogic.toString());
                        break;*/

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