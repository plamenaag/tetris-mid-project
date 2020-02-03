package com.tetris.manager;

import com.tetris.shape.Block;

import java.util.ArrayList;
import java.util.List;

public class PlayField {
    private final int hBlockCount;
    private final int vBlockCount;
    private final List<Block> blocks;

    public PlayField(int hBlockCount, int vBlockCount) {
        this.hBlockCount = hBlockCount;
        this.vBlockCount = vBlockCount;
        this.blocks = new ArrayList<>();
    }

    public int getHBlockCount() {
        return hBlockCount;
    }

    public int getVBlockCount() {
        return vBlockCount;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public Block getBlock(int x, int y) {
        Block block = null;
        for (Block bl : blocks) {
            if (bl.getYCoordinate() == y && bl.getXCoordinate() == x) {
                block = bl;
            }
        }

        return block;
    }

    public int getPositionBlockCount(int x, int y) {
        int count = 0;
        for (Block bl : blocks) {
            if (bl.getYCoordinate() == y && bl.getXCoordinate() == x) {
                count++;
            }
        }
        return count;
    }

    public List<Block> createBlocks() {
        // we create this empty list so we can pass it to Shape
        List<Block> blockList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Block block = new Block();
            blocks.add(block);
            blockList.add(block);
        }

        return blockList;
    }

    public int getStartPointX() {
        int result = this.hBlockCount / 2;
        return result;
    }

    public int getStartPointY() {
        return 0;
    }

    public int compressPlayField() {
        int points = 0;
        for (int y = 0; y < this.vBlockCount; ) {
            boolean isRowFull = true;
            for (int x = 0; x < this.hBlockCount; x++) {
                if (this.getBlock(x, y) == null) {
                    isRowFull = false;
                    break;
                }
            }

            if (isRowFull) {
                points += this.hBlockCount;
                for (int x = 0; x < this.hBlockCount; x++) {
                    this.getBlocks().remove(this.getBlock(x, y));
                }

                for (int compressY = y - 1; compressY >= 0; compressY--) {
                    for (int compressX = 0; compressX < this.hBlockCount; compressX++) {
                        Block block = this.getBlock(compressX, compressY);
                        if (block != null) {
                            block.setYCoordinate(block.getYCoordinate() + 1);
                        }
                    }
                }
            } else {
                y++;
            }
        }

        return points;
    }
}
