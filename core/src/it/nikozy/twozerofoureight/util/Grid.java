package it.nikozy.twozerofoureight.util;

import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;

public class Grid {

    private class Tile {
        public int Value;

        Tile(int value) {
            Value = value;
        }

        public void clear() {
            Value = 0;
        }

        public boolean isEmpty() {
            return Value == 0;
        }
    }

    private final int SIZE = 2;

    private int mScore;
    private Tile[] mGrid;

    public Grid() {
        init();
    }

    public void init() {
        mGrid = new Tile[SIZE * SIZE];
        for(int i = 0; i < SIZE * SIZE; i++) mGrid[i] = new Tile(0);
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
        if(!emptyTiles.isEmpty()) emptyTiles.get(MathUtils.random(0, emptyTiles.size() - 1)).Value = MathUtils.random() > 0.9 ? 4 : 2;
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
                    int nextX = x;
                    while(nextX != SIZE - 1 && getTile(nextX + 1, y, inverted).isEmpty()) nextX++;
                    if(nextX != x) {
                        getTile(nextX, y, inverted).Value = getTile(x, y, inverted).Value;
                        getTile(x, y, inverted).clear();
                        moved = true;
                    }

                    if(nextX != SIZE - 1) if(getTile(nextX + 1, y, inverted).Value == getTile(nextX, y, inverted).Value) {
                        getTile(nextX + 1, y, inverted).Value += getTile(nextX, y, inverted).Value;
                        getTile(nextX, y, inverted).clear();
                        mScore += getTile(nextX + 1, y, inverted).Value;
                        moved = true;
                    }
                }
            }
        else if(direction == 2 || direction == 3)
            for(int x = 1; x < SIZE; x++) {
                for(int y = 0; y < SIZE; y++) {
                    int nextX = x;
                    while(nextX != 0 && getTile(nextX - 1, y, inverted).isEmpty()) nextX--;
                    if(nextX != x) {
                        getTile(nextX, y, inverted).Value = getTile(x, y, inverted).Value;
                        getTile(x, y, inverted).clear();
                        moved = true;
                    }

                    if(nextX != 0) if(getTile(nextX, y, inverted).Value == getTile(nextX - 1, y, inverted).Value) {
                        getTile(nextX - 1, y, inverted).Value += getTile(nextX, y, inverted).Value;
                        getTile(nextX, y, inverted).clear();
                        mScore += getTile(nextX - 1, y, inverted).Value;
                        moved = true;
                    }
                }
            }

        if(moved) addTile();
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
}
