import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GUI  {
	Game gameState;
    JFrame frm;
    JPanel[][] grid = new JPanel[8][8];
    JLabel[][] pieces = new JLabel[8][8];

	
	public GUI () {
		gameState = new Game();
	}
	
	public GUI (Game gameStateIn) {
		gameState = gameStateIn;
	}
	
	private BufferedImage getImage(int r, int c) throws IOException {
		// returns Image object with image of piece at space (r,c)
		// null if no piece at (r,c)
		Piece piece = gameState.getBoard().getSpace(r,c).getPiece();
		if (piece == null) return null;
		
		String color;
		String name = piece.toString();
		boolean isWhite = ((ChessPiece) piece).isWhite();
		if (isWhite) color = "White";
		else color = "Black";
		
		String path = "chess_images/" + color + "/" + name + ".png";
		
		return ImageIO.read(new File(path));
	}
	
	public void sync() throws IOException {
		// sync the grid state with the current board state
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				if (getImage(r,c) != null) {
					ImageIcon piece = new ImageIcon(getImage(r,c));
					pieces[r][c].setIcon(piece);
				}
			}
		}
	}
	
	private void initGrid(Container pane) throws IOException {
		// initialize grid and spaces
		pane.setPreferredSize(new Dimension(600,600));
		pane.setLayout(new GridLayout(8,8));
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				grid[r][c] = new JPanel( new BorderLayout() );
				
				grid[r][c].setOpaque(true);
				
				// white - #ebecd0
				// black - #779556
				if (r % 2 == 0) {
					if (c % 2 == 0) grid[r][c].setBackground(Color.decode("#ebecd0"));
					else grid[r][c].setBackground(Color.decode("#779556"));
				} else {
					if (c % 2 == 0) grid[r][c].setBackground(Color.decode("#779556"));
					else grid[r][c].setBackground(Color.decode("#ebecd0"));
				}
				
				JLabel piecePic = new JLabel();
				pieces[r][c] = piecePic;
				pieces[r][c].setHorizontalAlignment(JLabel.CENTER);
				pieces[r][c].setVerticalAlignment(JLabel.CENTER);

				grid[r][c].add(piecePic, BorderLayout.CENTER);
				pane.add(grid[r][c]);
			}
		}
	}
	
	public void draw() throws IOException {
        frm = new JFrame();
        initGrid(frm.getContentPane());
        sync();
        frm.setTitle("Chess");
        frm.pack();
        frm.setVisible(true);
        frm.setResizable(false);
        frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
}
