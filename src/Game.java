//322622010 Itay Chananel

/**
 * The type Pow.
 *
 * @author Itay Chananel < itay.chananel@live.biu.ac.il >
 * @version 1.0
 * @since 2023 -05-05
 */

import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * The type Game.
 */
public class Game {

    /**
     * The First row.
     */
    static final int FIRST_ROW = 0;
    /**
     * The Second row.
     */
    static final int SECOND_ROW = 1;
    /**
     * The Third row.
     */
    static final int THIRD_ROW = 2;
    /**
     * The Fourth row.
     */
    static final int FOURTH_ROW = 3;
    /**
     * The Fifth row.
     */
    static final int FIFTH_ROW = 4;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;

    /**
     * The Initial x left bound.
     */
    static final int INITIAL_X_LEFT_BOUND = 0;
    /**
     * The Initial y left bound.
     */
    static final int INITIAL_Y_LEFT_BOUND = 0;
    /**
     * The Width left bound.
     */
    static final int WIDTH_LEFT_BOUND = 20;
    /**
     * The Height left bound.
     */
    static final int HEIGHT_LEFT_BOUND = 580;

    /**
     * The Initial x right bound.
     */
    static final int INITIAL_X_RIGHT_BOUND = 780;
    /**
     * The Initial y right bound.
     */
    static final int INITIAL_Y_RIGHT_BOUND = 20;
    /**
     * The Width right bound.
     */
    static final int WIDTH_RIGHT_BOUND = 20;
    /**
     * The Height right bound.
     */
    static final int HEIGHT_RIGHT_BOUND = 580;

    /**
     * The Initial x top bound.
     */
    static final int INITIAL_X_TOP_BOUND = 20;
    /**
     * The Initial y top bound.
     */
    static final int INITIAL_Y_TOP_BOUND = 0;
    /**
     * The Width top bound.
     */
    static final int WIDTH_TOP_BOUND = 780;
    /**
     * The Height top bound.
     */
    static final int HEIGHT_TOP_BOUND = 20;

    /**
     * The Initial x bottom bound.
     */
    static final int INITIAL_X_BOTTOM_BOUND = 0;
    /**
     * The Initial y bottom bound.
     */
    static final int INITIAL_Y_BOTTOM_BOUND = 580;
    /**
     * The Width bottom bound.
     */
    static final int WIDTH_BOTTOM_BOUND = 780;
    /**
     * The Height bottom bound.
     */
    static final int HEIGHT_BOTTOM_BOUND = 20;

    /**
     * The Initial x background.
     */
    static final int INITIAL_X_BACKGROUND = 0;
    /**
     * The Initial y background.
     */
    static final int INITIAL_Y_BACKGROUND = 0;
    /**
     * The Width background.
     */
    static final int WIDTH_BACKGROUND = 800;
    /**
     * The Height background.
     */
    static final int HEIGHT_BACKGROUND = 600;

    /**
     * The Number r for dark blue.
     */
    static final int NUMBER_R_FOR_DARK_BLUE = 0;
    /**
     * The Number g for dark blue.
     */
    static final int NUMBER_G_FOR_DARK_BLUE = 0;
    /**
     * The Number b for dark blue.
     */
    static final int NUMBER_B_FOR_DARK_BLUE = 139;

    /**
     * The Initial x first row.
     */
    static final int INITIAL_X_FIRST_ROW = 240;
    /**
     * The Initial y first row.
     */
    static final int INITIAL_Y_FIRST_ROW = 180;
    /**
     * The Rows of blocks.
     */
    static final int ROWS_OF_BLOCKS = 6;

    /**
     * The Number of blocks in first row.
     */
    static final int NUMBER_OF_BLOCKS_IN_FIRST_ROW = 12;

    /**
     * The Width of block.
     */
    static final int WIDTH_OF_BLOCK = 45;
    /**
     * The Height of block.
     */
    static final int HEIGHT_OF_BLOCK = 20;

    /**
     * The Width of game.
     */
    static final int WIDTH_OF_GAME = 800;
    /**
     * The Height of game.
     */
    static final int HEIGHT_OF_GAME = 600;

    /**
     * The Initial x ball 1.
     */
    static final int INITIAL_X_BALL1 = 50;
    /**
     * The Initial y ball 1.
     */
    static final int INITIAL_Y_BALL1 = 100;

    /**
     * The Initial dx ball 1.
     */
    static final int INITIAL_DX_BALL1 = 5;
    /**
     * The Initial dy ball 1.
     */
    static final int INITIAL_DY_BALL1 = 5;

    /**
     * The Initial x ball 2.
     */
    static final int INITIAL_X_BALL2 = 600;
    /**
     * The Initial y ball 2.
     */
    static final int INITIAL_Y_BALL2 = 500;

    /**
     * The Initial dx ball 2.
     */
    static final int INITIAL_DX_BALL2 = -5;
    /**
     * The Initial dy ball 2.
     */
    static final int INITIAL_DY_BALL2 = -5;

    /**
     * The Radius.
     */
    static final int RADIUS = 10;

    /**
     * The Sixty.
     */
    static final int SIXTY = 60;

    /**
     * The Thousand.
     */
    static final int THOUSAND = 1000;


    /**
     * Instantiates a new Game.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
    }

    /**
     * Add collidable.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * color of the row.
     *
     * @param i the
     * @return the color
     */
    public Color rowColor(int i) {
        if (i == FIRST_ROW) {
            return Color.GRAY;
        } else if (i == SECOND_ROW) {
            return Color.RED;
        } else if (i == THIRD_ROW) {
            return Color.YELLOW;
        } else if (i == FOURTH_ROW) {
            return Color.BLUE;
        } else if (i == FIFTH_ROW) {
            return Color.PINK;
        } else {
            return Color.GREEN;
        }
    }

    /**
     * Initialize.
     */
// Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize() {
        //left
        Block left = new Block(new Rectangle(new Point(INITIAL_X_LEFT_BOUND, INITIAL_Y_LEFT_BOUND),
                WIDTH_LEFT_BOUND, HEIGHT_LEFT_BOUND, Color.GRAY));
        //top
        Block top = new Block(new Rectangle(new Point(INITIAL_X_TOP_BOUND, INITIAL_Y_TOP_BOUND),
                WIDTH_TOP_BOUND, HEIGHT_TOP_BOUND, Color.GRAY));
        //right
        Block right = new Block(new Rectangle(new Point(INITIAL_X_RIGHT_BOUND, INITIAL_Y_RIGHT_BOUND),
                WIDTH_RIGHT_BOUND, HEIGHT_RIGHT_BOUND, Color.GRAY));
        //bottom
        Block bottom = new Block(new Rectangle(new Point(INITIAL_X_BOTTOM_BOUND, INITIAL_Y_BOTTOM_BOUND),
                WIDTH_BOTTOM_BOUND, HEIGHT_BOTTOM_BOUND, Color.GRAY));
        //background
        Block background = new Block(new Rectangle(new Point(INITIAL_X_BACKGROUND, INITIAL_Y_BACKGROUND),
                WIDTH_BACKGROUND, HEIGHT_BACKGROUND,
                new Color(NUMBER_R_FOR_DARK_BLUE, NUMBER_G_FOR_DARK_BLUE, NUMBER_B_FOR_DARK_BLUE)));
        //add backgrounds and bounds to the game
        background.addToGame(this);
        left.addToGame(this);
        top.addToGame(this);
        right.addToGame(this);
        bottom.addToGame(this);
        //x and y values of first brick in first line
        int x = INITIAL_X_FIRST_ROW;
        int y = INITIAL_Y_FIRST_ROW;
        //loop for the rows of the blocks
        for (int i = 0; i < ROWS_OF_BLOCKS; i++) {
            java.awt.Color color = rowColor(i);
            //loop for the blocks in each row
            for (int j = 0; j < NUMBER_OF_BLOCKS_IN_FIRST_ROW - i; j++) {
                Rectangle rect = new Rectangle(new Point(x, y), WIDTH_OF_BLOCK, HEIGHT_OF_BLOCK, color);
                Block block = new Block(rect);
                block.addToGame(this);
                x = x + WIDTH_OF_BLOCK;
            }
            y += HEIGHT_OF_BLOCK;
            x = INITIAL_X_FIRST_ROW + (i + 1) * WIDTH_OF_BLOCK;
        }
        this.gui = new GUI("Game", WIDTH_OF_GAME, HEIGHT_OF_GAME);
        //initialize the paddle
        Paddle paddle = new Paddle(gui.getKeyboardSensor());
        paddle.addToGame(this);
        //initialize two balls
        Ball ball1 = new Ball(new Point(INITIAL_X_BALL1, INITIAL_Y_BALL1), RADIUS, java.awt.Color.BLACK, environment);
        Velocity v1 = new Velocity(INITIAL_DX_BALL1, INITIAL_DY_BALL1);
        ball1.setVelocity(v1);
        Ball ball2 = new Ball(new Point(INITIAL_X_BALL2, INITIAL_Y_BALL2), RADIUS, java.awt.Color.WHITE, environment);
        Velocity v2 = new Velocity(INITIAL_DX_BALL2, INITIAL_DY_BALL2);
        ball2.setVelocity(v2);
        ball1.addToGame(this);
        ball2.addToGame(this);
    }

    /**
     * Run.
     */
// Run the game -- start the animation loop.
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = SIXTY;
        int millisecondsPerFrame = THOUSAND / framesPerSecond;
        while (true) {

            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
