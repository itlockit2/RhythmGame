package dynamic_beat_finish;

public class MainRealFinish {
	//가로 1280 * 세로 720으로 설정 
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	public static final int NOTE_SPEED = 5;
	public static final int SlEEP_TIME = 10;
	public static final int REACH_TIME = 2;
	
	public static void main(String[] args) {
		new DynamicBeat();	
	}
}