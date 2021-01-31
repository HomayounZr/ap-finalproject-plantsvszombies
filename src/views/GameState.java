/*** In The Name of Allah ***/
package views;

import helpers.BufferedImages;
import helpers.ImageIcons;
import helpers.threads.*;
import models.*;
import models.enums.PlantType;

import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This class holds the state of game and all of its elements.
 * This class also handles user inputs, which affect the game state.
 */
public class GameState {
	
	private KeyHandler keyHandler;
	private MouseHandler mouseHandler;

	private boolean isFinished;
	private ArrayList<Card> cards;
	private CopyOnWriteArrayList<LawnMower> lawnMowers;
	private Card selectedCard;
	private Plant[][] plants;
	private CopyOnWriteArrayList<Zombie> zombies;
	private CopyOnWriteArrayList<Sun> suns;
	private CopyOnWriteArrayList<Bullet> bullets;
	private int playerSuns;

	private ZombieGenerator zombieGenerator;
	private SkySunGenerator skySunGenerator;

	private StageTimer timer;
	private int currentStage;
	private int totalTimePassed;

	public GameState(GameSave save) {
		//
		// Initialize the game state and all elements ...
		//
		isFinished = false;

		if(save == null) {
			cards = new ArrayList<>();
			cards.add(new Card(PlantType.SUNFLOWER));
			cards.add(new Card(PlantType.PEASHOOTER));
			cards.add(new Card(PlantType.SNOWPEASHOOTER));
			cards.add(new Card(PlantType.GIANTWALLNUT));
			cards.add(new Card(PlantType.CHERRYBOMB));

			lawnMowers = new CopyOnWriteArrayList<>();
			for (int i = 0; i < 5; i++) {
				LawnMower lawnMower = new LawnMower(i);
				lawnMower.setLocation(0, (i + 1) * 120);
				lawnMowers.add(lawnMower);
			}

			plants = new Plant[9][5];

			playerSuns = 2500;
			suns = new CopyOnWriteArrayList<>();

			zombies = new CopyOnWriteArrayList<>();

			bullets = new CopyOnWriteArrayList<>();

			ThreadPool.execute(new GameStageThread(this));

		} else {
			loadSave(save);

			ThreadPool.execute(new ResumeGameStageThread(this,save.getLastStage(),save.getTimePassedLastStage()));
		}

		ThreadPool.execute(new BulletGuiThread(bullets));
		ThreadPool.execute(new BulletLogicalThread(bullets,zombies));

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
					showPauseMenu();
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
			System.out.println("panel clicked");
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
		if(selectedCard == null)
			return;

		// find grids
		int gridX = 0;
		int gridY = 0;
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
		int locationY = 115 + gridY * 120;

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
		int locationX = 82 + gridX * 94;

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

	public CopyOnWriteArrayList<LawnMower> getLawnMowers() {
		return lawnMowers;
	}

	public CopyOnWriteArrayList<Zombie> getZombies() {
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

	public CopyOnWriteArrayList<Sun> getSuns() {
		return suns;
	}

	public CopyOnWriteArrayList<Bullet> getBullets() {
		return bullets;
	}

	public Plant checkPlantExist(int x, int y){
		return plants[x][y];
	}

	public void collectSun(Sun sun){
//		if(suns.size() > 0){
			Iterator<Sun> it = suns.iterator();
			while(it.hasNext()){
				Sun _sun = it.next();
				if(_sun.getLocationX() == sun.getLocationX() && _sun.getLocationY() == sun.getLocationY()){
					suns.remove(_sun);
					playerSuns += 25;
					break;
				}
			}
//		}
//		System.out.println("" + suns.size());
	}

    public int getTotalTimePassed() {
        return totalTimePassed;
    }

    public void setTotalTimePassed(int totalTimePassed) {
        this.totalTimePassed = totalTimePassed;
    }

	public int getCurrentStage() {
		return currentStage;
	}

	// start collecting stage for 50 seconds
	public void startCollectingStage(){
		System.out.println("====> Starting collecting stage for 50 sec");

		skySunGenerator = new SkySunGenerator(suns);
		ThreadPool.execute(skySunGenerator);
		currentStage = 0;
	}

	// start stage 1 for 2.5 min, zombie per 30sec
	public void startStage1(){
		System.out.println("====> Starting stage 1 for 2.5 min");

		zombieGenerator = new ZombieGenerator(zombies,30,1,this);
		ThreadPool.execute(zombieGenerator);
		timer = new StageTimer(this);
		ThreadPool.execute(timer);
		currentStage = 1;
	}

	// start stage 2 for 3 min, 2 zombies per 30sec
	public void startStage2(){
		totalTimePassed = 150;
		System.out.println("====> Starting stage 2 for 3 min");

		zombieGenerator.setDuration(30);
		zombieGenerator.setCount(2);
		timer.reset();
		currentStage = 2;
	}

	// start final wave for 2.5 min, 2 zombies per 25sec
	public void startFinalWave(){
		totalTimePassed = 330;
		System.out.println("====> Starting final wave for 2.5 min");

		zombieGenerator.setDuration(25);
		zombieGenerator.setCount(2);
		timer.reset();
		currentStage = 3;
	}

	public Plant[][] getPlants() {
		if(plants == null)
			plants = new Plant[9][5];
		return plants;
	}

	public void checkIfWon(){
		zombieGenerator.stopThread();
		totalTimePassed = 480;
		timer.stopThread();
		while(zombies.size() > 0){
			// nothing
		}
		if(lawnMowers.size() > 0)
			isFinished = true;
	}

	private void showPauseMenu(){
//		ThreadPool.pausePool();
		PauseMenu menu = new PauseMenu(this);
		menu.show();
	}

	public void saveCurrentGame(){
		GameSave save = new GameSave(
				cards,
				plants,
				zombies,
				suns,
				lawnMowers,
				bullets,
				playerSuns,
				timer.getTimePassed(),
				currentStage
		);

		try{

			File file = new File("./data/gamesaves.txt");
			if(!file.exists())
				file.createNewFile();

			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
			GameSaves saves = (GameSaves)inputStream.readObject();
			if(saves == null)
				saves = new GameSaves();

			saves.addNewSave(save);

			file.delete();
			file.createNewFile();

			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));

			outputStream.writeObject(saves);

			outputStream.flush();

		} catch (Exception ex){
			ex.printStackTrace();
		}

		System.out.println("saved...");
	}

	public void loadSave(GameSave save){
		this.cards = save.getCards();
		this.plants = save.getPlants();
		this.zombies = save.getZombies();
		this.suns = save.getSuns();
		this.lawnMowers = save.getLawnMowers();
		this.bullets = save.getBullets();
		this.playerSuns = save.getPlayerSuns();
		int timePassed = save.getTimePassedLastStage();
		int stage = save.getLastStage();

//		System.out.println(bullets.size());

		for(Bullet bullet: bullets){
			bullet.resumeObject();
		}

		for(Card card: cards){
			card.resumeObject();
		}

		for(Zombie zombie: zombies){
			zombie.resumeObject(this);
		}

		for(LawnMower lawnMower: lawnMowers){
			lawnMower.resumeObject();
		}

		for(int i = 0;i < 9;i++)
			for(int j = 0;j < 5;j++)
				if(plants[i][j] != null)
					plants[i][j].resumeObject();
	}
}

class StageTimer implements Runnable {

	private int timePassed;
	private boolean running;
	private GameState state;

	public StageTimer(GameState state){
        this.state = state;
        timePassed = 0;
        running = true;
    }

	public void reset(){
		timePassed = 0;
		running = true;
	}

	@Override
	public void run() {
		try{

			while(running){
				timePassed += 1;
				state.setTotalTimePassed(state.getTotalTimePassed() + 1);
				Thread.sleep(1000);
			}

		} catch (Exception ex){

		}
	}

	public int getTimePassed(){
		return timePassed;
	}

	public void stopThread(){
		this.running = false;
	}

}