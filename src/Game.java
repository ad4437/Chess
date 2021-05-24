
public class Game {
	private Player p1;
	private Player p2;
	private Board b;
	private boolean turn;
	
	public Game() {
		p1 = new Player(true);
		p2 = new Player(false);
		b = new Board(false);
		turn = true;
	}
	
	public boolean isInteractable() {
		return false;
	}
	
	public void move() {
		
	}
	
	public void reset() {
		
	}
}
