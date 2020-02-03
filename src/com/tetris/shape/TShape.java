package com.tetris.shape;

import java.util.List;

@SuppressWarnings("ALL")
public class TShape extends Shape {

    public TShape(int centerX, int centerY, List<Block> blocks) {
        super(centerX, centerY, blocks);
    }

    @Override
    public void rotate() {
        String rotation = super.nextRotation();
        switch (rotation) {
            case "U":
                rotateUp();
                break;
            case "R":
                rotateRight();
                break;
            case "D":
                rotateDown();
                break;
            case "L":
                rotateLeft();
                break;
        }
    }

    public void rotateLeft() {
        this.getBlocks().get(0).setXCoordinate(getCenterBlockX() - 1);
        this.getBlocks().get(0).setYCoordinate(getCenterBlockY());

        this.getBlocks().get(1).setXCoordinate(getCenterBlockX());
        this.getBlocks().get(1).setYCoordinate(getCenterBlockY() + 1);

        this.getBlocks().get(2).setXCoordinate(getCenterBlockX());
        this.getBlocks().get(2).setYCoordinate(getCenterBlockY());

        this.getBlocks().get(3).setXCoordinate(getCenterBlockX());
        this.getBlocks().get(3).setYCoordinate(getCenterBlockY() - 1);
    }

    public void rotateUp() {
        this.getBlocks().get(0).setXCoordinate(getCenterBlockX() - 1);
        this.getBlocks().get(0).setYCoordinate(getCenterBlockY());

        this.getBlocks().get(1).setXCoordinate(getCenterBlockX());
        this.getBlocks().get(1).setYCoordinate(getCenterBlockY());

        this.getBlocks().get(2).setXCoordinate(getCenterBlockX());
        this.getBlocks().get(2).setYCoordinate(getCenterBlockY() + 1);

        this.getBlocks().get(3).setXCoordinate(getCenterBlockX() + 1);
        this.getBlocks().get(3).setYCoordinate(getCenterBlockY());
    }

    public void rotateRight() {
        this.getBlocks().get(0).setXCoordinate(getCenterBlockX());
        this.getBlocks().get(0).setYCoordinate(getCenterBlockY() + 1);

        this.getBlocks().get(1).setXCoordinate(getCenterBlockX());
        this.getBlocks().get(1).setYCoordinate(getCenterBlockY());

        this.getBlocks().get(2).setXCoordinate(getCenterBlockX());
        this.getBlocks().get(2).setYCoordinate(getCenterBlockY() - 1);

        this.getBlocks().get(3).setXCoordinate(getCenterBlockX() + 1);
        this.getBlocks().get(3).setYCoordinate(getCenterBlockY());
    }

    public void rotateDown() {
        this.getBlocks().get(0).setXCoordinate(getCenterBlockX() - 1);
        this.getBlocks().get(0).setYCoordinate(getCenterBlockY());

        this.getBlocks().get(1).setXCoordinate(getCenterBlockX());
        this.getBlocks().get(1).setYCoordinate(getCenterBlockY());

        this.getBlocks().get(2).setXCoordinate(getCenterBlockX() + 1);
        this.getBlocks().get(2).setYCoordinate(getCenterBlockY());

        this.getBlocks().get(3).setXCoordinate(getCenterBlockX());
        this.getBlocks().get(3).setYCoordinate(getCenterBlockY() - 1);
    }
}
