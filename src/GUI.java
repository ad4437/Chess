import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GUI  {
	/*
	 * Colors:
	 * white - #ebecd0
	 * green - #779556
	 * yellow (selected) - #f6f668
	 * red (invalid) - #b33430
	 * hardBlack - #22201d 
	 * softBlack - #272522
	 * 
	 */
	
	Game gameState;
    JFrame frm;
    JPanel[][] grid = new JPanel[8][8];
    PieceLabel[][] pieces = new PieceLabel[8][8];
    
    Space start;
    Space end;
    int piecesSelected = 0;
    SimpleAudioPlayer audio = new SimpleAudioPlayer();
	
	public GUI () {
		gameState = new Game();
	}
	
	public GUI (Game gameStateIn) {
		gameState = gameStateIn;
	}
	
	private void initSound(String path) {
		// Initializes audio player object with desired sound path
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
	
	private void select(Space space) {
		// Highlights selected space
		int r = space.getRow();
		int c = space.getCol();
		
		grid[r][c].setBackground(Color.decode("#f6f668"));

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
		if (isWhite) color = "white";
		else color = "black";
		
		String path = "assets/images/pieces/" + color + "/" + name + ".png";
		
		return ImageIO.read(new File(path));
	}
	
	private BufferedImage getIcon(String name) throws IOException {
		String path = "assets/images/icons/" + name + ".png";
		return ImageIO.read(new File(path));
	}
	
	private void sync() throws IOException {
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
	
	private void setSoundPath(String name) {
		try {
			if (name == null) audio.setPath(name);
			else audio.setPath("assets/sounds/" + name + ".wav");
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void settingsPopup() {
        JFrame popup = new JFrame();
        popup.setTitle("Settings");
        popup.setVisible(true);
        popup.setResizable(false);
        
        Container pane = popup.getContentPane();
        pane.setPreferredSize(new Dimension(250,300));
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBackground(Color.decode("#272522"));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel soundLabel = new JLabel("<html><u>Invalid Move Soud Effect</u><html>");
        soundLabel.setForeground(Color.WHITE);
        
        // Sound Radio Buttons
        JRadioButton hiLo = new JRadioButton("High Low Note");
        hiLo.setActionCommand("hilo");
        hiLo.setSelected(true);
        hiLo.setOpaque(false);
        hiLo.setForeground(Color.WHITE);

        JRadioButton quack = new JRadioButton("Quack");
        quack.setActionCommand("quack");
        quack.setOpaque(false);
        quack.setForeground(Color.WHITE);

        JRadioButton wrong = new JRadioButton("Wrong");
        wrong.setActionCommand("wrong");
        wrong.setOpaque(false);
        wrong.setForeground(Color.WHITE);

        JRadioButton none = new JRadioButton("No Sound");
        none.setActionCommand("none");
        none.setOpaque(false);
        none.setForeground(Color.WHITE);

        ButtonGroup group = new ButtonGroup();
        group.add(hiLo);
        group.add(quack);
        group.add(wrong);
        group.add(none);
        
        hiLo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setSoundPath(e.getActionCommand());
            }
        });
        
        quack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setSoundPath(e.getActionCommand());
            }
        });
        
        wrong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setSoundPath(e.getActionCommand());
            }
        });
        
        none.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setSoundPath(null);
            }
        });
        
        JButton done = new JButton("Done");

        panel.add(soundLabel);
        panel.add(hiLo);
        panel.add(quack);
        panel.add(wrong);
        panel.add(none);
        panel.add(done);


        popup.add(panel);
        popup.pack();

	}
	
	private JPanel renderSettings(JPanel panel) throws IOException {
		panel.setBackground(Color.decode("#272522"));
		JLabel settingsTitle = new JLabel("<HTML><u>Settings</u></HTML>", SwingConstants.CENTER);
		settingsTitle.setForeground(Color.WHITE);
		settingsTitle.setFont(settingsTitle.getFont().deriveFont(26.0f));

		JLabel settingsIcon = new JLabel();
		settingsIcon.setIcon(new ImageIcon(getIcon("settings")));
		settingsIcon.setBorder(new EmptyBorder(10, 10, 10, 10));
		settingsIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	settingsPopup();
            }
		});
				
		panel.add(settingsIcon, BorderLayout.PAGE_END);
		panel.add(settingsTitle, BorderLayout.PAGE_START);
		
		// , BorderLayout.CENTER

		return panel;
	}
	
	private JPanel renderGrid(JPanel panel) {
		// Initializes grid and spaces
		panel.setBackground(Color.decode("#272522"));

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
									audio.restart(); // plays sound
								} catch (IOException e2) {
									e2.printStackTrace();
								} catch (LineUnavailableException e2) {
									e2.printStackTrace();
								} catch (UnsupportedAudioFileException e2) {
									e2.printStackTrace();
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
				panel.add(grid[r][c]);
			}
		}
		return panel;
	}
	
	private void addComponents(Container pane) throws IOException {
		// Adds Grid and Settings Panels to Window
		pane.setPreferredSize(new Dimension(800,600));
		pane.setLayout(new BoxLayout(pane, BoxLayout.LINE_AXIS));
		
		JPanel boardPanel = new JPanel();
		boardPanel.setPreferredSize(new Dimension(600,600));
		boardPanel.setLayout(new GridLayout(8,8));
		renderGrid(boardPanel);

		JPanel settingsPanel = new JPanel();
		settingsPanel.setPreferredSize(new Dimension(200,600));
		settingsPanel.setLayout(new BorderLayout());
		renderSettings(settingsPanel);
		
		pane.add(boardPanel);
		pane.add(settingsPanel);
	}
	
	public void draw() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        frm = new JFrame();
        setSoundPath("hilo"); // default sound
        addComponents(frm.getContentPane());
        sync();
        frm.setTitle("Chess");
        frm.pack();
        frm.setVisible(true);
        frm.setResizable(false);
        frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
}
