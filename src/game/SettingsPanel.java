package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
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
import javax.swing.border.EmptyBorder;

public class SettingsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private SimpleAudioPlayer audio;
	
	public void setAudio(SimpleAudioPlayer audioIn) {
		audio = audioIn;
        setSoundPath("hilo"); // default sound
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
	
	public void draw() throws IOException {
		this.setBackground(Color.decode("#272522"));
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
				
		this.add(settingsIcon, BorderLayout.PAGE_END);
		this.add(settingsTitle, BorderLayout.PAGE_START);
		
		// , BorderLayout.CENTER
	}
	
	
}
