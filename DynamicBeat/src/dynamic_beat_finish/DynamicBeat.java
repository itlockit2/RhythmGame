package dynamic_beat_finish;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DynamicBeat extends JFrame {

	private Image screenImage;
	private Graphics screenGraphic;

	//���ȭ�� �̹��� ����
	private Image backGround = new ImageIcon(getClass().getClassLoader().getResource("images/introBackground.png")).getImage();
	
	
	//�޴��� �̹���
	private JLabel menuBar = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("images/menuBar.png")));


	//exitButton�Ǹ��콺�� �÷� �J������ �̹���
	private ImageIcon exitButtonEnteredImage = new ImageIcon(getClass().getClassLoader().getResource("images/exitButtonEntered.png"));
	//exitButton�� �⺻ �̹���
	private ImageIcon exitButtonBasicImage = new ImageIcon(getClass().getClassLoader().getResource("images/exitButtonBasic.png"));

	private ImageIcon startButtonBasicImage = new ImageIcon(getClass().getClassLoader().getResource("images/startButtonBasic.png"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(getClass().getClassLoader().getResource("images/startButtonEntered.png"));
	private ImageIcon quitButtonBasicImage = new ImageIcon(getClass().getClassLoader().getResource("images/quitButtonBasic.png"));
	private ImageIcon quitButtonEnteredImage = new ImageIcon(getClass().getClassLoader().getResource("images/quitButtonEntered.png"));

	private ImageIcon leftButtonBasicImage = new ImageIcon(getClass().getClassLoader().getResource("images/leftButtonBasic.png"));
	private ImageIcon leftButtonEnteredImage = new ImageIcon(getClass().getClassLoader().getResource("images/leftButtonEntered.png"));
	private ImageIcon rightButtonBasicImage = new ImageIcon(getClass().getClassLoader().getResource("images/rightButtonBasic.png"));
	private ImageIcon rightButtonEnteredImage = new ImageIcon(getClass().getClassLoader().getResource("images/rightButtonEntered.png"));
	
	private ImageIcon easyBasicButtonImage = new ImageIcon(getClass().getClassLoader().getResource("images/easyBasicButton.png"));
	private ImageIcon easyEnteredButtonImage = new ImageIcon(getClass().getClassLoader().getResource("images/easyEnteredButton.png"));
	private ImageIcon hardBasicButtonImage = new ImageIcon(getClass().getClassLoader().getResource("images/hardBasicButton.png"));
	private ImageIcon hardEnteredButtonImage = new ImageIcon(getClass().getClassLoader().getResource("images/hardEnteredButton.png"));
	
	private ImageIcon backBasicButtonImage = new ImageIcon(getClass().getClassLoader().getResource("images/backBasicButton.png"));
	private ImageIcon backEnteredButtonImage = new ImageIcon(getClass().getClassLoader().getResource("images/backEnteredButton.png"));



	//�̹��� ������ �� ��ư�� �־��ش�.
	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton quitButton = new JButton(quitButtonBasicImage);
	private JButton leftButton = new JButton(leftButtonBasicImage);
	private JButton rightButton = new JButton(rightButtonBasicImage);
	private JButton easyButton = new JButton(easyBasicButtonImage);
	private JButton hardButton = new JButton(hardBasicButtonImage);
	private JButton backButton = new JButton(backBasicButtonImage);
	


	private int mouseX, mouseY;
	//ó������ ����ȭ���� �ƴ� ��Ʈ��ȭ���̹Ƿ� false���� ������ �ȴ�.
	//�׷��� ����ȭ������ ������ true ������ �����Ų��.
	
	private boolean isMainScreen = false;
	private boolean isGameScreen = false;

	//ArrayList �� �̿��ؼ� Track�� ����
	ArrayList<Track> trackList = new ArrayList<Track>();

	//�� ���� �̹��� ����
	private Image selectedImage;
	//�� �뷡 ���� �̹��� ����
	private Image titleImage;
	//�� ���� �뷡 ����
	private Music selectedMusic;
	//���� ���õ� Ʈ�� ��ȣ ����
	private int nowSelected = 0;
	// ���� ���� ����
	Music introMusic;
	
	
	
	public static Game game;



	public DynamicBeat() {
		//Ʈ�������� �߰� �ؽ�Ʈ�̹���, ��ŸƮ�̹���, �����̹���, ��������, ���ӹ���
		trackList.add(new Track("migthyLoveText.png","migthyLoveStartImage.png","migthyLoveGameImage.jpg","mightyLoveSeleted.mp3","Mighty Love - Joakim Karud.mp3","Mighty Love - Joakim Karud"));
		trackList.add(new Track("wildFlowerText.png","wildFlowerStartImage.png","wildFlowerGameImage.jpg","wildFlowerSeleted.mp3","Joakim Karud - Wild Flower.mp3","Wild Flower - Joakim Karud"));
		trackList.add(new Track("anergyText.png","energyStartImage.png","energyGameImage.jpg","energySeleted.mp3","Energy - Bensound.mp3","Energy - Bensound"));
	
		
		setUndecorated(true);
		setTitle("Dynamic Beat");
		setSize(MainRealFinish.SCREEN_WIDTH, MainRealFinish.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);

		//������� ����
		introMusic = new Music("introMusic.mp3", true);
		introMusic.start();
		
		//Ű���� �̺�Ʈ ó��
		addKeyListener(new KeyListener());

		//exitButton ����
		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusable(false);
		exitButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e){
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();

			}
			@Override
			public void mouseExited(MouseEvent e){
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e){
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				try{
					Thread.sleep(1000);
				} catch(InterruptedException ex){
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(exitButton);


		//startButton ����
		//start ��ư�� ��ġ���� ( x��ǥ, y��ǥ, ����, ����);
		startButton.setBounds(360, 500, 300, 118);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusable(false);
		startButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e){
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();

			}
			@Override
			public void mouseExited(MouseEvent e){
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			//���콺�� ���������� �̺�Ʈ ó���� �Ѵ�.
			public void mousePressed(MouseEvent e){
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				enterMain();
			}
		});
		add(startButton);

		//quitButton ����
		//quit ��ư�� ��ġ ����
		quitButton.setBounds(680,500, 300, 118);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusable(false);
		quitButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e){
				quitButton.setIcon(quitButtonEnteredImage);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();

			}
			@Override
			public void mouseExited(MouseEvent e){
				quitButton.setIcon(quitButtonBasicImage);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e){
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				try{
					Thread.sleep(1000);
				} catch(InterruptedException ex){
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(quitButton);

		//leftButton ����
		//left ��ư�� ��ġ ���� �� ũ�� ����
		//leftButton�� ó���� ������ �ʵ��� �������ش�.
		leftButton.setVisible(false);
		leftButton.setBounds(140,360, 60, 60);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusable(false);
		leftButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e){
				leftButton.setIcon(leftButtonEnteredImage);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();

			}
			@Override
			public void mouseExited(MouseEvent e){
				leftButton.setIcon(leftButtonBasicImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e){
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				selectLeft();
			}
		});
		add(leftButton);

		//rightButton ����
		//rightButton ��ư�� ��ġ ���� �� ũ�� ����
		//rightButton�� ó���� ������ �ʵ��� �������ش�.
		rightButton.setVisible(false);
		rightButton.setBounds(1080,360, 60, 60);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusable(false);
		rightButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e){
				rightButton.setIcon(rightButtonEnteredImage);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();

			}
			@Override
			public void mouseExited(MouseEvent e){
				rightButton.setIcon(rightButtonBasicImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e){
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				selectRight();
			}
		});
		add(rightButton);


		menuBar.setBounds(0, 0, 1280, 30);
		menuBar.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});

		menuBar.addMouseMotionListener(new MouseMotionAdapter(){
			@Override
			public void mouseDragged(MouseEvent e){
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y- mouseY);
			}
		});
		add(menuBar);
		
				//easyButton ����
				//easy ��ư�� ��ġ ���� �� ũ�� ����
				//easyButton�� ó���� ������ �ʵ��� �������ش�.
				easyButton.setVisible(false);
				easyButton.setBounds(375,605, 250, 67);
				easyButton.setBorderPainted(false);
				easyButton.setContentAreaFilled(false);
				easyButton.setFocusable(false);
				easyButton.addMouseListener(new MouseAdapter(){
					@Override
					public void mouseEntered(MouseEvent e){
						easyButton.setIcon(easyEnteredButtonImage);
						easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
						Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
						buttonEnteredMusic.start();

					}
					@Override
					public void mouseExited(MouseEvent e){
						easyButton.setIcon(easyBasicButtonImage);
						easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					}
					@Override
					public void mousePressed(MouseEvent e){
						Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
						buttonPressedMusic.start();
						gameStart(nowSelected,"Easy");
					}
				});
				add(easyButton);
				
				//hardButton ����
				//hard ��ư�� ��ġ ���� �� ũ�� ����
				//hardButton�� ó���� ������ �ʵ��� �������ش�.
				hardButton.setVisible(false);
				hardButton.setBounds(655,605, 250, 67);
				hardButton.setBorderPainted(false);
				hardButton.setContentAreaFilled(false);
				hardButton.setFocusable(false);
				hardButton.addMouseListener(new MouseAdapter(){
					@Override
					public void mouseEntered(MouseEvent e){
						hardButton.setIcon(hardEnteredButtonImage);
						hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
						Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
						buttonEnteredMusic.start();

					}
					@Override
					public void mouseExited(MouseEvent e){
						hardButton.setIcon(hardBasicButtonImage);
						hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					}
					@Override
					public void mousePressed(MouseEvent e){
						Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
						buttonPressedMusic.start();
						gameStart(nowSelected,"Hard");
					}
				});
				add(hardButton);
				
				//backButton ����
				//back ��ư�� ��ġ ���� �� ũ�� ����
				//backButton�� ó���� ������ �ʵ��� �������ش�.
				backButton.setVisible(false);
				backButton.setBounds(20,50, 250, 67);
				backButton.setBorderPainted(false);
				backButton.setContentAreaFilled(false);
				backButton.setFocusable(false);
				backButton.addMouseListener(new MouseAdapter(){
					@Override
					public void mouseEntered(MouseEvent e){
						backButton.setIcon(backEnteredButtonImage);
						backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
						Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
						buttonEnteredMusic.start();

					}
					@Override
					public void mouseExited(MouseEvent e){
						backButton.setIcon(backBasicButtonImage);
						backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					}
					@Override
					public void mousePressed(MouseEvent e){
						Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
						buttonPressedMusic.start();
						backMain();
					}
				});
				add(backButton);

}

	public void paint(Graphics g) {
		screenImage = createImage(MainRealFinish.SCREEN_WIDTH, MainRealFinish.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D) screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g) {
		// g.drawImage �� add�� �ƴ϶� �������� �̹����� �׷��ٶ� ����Ѵ�.
		g.drawImage(backGround, 0, 0, null);
		//���� isMainScreen�� ���� true�̸� Ư���� �̹����� �׷��ش�.
		if(isMainScreen){
			//�� ���� �̹��� �� �־��ش�.
			g.drawImage(selectedImage, 340, 150, null);
			// �� ���� �̹����� �־��ش�.
			g.drawImage(titleImage, 340, 50, null);
		}
		if(isGameScreen){
				game.screenDraw(g);
			}
		// add�� �ش��ϴ� �κ��� �׷��ش�.
		paintComponents(g);
		// �ð����� �־��ش�
		try {
			Thread.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.repaint();
	}

	public void selectTrack(int nowSelected){
		//�뷡�� ���۵Ǿ� ������ �뷡�� �����Ѵ�.
		if(selectedMusic != null)
			selectedMusic.close();

		titleImage = new ImageIcon(getClass().getClassLoader().getResource("images/" +  trackList.get(nowSelected).getTitleImage())).getImage();
		selectedImage = new ImageIcon(getClass().getClassLoader().getResource("images/" +  trackList.get(nowSelected).getStartImage())).getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(),true);
		selectedMusic.start();

	}

	//���� ��ư�� ��������
	public void selectLeft(){
		//���� ���� �� ������ ���
		if(nowSelected == 0)
			//�� ������ ������ �̵��ϰ�
			nowSelected = trackList.size() -1;
		else
			//�׷��� ���� ��� ���� ������ �̵��Ѵ�.
			nowSelected--;
		selectTrack(nowSelected);
	}
	//������ ��ư�� ��������
	public void selectRight(){
		//������ ���ϰ��
		if(nowSelected == trackList.size()-1)
			// �� ���ʰ����� �̵��ϰ�
			nowSelected = 0;
		else
			//�׷��� ������� ������ ������ �̵��Ѵ�.
			nowSelected++;
		selectTrack(nowSelected);
	}
	
	public void gameStart(int nowSelected, String difficulty){
		//���� �̹� �뷡�� ����ǰ� �ִٸ� �뷡�� ���ش�
		if(selectedMusic != null)
			selectedMusic.close();
		//isMainScreen �� false ���� �־��༭ draw �Լ����� MainScreen�� ��µ��� �ʰԲ� �Ѵ�.
		isMainScreen = false;
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		backButton.setVisible(true);
		backGround =  new ImageIcon(getClass().getClassLoader().getResource("images/" + trackList.get(nowSelected).getGameImage())).getImage();
		isGameScreen = true;
		game = new Game(trackList.get(nowSelected).getTitleName(),difficulty, trackList.get(nowSelected).getGameMusic());
		game.start();
		setFocusable(true);
	}
	
	public void backMain(){
		isMainScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		backGround =  new ImageIcon(getClass().getClassLoader().getResource("images/mainBackground.png")).getImage();
		backButton.setVisible(false);
		selectTrack(nowSelected);
		isGameScreen = false;
		game.close();
	}
	
	public void enterMain(){
		//���۹�ư�� �������ư�� �Ⱥ��̰� �Ѵ�.
		startButton.setVisible(false);
		quitButton.setVisible(false);
		//�׸��� ���ȭ���� �������ش�.
		backGround =  new ImageIcon(getClass().getClassLoader().getResource("images/mainBackground.png")).getImage();
		// isMainScreen�� ���� true�� ��������ش�.
		isMainScreen = true;
		// leftButton �� ���̰� �Ѵ�.
		leftButton.setVisible(true);
		// rightButton �� ���̰� �Ѵ�.
		rightButton.setVisible(true);
		// easyButton�� ���̰��Ѵ�.
		easyButton.setVisible(true);	
		// hardButton�� ���̰��Ѵ�.
		hardButton.setVisible(true);
		// ��Ʈ�� ������ �����
		introMusic.close();
		// ���õ� ������ ư��.
		selectTrack(0);
	}

}
