package com.tetris.shape;

import java.util.List;

@SuppressWarnings("ALL")
public class ZShape extends Shape {

    public ZShape(int centerX, int centerY, List<Block> blocks) {
        super(centerX, centerY, blocks);
    }

    @Override
    public void rotate() {
        String rotation = super.nextRotation();
        switch (rotation) {
            case "U":
                rotateV();
                break;
            case "R":
                rotateH();
                break;
            case "D":
                rotateV();
                break;
            case "L":
                rotateH();
                break;
        }
    }

    public void rotateH() {
        this.getBlocks().get(0).setXCoordinate(getCenterBlockX() - 1);
        this.getBlocks().get(0).setYCoordinate(getCenterBlockY());

        this.getBlocks().get(1).setXCoordinate(getCenterBlockX());
        this.getBlocks().get(1).setYCoordinate(getCenterBlockY());

        this.getBlocks().get(2).setXCoordinate(getCenterBlockX());
        this.getBlocks().get(2).setYCoordinate(getCenterBlockY() - 1);

        this.getBlocks().get(3).setXCoordinate(getCenterBlockX() + 1);
        this.getBlocks().get(3).setYCoordinate(getCenterBlockY() - 1);
    }

    public void rotateV() {
        this.getBlocks().get(0).setXCoordinate(getCenterBlockX());
        this.getBlocks().get(0).setYCoordinate(getCenterBlockY() + 1);

        this.getBlocks().get(1).setXCoordinate(getCenterBlockX());
        this.getBlocks().get(1).setYCoordinate(getCenterBlockY());

        this.getBlocks().get(2).setXCoordinate(getCenterBlockX() - 1);
        this.getBlocks().get(2).setYCoordinate(getCenterBlockY());

        this.getBlocks().get(3).setXCoordinate(getCenterBlockX() - 1);
        this.getBlocks().get(3).setYCoordinate(getCenterBlockY() - 1);
    }
}
