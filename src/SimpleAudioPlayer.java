/*
 * Code Modeled After GeeksForGeeks Code
 * https://www.geeksforgeeks.org/play-audio-file-using-java/
 */

// Java program to play an Audio file using Clip Object
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SimpleAudioPlayer
{

	Long currentFrame;
	Clip clip;
	boolean isOn = false;
	
	AudioInputStream audioInputStream;
	static String filePath;

	public SimpleAudioPlayer() {}
	
	public SimpleAudioPlayer(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException
	{
		setPath(path);
	}
	
	public void setPath(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		if (path == null) {
			filePath = null;
			return;
		}
		filePath = path;
		// create AudioInputStream object
		audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
		
		// create clip reference
		clip = AudioSystem.getClip();
		
		// open audioInputStream to the clip
		clip.open(audioInputStream);
		
	}
	
	// Method to play the audio
	public void play()
	{
		//start the clip
		clip.start();
	}
	
	// Method to restart the audio
	public void restart() throws IOException, LineUnavailableException,
											UnsupportedAudioFileException
	{
		if (filePath == null) return;
		clip.stop();
		clip.close();
		resetAudioStream();
		currentFrame = 0L;
		clip.setMicrosecondPosition(0);
		this.play();
	}
	
    // Method to reset audio stream
    public void resetAudioStream() throws UnsupportedAudioFileException, IOException,
                                            LineUnavailableException 
    {
        audioInputStream = AudioSystem.getAudioInputStream(
        new File(filePath).getAbsoluteFile());
        clip.open(audioInputStream);
    }
}
