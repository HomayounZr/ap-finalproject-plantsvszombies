/*** In The Name of Allah ***/
package views;

import models.*;
import models.enums.PlantType;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * This class holds the state of game and all of its elements.
 * This class also handles user inputs, which affect the game state.
 */
public class GameState {
	
	private KeyHandler keyHandler;
	private MouseHandler mouseHandler;
//	private CardMouseListener cardMouseListener;

	private ArrayList<Card> cards;
	private ArrayList<LawnMower> lawnMowers;
	private Card selectedCard;
	private ArrayList<Plant> plants;

	public GameState() {
		//
		// Initialize the game state and all elements ...
		//

		cards = new ArrayList<>();
		cards.add(new Card(PlantType.SUNFLOWER));
		cards.add(new Card(PlantType.PEASHOOTER));
		cards.add(new Card(PlantType.SNOWPEASHOOTER));
		cards.add(new Card(PlantType.GIANTWALLNUT));
		cards.add(new Card(PlantType.CHERRYBOMB));

		lawnMowers = new ArrayList<>();
		for(int i = 0;i < 5;i++){
			lawnMowers.add(new LawnMower(i));
		}

		plants = new ArrayList<>();
		plants.add(new PeaShooterPlant(new Coordinate(2,1)));

		// initializing handlers
		keyHandler = new KeyHandler();
		mouseHandler = new MouseHandler();
	}
	
	/**
	 * The method which updates the game state.
	 */
	public void update() {
		//
		// Update the state of all game elements 
		//  based on user input and elapsed time ...
		//
	}

	public KeyListener getKeyListener() {
		return keyHandler;
	}
	public MouseListener getMouseListener() {
		return mouseHandler;
	}
	public MouseMotionListener getMouseMotionListener() {
		return mouseHandler;
	}

	/**
	 * The keyboard handler.
	 */
	class KeyHandler implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()){
				case KeyEvent.VK_ESCAPE:
					// show pause menu
					break;
				default:
					break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

	}

	/**
	 * The mouse handler.
	 */
	class MouseHandler implements MouseListener, MouseMotionListener {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// find grids
			int gridX = 0;
			int gridY = 0;
//			int x = e.getX() - 77;
//			int y = e.getY() - 102;
			int x = e.getX();
			int y = e.getY();

			// finding y
			if(y < 120){
				gridY = 0;
			} else if(y < 240){
				gridY = 1;
			} else if(y < 360){
				gridY = 2;
			} else if(y < 480){
				gridY = 3;
			} else {
				gridY = 4;
			}

			// finding x
			if(x < 94){
				gridX = 0;
			} else if(x < 188){
				gridX = 1;
			} else if(x < 282){
				gridX = 2;
			} else if(x < 376){
				gridX = 3;
			} else if(x < 470){
				gridX = 4;
			} else if(x < 564){
				gridX = 5;
			} else if(x < 658){
				gridX = 6;
			} else if(x < 752){
				gridX = 7;
			} else {
				gridX = 8;
			}

			JOptionPane.showMessageDialog(
					null,
					"" + gridX + " " + gridY + " selected...",
					"" + x + " " + y,
					JOptionPane.INFORMATION_MESSAGE
			);

			plants.add(new WallNutPlant(new Coordinate(gridX,gridY)));
			cards.get(3).useCard();
			update();

		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseDragged(MouseEvent e) {
		}

		@Override
		public void mouseMoved(MouseEvent e) {
		}
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public ArrayList<LawnMower> getLawnMowers() {
		return lawnMowers;
	}

	public void changeSelectedCard(Card card){
		this.selectedCard = card;
		System.out.println(card.getName() + " is " + (card.getIsEnable() ? "available" : "reloading"));
	}

	public Plant checkPlantExist(int x,int y){
		for(Plant plant: plants){
			if(plant.getCoordinate().equals(new Coordinate(x,y)))
				return plant;
		}
		return null;
	}
}

