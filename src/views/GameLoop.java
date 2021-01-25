package views;
/**
 * A very simple structure for the main game loop.
 * THIS IS NOT PERFECT, but works for most situations.
 * Note that to make this work, none of the 2 methods
 * in the while loop (update() and render()) should be
 * long running! Both must execute very quickly, without
 * any waiting and blocking!
 */

public class GameLoop implements Runnable {
    /**
     * Frame Per Second.
     * Higher is better, but any value above 24 is fine.
     */
    public static final int FPS = 60;

    private GameFrame canvas;
    private GameState state;

    public GameLoop(GameFrame frame) {
        canvas = frame;
    }
    /**
     * This must be called before the game loop starts.
     */
    public void init() {
        // Perform all initializations ...
        state = new GameState();
        //Should Define THe Listener Keys in GameState Class
        //canvas.addKeyListener(state.getKeyListener());
        //canvas.addMouseListener(state.getMouseListener());
        //canvas.addMouseMotionListener(state.getMouseMotionListener());
    }

    /**
     * Commented Method Should be Implemented in GameState
     */

    @Override
    public void run() {
/*
        boolean gameOver = false;
        while (!GameState.gameOver&&!GameState.finished) {
            try {
                long start = System.currentTimeMillis();
                state.update();
                canvas.render(state);

                long delay = (1000 / FPS) - (System.currentTimeMillis() - start);
                if (delay > 0) {
                    Thread.sleep(delay);
                }
            } catch (InterruptedException ex) {
            }
  */      }
}
