package com.app.adventureone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Player extends Entity {

    private final int tileSize = 32;
    private final int maxRowSize = 15;
    private final int maxColSize = 20;
    private final int screenWidth = tileSize * maxColSize;//480
    private final int screenHeight = tileSize * maxRowSize;//640
    private String direction = "down";
    private Goblin goblin;
    private Sword sword;
    private Hole hole;
    public ArrayList<String> inventory;
    private Animation<TextureAtlas.AtlasRegion> walkLeftAnimation;
    private Animation<TextureAtlas.AtlasRegion> walkRightAnimation;
    private Animation<TextureAtlas.AtlasRegion> walkBackwardsAnimation;
    private Animation<TextureAtlas.AtlasRegion> walkForwardsAnimation;
    private Animation<TextureAtlas.AtlasRegion> fightingAnimation;


    public Player(TextureAtlas atlas, Vector2 position, int size, Rectangle rect, Goblin goblin, int hp, int strength, Sword sword, Hole hole) {
        super(atlas, "hero", position, size, rect, hp, strength);
        this.goblin = goblin;
        this.sword = sword;
        this.hole = hole;

        inventory = new ArrayList<>();
        initAnimations();
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }

    public void initAnimations() {
        walkLeftAnimation = new Animation<>(0.2f, atlas.findRegions("hero_walking_left"));
        walkLeftAnimation.setPlayMode(Animation.PlayMode.LOOP);
        walkLeftAnimation.getKeyFrame(0);
        walkRightAnimation = new Animation<>(0.2f, atlas.findRegions("hero_walking_right"));
        walkRightAnimation.setPlayMode(Animation.PlayMode.LOOP);
        walkRightAnimation.getKeyFrame(0);
        walkBackwardsAnimation = new Animation<>(0.2f, atlas.findRegions("hero_backwards_walking"));
        walkBackwardsAnimation.setPlayMode(Animation.PlayMode.LOOP);
        walkBackwardsAnimation.getKeyFrame(0);
        walkForwardsAnimation = new Animation<>(0.2f, atlas.findRegions("hero_walking_"));
        walkForwardsAnimation.setPlayMode(Animation.PlayMode.LOOP);
        walkForwardsAnimation.getKeyFrame(0);
        fightingAnimation = new Animation<>(0.2f, atlas.findRegions("hero_fighting"));
        fightingAnimation.setPlayMode(Animation.PlayMode.LOOP);
        fightingAnimation.getKeyFrame(0);
    }


    public void handleKeys(float deltaTime) {

        boolean isMoving = false;


        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            position.y++;
            direction = "up";
            isMoving = true;
            TextureRegion region = walkBackwardsAnimation.getKeyFrame(deltaTime, true);
            sprite.setRegion(region);
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            position.x--;
            direction = "left";
            isMoving = true;
            TextureRegion region = walkLeftAnimation.getKeyFrame(deltaTime, true);
            sprite.setRegion(region);
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            position.y--;
            direction = "down";
            isMoving = true;
            TextureRegion region = walkForwardsAnimation.getKeyFrame(deltaTime, true);
            sprite.setRegion(region);
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            position.x++;
            direction = "right";
            isMoving = true;
            TextureRegion region = walkRightAnimation.getKeyFrame(deltaTime, true);
            sprite.setRegion(region);
        }

        rect.setPosition(position.x, position.y);

        if (!isMoving) {
            switch (direction) {
                case "up":
                    sprite.setRegion(atlas.findRegion("hero_backwards"));
                    break;
                case "left":
                    sprite.setRegion(atlas.findRegion("hero_facing_left"));
                    break;
                case "right":
                    sprite.setRegion(atlas.findRegion("hero_facing_right"));
                    break;
                default:
                    sprite.setRegion(atlas.findRegion("hero"));
            }
        }
    }

    public void handleBounds() {
        if (position.x < 0) {
            position.x = 0;
        } else if (position.x > screenWidth - size) {
            position.x = screenWidth - size;
        }

        if (position.y < 0) {
            position.y = 0;
        } else if (position.y > screenHeight - size) {
            position.y = screenHeight - size;
        }
    }

    public void checkCollision(TextureAtlas atlas, float deltaTime) {
        if (rect.overlaps(goblin.rect)) {
            battle();
            if (inventory.contains("Sword")) {
                TextureRegion region = fightingAnimation.getKeyFrame(deltaTime, true);
                sprite.setRegion(region);
            } else {
                sprite.setRegion(atlas.findRegion("hero_fighting_empty"));
            }
        }

        if (rect.overlaps(sword.rect)) {
            if (!inventory.contains("Sword")) {
                inventory.add("Sword");
                sword.isThere = false;
            }
        }

        if (rect.overlaps(hole.rect)) {
            Gdx.gl.glClearColor(0, 0, 0, 1f);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            sword.sprite.setAlpha(0);
            goblin.sprite.setAlpha(0);
        }
    }

    public void battle() {
        char attackTurn = 'p';

        while (hp > 0 && goblin.hp > 0) {
            if (attackTurn == 'p') {
                goblin.hp -= strength;
                System.out.println("player did: " + strength + " damage. goblin hp remaining: " + goblin.hp);
                if (goblin.hp <= 0) {
                    System.out.println("goblin died");
                    goblin.hp = 0;
                    System.out.println("goblin alive? : " + goblin.isAlive());
                }
                attackTurn = 'g';
            } else {
                hp -= goblin.strength;
                System.out.println("goblin did: " + goblin.strength + " damage. player hp remaining: " + hp);
                if (hp <= 0) {
                    System.out.println("player died");
                    hp = 0;
                }
                attackTurn = 'p';
            }
        }
    }
}
