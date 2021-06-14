package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
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

import com.sun.glass.events.WindowEvent;

import pieces.ChessPiece;
import pieces.Piece;

public class SidePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Board board;	
	private JPanel whiteCaptured;
	private JPanel blackCaptured;
	private String currentSound = "hilo";
	private SimpleAudioPlayer audio;
	
	public void setAudio(SimpleAudioPlayer audioIn) {
		audio = audioIn;
        setSoundPath(currentSound); // default sound
	}	
	
	public void setBoardState(Board brd) {
		board = brd;
	}
	
	private BufferedImage getSmImage(String name, boolean isWhite) throws IOException {
		// returns small Piece Image
		String color;
		if (isWhite) color = "white";
		else color = "black";
		
		String path = "assets/images/pieces/" + color + "/" + name + "-sm.png";
		
		return ImageIO.read(new File(path));
	}
	
	private BufferedImage getIcon(String name) throws IOException {
		String path = "assets/images/icons/" + name + ".png";
		return ImageIO.read(new File(path));
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
	
	public void sync() throws IOException {
		// sync the side panel with the current board state
		whiteCaptured.removeAll();
		blackCaptured.removeAll();
		System.out.println(board.getWhiteCaptured().toString());
		System.out.println(board.getBlackCaptured().toString());

		// white captured
		for (String piece : board.getWhiteCaptured()) {
			JLabel label = new JLabel();
			ImageIcon img = new ImageIcon(getSmImage(piece, true));
			label.setIcon(img);
			whiteCaptured.add(label);
		}
		
		// black captured
		for (String piece : board.getBlackCaptured()) {
			JLabel label = new JLabel();
			ImageIcon img = new ImageIcon(getSmImage(piece, false));
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
        
        // Sound Radio Buttons
        JRadioButton hiLo = new JRadioButton("High Low Note");
        hiLo.setActionCommand("hilo");
        hiLo.setSelected(currentSound.equals("hilo"));
        hiLo.setOpaque(false);
        hiLo.setForeground(Color.WHITE);

        JRadioButton quack = new JRadioButton("Quack");
        quack.setActionCommand("quack");
        quack.setSelected(currentSound.equals("quack"));
        quack.setOpaque(false);
        quack.setForeground(Color.WHITE);
        
        JRadioButton bruh = new JRadioButton("Bruh");
        bruh.setActionCommand("bruh");
        bruh.setSelected(currentSound.equals("bruh"));
        bruh.setOpaque(false);
        bruh.setForeground(Color.WHITE);

        JRadioButton wrong = new JRadioButton("Wrong");
        wrong.setActionCommand("wrong");
        wrong.setSelected(currentSound.equals("wrong"));
        wrong.setOpaque(false);
        wrong.setForeground(Color.WHITE);

        JRadioButton none = new JRadioButton("No Sound");
        none.setActionCommand("none");
        none.setSelected(currentSound.equals("none"));
        none.setOpaque(false);
        none.setForeground(Color.WHITE);

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
	
	public void draw(JPanel panel) throws IOException {
		this.setOpaque(false);
		
		whiteCaptured = new JPanel(new WrapLayout(FlowLayout.LEFT));
		whiteCaptured.setOpaque(false);
		
		blackCaptured = new JPanel(new WrapLayout(FlowLayout.LEFT));
		blackCaptured.setOpaque(false);

		JLabel settingsIcon = new JLabel();
		settingsIcon.setIcon(new ImageIcon(getIcon("settings")));
		settingsIcon.setBorder(new EmptyBorder(10, 10, 10, 10));
		settingsIcon.setOpaque(false);
		settingsIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(settingsIcon);
            	topFrame.setEnabled(false);
            	settingsPopup();
            }
		});	

		panel.add(settingsIcon);
		this.add(blackCaptured, BorderLayout.PAGE_END);
		this.add(whiteCaptured, BorderLayout.PAGE_START);		
	}
	
	
}
