package com.app.adventureone;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Fire extends Asset {

    private Animation<TextureAtlas.AtlasRegion> fireAnimation;


    public Fire(TextureAtlas atlas, Vector2 position, Rectangle rect, int size) {
        super(atlas, "fire", position, rect, size);

        fireAnimation = new Animation<>(0.3f, atlas.findRegions("fire"));
        fireAnimation.setPlayMode(Animation.PlayMode.LOOP_RANDOM);
        fireAnimation.getKeyFrame(0);
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }

    public void render(float deltaTime) {
        TextureRegion region = fireAnimation.getKeyFrame(deltaTime, true);
        sprite.setRegion(region);
    }
}
