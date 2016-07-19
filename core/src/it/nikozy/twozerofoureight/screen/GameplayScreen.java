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
import it.nikozy.twozerofoureight.ui.Tile;
import it.nikozy.twozerofoureight.util.Grid;

import static it.nikozy.twozerofoureight.util.GameConfiguration.*;

public class GameplayScreen implements Screen {
    public final String TAG = GameplayScreen.class.getName();

    private Cell[] mCells;
    private Stage mStage;

    @Override
    public void show() {
        Gdx.app.log(TAG, "show()");
        mStage = new Stage(new StretchViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT));
        mStage.setDebugAll(true);
        mCells = new Cell[Grid.SIZE * Grid.SIZE];

        Table table = new Table();
        table.setFillParent(true);
        table.setTransform(true);
        mStage.addActor(table);

        for(int i = 0; i < Grid.SIZE * Grid.SIZE; i++) {
            if(i % Grid.SIZE == 0) table.row();
            mCells[i] = table.add().size(128.0f);
        }

        getTile(0, 0).setActor(new Tile(2));
        getTile(1, 0).setActor(new Tile(4));
        getTile(2, 0).setActor(new Tile(8));
        getTile(3, 0).setActor(new Tile(16));
        getTile(0, 1).setActor(new Tile(32));
        getTile(1, 1).setActor(new Tile(64));
        getTile(2, 1).setActor(new Tile(128));
        getTile(3, 1).setActor(new Tile(256));
        getTile(0, 2).setActor(new Tile(512));
        getTile(1, 2).setActor(new Tile(1024));
        getTile(2, 2).setActor(new Tile(2048));

        final Grid grid = new Grid();
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
        return mCells[y * Grid.SIZE + x];
    }
}