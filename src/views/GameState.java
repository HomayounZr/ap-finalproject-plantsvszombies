/*** In The Name of Allah ***/
package views;

import helpers.BufferedImages;
import helpers.ImageIcons;
import helpers.threads.*;
import models.*;
import models.enums.PlantType;

import java.awt.event.*;
import java.util.ArrayList;

/**
 * This class holds the state of game and all of its elements.
 * This class also handles user inputs, which affect the game state.
 */
public class GameState {
	
	private KeyHandler keyHandler;
	private MouseHandler mouseHandler;

	private boolean isFinished;
	private ArrayList<Card> cards;
	private ArrayList<LawnMower> lawnMowers;
	private Card selectedCard;
	private Plant[][] plants;
	private ArrayList<Zombie> zombies;
	private ArrayList<Sun> suns;
	private ArrayList<Bullet> bullets;
	private int playerSuns;

	private ZombieGenerator zombieGenerator;
	private SkySunGenerator skySunGenerator;

	public GameState() {
		//
		// Initialize the game state and all elements ...
		//
		isFinished = false;

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

//		plants = new ArrayList<>();
		plants = new Plant[9][5];
		Plant newPlant = new PeaShooterPlant(new Coordinate(3,2));
		newPlant.setLocation(82 + 3*94,110 + 2*120);
		plants[3][2] = newPlant;

		playerSuns = 200;
		suns = new ArrayList<>(0);

		zombies = new ArrayList<>();

		bullets = new ArrayList<>();
		Bullet bullet = new Bullet(
				BufferedImages.bullet_normal,
				25,
				newPlant.getCoordinate(),
				ImageIcons.bullet_normal
		);
		bullet.setLocation(90 + 3*94,115 + 2*120);
		bullets.add(bullet);
		ThreadPool.execute(new BulletGuiThread(bullets));
		ThreadPool.execute(new BulletLogicalThread(bullets,zombies));

		// starting game with stage thread
		ThreadPool.execute(new GameStageThread(this));

		// initializing handlers
		keyHandler = new KeyHandler();
		mouseHandler = new MouseHandler();
	}

	public boolean getIsFinished(){
		return isFinished;
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
			createNewPlant(e);
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

	public void createNewPlant(MouseEvent e){
//		if(selectedCard == null)
//			return;

		// find grids
		int gridX = 0;
		int gridY = 0;
//		int x = e.getX() - 82;
//		int y = e.getY() - 110;
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
		int locationX = 115 + gridY * 120;

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
		int locationY = 82 + gridX * 94;

		System.out.println("" + x + " - " + y + " / " + gridX + " - " + gridY);
			Plant newPlant = null;
			switch (selectedCard.getPlantType()){
				case SUNFLOWER:
					newPlant = new SunFlowerPlant(new Coordinate(gridX,gridY));
					break;
				case PEASHOOTER:
					newPlant = new PeaShooterPlant(new Coordinate(gridX,gridY));
					break;
				case SNOWPEASHOOTER:
					newPlant = new SnowPeaShooterPlant(new Coordinate(gridX,gridY));
					break;
				case GIANTWALLNUT:
					newPlant = new WallNutPlant(new Coordinate(gridX,gridY));
					break;
				case CHERRYBOMB:
					newPlant = new CherryBombPlant(new Coordinate(gridX,gridY));
					break;
				default:
					break;
			}
			newPlant.setLocation(locationX,locationY);
			newPlant.addGameStateValues(suns,bullets);
			playerSuns -= selectedCard.getSunsNeed();
			selectedCard.useCard();
			selectedCard = null;

			plants[gridX][gridY] = newPlant;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public ArrayList<LawnMower> getLawnMowers() {
		return lawnMowers;
	}

	public ArrayList<Zombie> getZombies() {
		return zombies;
	}

	public void changeSelectedCard(Card card){
		if(card.getIsEnable())
			if(playerSuns >= card.getSunsNeed())
				this.selectedCard = card;

		System.out.println(card.getName() + " is " + (card.getIsEnable() ? "available" : "reloading"));
	}

	public int getPlayerSuns() {
		return playerSuns;
	}

	public ArrayList<Sun> getSuns() {
		return suns;
	}

	public ArrayList<Bullet> getBullets() {
		return bullets;
	}

	public Plant checkPlantExist(int x, int y){
		return plants[x][y];
	}

	public synchronized void collectSun(Sun sun){
		if(suns.size() > 0){
			suns.remove(sun);
			playerSuns += 25;
		}
		System.out.println("" + suns.size());
	}

	// start collecting stage for 50 seconds
	public void startCollectingStage(){
		System.out.println("====> Starting collecting stage for 50 sec");

//		skySunGenerator = new SkySunGenerator(suns);
//		ThreadPool.execute(skySunGenerator);
	}

	// start stage 1 for 2.5 min, zombie per 30sec
	public void startStage1(){
		System.out.println("====> Starting stage 1 for 2.5 min");

		zombieGenerator = new ZombieGenerator(zombies,30,1);
		ThreadPool.execute(zombieGenerator);
	}

	// start stage 2 for 3 min, 2 zombies per 30sec
	public void startStage2(){
		System.out.println("====> Starting stage 2 for 3 min");

	}

	// start final wave for 2.5 min, 2 zombies per 25sec
	public void startFinalWave(){
		System.out.println("====> Starting final wave for 2.5 min");

	}

}