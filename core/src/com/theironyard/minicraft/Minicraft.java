package com.theironyard.minicraft;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Minicraft extends ApplicationAdapter {
    final int WIDTH = 50;
    final int HEIGHT = 50;
    final int MAX_VELOCITY = 150;

    SpriteBatch batch;
    TextureRegion down, up, right, left;
    FitViewport viewport;
    TextureRegion img;

    float x = 0;
    float y = 0;
    float xv = 0;
    float yv = 0;

    @Override
    public void create () {
        batch = new SpriteBatch();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Texture tiles = new Texture("tiles.png");
        TextureRegion[][] grid = TextureRegion.split(tiles, 16, 16);
        down = grid[6][0];
        up = grid[6][1];
        right = grid[6][3];
        left = new TextureRegion(right);
        left.flip(true, false);
        img = down;
    }

    @Override
    public void render () {
        move();
        draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    void move() {


        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            yv = MAX_VELOCITY;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            yv = MAX_VELOCITY * -1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            xv = MAX_VELOCITY;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            xv = MAX_VELOCITY * -1;
        }

        x += xv * Gdx.graphics.getDeltaTime();
        y += yv * Gdx.graphics.getDeltaTime();

        if (y < 0 - HEIGHT) {
            y = Gdx.graphics.getHeight() - HEIGHT;
        }
        else if (y > (Gdx.graphics.getHeight())) {
            y = 0;
        }
        if (x < 0 - WIDTH) {
            x = Gdx.graphics.getWidth() - WIDTH;
        }
        else if (x > (Gdx.graphics.getWidth())) {
            x = 0;
        }

        xv *= 0;
        yv *= 0;
    }

    void draw() {

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            img = up;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            img = down;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            img = right;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            img = left;
        }

        Gdx.gl.glClearColor(0.5f, 0.5f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, x, y, WIDTH, HEIGHT);
        batch.end();
    }
}
