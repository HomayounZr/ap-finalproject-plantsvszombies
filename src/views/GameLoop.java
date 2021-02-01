/*** In The Name of Allah ***/
package views;

import appStart.Configurations;
import appStart.GameManagement;
import helpers.threads.AudioThreadPool;
import helpers.threads.ThreadPool;
import models.GameSave;

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

	/**
	 * Constructor of Class
	 * @param gc GameFrame
	 */

	public GameLoop(GameFrame gc) {
		canvas = gc;
	}

	/**
	 * Method OF Initializing the Game
	 *@param save GameSave if nothing , it's a new Game
	 */

	public void init(GameSave save) {
		//
		// Perform all initializations ...
		//
		state = new GameState(save);
		canvas.addKeyListener(state.getKeyListener());
//		canvas.addMouseListener(state.getMouse());
//		canvas.addMouseMotionListener(state.getMouseMotionListener());
//		canvas.requestFocusInWindow();
	}

	/**
	 * Game Main Event Course
	 *Checks The GameEnding Situations , Elements Death or Score Based
	 *Updating the Game
	 *Score Handling For Both Versions Of the Game
	 */

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
		AudioThreadPool.shutdown();
		AudioThreadPool.init();

		int score;
		if(GameManagement.isEasy){
			if(finished){
				score = 3;
			} else {
				score = -1;
			}
		} else {
			if(finished){
				score = 10;
			} else {
				score = -3;
			}
		}
		GameResult gameResult = new GameResult(score,finished);
		gameResult.show();

	}

}
