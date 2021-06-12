import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Main {	
	public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		boolean isGameOver = false;
		
		Game game = new Game();
		GUI gui = new GUI(game);
		gui.draw();
		
//		while (!isGameOver) {
//			
//			game.nextTurn();
//		}
	}
}
