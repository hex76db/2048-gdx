package it.nikozy.twozerofoureight.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import it.nikozy.twozerofoureight.util.GameConfiguration;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static it.nikozy.twozerofoureight.util.Utilities.ASSETS;
import static it.nikozy.twozerofoureight.util.Utilities.getBackground;

public class UITile extends Image {
    private int mValue;
    private BitmapFont mFont;

    public UITile(int value, boolean effect) {
        init(value, effect);
    }

    public void init(int value, boolean effect) {
        setDrawable(new TextureRegionDrawable(new TextureRegion(getBackground(value))));
        setOrigin(64.f, 64.f);
        mValue = value;
        mFont = ASSETS.get(GameConfiguration.FONT_FILE, BitmapFont.class);
        if(effect) addAction(sequence(scaleTo(1.25f, 1.25f, 0.1f, Interpolation.circle), scaleTo(0.75f, 0.75f, 0.1f, Interpolation.circle), scaleTo(1.0f, 1.0f, 0.1f, Interpolation.circle)));
    }

    public void moveTile(int ix, int iy, int fx, int fy) {
        setPosition(fx, fy);
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
