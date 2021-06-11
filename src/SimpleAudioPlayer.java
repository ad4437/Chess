/*
 * Code Taken From GeeksForGeeks
 * https://www.geeksforgeeks.org/play-audio-file-using-java/
 */

// Java program to play an Audio
// file using Clip Object
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SimpleAudioPlayer
{

	// to store current position
	Long currentFrame;
	Clip clip;
	
	// current status of clip
	String status;
	
	AudioInputStream audioInputStream;
	static String filePath;

	// constructor to initialize streams and clip
	public SimpleAudioPlayer(String path)
		throws UnsupportedAudioFileException,
		IOException, LineUnavailableException
	{
		filePath = path;
		// create AudioInputStream object
		audioInputStream =
				AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
		
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
		
		status = "play";
	}
	
	// Method to restart the audio
	public void restart() throws IOException, LineUnavailableException,
											UnsupportedAudioFileException
	{
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
