package com.app.adventureone;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class Entity {

    TextureAtlas atlas;
    public Sprite sprite;
    public Vector2 position;
    public int size;
    public Rectangle rect;
    public int hp;
    public int strength;

    public Entity(TextureAtlas atlas, String sprite, Vector2 position, int size, Rectangle rect, int hp, int strength) {

        this.atlas = atlas;
        this.sprite = new Sprite(atlas.findRegion(sprite));
        this.sprite.setSize(size, size);
        this.position = position;
        this.size = size;
        this.rect = rect;
        this.hp = hp;
        this.strength = strength;
    }

    public void draw(SpriteBatch batch) {
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
        rect.setPosition(position.x, position.y);
    }
}
