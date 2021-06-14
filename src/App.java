import game.*;
import pieces.*;

import java.awt.*;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class App  {
	/*
	 * Colors:
	 * white - #ebecd0
	 * green - #779556
	 * yellow (selected) - #f6f668
	 * red (invalid) - #b33430
	 * hardBlack - #22201d 
	 * softBlack - #413e3c
	 * 
	 */
	
	private Game gameState;
	
	private JFrame frm;
	private GridPanel boardPanel;
	private SidePanel sidePanel;
    
	private SimpleAudioPlayer audio = new SimpleAudioPlayer();
	
	public App () {
		gameState = new Game();
	}
	
	public App (Game gameStateIn) {
		gameState = gameStateIn;
	}
	
	private void addComponents(Container pane) throws IOException {
		// Adds Grid and Settings Panels to Window
		pane.setPreferredSize(new Dimension(800,600));
		pane.setLayout(new BoxLayout(pane, BoxLayout.LINE_AXIS));
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setBackground(Color.decode("#413e3c"));
		
		JPanel settingsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		settingsPanel.setBackground(Color.decode("#413e3c"));
				
		sidePanel = new SidePanel();
		sidePanel.setAudio(audio);
		sidePanel.setBoardState(gameState.getBoard());
		sidePanel.setPreferredSize(new Dimension(200,600));
		sidePanel.setLayout(new BorderLayout());
		sidePanel.draw(settingsPanel);

		boardPanel = new GridPanel();
		boardPanel.setGame(gameState);
		boardPanel.setAudio(audio);
		boardPanel.setSidePanel(sidePanel);
		boardPanel.setPreferredSize(new Dimension(600,600));
		boardPanel.setLayout(new GridLayout(8,8));
		boardPanel.draw();

		panel.add(sidePanel);
		panel.add(settingsPanel);
		pane.add(boardPanel);
		pane.add(panel);
	}
	
	public void run() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        frm = new JFrame();
        addComponents(frm.getContentPane());
        boardPanel.sync();
        frm.setTitle("Chess");
        frm.pack();
        frm.setVisible(true);
        frm.setResizable(false);
        frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
}
