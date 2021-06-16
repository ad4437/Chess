package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import pieces.*;

public class GridPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;

	private Game gameState;	
	private Space start;
	private Space end;
	private int piecesSelected = 0;
	private boolean isGameOver = false;
	private SidePanel sidePanel;
	private SimpleAudioPlayer audio;

	private JPanel[][] grid = new JPanel[8][8];
	private PieceLabel[][] pieces = new PieceLabel[8][8];
	
	public void setGame(Game gameStateIn) {
		gameState = gameStateIn;
	}
	
	public void setAudio(SimpleAudioPlayer audioIn) {
		audio = audioIn;
	}
	
	public void setSidePanel(SidePanel sidePanelIn) {
		sidePanel = sidePanelIn;
	}
	
	private BufferedImage getImage(int r, int c) throws IOException {
		// returns Image object with image of piece at space (r,c)
		// null if no piece at (r,c)
		Piece piece = gameState.getBoard().getSpace(r,c).getPiece();
		if (piece == null) return null;
		
		String color;
		String name = piece.toString();
		boolean isWhite = ((ChessPiece) piece).isWhite();
		if (isWhite) color = "white";
		else color = "black";
		
		String path = "assets/images/pieces/" + color + "/" + name + ".png";
		
		return ImageIO.read(new File(path));
	}
    
	private void setSelectColor(Space space) {
		// Highlights selected space
		int r = space.getRow();
		int c = space.getCol();
		
		grid[r][c].setBackground(Color.decode("#f6f668"));
	}
	
	private void setCheckColor(Space space) {
		// Highlights selected space
		int r = space.getRow();
		int c = space.getCol();
		
		grid[r][c].setBackground(Color.decode("#b33430"));
	}
    
	private void resetBackground(Space space) {
		// Resets the background color of a space to original w/b color
		int r = space.getRow();
		int c = space.getCol();
		resetBackground(r, c);
	}
	
	private void resetBackground(int r, int c) {
		// Resets the background color of a space to original w/b color
		grid[r][c].setOpaque(true);
		
		if (gameState.kingInCheck()) {
			setCheckColor(gameState.getKingSpace());
		}
		
		if (r % 2 == 0) {
			if (c % 2 == 0) grid[r][c].setBackground(Color.decode("#ebecd0"));
			else grid[r][c].setBackground(Color.decode("#779556"));
		} else {
			if (c % 2 == 0) grid[r][c].setBackground(Color.decode("#779556"));
			else grid[r][c].setBackground(Color.decode("#ebecd0"));
		}
	}
	
	public void setIsGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}
	
	public void sync() throws IOException {
		// sync the grid state with the current board state
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				resetBackground(r,c);
				if (getImage(r,c) != null) {
					ImageIcon piece = new ImageIcon(getImage(r,c));
					pieces[r][c].setIcon(piece);
				} else {
					pieces[r][c].setIcon(null);
				}
			}
		}
	}

	public void draw() {
		// Initializes grid and spaces
		this.setBackground(Color.decode("#272522"));

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
	                	if (isGameOver) return;
	                	Space clicked = gameState.getBoard().getSpace(piecePic.getRow(), piecePic.getCol());
	                	if (piecesSelected == 0) {
		                	if (clicked.getPiece() == null) return;
		                	if (((ChessPiece)clicked.getPiece()).isWhite() && !gameState.isWhiteTurn() || 
		                			!((ChessPiece)clicked.getPiece()).isWhite() && gameState.isWhiteTurn()) return;
		                	piecesSelected++;
		                	System.out.println(piecesSelected);
	                		start = clicked;
	                		setSelectColor(start);
	                	} else {
		                	piecesSelected++;
		                	System.out.println(piecesSelected);
	                		end = gameState.getBoard().getSpace(piecePic.getRow(), piecePic.getCol());
	                		if (!gameState.attemptMove(start, end)) {
	                			System.out.println("Invalid Move");
								try {
									audio.restart(); // plays sound
								} catch (IOException e2) {
									e2.printStackTrace();
								} catch (LineUnavailableException e2) {
									e2.printStackTrace();
								} catch (UnsupportedAudioFileException e2) {
									e2.printStackTrace();
								}
	                		} else {
	                			if (gameState.isGameOver()) {
	                				isGameOver = true;
	                				sidePanel.setIsGameOver(true);
	                				System.out.println("Game Over");
	                				try {
										sidePanel.endGame(gameState.getState());
									} catch (IOException e1) {
										e1.printStackTrace();
									}
	                			}

	                			resetBackground(gameState.getKingSpace());
	                			gameState.nextTurn();
	                			if (gameState.kingInCheck()) {
	                				System.out.println("In Check");
	                				setCheckColor(gameState.getKingSpace());
	                			}
	                		}
	                		resetBackground(start);
	                		resetBackground(end);
                			start = null;
                			end = null;
	                		piecesSelected = 0;
	                		try {
								sync();
								sidePanel.sync();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
	                	}
	                }
				});

				grid[r][c].add(piecePic, BorderLayout.CENTER);
				this.add(grid[r][c]);
			}
		}
	}
	
}
