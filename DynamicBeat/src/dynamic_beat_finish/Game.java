package dynamic_beat_finish;

import java.awt.*;
import java.util.*;

import javax.swing.*;

public class Game extends Thread {

	// 노트구분선 이미지
	private Image noteRouteLineImage = new ImageIcon(getClass().getClassLoader().getResource("images/noteRouteLine.png"))
			.getImage();

	// 판정선 이미지
	private Image judgementLineImage = new ImageIcon(getClass().getClassLoader().getResource("images/judgementLine.png"))
			.getImage();

	// 게임정보 이미지
	private Image gameInfoImage = new ImageIcon(getClass().getClassLoader().getResource("images/gameInfo.png")).getImage();

	// 콤보갯수
	private int comboNum;
	
	// 점수
	private int point;

	// 노트가 움직이는 공간 이미지
	private Image noteRouteSImage = new ImageIcon(getClass().getClassLoader().getResource("images/noteRoute.png")).getImage();
	private Image noteRouteDImage = new ImageIcon(getClass().getClassLoader().getResource("images/noteRoute.png")).getImage();
	private Image noteRouteFImage = new ImageIcon(getClass().getClassLoader().getResource("images/noteRoute.png")).getImage();
	private Image noteRouteSpace1Image = new ImageIcon(getClass().getClassLoader().getResource("images/noteRoute.png"))
			.getImage();
	private Image noteRouteSpace2Image = new ImageIcon(getClass().getClassLoader().getResource("images/noteRoute.png"))
			.getImage();
	private Image noteRouteJImage = new ImageIcon(getClass().getClassLoader().getResource("images/noteRoute.png")).getImage();
	private Image noteRouteKImage = new ImageIcon(getClass().getClassLoader().getResource("images/noteRoute.png")).getImage();
	private Image noteRouteLImage = new ImageIcon(getClass().getClassLoader().getResource("images/noteRoute.png")).getImage();

	// 게임화면 디자인
	private Image judgeImage;
	private Image redRensFlareImage;

	private String titleName;
	private String difficulty;
	private String musicTitle;
	private Music gameMusic;

	ArrayList<Note> noteList = new ArrayList<Note>();

	public Game(String titleName, String difficulty, String musicTitle) {
		comboNum = 0;
		this.titleName = titleName;
		this.difficulty = difficulty;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle, false);

	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(noteRouteSImage, 228, 30, null);
		g.drawImage(noteRouteDImage, 332, 30, null);
		g.drawImage(noteRouteFImage, 436, 30, null);
		g.drawImage(noteRouteSpace1Image, 540, 30, null);
		g.drawImage(noteRouteSpace2Image, 640, 30, null);
		g.drawImage(noteRouteJImage, 744, 30, null);
		g.drawImage(noteRouteKImage, 848, 30, null);
		g.drawImage(noteRouteLImage, 952, 30, null);
		g.drawImage(noteRouteLineImage, 224, 30, null);
		g.drawImage(noteRouteLineImage, 328, 30, null);
		g.drawImage(noteRouteLineImage, 432, 30, null);
		g.drawImage(noteRouteLineImage, 536, 30, null);
		g.drawImage(noteRouteLineImage, 740, 30, null);
		g.drawImage(noteRouteLineImage, 844, 30, null);
		g.drawImage(noteRouteLineImage, 948, 30, null);
		g.drawImage(noteRouteLineImage, 1052, 30, null);
		g.drawImage(gameInfoImage, 0, 660, null);
		g.drawImage(judgementLineImage, 0, 580, null);
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if (!note.isProceeded()) {
				noteList.remove(i);
				i--;
			} else {
				note.screenDraw(g);
			}
		}
		g.setColor(Color.WHITE);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		// 타이틀이름 화면에 출력해준다.
		g.drawString(titleName, 20, 702);
		// 난이도를 화면에 출력해준다.
		g.drawString(difficulty, 1190, 702);
		g.setFont(new Font("Arial", Font.PLAIN, 26));
		g.setColor(Color.DARK_GRAY);
		g.drawString("S", 270, 609);
		g.drawString("D", 374, 609);
		g.drawString("F", 478, 609);
		g.drawString("Space Bar", 580, 609);
		g.drawString("J", 784, 609);
		g.drawString("K", 889, 609);
		g.drawString("L", 993, 609);
		g.setFont(new Font("배달의민족 주아보통", Font.BOLD, 30));
		g.setColor(Color.BLACK);
		g.drawString("COMBO", 565, 500);
		g.setColor(Color.PINK);
		g.drawString(String.valueOf(comboNum), 690, 500);
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Elephant", Font.BOLD, 30));
		g.drawString(String.valueOf(point), 545, 702);
		g.drawImage(redRensFlareImage, 400, 250, 500, 400, null);
		g.drawImage(judgeImage, 465, 370, null);

	}

	public void pressS() {
		judge("S");
		noteRouteSImage = new ImageIcon(getClass().getClassLoader().getResource("images/noteRoutePressed.png")).getImage();
	}

	public void releaseS() {
		noteRouteSImage = new ImageIcon(getClass().getClassLoader().getResource("images/noteRoute.png")).getImage();
	}

	public void pressD() {
		judge("D");
		noteRouteDImage = new ImageIcon(getClass().getClassLoader().getResource("images/noteRoutePressed.png")).getImage();
	}

	public void releaseD() {
		noteRouteDImage = new ImageIcon(getClass().getClassLoader().getResource("images/noteRoute.png")).getImage();
	}

	public void pressF() {
		judge("F");
		noteRouteFImage = new ImageIcon(getClass().getClassLoader().getResource("images/noteRoutePressed.png")).getImage();
	}

	public void releaseF() {
		noteRouteFImage = new ImageIcon(getClass().getClassLoader().getResource("images/noteRoute.png")).getImage();
	}

	public void pressSpace() {
		judge("Space");
		noteRouteSpace1Image = new ImageIcon(getClass().getClassLoader().getResource("images/noteRoutePressed.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(getClass().getClassLoader().getResource("images/noteRoutePressed.png")).getImage();
	}

	public void releaseSpace() {
		noteRouteSpace1Image = new ImageIcon(getClass().getClassLoader().getResource("images/noteRoute.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(getClass().getClassLoader().getResource("images/noteRoute.png")).getImage();
	}

	public void pressJ() {
		judge("J");
		noteRouteJImage = new ImageIcon(getClass().getClassLoader().getResource("images/noteRoutePressed.png")).getImage();
	}

	public void releaseJ() {
		noteRouteJImage = new ImageIcon(getClass().getClassLoader().getResource("images/noteRoute.png")).getImage();
	}

	public void pressK() {
		judge("K");
		noteRouteKImage = new ImageIcon(getClass().getClassLoader().getResource("images/noteRoutePressed.png")).getImage();
	}

	public void releaseK() {
		noteRouteKImage = new ImageIcon(getClass().getClassLoader().getResource("images/noteRoute.png")).getImage();
	}

	public void pressL() {
		judge("L");
		noteRouteLImage = new ImageIcon(getClass().getClassLoader().getResource("images/noteRoutePressed.png")).getImage();
	}

	public void releaseL() {
		noteRouteLImage = new ImageIcon(getClass().getClassLoader().getResource("images/noteRoute.png")).getImage();
	}

	@Override
	public void run() {
		dropNotes();
	}

	public void close() {
		gameMusic.close();
		this.interrupt();
	}

	public void judge(String input) {
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if (input.equals(note.getNoteType())) {
				judgeEvent(note.judge());
				break;
			}
		}
	}

	public void judgeEvent(String judge) {
		if (!judge.equals("None")) {
			redRensFlareImage = new ImageIcon(getClass().getClassLoader().getResource("images/redLengeFlare.png")).getImage();
		}

		if (judge.equals("Early")) {
			judgeImage = new ImageIcon(getClass().getClassLoader().getResource("images/judgeEalryImage.png")).getImage();
			comboNum = 0;
		} else if (judge.equals("Perfect")) {
			judgeImage = new ImageIcon(getClass().getClassLoader().getResource("images/JudgePerfectImage.png")).getImage();
			comboNum++;
			point += 10000;
		} else if (judge.equals("Good")) {
			judgeImage = new ImageIcon(getClass().getClassLoader().getResource("images/judgeGoodImage.png")).getImage();
			comboNum++;
			point += 5000;
		} else if (judge.equals("Great")) {
			judgeImage = new ImageIcon(getClass().getClassLoader().getResource("images/judgeGreatImage.png")).getImage();
			comboNum++;
			point += 2500;
		} else if (judge.equals("Late")) {
			judgeImage = new ImageIcon(getClass().getClassLoader().getResource("images/judgeLateImage.png")).getImage();
			comboNum = 0;
		} else if (judge.equals("Miss")) {
			judgeImage = new ImageIcon(getClass().getClassLoader().getResource("images/JudgeMissImage.png")).getImage();
			comboNum = 0;
		}

	}

	public void dropNotes() {
		Beat[] beats = null;
		if (titleName.equals("Mighty Love - Joakim Karud")) {
			int startTime = 2500 - MainRealFinish.REACH_TIME * 1000;
			int gap = 125;
			beats = new Beat[] { new Beat(startTime, "S"), new Beat(startTime + gap * 2, "D"),
					new Beat(startTime + gap * 4, "S"), new Beat(startTime + gap * 6, "D"),
					new Beat(startTime + gap * 8, "S"), new Beat(startTime + gap * 10, "D"),
					new Beat(startTime + gap * 12, "S"), new Beat(startTime + gap * 14, "D"),
					new Beat(startTime + gap * 18, "J"), new Beat(startTime + gap * 20, "K"),
					new Beat(startTime + gap * 22, "J"), new Beat(startTime + gap * 24, "K"),
					new Beat(startTime + gap * 26, "J"), new Beat(startTime + gap * 28, "K"),
					new Beat(startTime + gap * 30, "J"), new Beat(startTime + gap * 32, "K"),
					new Beat(startTime + gap * 36, "S"), new Beat(startTime + gap * 38, "D"),
					new Beat(startTime + gap * 40, "S"), new Beat(startTime + gap * 42, "D"),
					new Beat(startTime + gap * 44, "S"), new Beat(startTime + gap * 46, "D"),
					new Beat(startTime + gap * 48, "J"), new Beat(startTime + gap * 49, "K"),
					new Beat(startTime + gap * 50, "L"), new Beat(startTime + gap * 52, "F"),
					new Beat(startTime + gap * 52, "Space"), new Beat(startTime + gap * 52, "J"), };

		} else if (titleName.equals("JoaKim Karud - Wild Flower")) {
			int startTime = 1000 - MainRealFinish.REACH_TIME * 1000;
			beats = new Beat[] { new Beat(startTime, "space"), };

		} else if (titleName.equals("Bendsound - Energy")) {
			int startTime = 1000 - MainRealFinish.REACH_TIME * 1000;
			beats = new Beat[] { new Beat(startTime, "space"), };
		}

		int i = 0;
		gameMusic.start();
		while (i < beats.length && !isInterrupted()) {
			boolean dropped = false;
			if (beats[i].getTime() <= gameMusic.getTime()) {
				Note note = new Note(beats[i].getNoteName());
				note.start();
				noteList.add(note);
				i++;
				dropped = true;
			}
			if (!dropped) {
				try {
					Thread.sleep(5);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	public int getComboNum() {
		return comboNum;
	}

	public void setComboNum(int comboNum) {
		this.comboNum = comboNum;
	}

	public Image getJudgeImage() {
		return judgeImage;
	}

	public void setJudgeImage(Image judgeImage) {
		this.judgeImage = judgeImage;
	}
	
	

}
