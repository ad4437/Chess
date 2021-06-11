import java.io.IOException;

public class Main {	
	public static void main(String[] args) throws IOException {
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
