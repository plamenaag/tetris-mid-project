package com.tetris.manager;

import com.tetris.shape.Block;
import com.tetris.shape.LShape;
import com.tetris.shape.Shape;
import com.tetris.shape.SquareShape;
import com.tetris.shape.StickShape;
import com.tetris.shape.TShape;
import com.tetris.shape.ZShape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameEngine extends JFrame implements KeyListener {
    private PlayField playField;
    private Shape currentShape;
    private Long lastDropTime;
    private int score;

    public GameEngine(int xSize, int ySize) {
        super();
        this.score = 0;
        this.playField = new PlayField(xSize, ySize);
        this.initializeScreen();
    }

    private void initializeScreen() {
        int screenWidth = 2 * Constants.BORDER_WIDTH
                + playField.getHBlockCount() * Constants.BLOCK_SIZE
                + playField.getHBlockCount() * 1
                + Constants.FRAME_SIZE * 2
                + 1;

        int screenHeight = 2 * Constants.BORDER_WIDTH
                + playField.getVBlockCount() * Constants.BLOCK_SIZE
                + playField.getVBlockCount() * 1
                + Constants.BAR_HEIGHT
                + 4;

        this.setSize(screenWidth, screenHeight);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addKeyListener(this);
        this.setLocationRelativeTo(null);// center the dialog in the screen
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(Constants.BORDER_WIDTH,
                Constants.BORDER_WIDTH, Constants.BORDER_WIDTH, Constants.BORDER_WIDTH, Color.RED));
    }

    private Shape getRandomShape() {
        Random r = new Random();
        int val = r.nextInt(5);

        switch (val) {
            case 0:
                return new LShape(playField.getStartPointX(), playField.getStartPointY(), playField.createBlocks());
            case 1:
                return new SquareShape(playField.getStartPointX(), playField.getStartPointY(), playField.createBlocks());
            case 2:
                return new StickShape(playField.getStartPointX(), playField.getStartPointY(), playField.createBlocks());
            case 3:
                return new TShape(playField.getStartPointX(), playField.getStartPointY(), playField.createBlocks());
            default:
                return new ZShape(playField.getStartPointX(), playField.getStartPointY(), playField.createBlocks());
        }
    }

    public void run() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(() -> {
            if (currentShape == null) {
                currentShape = getRandomShape();
                currentShape.rotate();
                if (!this.canCurrentShapeMoveDown()) {
                    executor.shutdownNow();
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, "GAME OVER!!! Score: " + score);
                    this.repaint();
                }
            }

            this.repaint();

            boolean shapeCanMoveDown = this.canCurrentShapeMoveDown();
            if (shapeCanMoveDown) {
                if (hasDropTimeElapsed()) {
                    currentShape.moveDown();
                    lastDropTime = System.currentTimeMillis();
                }
            } else {
                currentShape = null;
            }
            score += playField.compressPlayField();

        }, 0, 16, TimeUnit.MILLISECONDS);
    }

    private boolean hasDropTimeElapsed() {
        if (lastDropTime == null || System.currentTimeMillis() - lastDropTime > 200) {
            return true;
        }
        return false;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Block block : playField.getBlocks()) {
            int x = Constants.FRAME_SIZE + Constants.BORDER_WIDTH + 1 + block.getXCoordinate() * (Constants.BLOCK_SIZE + 1);
            int y = Constants.BAR_HEIGHT + Constants.BORDER_WIDTH + 1 + block.getYCoordinate() * (Constants.BLOCK_SIZE + 1);
            g.fillRect(x, y, Constants.BLOCK_SIZE, Constants.BLOCK_SIZE);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (currentShape != null && this.canCurrentShapeMoveRight()) {
                currentShape.moveRight();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (currentShape != null && this.canCurrentShapeMoveLeft()) {
                currentShape.moveLeft();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (currentShape != null && this.canCurrentShapeMoveDown()) {
                currentShape.moveDown();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (currentShape != null) {
                currentShape.rotate();
                if (isRotationInvalid()) {
                    currentShape.undoRotation();
                }
            }
        }
    }

    private boolean isRotationInvalid() {
        for (Block block : currentShape.getBlocks()) {
            if (block.getXCoordinate() < 0 || block.getXCoordinate() >= playField.getHBlockCount()) {
                return true;
            }
            if (playField.getPositionBlockCount(block.getXCoordinate(), block.getYCoordinate()) >= 2) {
                return true;
            }
        }

        return false;
    }

    private boolean canCurrentShapeMoveDown() {
        for (Block block : currentShape.getBlocks()) {
            if (block.getYCoordinate() == playField.getVBlockCount() - 1) {
                return false;
            }

            Block bl = playField.getBlock(block.getXCoordinate(), block.getYCoordinate() + 1);
            if (bl != null && !currentShape.getBlocks().contains(bl)) {
                return false;
            }
        }

        return true;
    }

    private boolean canCurrentShapeMoveLeft() {
        for (Block block : currentShape.getBlocks()) {
            if (block.getXCoordinate() == 0) {
                return false;
            }

            Block bl = playField.getBlock(block.getXCoordinate() - 1, block.getYCoordinate());
            if (bl != null && !currentShape.getBlocks().contains(bl)) {
                return false;
            }
        }

        return true;
    }

    private boolean canCurrentShapeMoveRight() {
        for (Block block : currentShape.getBlocks()) {
            if (block.getXCoordinate() == playField.getHBlockCount() - 1) {
                return false;
            }

            Block bl = playField.getBlock(block.getXCoordinate() + 1, block.getYCoordinate());

            if (bl != null && !currentShape.getBlocks().contains(bl)) {
                return false;
            }
        }

        return true;
    }
}