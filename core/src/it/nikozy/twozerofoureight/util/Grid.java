package it.nikozy.twozerofoureight.util;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import it.nikozy.twozerofoureight.ui.UITile;

import java.util.ArrayList;

public class Grid {
    private class Tile {
        public int Value;
        public int X;
        public int Y;

        Tile(int value, int x, int y) {
            Value = value;
            X = x;
            Y = y;
        }

        public void clear() {
            Value = 0;
        }

        public boolean isEmpty() {
            return Value == 0;
        }
    }

    public static final int SIZE = 4;

    private int mScore;
    private Tile[] mGrid;
    private Cell[] mTiles;
    private Label mLabel;

    public Grid(Cell[] tiles, Label label) {
        setTiles(tiles);
        setLabel(label);
        init();
        mLabel.setText("MOVE ARROWS TO PLAY");
    }

    public void init() {
        for(Cell c : mTiles) c.clearActor();
        mGrid = new Tile[SIZE * SIZE];
        for(int i = 0; i < SIZE * SIZE; i++) mGrid[i] = new Tile(0, i % SIZE, i / SIZE);
        mScore = 0;

        addTile();
        addTile();
    }

    public ArrayList<Tile> getEmptyTiles() {
        ArrayList<Tile> emptyTile = new ArrayList<Tile>();
        for(int i = 0; i < SIZE * SIZE; i++) {
            if(mGrid[i].isEmpty()) emptyTile.add(mGrid[i]);
        }
        return emptyTile;
    }

    public void addTile() {
        ArrayList<Tile> emptyTiles = getEmptyTiles();
        if(!emptyTiles.isEmpty()) {
            Tile tile = emptyTiles.get(MathUtils.random(0, emptyTiles.size() - 1));
            tile.Value = MathUtils.random() > 0.9 ? 4 : 2;
            animateTileCreation(tile.X, tile.Y, tile.Value);
        }
    }

    public Tile getTile(int x, int y) {
        return getTile(x, y, false);
    }

    public Tile getTile(int x, int y, boolean inverted) {
        return !inverted ? mGrid[y * SIZE + x] : mGrid[x * SIZE + y];
    }

    public void up() {
        move(3);
    }

    public void down() {
        move(1);
    }

    public void left() {
        move(2);
    }

    public void right() {
        move(0);
    }

    // Directions value
    // 0 -> right
    // 1 -> down
    // 2 -> left
    // 3 -> up
    public void move(int direction) {
        boolean moved = false;
        boolean inverted = direction == 0 || direction == 2 ? false : true;
        if(direction == 0 || direction == 1)
            for(int x = SIZE - 2; x >= 0; x--) {
                for(int y = 0; y < SIZE; y++) {
                    if(getTile(x, y, inverted).isEmpty()) continue;
                    int nextPos = x;
                    while(nextPos != SIZE - 1 && getTile(nextPos + 1, y, inverted).isEmpty()) nextPos++;
                    if(nextPos != x) {
                        getTile(nextPos, y, inverted).Value = getTile(x, y, inverted).Value;
                        getTile(x, y, inverted).clear();
                        if(!inverted) moveTileTo(x, y, nextPos, y); else moveTileTo(y, x, y, nextPos);
                        moved = true;
                    }

                    if(nextPos != SIZE - 1) if(getTile(nextPos + 1, y, inverted).Value == getTile(nextPos, y, inverted).Value) {
                        int value = getTile(nextPos + 1, y, inverted).Value += getTile(nextPos, y, inverted).Value;
                        getTile(nextPos, y, inverted).clear();
                        mScore += getTile(nextPos + 1, y, inverted).Value;
                        if(inverted) animateTileMerging(y, nextPos + 1, getTile(nextPos + 1, y, inverted).Value, y, nextPos); else animateTileMerging(nextPos + 1, y, getTile(nextPos + 1, y, inverted).Value, nextPos, y);
                        moved = true;
                    }
                }
            }
        else if(direction == 2 || direction == 3)
            for(int x = 1; x < SIZE; x++) {
                for(int y = 0; y < SIZE; y++) {
                    if(getTile(x, y, inverted).isEmpty()) continue;
                    int nextPos = x;
                    while(nextPos != 0 && getTile(nextPos - 1, y, inverted).isEmpty()) nextPos--;
                    if(nextPos != x) {
                        getTile(nextPos, y, inverted).Value = getTile(x, y, inverted).Value;
                        getTile(x, y, inverted).clear();
                        if(!inverted) moveTileTo(x, y, nextPos, y); else moveTileTo(y, x, y, nextPos);
                        moved = true;
                    }

                    if(nextPos != 0) if(getTile(nextPos, y, inverted).Value == getTile(nextPos - 1, y, inverted).Value) {
                        int value = getTile(nextPos - 1, y, inverted).Value += getTile(nextPos, y, inverted).Value;
                        getTile(nextPos, y, inverted).clear();
                        mScore += getTile(nextPos - 1, y, inverted).Value;
                        if(inverted) animateTileMerging(y, nextPos - 1, getTile(nextPos - 1, y, inverted).Value, y, nextPos); else animateTileMerging(nextPos - 1, y, getTile(nextPos - 1, y, inverted).Value, nextPos, y);
                        moved = true;
                    }
                }
            }

        if(moved) addTile();

        if(!isGameOver()) mLabel.setText("SCORE: " + mScore);
        else mLabel.setText("PRESS R TO RESTART");
    }

    public boolean isGameOver() {
        if(!getEmptyTiles().isEmpty()) return false;
        for(int x = 0; x < SIZE; x++) {
            for(int y = 0; y < SIZE; y++) {
                if(x > 0) if(getTile(x, y).Value == getTile(x - 1, y).Value)
                    return false;
                if(x < SIZE - 1) if(getTile(x, y).Value == getTile(x + 1, y).Value)
                    return false;
                if(y > 0) if(getTile(x, y).Value == getTile(x, y - 1).Value)
                    return false;
                if(y < SIZE - 1) if(getTile(x, y).Value == getTile(x, y + 1).Value)
                    return false;
            }
        }
        return true;
    }

    public void moveTileTo(int ix, int iy, int fx, int fy) {
        UITile a = new UITile(getTile(fx, fy).Value, false);
        mTiles[fy * SIZE + fx].setActor(a);
        mTiles[iy * SIZE + ix].clearActor();
        a.moveTile(ix, iy, fx, fy);
    }

    public void animateTileCreation(int x, int y, int value) {
        mTiles[y * SIZE + x].setActor(new UITile(value, true));
    }

    public void animateTileMerging(int x, int y, int value, int oldx, int oldy) {
        UITile t = (UITile) mTiles[y * SIZE + x].getActor();
        t.init(value, true);
        mTiles[oldy * SIZE + oldx].clearActor();
    }

    @Override
    public String toString() {
        String res = String.format("SCORE: %d, GAMEOVER? %b\n", mScore, isGameOver());
        for(int y = 0; y < SIZE; y++) {
            for(int x = 0; x < SIZE; x++) {
                res += getTile(x, y).Value + " ";
            }
            res += "\n";
        }
        return res;
    }

    public void setTiles(Cell[] cells) {
        mTiles = cells;
    }

    public void setLabel(Label l) {
        mLabel = l;
    }
}
