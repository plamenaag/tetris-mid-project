package com.tetris.shape;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public abstract class Shape {
    private Queue<String> rotations;
    private List<Block> blocks;
    private int centerBlockY;
    private int centerBlockX;

    public Shape(int centerBlockX, int centerBlockY, List<Block> blocks) {
        rotations = new LinkedList<>();
        rotations.add("U");
        rotations.add("R");
        rotations.add("D");
        rotations.add("L");
        this.blocks = blocks;
        this.centerBlockX = centerBlockX;
        this.centerBlockY = centerBlockY;
        Random r = new Random();
        int randomRotations = r.nextInt(4);
        for (int i = 0; i < randomRotations; i++) {
            this.rotate();
        }
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    public int getCenterBlockY() {
        return centerBlockY;
    }

    public void setCenterBlockY(int centerBlockY) {
        this.centerBlockY = centerBlockY;
    }

    public int getCenterBlockX() {
        return centerBlockX;
    }

    public void setCenterBlockX(int centerBlockX) {
        this.centerBlockX = centerBlockX;
    }

    public void moveDown() {
        for (Block block : blocks) {
            block.setYCoordinate(block.getYCoordinate() + 1);
        }

        centerBlockY++;
    }

    public void moveLeft() {
        for (Block block : blocks) {
            block.setXCoordinate(block.getXCoordinate() - 1);
        }

        centerBlockX--;
    }

    public void moveRight() {
        for (Block block : blocks) {
            block.setXCoordinate(block.getXCoordinate() + 1);
        }

        centerBlockX++;
    }

    public String nextRotation() {
        String rotation = rotations.poll();
        rotations.add(rotation);
        return rotation;
    }

    public abstract void rotate();

    public void undoRotation() {
        this.rotate();
        this.rotate();
        this.rotate();
    }
}
