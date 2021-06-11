import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class GUI  {
	/*
	 * Colors:
	 * 
	 * white - #ebecd0
	 * black - #779556
	 * yellow (selected) - #f6f668
	 * red (invalid) - #b33430
	 */
	Game gameState;
    JFrame frm;
    JPanel[][] grid = new JPanel[8][8];
    PieceLabel[][] pieces = new PieceLabel[8][8];
    
    Space start;
    Space end;
    int piecesSelected = 0;
    SimpleAudioPlayer audio;

	
	public GUI () {
		gameState = new Game();
	}
	
	public GUI (Game gameStateIn) {
		gameState = gameStateIn;
	}
	
	public void initSound(String path) {
	    try {
			audio = new SimpleAudioPlayer(path);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	private void invalidMove() throws InterruptedException {
		int r = end.getRow();
		int c = end.getCol();
		grid[r][c].setBackground(Color.decode("#b33430"));
	}
	
	private void select(Space space) {
		int r = space.getRow();
		int c = space.getCol();
		
		grid[r][c].setBackground(Color.decode("#f6f668"));

	}
	
	private void resetBackground(Space space) {
		int r = space.getRow();
		int c = space.getCol();
		resetBackground(r, c);
	}
	
	private void resetBackground(int r, int c) {
		grid[r][c].setOpaque(true);

		if (r % 2 == 0) {
			if (c % 2 == 0) grid[r][c].setBackground(Color.decode("#ebecd0"));
			else grid[r][c].setBackground(Color.decode("#779556"));
		} else {
			if (c % 2 == 0) grid[r][c].setBackground(Color.decode("#779556"));
			else grid[r][c].setBackground(Color.decode("#ebecd0"));
		}
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
		
		String path = "assets/images/" + color + "/" + name + ".png";
		
		return ImageIO.read(new File(path));
	}
	
	public void sync() throws IOException {
		// sync the grid state with the current board state
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				if (getImage(r,c) != null) {
					ImageIcon piece = new ImageIcon(getImage(r,c));
					pieces[r][c].setIcon(piece);
				} else {
					pieces[r][c].setIcon(null);
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
				resetBackground(r,c);
				
				PieceLabel piecePic = new PieceLabel();
				piecePic.setloc(r, c);
				pieces[r][c] = piecePic;
				pieces[r][c].setHorizontalAlignment(PieceLabel.CENTER);
				pieces[r][c].setVerticalAlignment(PieceLabel.CENTER);
				piecePic.addMouseListener(new MouseAdapter() {
	                @Override
	                public void mouseClicked(MouseEvent e) {
	                	piecesSelected++;
	                	System.out.println(piecesSelected);
	                	if (piecesSelected == 1) {
	                		start = gameState.getBoard().getSpace(piecePic.getRow(), piecePic.getCol());
							select(start);
	                	} else {
	                		end = gameState.getBoard().getSpace(piecePic.getRow(), piecePic.getCol());
	                		if (!gameState.attemptMove(start, end)) {
	                			System.out.println("Invalid Move");
								try {
									audio.restart();
								} catch (IOException e2) {
									e2.printStackTrace();
								} catch (LineUnavailableException e2) {
									e2.printStackTrace();
								} catch (UnsupportedAudioFileException e2) {
									e2.printStackTrace();
								}

	                			try {
									invalidMove();
									piecePic.repaint();
//									TimeUnit.SECONDS.sleep(2);
									resetBackground(end);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
	                		} 
	                		resetBackground(start);
	                		resetBackground(end);
                			start = null;
                			end = null;
	                		piecesSelected = 0;
	                		try {
								sync();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
	                	}
	                }
				});

				grid[r][c].add(piecePic, BorderLayout.CENTER);
				pane.add(grid[r][c]);
			}
		}
	}
	
	public void draw() throws IOException {
        frm = new JFrame();
        initSound("assets/sounds/hilo.wav"); //TODO: Allow user to set the sound through the settings
        initGrid(frm.getContentPane());
        sync();
        frm.setTitle("Chess");
        frm.pack();
        frm.setVisible(true);
        frm.setResizable(false);
        frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
}
