package it.nikozy.twozerofoureight.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import it.nikozy.twozerofoureight.util.GameConfiguration;

import static it.nikozy.twozerofoureight.util.Globals.ASSETS;
import static it.nikozy.twozerofoureight.util.Globals.getBackground;

public class Tile extends Image {
    private int mValue;
    private BitmapFont mFont;

    public Tile(int value) {
        super(getBackground(value));
        mValue = value;
        mFont = ASSETS.get(GameConfiguration.FONT_FILE, BitmapFont.class);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        mFont.setColor(mValue > 4 ? Color.WHITE : Color.BLACK);
        GlyphLayout layout = new GlyphLayout(mFont, String.valueOf(mValue));

        float fontX = getX() + (getWidth() - layout.width) / 2;
        float fontY = getY() + (getHeight() + layout.height) / 2;
        mFont.draw(batch, layout, fontX, fontY);
    }
}
