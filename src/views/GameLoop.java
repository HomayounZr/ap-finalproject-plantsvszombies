/*** In The Name of Allah ***/
package views;

import helpers.threads.ThreadPool;

import javax.swing.*;

/**
 * A very simple structure for the main game loop.
 * THIS IS NOT PERFECT, but works for most situations.
 * Note that to make this work, none of the 2 methods
 * in the while loop (update() and render()) should be
 * long running! Both must execute very quickly, without
 * any waiting and blocking!
 *
 * Detailed discussion on different game-loop design-patterns
 * is available in the following link:
 *    http://gameprogrammingpatterns.com/game-loop.html
 */
public class GameLoop implements Runnable {

    /**
     * Frame Per Second.
     * Higher is better, but any value above 24 is fine.
     */
    public static final int FPS = 30;

    private GameFrame canvas;
    private GameState state;

    public GameLoop(GameFrame gc) {
        canvas = gc;
    }

    public void init() {
        //
        // Perform all initializations ...
        //
        state = new GameState();
        canvas.addKeyListener(state.getKeyListener());
//		canvas.addMouseListener(state.getMouse());
//		canvas.addMouseMotionListener(state.getMouseMotionListener());
//		canvas.requestFocusInWindow();
    }

    @Override
    public void run() {
        boolean gameOver = false;
        boolean finished = false;
        while (!gameOver && !finished) {
            try {
                long start = System.currentTimeMillis();
                //
                state.update();
                canvas.render(state);
                if(state.getLawnMowers().size() <= 0){
                    gameOver = true;
                }
                finished = state.getIsFinished();
                //
                long delay = (1000 / FPS) - (System.currentTimeMillis() - start);
                if (delay > 0)
                    Thread.sleep(delay);
            } catch (InterruptedException ex) {
            }
        }

        ThreadPool.shutdownNow();

        if(gameOver){
            JOptionPane.showMessageDialog(
                    null,
                    "You Lose",
                    "Mission Failed",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
        if(finished){
            JOptionPane.showMessageDialog(
                    null,
                    "You Won",
                    "Mission Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }

    }

}
