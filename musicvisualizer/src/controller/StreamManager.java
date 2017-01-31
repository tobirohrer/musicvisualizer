package controller;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

import Model.AudioPlayer;
import Model.SoundDetails;

public class StreamManager {
	
	private SoundDetails soundDetails;
	
	public StreamManager(SoundDetails soundDetails){
		this.soundDetails = soundDetails;
	}
	
	public void playClip(String audioFile) {
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(this.getClass().getResourceAsStream(audioFile));
			
			AudioFormat originalFormat = audioInputStream.getFormat();
			
			//Todo: Umwaldung von input in decoded Stream, falls inputformat nicht "das Richtige" ist.
			
			AudioInputStream decodedStream = audioInputStream;
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioInputStream.getFormat());
			
			SourceDataLine sourceDataLine = (SourceDataLine)AudioSystem.getLine(info);
			
			sourceDataLine.open(originalFormat);
			sourceDataLine.start();
				
			Thread audioPlayer = new Thread(new AudioPlayer(sourceDataLine, decodedStream, soundDetails));
			
			audioPlayer.start();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
}
