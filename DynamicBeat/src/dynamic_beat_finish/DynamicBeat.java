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

	//배경화면 이미지 변수
	private Image backGround = new ImageIcon(getClass().getClassLoader().getResource("images/introBackground.png")).getImage();
	
	
	//메뉴바 이미지
	private JLabel menuBar = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("images/menuBar.png")));


	//exitButton의마우스를 올려 놧을떄의 이미지
	private ImageIcon exitButtonEnteredImage = new ImageIcon(getClass().getClassLoader().getResource("images/exitButtonEntered.png"));
	//exitButton의 기본 이미지
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



	//이미지 파일을 각 버튼에 넣어준다.
	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton quitButton = new JButton(quitButtonBasicImage);
	private JButton leftButton = new JButton(leftButtonBasicImage);
	private JButton rightButton = new JButton(rightButtonBasicImage);
	private JButton easyButton = new JButton(easyBasicButtonImage);
	private JButton hardButton = new JButton(hardBasicButtonImage);
	private JButton backButton = new JButton(backBasicButtonImage);
	


	private int mouseX, mouseY;
	//처음에는 메인화면이 아닌 인트로화면이므로 false값을 가지게 된다.
	//그렇게 메인화면으로 들어오면 true 값으로 변경시킨다.
	
	private boolean isMainScreen = false;
	private boolean isGameScreen = false;

	//ArrayList 를 이용해서 Track을 관리
	ArrayList<Track> trackList = new ArrayList<Track>();

	//곡 선택 이미지 변수
	private Image selectedImage;
	//곡 노래 글자 이미지 변수
	private Image titleImage;
	//곡 선택 노래 변수
	private Music selectedMusic;
	//현재 선택된 트랙 번호 변수
	private int nowSelected = 0;
	// 음악 변수 지정
	Music introMusic;
	
	
	
	public static Game game;



	public DynamicBeat() {
		//트랙내용을 추가 텍스트이미지, 스타트이미지, 게임이미지, 셀렉뮤직, 게임뮤직
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

		//배경음악 실행
		introMusic = new Music("introMusic.mp3", true);
		introMusic.start();
		
		//키보드 이벤트 처리
		addKeyListener(new KeyListener());

		//exitButton 정의
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


		//startButton 정의
		//start 버튼의 위치지정 ( x좌표, y좌표, 가로, 세로);
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
			//마우스를 눌렀을때의 이벤트 처리를 한다.
			public void mousePressed(MouseEvent e){
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				enterMain();
			}
		});
		add(startButton);

		//quitButton 정의
		//quit 버튼의 위치 지정
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

		//leftButton 정의
		//left 버튼의 위치 지정 및 크기 지정
		//leftButton은 처음에 보이지 않도록 지정해준다.
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

		//rightButton 정의
		//rightButton 버튼의 위치 지정 및 크기 지정
		//rightButton은 처음에 보이지 않도록 지정해준다.
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
		
				//easyButton 정의
				//easy 버튼의 위치 지정 및 크기 지정
				//easyButton은 처음에 보이지 않도록 지정해준다.
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
				
				//hardButton 정의
				//hard 버튼의 위치 지정 및 크기 지정
				//hardButton은 처음에 보이지 않도록 지정해준다.
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
				
				//backButton 정의
				//back 버튼의 위치 지정 및 크기 지정
				//backButton은 처음에 보이지 않도록 지정해준다.
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
		// g.drawImage 는 add가 아니라 역동적인 이미지를 그려줄때 사용한다.
		g.drawImage(backGround, 0, 0, null);
		//만약 isMainScreen의 값이 true이면 특정한 이미지를 그려준다.
		if(isMainScreen){
			//곡 선택 이미지 를 넣어준다.
			g.drawImage(selectedImage, 340, 150, null);
			// 곡 제목 이미지를 넣어준다.
			g.drawImage(titleImage, 340, 50, null);
		}
		if(isGameScreen){
				game.screenDraw(g);
			}
		// add에 해당하는 부분을 그려준다.
		paintComponents(g);
		// 시간차를 넣어준다
		try {
			Thread.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.repaint();
	}

	public void selectTrack(int nowSelected){
		//노래가 시작되어 있으면 노래를 종료한다.
		if(selectedMusic != null)
			selectedMusic.close();

		titleImage = new ImageIcon(getClass().getClassLoader().getResource("images/" +  trackList.get(nowSelected).getTitleImage())).getImage();
		selectedImage = new ImageIcon(getClass().getClassLoader().getResource("images/" +  trackList.get(nowSelected).getStartImage())).getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(),true);
		selectedMusic.start();

	}

	//왼쪽 버튼을 눌렀을때
	public void selectLeft(){
		//현재 값이 맨 왼쪽일 경우
		if(nowSelected == 0)
			//맨 오른쪽 곡으로 이동하고
			nowSelected = trackList.size() -1;
		else
			//그렇지 않을 경우 왼쪽 곡으로 이동한다.
			nowSelected--;
		selectTrack(nowSelected);
	}
	//오른쪽 버튼을 눌렀을떄
	public void selectRight(){
		//마지막 곡일경우
		if(nowSelected == trackList.size()-1)
			// 맨 왼쪽곡으로 이동하고
			nowSelected = 0;
		else
			//그렇지 않을경우 오른쪽 곡으로 이동한다.
			nowSelected++;
		selectTrack(nowSelected);
	}
	
	public void gameStart(int nowSelected, String difficulty){
		//만약 이미 노래가 재생되고 있다면 노래를 꺼준다
		if(selectedMusic != null)
			selectedMusic.close();
		//isMainScreen 에 false 값을 넣어줘서 draw 함수에서 MainScreen이 출력되지 않게끔 한다.
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
		//시작버튼과 나가기버튼을 안보이게 한다.
		startButton.setVisible(false);
		quitButton.setVisible(false);
		//그리고 배경화면을 변경해준다.
		backGround =  new ImageIcon(getClass().getClassLoader().getResource("images/mainBackground.png")).getImage();
		// isMainScreen의 값을 true로 변경시켜준다.
		isMainScreen = true;
		// leftButton 을 보이게 한다.
		leftButton.setVisible(true);
		// rightButton 을 보이게 한다.
		rightButton.setVisible(true);
		// easyButton을 보이게한다.
		easyButton.setVisible(true);	
		// hardButton을 보이게한다.
		hardButton.setVisible(true);
		// 인트로 음악을 멈춘다
		introMusic.close();
		// 선택된 음악을 튼다.
		selectTrack(0);
	}

}
