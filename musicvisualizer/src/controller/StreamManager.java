package controller;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import Model.AudioPlayer;
import Model.SoundDetails;

/**
 * Class creates Input Stream from .WAV file and opens up a Source Data line. This data line is then used to create an audioPlayer Object
 * @author tobi
 *	.
 */
public class StreamManager {

	private SoundDetails soundDetails;
	private String audioFile;

	/**
	 * Constructor of StreamManager Class.
	 * @param soundDetails: is passed through so the thread is known in main class.
	 * @param audioFile: Filename of audio to played as String.
	 * 
	 */
	public StreamManager(SoundDetails soundDetails, String audioFile) {
		this.soundDetails = soundDetails;
		this.audioFile = audioFile;

	}

	/**
	 * opens the Stream and the data line and creates the audioPlayer thread.
	 */
	public void openStreamsAndStartAudioPlayer() {
		AudioInputStream audioInputStream = null;
		SourceDataLine sourceDataLine = null;
		try {
			audioInputStream = createAudioInputStream();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			sourceDataLine = createSourceDataLine(audioInputStream);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			sourceDataLine.open(audioInputStream.getFormat());
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sourceDataLine.start();
		Thread audioPlayer = new Thread(new AudioPlayer(sourceDataLine, audioInputStream, soundDetails));
		audioPlayer.start();

	}

	/**
	 * Creates the Audio Input Stream
	 * @return the audioInputStream
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 */
	private AudioInputStream createAudioInputStream() throws UnsupportedAudioFileException, IOException {
		AudioInputStream audioInputStream = AudioSystem
				.getAudioInputStream(this.getClass().getResourceAsStream(audioFile));
		return audioInputStream;
	}

	/**
	 * Creates the Source Data Line
	 * @param audioInputStream
	 * @return the sourceDataLine
	 * @throws LineUnavailableException
	 */
	private SourceDataLine createSourceDataLine(AudioInputStream audioInputStream) throws LineUnavailableException {
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioInputStream.getFormat());
		SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(info);
		return sourceDataLine;
	}
}
