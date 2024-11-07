package com.app.adventureone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class ButtonHandler {
    public Stage stage;
    public Skin debugSkin;
    public Skin inventorySkin;
    public Skin inventoryLabelSkin;
    public boolean isDebug = false;
    public boolean isInventory = false;
    public BitmapFont font;
    private Player player;

    public ButtonHandler(Player player) {

        debugSkin = new Skin(Gdx.files.internal("ui/debugbuttonStyle.json"));
        inventorySkin = new Skin(Gdx.files.internal("ui/invbuttonStyle.json"));
        font = new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"));
        font.getData().scale(3f);

        inventoryLabelSkin = new Skin(Gdx.files.internal("ui/invlabelStyle.json"));

        this.player = player;

        stage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.top().left();
        table.setFillParent(true);
        stage.addActor(table);

        Button debugButton = new Button(debugSkin);
        Button inventoryButton = new Button(inventorySkin);
        Label inventoryLabel = new Label("", inventoryLabelSkin);
        inventoryLabel.setVisible(false);

        table.add(debugButton).width(100).height(50);
        table.add(inventoryButton).width(100).height(50);
        table.add(inventoryLabel).width(100).height(75);

        debugButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                isDebug = !isDebug;
            }
        });

        inventoryButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                isInventory = !isInventory;
                inventoryLabel.setVisible(isInventory);

                if (isInventory) {
                    if (player.inventory.contains("Sword")) {
                        inventoryLabel.setText("Sword");
                    }
                    if (player.inventory.contains("Potion")) {
                        inventoryLabel.setText("Potion");
                    }
                    if (player.inventory.contains("Sword") && player.inventory.contains("Potion")) {
                        inventoryLabel.setText("Sword, \nPotion");
                    }
                } else {
                    inventoryLabel.setText("");
                }
            }
        });
    }

    public void render() {
        stage.getViewport().apply();
        stage.act();
        stage.draw();
    }
}
