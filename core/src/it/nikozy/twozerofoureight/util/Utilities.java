package it.nikozy.twozerofoureight.util;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class Utilities {
    public static AssetManager ASSETS;
    public static Texture[] BACKGROUND;

    public static Texture getBackground(int value) {
        return BACKGROUND[(int) MathUtils.log2(value) - 1];
    }

    public static void generateBackground() {
        BACKGROUND = new Texture[(int) MathUtils.log2(2048)];
        for(int i = 0; i < (int) MathUtils.log2(2048); i++) {
            Pixmap p = new Pixmap(128, 128, Pixmap.Format.RGBA8888);
            switch (i) {
                case 0:
                    p.setColor(Color.valueOf("#eee4da"));
                    break;
                case 1:
                    p.setColor(Color.valueOf("#ede0c8"));
                    break;
                case 2:
                    p.setColor(Color.valueOf("#f2b179"));
                    break;
                case 3:
                    p.setColor(Color.valueOf("#f59563"));
                    break;
                case 4:
                    p.setColor(Color.valueOf("#f67c5f"));
                    break;
                case 5:
                    p.setColor(Color.valueOf("#f65e3b"));
                    break;
                case 6:
                    p.setColor(Color.valueOf("#edcf72"));
                    break;
                case 7:
                    p.setColor(Color.valueOf("#edcc61"));
                    break;
                case 8:
                    p.setColor(Color.valueOf("#edc850"));
                    break;
                case 9:
                    p.setColor(Color.valueOf("#edc53f"));
                    break;
                case 10:
                    p.setColor(Color.valueOf("#edc22e"));
                    break;
            }
            p.fill();
            BACKGROUND[i] = new Texture(p);
        }
    }
}
