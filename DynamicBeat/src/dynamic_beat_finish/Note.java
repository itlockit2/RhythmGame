package dynamic_beat_finish;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread {

	// 노트 이미지
	private Image noteBasicImage = new ImageIcon(getClass().getClassLoader().getResource("images/noteBasic.png")).getImage();
	private int x, y = 580 - (1000 / MainRealFinish.SlEEP_TIME * MainRealFinish.NOTE_SPEED) * MainRealFinish.REACH_TIME;
	private String noteType;
	private boolean proceeded = true;

	public void close() {
		proceeded = false;
	}

	public Note(String noteType) {
		if (noteType.equals("S")) {
			x = 228;
		} else if (noteType.equals("D")) {
			x = 332;
		} else if (noteType.equals("F")) {
			x = 436;
		} else if (noteType.equals("Space")) {
			x = 540;
		} else if (noteType.equals("J")) {
			x = 744;
		} else if (noteType.equals("K")) {
			x = 848;
		} else if (noteType.equals("L")) {
			x = 952;
		}
		this.noteType = noteType;
	}

	public void screenDraw(Graphics2D g) {
		if (!noteType.equals("Space")) {
			g.drawImage(noteBasicImage, x, y, null);
		} else {
			g.drawImage(noteBasicImage, x, y, null);
			g.drawImage(noteBasicImage, x + 100, y, null);
		}
	}

	public void drop() {
		y += MainRealFinish.NOTE_SPEED;
		if (y >= 620) {
			close();
			DynamicBeat.game.setComboNum(0);
			DynamicBeat.game.setJudgeImage(new ImageIcon(getClass().getClassLoader().getResource("images/JudgeMissImage.png")).getImage());
			
		}
	}

	@Override
	public void run() {
		try {
			while (true) {
				drop();
				if (proceeded) {
					Thread.sleep(MainRealFinish.SlEEP_TIME);
				} else {
					interrupt();
					break;
				}
				Thread.sleep(MainRealFinish.SlEEP_TIME);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public String judge() {
		if (y >= 620) {
			close();
			return "Miss";
		} else if (y >= 613) {
			close();
			return "Late";
		} else if (y >= 600) {
			close();
			return "Good";
		} else if (y >= 587) {
			close();
			return "Great";
		} else if (y >= 573) {
			close();
			return "Perfect";
		} else if (y >= 565) {
			close();
			return "Great";
		} else if (y >= 550) {
			close();
			return "Good";
		} else if (y >= 535) {
			close();
			return "Early";
		}
		return "None";
	}

	public boolean isProceeded() {
		return proceeded;
	}

	public void setProceeded(boolean proceeded) {
		this.proceeded = proceeded;
	}

	public String getNoteType() {
		return noteType;
	}

	public void setNoteType(String noteType) {
		this.noteType = noteType;
	}

}
