package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import pieces.Space;

public class SidePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Game gameState;
	private GridPanel gridPanel;
	private boolean isGameOver = false;
	private JPanel whiteCaptured;
	private JPanel blackCaptured;
	private JPanel sideDisplay;
	private String currentSound = "hilo";
	private SimpleAudioPlayer audio;
	
	public void setGameState(Game game) {
        gameState = game;
	}
	
	public void setGridPanel(GridPanel grid) {
		gridPanel = grid;
	}
	
	public void setAudio(SimpleAudioPlayer audioIn) {
		audio = audioIn;
        setSoundPath(currentSound); // default sound
	}	
	
	public void setIsGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}
	
	private URL getImage(String name, boolean isWhite, boolean small) throws IOException {
		// returns Piece Image
		String color;
		if (isWhite) color = "white";
		else color = "black";		
		return getClass().getClassLoader().getResource(color + "/" + name + ((small) ? "-sm" : "") + ".png");
	}
	
	private URL getIcon(String name) throws IOException {
		return getClass().getClassLoader().getResource("icons/" + name + ".png");
	}
	
	private void setSoundPath(String name) {
		try {
			if (name.equals("none")) audio.setPath(name);
			else audio.setPath("sounds/" + name + ".wav");
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	public void sync() throws IOException {
		// sync the side panel with the current board state
		whiteCaptured.removeAll();
		blackCaptured.removeAll();

		// white captured
		for (String piece : gameState.getBoard().getWhiteCaptured()) {
			JLabel label = new JLabel();
			ImageIcon img = new ImageIcon(getImage(piece, true, true));
			label.setIcon(img);
			whiteCaptured.add(label);
		}
		
		// black captured
		for (String piece : gameState.getBoard().getBlackCaptured()) {
			JLabel label = new JLabel();
			ImageIcon img = new ImageIcon(getImage(piece, false, true));
			label.setIcon(img);
			blackCaptured.add(label);
		}
	}
	
	private void settingsPopup() {
        JFrame popup = new JFrame();
        popup.setTitle("Settings");
        popup.setVisible(true);
        popup.setResizable(false);
        
    	JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
    	
        popup.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            	topFrame.setEnabled(true);
            }
        });
        
        Container pane = popup.getContentPane();
        pane.setPreferredSize(new Dimension(250,300));
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBackground(Color.decode("#272522"));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel soundLabel = new JLabel("<html><u>Invalid Move Soud Effect</u><html>");
        soundLabel.setForeground(Color.WHITE);
        soundLabel.setFont(new Font("Montserrat", Font.PLAIN, 14));
        
        // Sound Radio Buttons
        JRadioButton hiLo = new JRadioButton("High Low Note");
        hiLo.setActionCommand("hilo");
        hiLo.setSelected(currentSound.equals("hilo"));
        hiLo.setOpaque(false);
        hiLo.setForeground(Color.WHITE);
        hiLo.setFont(new Font("Montserrat", Font.PLAIN, 12));
        hiLo.setFocusPainted(false);

        JRadioButton quack = new JRadioButton("Quack");
        quack.setActionCommand("quack");
        quack.setSelected(currentSound.equals("quack"));
        quack.setOpaque(false);
        quack.setForeground(Color.WHITE);
        quack.setFont(new Font("Montserrat", Font.PLAIN, 12));
        quack.setFocusPainted(false);
        
        JRadioButton bruh = new JRadioButton("Bruh");
        bruh.setActionCommand("bruh");
        bruh.setSelected(currentSound.equals("bruh"));
        bruh.setOpaque(false);
        bruh.setForeground(Color.WHITE);
        bruh.setFont(new Font("Montserrat", Font.PLAIN, 12));
        bruh.setFocusPainted(false);

        JRadioButton wrong = new JRadioButton("Wrong");
        wrong.setActionCommand("wrong");
        wrong.setSelected(currentSound.equals("wrong"));
        wrong.setOpaque(false);
        wrong.setForeground(Color.WHITE);
        wrong.setFont(new Font("Montserrat", Font.PLAIN, 12));
        wrong.setFocusPainted(false);

        JRadioButton none = new JRadioButton("No Sound");
        none.setActionCommand("none");
        none.setSelected(currentSound.equals("none"));
        none.setOpaque(false);
        none.setForeground(Color.WHITE);
        none.setFont(new Font("Montserrat", Font.PLAIN, 12));
        none.setFocusPainted(false);

        ButtonGroup group = new ButtonGroup();
        group.add(hiLo);
        group.add(quack);
        group.add(bruh);
        group.add(wrong);
        group.add(none);
        
        JButton done = new JButton("Save");
        done.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	currentSound = group.getSelection().getActionCommand();
            	setSoundPath(currentSound);
            	popup.dispose();
            	topFrame.setEnabled(true);
            }
        });

        panel.add(soundLabel);
        panel.add(hiLo);
        panel.add(quack);
        panel.add(bruh);
        panel.add(wrong);
        panel.add(none);
        panel.add(done);

        popup.add(panel);
        popup.pack();

	}
	
	private void transform(Space end, int piece) {
		// pawn transformation
		gameState.transformPawn(end, piece);
		try {
			gridPanel.sync();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		gridPanel.setDisabled(false);
		sideDisplay.removeAll();
		sideDisplay.repaint();
	}
	
	public void newPieceInput(Space end) throws IOException {
		// get pawn transformation user input
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setOpaque(false);
		
		JLabel queen = new JLabel();
		queen.setIcon(new ImageIcon(getImage("queen", gameState.isWhiteTurn(), false)));
		queen.setAlignmentX(Component.CENTER_ALIGNMENT);
		queen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	transform(end, 1);
            }
		});	
		
		JLabel rook = new JLabel();
		rook.setIcon(new ImageIcon(getImage("rook", gameState.isWhiteTurn(), false)));
		rook.setAlignmentX(Component.CENTER_ALIGNMENT);
		rook.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	transform(end, 2);
            }
		});	
		
		JLabel bishop = new JLabel();
		bishop.setIcon(new ImageIcon(getImage("bishop", gameState.isWhiteTurn(), false)));
		bishop.setAlignmentX(Component.CENTER_ALIGNMENT);
		bishop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	transform(end, 3);
            }
		});	
		
		JLabel knight = new JLabel();
		knight.setIcon(new ImageIcon(getImage("knight", gameState.isWhiteTurn(), false)));
		knight.setAlignmentX(Component.CENTER_ALIGNMENT);
		knight.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	transform(end, 4);
            }
		});	
		
		panel.add( Box.createVerticalGlue() );
		panel.add(queen);
		panel.add(rook);
		panel.add(bishop);
		panel.add(knight);
		panel.add( Box.createVerticalGlue() );

		sideDisplay.add(panel);
	}
	
	public void endGame(String outcome) throws IOException {
		// Display End Game Text
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setOpaque(false);
		
		JLabel winner = new JLabel(outcome, SwingConstants.CENTER);
		winner.setForeground(Color.WHITE);
		winner.setFont(new Font("Montserrat", Font.PLAIN, 32));
		winner.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel newGame = new JLabel("New Game", SwingConstants.CENTER);
		newGame.setIcon(new ImageIcon(getIcon("plus")));
		newGame.setForeground(Color.WHITE);
		newGame.setFont(new Font("Montserrat", Font.PLAIN, 16));
		newGame.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		newGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	gameState.reset();
            	gridPanel.setIsGameOver(false);
            	isGameOver = false;
        		whiteCaptured.removeAll();
        		blackCaptured.removeAll();
            	sideDisplay.removeAll();
            	sideDisplay.repaint();
            	try {
					gridPanel.sync();
				} catch (IOException e1) {					
					e1.printStackTrace();
				}
            }
		});	

		panel.add( Box.createVerticalGlue() );
		panel.add(winner);
		panel.add(newGame);
		panel.add( Box.createVerticalGlue() );

		sideDisplay.add(panel, BorderLayout.CENTER);
		System.out.println(outcome);
	}
	
	public void draw(JPanel panel) throws IOException {
		this.setOpaque(false);
		
		whiteCaptured = new JPanel(new WrapLayout(FlowLayout.LEFT));
		whiteCaptured.setOpaque(false);
		
		blackCaptured = new JPanel(new WrapLayout(FlowLayout.LEFT));
		blackCaptured.setOpaque(false);

		sideDisplay = new JPanel(new BorderLayout());
		sideDisplay.setOpaque(false);
		
		this.add(blackCaptured, BorderLayout.PAGE_END);
		this.add(whiteCaptured, BorderLayout.PAGE_START);
		this.add(sideDisplay, BorderLayout.CENTER);
		
		JLabel settingsIcon = new JLabel();
		settingsIcon.setIcon(new ImageIcon(getIcon("settings")));
		settingsIcon.setBorder(new EmptyBorder(10, 10, 10, 0));
		settingsIcon.setOpaque(false);
		settingsIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	if (isGameOver) return;
            	JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(settingsIcon);
            	topFrame.setEnabled(false);
            	settingsPopup();
            }
		});	
				
		JLabel surrenderIcon = new JLabel();
		surrenderIcon.setIcon(new ImageIcon(getIcon("flag")));
		surrenderIcon.setBorder(new EmptyBorder(10, 0, 10, 10));
		surrenderIcon.setOpaque(false);
		surrenderIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	// TODO: surrender doesn't update GUI
            	if (isGameOver) return;
            	gameState.setSurrendered(true);
            	isGameOver = gameState.isGameOver();
            	gridPanel.setIsGameOver(isGameOver);
				try {
					endGame(gameState.getState());
					revalidate();
					repaint();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
            }
		});	
		panel.add(settingsIcon);
		panel.add(surrenderIcon);
	}
	
}
