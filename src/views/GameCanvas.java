/*** In The Name of Allah ***/
package views;

import helpers.BufferedImages;
import helpers.ImageIcons;
import helpers.threads.ThreadPool;
import models.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * The canvas on which the rendering is performed.
 * This structure uses the old conventional double-buffering method.
 * A more modern approach is to use the BufferStrategy class.
 * For more information on how to use BufferStrategy check out:
 *    http://docs.oracle.com/javase/tutorial/extra/fullscreen/bufferstrategy.html
 *    http://docs.oracle.com/javase/8/docs/api/java/awt/image/BufferStrategy.html
 */
public class GameCanvas extends JPanel {
	
	public static final int GAME_HEIGHT = 752;                  // 720p game resolution
	public static final int GAME_WIDTH = 1000;  // wide aspect ratio
	
	private BufferedImage bufferedScreen;
	private Graphics2D bufferedGraphics;

	private JPanel previousZombiePanel = null;

	public GameCanvas() {
		super(new BorderLayout());
		setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
		//
		// Other initializations ...
		//
		// Create the offscreen buffer for offline drawing;
//		bufferedScreen = new BufferedImage(GAME_WIDTH, GAME_HEIGHT, BufferedImage.TYPE_INT_ARGB);
		try{
			bufferedScreen = ImageIO.read(new File("./images/mainBG.png"));
			bufferedGraphics = (Graphics2D) bufferedScreen.createGraphics();
		} catch (Exception ex){
			ex.printStackTrace();
		}
		// Get the graphics of the offscreen buffer;

		// this east panel is just for better alignment of other elements
		JPanel eastPanel = new JPanel(new GridLayout(5,1));
		eastPanel.setPreferredSize(new Dimension(77,600));
		eastPanel.setBackground(new Color(255,255,255,0));

		this.add(eastPanel,BorderLayout.EAST);

		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(1000,50));
		southPanel.setBackground(new Color(255,255,255,0));

		this.add(southPanel,BorderLayout.SOUTH);
	}

	/**
	 * The paint method of this component.
	 */
	@Override
	public void paintComponent(Graphics g) {
		// Call super class's paint
		super.paintComponent(g);
		// Now, draw the offscreen image to the screen like a normal image.
		g.drawImage(bufferedScreen, 0, 0, this);
	}
	
	
	/**
	 * The method to perform renderings of game elements.
	 */
	public synchronized void render(GameState state) {
		//
		// Draw all game elements according to the game state
		//  on the offscreen image (using 'bufferedGraphics') ...
		//

//		if(currentCenterPanel != null){
//			this.remove(currentCenterPanel);
//			this.remove(currentWestPanel);
//			this.remove(currentNorthPanel);
//		}

//		removeAll();

		createCardsPanel(state,state.getCards());

		addLawnMowers(state,state.getLawnMowers());

		addCenterPanel(state);

		// Then finally:
		EventQueue.invokeLater(doRepaint);
	}
	
	/**
	 * This can be much more cleaner using member reference (Java-8 syntax):
	 *    Runnable doRepaint = this::repaint;
	 * or to use lambda expressions (again from Java-8 syntax):
	 *    Runnable doRepaint = () -> { repaint(); };
	 * but I left it as is, so students won't get confused.
	 */
	private final Runnable doRepaint = new Runnable() {
		@Override
		public void run() {
			revalidate();
			repaint();
//			paintComponent(getGraphics());
			// Tell the system to do the drawing NOW;
			// otherwise it can take a few extra ms and will feel jerky!
			Toolkit.getDefaultToolkit().sync();
		}
	};
	
	/** 
	 * This is required for good double-buffering.
	 */
	@Override
	public void update(Graphics g) {
		paint(g);
	}


	private void createCardsPanel(GameState state,ArrayList<Card> cards){
		JPanel cardsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		cardsPanel.setPreferredSize(new Dimension(64*5,102));
		cardsPanel.setBorder(new EmptyBorder(0,30,0,0));
		cardsPanel.setBackground(new Color(255,255,255,0));
		cardsPanel.setAlignmentY(FlowLayout.LEADING);

		JLabel labelSuns = new JLabel("" + state.getPlayerSuns());
		labelSuns.setFont(new Font(null,Font.BOLD,20));
		labelSuns.setPreferredSize(new Dimension(64,102));
		labelSuns.setBorder(new EmptyBorder(60,0,0,0));
		cardsPanel.add(labelSuns);

		for(Card card: cards){
			try{

//				ImageIcon icon = new ImageIcon(card.getImage());
				JLabel label = new JLabel(card.getImageIcon());
				label.setPreferredSize(new Dimension(64,102));
				label.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						super.mousePressed(e);

						Card selectedCard = card;
						state.changeSelectedCard(card);
					}
				});
				label.setEnabled(card.getIsEnable());
				cardsPanel.add(label);

			} catch (Exception ex){

			}
		}

		this.add(cardsPanel,BorderLayout.NORTH);
	}

	private void addLawnMowers(GameState state,ArrayList<LawnMower> lawnMowers){
		JPanel westPanel = new JPanel(new GridLayout(5,1));
		westPanel.setPreferredSize(new Dimension(77,600));
		westPanel.setBackground(new Color(255,255,255,0));

		int i = 0;
		for(LawnMower lawnMower: lawnMowers){
			if(i == lawnMower.getRow()){
				// create label for lawn mower
				try{

//					BufferedImage image = ImageIO.read(new File(lawnMower.getImageUri()));
//					ImageIcon icon = new ImageIcon(ImageIcons.lawn_mower);
					JLabel label = new JLabel(lawnMower.getImageIcon());
					label.setPreferredSize(new Dimension(77,130));
					label.setBorder(new EmptyBorder(0,0,20,0));
					westPanel.add(label);

				} catch (Exception ex){
					ex.printStackTrace();
				}

			}
			i++;
		}

		this.add(westPanel,BorderLayout.WEST);
	}

	private void addCenterPanel(GameState state){
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(77,102,846,600);

		GridBagLayout centerLayout = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = (double)1 / 9;
		gbc.weighty = (double)1 / 5;
		JPanel centerPanel = new JPanel(centerLayout);
		centerPanel.setBounds(0,0,846,600);
		centerPanel.setBackground(new Color(255,255,255,0));
		centerPanel.setOpaque(true);

		for(int i = 0;i < 5;i++){
			for(int j = 0;j < 9;j++){
				try{

					Plant cellPlant = state.checkPlantExist(j,i);
					if(cellPlant != null){
//						ImageIcon icon = new ImageIcon(cellPlant.getImage());
						JLabel label = new JLabel(cellPlant.getImageIcon());
						gbc.gridx = j;
						gbc.gridy = i;
						label.setPreferredSize(new Dimension(94,120));
						centerLayout.setConstraints(label,gbc);
						centerPanel.add(label);
					} else {
						JLabel label = new JLabel();
						gbc.gridx = j;
						gbc.gridy = i;
						label.setPreferredSize(new Dimension(94,120));
						centerLayout.setConstraints(label,gbc);
						centerPanel.add(label);
					}

				} catch (Exception ex){
					ex.printStackTrace();
				}
			}
		}

		centerPanel.addMouseListener(state.getMouseListener());

		layeredPane.add(centerPanel,Integer.valueOf(1));
		layeredPane.add(generateSecondPanel(state),Integer.valueOf(2));

		this.add(layeredPane,BorderLayout.CENTER);
	}

	private JPanel generateSecondPanel(GameState state){

		JPanel panel = new JPanel(null);
		panel.setBounds(0,0,846,600);
		panel.setBackground(new Color(255,255,255,0));

		// adding zombies
		synchronized (state.getZombies()){
			for(Zombie zombie: state.getZombies()){
				try{

					ImageIcon icon = new ImageIcon(zombie.getImage());
					JLabel label = new JLabel(icon);
					label.setBackground(Color.BLUE);
					label.setBounds(zombie.getX(),zombie.getY(),150,150);
					panel.add(label);

				} catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}

		synchronized (state.getSuns()){
			for(Sun sun: state.getSuns()){
				try{

//					ImageIcon icon = new ImageIcon(BufferedImages.sun);
					JLabel label = new JLabel(ImageIcons.sun);
					label.setBackground(Color.BLUE);
					label.setBounds(sun.getLocationX(),sun.getLocationY(),50,50);

					label.addMouseListener(new MouseAdapter() {
						@Override
						public synchronized void mousePressed(MouseEvent e) {
							super.mousePressed(e);
							state.collectSun(sun);
							System.out.println("Hi");
						}
					});

					panel.add(label);

				} catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}


		// adding bullets
		synchronized (state.getBullets()){
			for(Bullet bullet: state.getBullets()){
				try{

//					ImageIcon icon = new ImageIcon(bullet.getImage());
					JLabel label = new JLabel(bullet.getImageIcon());
					label.setBackground(Color.BLUE);
					label.setBounds(bullet.getLocationX(),bullet.getLocationY(),50,50);

					panel.add(label);

				} catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}


		panel.setOpaque(false);

//		if(previousZombiePanel != null){
//			previousZombiePanel.revalidate();
//			previousZombiePanel.repaint();
//		}
//		previousZombiePanel = panel;

		return panel;
	}
}