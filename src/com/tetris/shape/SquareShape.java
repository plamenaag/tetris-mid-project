package com.tetris.shape;

import java.util.List;

@SuppressWarnings("ALL")
public class SquareShape extends Shape {

    public SquareShape(int centerX, int centerY, List<Block> blocks) {
        super(centerX, centerY, blocks);
    }

    @Override
    public void rotate() {
        mainPosition();
    }

    private void mainPosition() {
        this.getBlocks().get(0).setXCoordinate(getCenterBlockX());
        this.getBlocks().get(0).setYCoordinate(getCenterBlockY());

        this.getBlocks().get(1).setXCoordinate(getCenterBlockX() + 1);
        this.getBlocks().get(1).setYCoordinate(getCenterBlockY());

        this.getBlocks().get(2).setXCoordinate(getCenterBlockX());
        this.getBlocks().get(2).setYCoordinate(getCenterBlockY() - 1);

        this.getBlocks().get(3).setXCoordinate(getCenterBlockX() + 1);
        this.getBlocks().get(3).setYCoordinate(getCenterBlockY() - 1);
    }
}
